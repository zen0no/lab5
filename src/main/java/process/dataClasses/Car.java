package process.dataClasses;

import java.util.Objects;

public class Car {
    private boolean cool;

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
