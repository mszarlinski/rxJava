package pl.mszarlinski.rxjava;

/**
 * Created by Maciej on 2015-01-19.
 */
public class IoStat {
    private final String name;
    private final double value;

    private IoStat(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public static IoStat of(String name, double value) {
        return new IoStat(name, value);
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }
}
