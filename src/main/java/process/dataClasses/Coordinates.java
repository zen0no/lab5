package process.dataClasses;

import process.exceptions.IllegalModelFieldException;

import java.util.Objects;

public class Coordinates implements Comparable<Coordinates>{
    private final long x; // max: 673
    private final   long y;

    public Coordinates(int x, int y) throws IllegalArgumentException
    {
        if (Math.abs(x) > 673) throw new IllegalArgumentException("Abs of x is more than maximum possible value");

        this.x = x;
        this.y = y;
    }

    public static Coordinates parseCoordinates(String s){
        try
        {
            String[] args = s.split(":");
            try {
                return new Coordinates(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            } catch (IndexOutOfBoundsException e) {
                throw new IllegalModelFieldException("can't parse coordinates from \"" + s + "\"");
            }
        }
        catch (NullPointerException e){
            return null;
        }
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
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
