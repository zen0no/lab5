package process.dataClasses;

import process.exceptions.IllegalModelFieldException;
import process.exceptions.ModelFieldException;
import process.exceptions.NullFieldException;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

public class HumanBeing implements Comparable<HumanBeing>{
    private static int primaryKeyCounter = 0;


    private final Set<String> fields = Set.of("id",
            "creationDate",
            "name",
            "coordinates",
            "impactSpeed",
            "car",
            "realHero",
            "hasToothpick",
            "weaponType",
            "mood"
            );

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


    public HumanBeing(){
        setId(primaryKeyCounter);
        primaryKeyCounter++;
        setCreationDate(new Date());
    }

    public int getPrimaryKey() {
        return id;
    }

    public static void setPrimaryKeyCounter(int counter){
        primaryKeyCounter = counter;
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

    public Set<String> getFields() {
        return fields;
    }

    public void setCar(Car car) throws ModelFieldException{
        if (car == null) throw new NullFieldException("HumanBeingModel.car is not nullable");
        this.car = car;
    }

    public void setCoordinates(Coordinates coordinates) throws ModelFieldException{
        if (coordinates == null) throw new NullFieldException("HumanBeingModel.coordinates is not nullable");
        this.coordinates = coordinates;
    }

    private void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setRealHero(Boolean realHero) throws ModelFieldException{
        if (realHero == null) throw new NullFieldException("HumanBeingModel.realHero is not nullable");
        this.realHero = realHero;
    }

    public void setHasToothpick(boolean hasToothpick) {

        this.hasToothpick = hasToothpick;
    }

    private void setId(int id) {
        this.id = id;
    }

    public void setImpactSpeed(Integer impactSpeed) throws ModelFieldException{
        if (impactSpeed == null) throw new NullFieldException("HumanBeingModel.impactSpeed is not nullable");
        this.impactSpeed = impactSpeed;
    }

    public void setMood(Mood mood) throws ModelFieldException{
        if (mood == null) throw new NullFieldException("HumanBeingModel.mood is not nullable");
        this.mood = mood;
    }

    public void setName(String name) throws ModelFieldException{
        if (name == null) throw new NullFieldException("HumanBeingModel.name is not nullable");
        if (name.equals("")) throw new IllegalModelFieldException("HumanBeingModel.name can't be empty");
        this.name = name;
    }

    public void setWeaponType(WeaponType weaponType) throws ModelFieldException {
        if (weaponType == null) throw new NullFieldException("HumanBeingModel.weaponType is not nullable");
        this.weaponType = weaponType;
    }

    @Override
    public int compareTo(HumanBeing o){
        int result = getName().compareTo(o.getName());
        if (result != 0) return result;
        result = getCoordinates().compareTo(o.getCoordinates());
        if (result != 0) return result;
        result = Integer.compare(getImpactSpeed(), o.getImpactSpeed());
        if (result != 0) return result;
        result = getCoordinates().compareTo(o.getCoordinates());
        if (result != 0) return result;
        result = getCar().compareTo(o.getCar());
        if (result != 0) return result;
        result = Boolean.compare(isRealHero(), o.isRealHero());
        if (result != 0) return result;
        result = Boolean.compare(isHasToothpick(), o.isHasToothpick());
        if (result != 0) return result;
        result = getMood().compareTo(o.getMood());
        if (result != 0) return result;
        result = getWeaponType().compareTo(o.getWeaponType());
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HumanBeing that = (HumanBeing) o;
        return id == that.id && realHero == that.realHero && hasToothpick == that.hasToothpick && impactSpeed == that.impactSpeed && Objects.equals(name, that.name) && Objects.equals(coordinates, that.coordinates) && Objects.equals(creationDate, that.creationDate) && weaponType == that.weaponType && mood == that.mood && Objects.equals(car, that.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, realHero, hasToothpick, impactSpeed, weaponType, mood, car);
    }
}
