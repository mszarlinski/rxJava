package pl.mszarlinski.rxjava.view;

import org.hyperic.sigar.Sigar;

/**
 * Created by Maciej on 2015-01-24.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println(System.getProperty("java.library.path"));
        System.out.println(new Sigar().getCpu().toString());
    }
}
