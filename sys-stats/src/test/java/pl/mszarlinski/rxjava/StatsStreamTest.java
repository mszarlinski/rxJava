package pl.mszarlinski.rxjava;

import org.junit.Test;
import pl.mszarlinski.rxjava.StatsStream;
import rx.Observable;
import rx.Subscription;

import static rx.Observable.create;

/**
 * Created by Maciej on 2015-01-21.
 */
public class StatsStreamTest {

    @Test
    public void printOutTest() throws InterruptedException {
        final Subscription subscription = Observable.create(StatsStream.finiteWithPeriod(10, 10))
                .map(stat -> stat.getValue())
                .filter(val -> val > 0)
                .subscribe(System.out::println);

        Thread.sleep(2000);

        subscription.unsubscribe();

    }
}
