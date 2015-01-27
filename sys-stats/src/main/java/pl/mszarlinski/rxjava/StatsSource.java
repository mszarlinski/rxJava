package pl.mszarlinski.rxjava;

import java.util.Random;

/**
 * Created by Maciej on 2015-01-19.
 */
public class StatsSource {

    final private static Random r = new Random();

    public IoStat getStats() {
        return IoStat.of(Thread.currentThread().getName(), r.nextDouble());
    }
}
