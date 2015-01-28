package pl.mszarlinski.rxjava;

/**
 * Created by Maciej on 2015-01-19.
 */
public class Stat {
    private final String name;
    private final double value;

    private Stat(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public static Stat of(String name, double value) {
        return new Stat(name, value);
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Stat{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
