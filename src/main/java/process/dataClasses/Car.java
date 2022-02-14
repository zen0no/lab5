package process.dataClasses;

import java.util.Locale;
import java.util.Objects;

public class Car {
    private final boolean cool;

    public Car(boolean cool){
        this.cool = cool;
    }

    public static Car parseCar(String s) throws IllegalArgumentException{
        if ("true".equals(s.toLowerCase(Locale.ROOT))) return new Car(true);
        else if ("false".equals(s.toLowerCase(Locale.ROOT))) return new Car(false);
        else throw new IllegalArgumentException("incorrect format of string");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return cool == car.cool;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cool);
    }
}
