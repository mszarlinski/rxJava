package pl.mszarlinski.rxjava.producer.impl;

import pl.mszarlinski.rxjava.Stat;
import rx.exceptions.Exceptions;

import static rx.exceptions.Exceptions.*;

/**
 * Created by mszarlinski@cyfrowypolsat.pl on 2015-01-28.
 */
public class UsedMemoryProducer extends SigarStatsProducer {

    private static final String NAME = "used-memory";

    public Stat produceStat() {

        try {
            return Stat.of(NAME, sigar.getMem().getActualUsed());
        } catch (Exception ex) {
            throw propagate(ex);
        }
    }
}
