package pl.mszarlinski.rxjava;

import java.util.List;
import pl.mszarlinski.rxjava.producer.IStatsProducer;
import pl.mszarlinski.rxjava.producer.impl.UsedMemoryProducer;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

import static java.util.Arrays.*;

/**
 * Created by Maciej on 2015-01-19.
 */
public class StatsStream implements Observable.OnSubscribe<Stat> {

    private static final long INITIAL_DELAY_ONE_SEC = 1000L;

    private static final TimeUnit TIME_UNIT_MILLIS = TimeUnit.MILLISECONDS;

    private final List<IStatsProducer> statsProducerList = asList(
            new UsedMemoryProducer()
    );

    private long period = 100L;

    private boolean isInfinite = true;

    private long numberOfLoops = 0L;

    private Scheduler scheduler = Schedulers.computation();

    public static StatsStream withPeriod(long period) {
        StatsStream ss = new StatsStream();
        ss.period = period;
        return ss;
    }

    public static StatsStream finiteWithPeriod(long numberOfElements, long period) {
        StatsStream ss = new StatsStream();
        ss.numberOfLoops = numberOfElements;
        ss.period = period;
        ss.isInfinite = false;
        return ss;
    }

    /**
     * The most important method - push statistics to subscriber.
     *
     * @param subscriber
     */
    public void call(final Subscriber<? super Stat> subscriber) {
        final Scheduler.Worker worker = scheduler.createWorker();
        subscriber.add(worker);
        worker.schedulePeriodically(() -> {
            if (!isInfinite && numberOfLoops <= 0) {
                worker.unsubscribe();
                subscriber.onCompleted();
            }

            try {
                statsProducerList.forEach(p ->
                    subscriber.onNext(p.produceStat())
                );

                if (!isInfinite) {
                    numberOfLoops--;
                }
            } catch (Throwable t) {
                try {
                    subscriber.onError(t);
                } finally {
                    worker.unsubscribe();
                }
            }
        }, INITIAL_DELAY_ONE_SEC, period, TIME_UNIT_MILLIS);
    }
}
