package process.dataClasses;

import process.exceptions.IllegalModelFieldException;
import process.exceptions.ModelFieldException;
import process.exceptions.NullFieldException;

import java.text.SimpleDateFormat;
import java.util.*;

public class HumanBeing implements Comparable<HumanBeing>{

    private static final List<String> fields = List.of(
            "name",
            "coordinates",
            "impactSpeed",
            "car",
            "realHero",
            "hasToothpick",
            "weaponType",
            "mood"
            );

    private static final List<String> autoGeneratedFields = List.of(
            "id",
            "creationDate"
    );

    private final Set<String> currentFields = new HashSet<>();


    private final String primaryKey;
    private final int id;
    private String name;
    private Coordinates coordinates;
    private final Date creationDate;
    private Boolean realHero;
    private Boolean hasToothpick;
    private int impactSpeed;
    private WeaponType weaponType;
    private Mood mood;
    private Car car;


    public HumanBeing(String primaryKey, Integer id){
        this.primaryKey = primaryKey;
        this.id = id;
        creationDate = new Date();
    }

    public HumanBeing(String primaryKey, Integer id, Date creationDate) {
        this.primaryKey = primaryKey;
        this.id = id;
        this.creationDate = creationDate;
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

    public Boolean isHasToothpick() {
        return hasToothpick;
    }

    public String getPrimaryKey(){
        return this.primaryKey;
    }

    public static List<String> getFields() {
        return fields;
    }

    public static List<String> getAutoGeneratedFields(){
        return autoGeneratedFields;
    }

    public Set<String> getCurrentFields() {return currentFields;}

    public void setCar(Car car) throws ModelFieldException{
        if (car == null) throw new NullFieldException("HumanBeing.car is not nullable");
        this.car = car;
        currentFields.add("car");
    }

    public void setCoordinates(Coordinates coordinates) throws ModelFieldException{
        if (coordinates == null) throw new NullFieldException("HumanBeing.coordinates is not nullable");
        this.coordinates = coordinates;
        currentFields.add("coordinates");
    }

    public void setRealHero(Boolean realHero) throws ModelFieldException{
        if (realHero == null) throw new NullFieldException("HumanBeing.realHero is not nullable");
        this.realHero = realHero;
        this.currentFields.add("realHero");
    }

    public void setHasToothpick(Boolean hasToothpick) {
        this.hasToothpick = hasToothpick;
        this.currentFields.add("hasToothpick");
    }

    public void setImpactSpeed(Integer impactSpeed) throws ModelFieldException{
        if (impactSpeed == null) throw new NullFieldException("HumanBeing.impactSpeed is not nullable");
        this.impactSpeed = impactSpeed;
        this.currentFields.add("impactSpeed");
    }

    public void setMood(Mood mood) throws ModelFieldException{
        if (mood == null) throw new NullFieldException("HumanBeing.mood is not nullable");
        this.mood = mood;
        this.currentFields.add("mood");
    }

    public void setName(String name) throws ModelFieldException{
        if (name == null) throw new NullFieldException("HumanBeing.name is not nullable");
        if (name.equals("")) throw new IllegalModelFieldException("HumanBeing.name can't be empty");
        this.name = name;
        this.currentFields.add("name");
    }

    public void setWeaponType(WeaponType weaponType) throws ModelFieldException {
        if (weaponType == null) throw new NullFieldException("HumanBeing.weaponType is not nullable");
        this.weaponType = weaponType;
        this.currentFields.add("weaponType");
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

    public int compareToMap(Map.Entry<String, String> field){
        if (!getFields().contains(field.getKey())) throw new IllegalModelFieldException("HumanBeing has no such field");

        if (field.getKey().equals("name")) return getName().compareTo(field.getValue());
        try {
            if (field.getKey().equals("impactSpeed")) return Integer.compare(getImpactSpeed(), Integer.parseInt(field.getValue()));
        }
        catch (NumberFormatException e){
            throw new IllegalModelFieldException("Incorrect value for HumanBeing.impactSpeed");
        }
        try {
            if (field.getKey().equals("coordinates")) return getCoordinates().compareTo(Coordinates.
                    parseCoordinates(field.getValue()));
        }
        catch (IllegalArgumentException e){
            throw new IllegalModelFieldException("Incorrect value for HumanBeing.coordinates");
        }
        try {
            if (field.getKey().equals("car")) return getCar().compareTo(Car.
                    parseCar(field.getValue()));
        }
        catch (IllegalArgumentException e){
            throw new IllegalModelFieldException("Incorrect value for HumanBeing.car");
        }
        try {
            if (field.getKey().equals("mood")) return getMood().compareTo(Mood.
                    parseMood(field.getValue()));
        }
        catch (IllegalArgumentException e){
            throw new IllegalModelFieldException("Incorrect value for HumanBeing.mood");
        }
        try {
            if (field.getKey().equals("weaponType")) return getWeaponType().compareTo(WeaponType.
                    parseWeaponType(field.getValue()));
        }
        catch (IllegalArgumentException e){
            throw new IllegalModelFieldException("Incorrect value for HumanBeing.weaponType");
        }
        try {
            if (field.getKey().equals("realHero")) return Boolean.compare(isRealHero(), Boolean.parseBoolean(field.getValue()));
        }
        catch (IllegalArgumentException e){
            throw new IllegalModelFieldException("Incorrect value for HumanBeing.realHero");
        }
        try {
            if (field.getKey().equals("hasToothpick")) return Boolean.compare(isHasToothpick(), Boolean.parseBoolean(field.getValue()));
        }
        catch (IllegalArgumentException e){
            throw new IllegalModelFieldException("Incorrect value for HumanBeing.hasToothpick");
        }
        return 0;
    }

    public String show(){
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy, hh:mm a", Locale.ENGLISH);
        return """
                %s:
                    id: %s;
                    creationDate: %s;
                    name: %s;
                    coordinates: %s;
                    impactSpeed: %s;
                    car: %s;
                    realHero: %s;
                    hasToothpick: %s;
                    weaponType: %s;
                    mood: %s;
                """.formatted(primaryKey, String.valueOf(id), formatter.format(creationDate), name, coordinates.toString(),
                String.valueOf(impactSpeed), car.toString(), String.valueOf(realHero), String.valueOf(hasToothpick),
                weaponType.toString(), mood.toString());
    }

    @Override
    public String toString() {
        return "HumanBeing{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", realHero=" + realHero +
                ", hasToothpick=" + hasToothpick +
                ", impactSpeed=" + impactSpeed +
                ", weaponType=" + weaponType +
                ", mood=" + mood +
                ", car=" + car +
                '}';
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
