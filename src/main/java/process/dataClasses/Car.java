package process.dataClasses;

import process.exceptions.NullFieldException;

import java.util.Locale;
import java.util.Objects;

public class Car implements Comparable<Car>{
    private final boolean cool;

    public Car(boolean cool){
        this.cool = cool;
    }

    public static Car parseCar(String s) throws IllegalArgumentException{
        try {
            if ("true".equals(s.toLowerCase(Locale.ROOT))) return new Car(true);
            else if ("false".equals(s.toLowerCase(Locale.ROOT))) return new Car(false);
            else throw new IllegalArgumentException("can't parse car from \"" + s + "\"");
        }
        catch (NullPointerException e){
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return cool == car.cool;
    }

    public boolean isCool() {
        return cool;
    }

    @Override
    public String toString() {
        return String.valueOf(cool);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cool);
    }

    @Override
    public int compareTo(Car o) {
        return Boolean.compare(isCool(), o.isCool());
    }
}
