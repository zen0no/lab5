package process.dataClasses;

import process.exceptions.IllegalModelFieldException;

import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Car implements Comparable<Car>{

    private static final Set<String> fields = Set.of("cool");
    private boolean cool;

    public Car(boolean cool){
        this.cool = cool;
    }
    public Car() {}

    public static Car parseCar(Map<String, String> args) throws IllegalArgumentException{
        try {
            for (Map.Entry<String, String> f: args.entrySet()){
                if (!getFields().contains(f.getKey())) throw new IllegalModelFieldException("No such field: Car." + f.getKey());
            }
            if (args.containsKey("cool")) {
                String s = args.get("cool");
                if ("true".equals(s.toLowerCase(Locale.ROOT))) return new Car(true);
                else if ("false".equals(s.toLowerCase(Locale.ROOT))) return new Car(false);
                else throw new IllegalArgumentException("invalid value for Car.cool: \"" + s + "\"");
            }

            return new Car();
        }
        catch (NullPointerException e){
            return null;
        }
    }

    public static Car parseCar(String s) throws IllegalArgumentException{
        if (s == null) return new Car();
        if ("true".equals(s.toLowerCase(Locale.ROOT))) return new Car(true);
        else if ("false".equals(s.toLowerCase(Locale.ROOT))) return new Car(false);
        else throw new IllegalArgumentException("incorrect format of string");
    }

    public static Set<String> getFields() {
        return fields;
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

    public void setCool(Boolean cool){
        this.cool = cool;
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
