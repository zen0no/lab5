package process.dataClasses;

import java.util.Date;

public class HumanBeing{
    private int id;
    private String name;
    private Coordinates coordinates;
    private java.util.Date creationDate;
    private boolean hasToothpick;
    private int impactSpeed;
    private WeaponType weaponType;
    private Mood mood;
    private Car car;


    public HumanBeing()
    {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Car getCar() {
        return car;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public int getImpactSpeed() {
        return impactSpeed;
    }

    public Mood getMood() {
        return mood;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public boolean isHasToothpick() {
        return hasToothpick;
    }
}
