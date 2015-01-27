package pl.mszarlinski.rxjava;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.Swap;
import org.junit.Test;

/**
 * Created by Maciej on 2015-01-21.
 */
public class SigarTest {

    @Test
    public void useSigarTest() throws Exception {
        Sigar sigar = new Sigar();
        final Mem mem = sigar.getMem(); // Memory
        CpuPerc cpuPerc = sigar.getCpuPerc(); // Percentage CPU
        Swap swap = sigar.getSwap(); // Swap Space
        System.out.println("#CPUs: " + sigar.getCpuList().length);
        System.out.println("Free memory: " + mem.toString());
        System.out.println("CPU: " + cpuPerc.toString());
        System.out.println("Swap: " + swap.toString());
    }
}
