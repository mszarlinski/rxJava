package pl.mszarlinski.rxjava.producer.impl;

import org.hyperic.sigar.Sigar;
import pl.mszarlinski.rxjava.producer.IStatsProducer;

/**
 * Created by mszarlinski on 2015-01-28.
 */
public abstract class SigarStatsProducer implements IStatsProducer {
    protected Sigar sigar = new Sigar();
}
