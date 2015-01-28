package pl.mszarlinski.rxjava.producer.impl;

import org.hyperic.sigar.Cpu;
import pl.mszarlinski.rxjava.Stat;

import static rx.exceptions.Exceptions.propagate;

/**
 * Created by mszarlinski on 2015-01-28.
 */
public class CpusLoadProducer extends SigarStatsProducer {

    private static final String NAME = "cpus-load";

    public Stat produceStat() {

        try {
            final Cpu cpu = sigar.getCpu();
            return Stat.of(NAME, cpu.getTotal() - cpu.getIdle());
        } catch (Exception ex) {
            throw propagate(ex);
        }
    }
}
