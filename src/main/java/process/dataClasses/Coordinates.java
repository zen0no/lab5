package process.dataClasses;

import process.exceptions.IllegalModelFieldException;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Coordinates implements Comparable<Coordinates>{

    private static final Set<String> fields = Set.of("x", "y");

    private final long x; // max: 673
    private final long y;

    public Coordinates(long x, long y) throws IllegalArgumentException
    {
        if (Math.abs(x) > 673) throw new IllegalArgumentException("Abs of x is more than maximum possible value");

        this.x = x;
        this.y = y;
    }

    public static Coordinates parseCoordinates(Map<String, String> args) {
        try {

            if (!fields.equals(args.keySet())) throw new IllegalModelFieldException("Can't parse coordinates");
            long x = Long.parseLong(args.get("x"));
            long y = Long.parseLong(args.get("y"));
            return new Coordinates(x, y);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static Coordinates parseCoordinates(String s){
        String[] args = s.split(";");
        try {
            return new Coordinates(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        }

        catch (IndexOutOfBoundsException e){
            throw new IllegalArgumentException("incorrect format of string");
        }
    }


    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public static Set<String> getFields() {
        return fields;
    }

    @Override
    public int compareTo(Coordinates o){
        if (getX() > o.getX()) return 1;
        if (getX() < o.getX()) return -1;
        if (getY() > o.getY()) return 1;
        if (getY() < o.getY()) return -1;
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x && y == that.y;
    }

    @Override
    public String toString() {
        return x + ":" + y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
