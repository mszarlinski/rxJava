package pl.mszarlinski.rxjava;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * Created by Maciej on 2015-01-19.
 */
public class StatsStream implements Observable.OnSubscribe<IoStat> {
    private static final long INITIAL_DELAY_ONE_SEC = 1000L;
    private static final TimeUnit TIME_UNIT_MILLIS = TimeUnit.MILLISECONDS;

    private final StatsSource statsSource = new StatsSource();

    private long period = 100L;

    private boolean isInfinite = true;
    private long numberOfElements = 0L;

    private Scheduler scheduler = Schedulers.computation();

    public static StatsStream withPeriod(long period) {
        StatsStream ss = new StatsStream();
        ss.period = period;
        return ss;
    }

    public static StatsStream finiteWithPeriod(long numberOfElements, long period) {
        StatsStream ss = new StatsStream();
        ss.numberOfElements = numberOfElements;
        ss.period = period;
        ss.isInfinite = false;
        return ss;
    }

    /**
     * The most important method - push statistics to subscriber.
     *
     * @param subscriber
     */
    public void call(final Subscriber<? super IoStat> subscriber) {
        final Scheduler.Worker worker = scheduler.createWorker();
        subscriber.add(worker);
        worker.schedulePeriodically(() -> {
            if (!isInfinite && numberOfElements <= 0) {
                worker.unsubscribe();
                subscriber.onCompleted();
            }

            try {
                subscriber.onNext(statsSource.getStats());
                if (!isInfinite) numberOfElements--;
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
