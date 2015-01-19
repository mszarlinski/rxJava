package pl.mszarlinski.rxjava;

import org.junit.Test;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by mszarlinski on 2015-01-19.
 */
public class SampleTest {

    @Test
    public void testObservable() {
        Observable<String> observable = Observable.from(new String[]{"ala", "ma", "kota"});
        observable.filter(s -> s.contains("a"))
                .map(s -> s.replace("kot", "ps"))
                .subscribe(new Action1<String>() {
                               @Override
                               public void call(String s) {
                                   System.out.println(s);
                               }
                           }
                );
    }
}
