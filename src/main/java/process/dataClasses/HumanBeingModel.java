package process.dataClasses;

import process.exceptions.IllegalModelFieldException;
import process.exceptions.EmptyStringModelFieldException;
import process.exceptions.ModelFieldException;
import process.exceptions.NullFieldException;
import process.model.Model;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

public class HumanBeingModel implements Model {
    private static int primaryKeyCounter = 0;

    private int id;
    private String name;
    private Coordinates coordinates;
    private java.util.Date creationDate;
    private boolean realHero;
    private boolean hasToothpick;
    private int impactSpeed;
    private WeaponType weaponType;
    private Mood mood;
    private Car car;


    private HumanBeingModel(int id, String name, Coordinates coordinates, Date creationDate, boolean hasToothpick, int impactSpeed, WeaponType weaponType, Mood mood, Car car)
    {
        setId(id);
        setName(name);
        setCoordinates(coordinates);
        setCreationDate(creationDate);
        setHasToothpick(hasToothpick);
        setImpactSpeed(impactSpeed);
        setWeaponType(weaponType);
        setMood(mood);
        setCar(car);

    }

    private HumanBeingModel(Map<String, String> args) throws ModelFieldException {
        if (args.containsKey("id")) throw new IllegalModelFieldException("HumanBeingModel.id generates automatically.");
        this.id = primaryKeyCounter;
        primaryKeyCounter++;

        if (args.containsKey("creationDate")) throw new IllegalModelFieldException("HumanBeingModel.creationDate generates automatically");
        this.creationDate = new Date();

        if (args.get("name") == null) throw new NullFieldException("HumanBeingModel.name is not nullable");
        if (args.get("name").equals("")) throw new EmptyStringModelFieldException("HumanBeingModel.name can't be empty");
        setName(args.get("name"));


        if (args.get("mood") == null) throw new NullFieldException("HumanBeingModel.mood is not nullable");
        setMood(Mood.parseMood(args.get("mood")));


        if (args.get("car") == null) throw new NullFieldException("HumanBeingModel.car is not nullable");
        setCar(Car.parseCar(args.get("car")));


        if (args.get("coordinates") == null) throw new NullFieldException("HumanBeingModel.coordinates is not nullable");
        setCoordinates(Coordinates.parseCoordinates(args.get("coordinates")));


        if (args.get("realHero") == null) throw new NullFieldException("HumanBeingModel.realHero is not nullable");
        setRealHero(Boolean.parseBoolean(args.get("realHero")));


        setRealHero(Boolean.parseBoolean(args.get("hasToothpick")));


        if (args.get("impactSpeed") == null) throw new NullFieldException("HumanBeingModel.impactSpeed is not nullable");
        setImpactSpeed(Integer.parseInt(args.get("impactSpeed")));

        if (args.get("weaponType") == null) throw new NullFieldException("HumanBeingModel.weaponType is not nullable");
        setWeaponType(WeaponType.parseWeaponType(args.get("weaponType")));

    }

    public int getPrimaryKey() {
        return id;
    }

    public int getId(){ return id;}

    public String getName() {
        return name;
    }

    public Car getCar() {
        return car;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public boolean isRealHero() {
        return realHero;
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

    public void setCar(Car car) {
        this.car = car;
    }

    public void setCar(String s) { this.car = Car.parseCar(s);}

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCoordinates(String coordinates) {this.coordinates = Coordinates.parseCoordinates(coordinates);}

    private void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setRealHero(boolean realHero) {
        this.realHero = realHero;
    }

    public void setHasToothpick(boolean hasToothpick) {
        this.hasToothpick = hasToothpick;
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setId(String id) { this.id = Integer.parseInt(id);}

    public void setImpactSpeed(int impactSpeed) {
        this.impactSpeed = impactSpeed;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HumanBeingModel that = (HumanBeingModel) o;
        return id == that.id && realHero == that.realHero && hasToothpick == that.hasToothpick && impactSpeed == that.impactSpeed && Objects.equals(name, that.name) && Objects.equals(coordinates, that.coordinates) && Objects.equals(creationDate, that.creationDate) && weaponType == that.weaponType && mood == that.mood && Objects.equals(car, that.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, realHero, hasToothpick, impactSpeed, weaponType, mood, car);
    }
}
