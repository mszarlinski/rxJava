package pl.mszarlinski.rxjava.view;

import pl.mszarlinski.rxjava.StatsStream;
import rx.Observable;
import rx.Subscription;

/**
 * Created by Maciej on 2015-01-24.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        final Subscription subscription = Observable.create(StatsStream.withPeriod(1000))
                .subscribe(System.out::println);

        System.in.read();

        subscription.unsubscribe();
    }
}
