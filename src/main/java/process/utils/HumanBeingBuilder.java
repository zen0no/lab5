package process.utils;

import process.dataClasses.*;
import process.exceptions.BuilderException;
import process.exceptions.IllegalModelFieldException;
import process.exceptions.ModelFieldException;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public class HumanBeingBuilder {
    HumanBeing currentHuman;

    public void create(int primaryKey) throws BuilderException {
        if (currentHuman == null) currentHuman = new HumanBeing(primaryKey);
        else throw new BuilderException("Entity is already building");
    }

    public void build(Map<String, String> args) throws ModelFieldException, BuilderException{
        Set<String> fields = currentHuman.getFields();
        for (String f: args.keySet()){
            if (!fields.contains(f)) throw new IllegalModelFieldException("HumanBeing has no field " + f);
        }

        if (args.containsKey("id")) throw new IllegalModelFieldException("HumanBeing.id is generated automatically");
        if (args.containsKey("creationDate")) throw new IllegalModelFieldException("HumanBeing.creationDate is generated automatically");

        try {
            currentHuman.setCreationDate(new Date());
            currentHuman.setName(args.get("name"));
            currentHuman.setCar(Car.parseCar(args.get("car")));
            currentHuman.setMood(Mood.parseMood(args.get("mood")));
            currentHuman.setWeaponType(WeaponType.parseWeaponType(args.get("weaponType")));
            currentHuman.setCoordinates(Coordinates.parseCoordinates(args.get("coordinates")));
            if (args.get("hasToothpick") != null)
                currentHuman.setHasToothpick(Boolean.parseBoolean(args.get("hasToothpick")));
            currentHuman.setRealHero(Boolean.parseBoolean(args.get("realHero")));
            currentHuman.setImpactSpeed(Integer.parseInt(args.get("impactSpeed")));
        }
        catch (ModelFieldException | IllegalArgumentException e){
            throw new BuilderException("Builder can't build object: " + e.getMessage());
        }
    }

    public void buildFromFile(Map<String, String> args) throws ModelFieldException, BuilderException{
        Set<String> fields = currentHuman.getFields();
        for (String f: args.keySet()){
            if (!fields.contains(f)) throw new IllegalModelFieldException("HumanBeing has no field " + f);
        }

        if (args.containsKey("id")) throw new IllegalModelFieldException("HumanBeing.id is generated automatically");

        try {
            currentHuman.setName(args.get("name"));
            currentHuman.setCar(Car.parseCar(args.get("car")));
            currentHuman.setMood(Mood.parseMood(args.get("mood")));
            currentHuman.setWeaponType(WeaponType.parseWeaponType(args.get("weaponType")));
            currentHuman.setCoordinates(Coordinates.parseCoordinates(args.get("coordinates")));
            currentHuman.setHasToothpick(Boolean.parseBoolean(args.get("hasToothpick")));
            currentHuman.setRealHero(Boolean.parseBoolean(args.get("realHero")));
            currentHuman.setImpactSpeed(Integer.parseInt(args.get("impactSpeed")));
            currentHuman.setCreationDate(java.text.DateFormat.getDateInstance().parse(args.get("creationDate")));
        }
        catch (ModelFieldException | ParseException e){
            throw new BuilderException("Builder can't build object: " + e.getMessage());
        }
    }

    public HumanBeing update(HumanBeing entity, Map<String, String> args) throws BuilderException, IllegalModelFieldException{
        Set<String> fields = entity.getFields();
        for (String f: args.keySet()){
            if (!fields.contains(f)) throw new IllegalModelFieldException("HumanBeing has no field " + f);
        }

        if (args.containsKey("id")) throw new IllegalModelFieldException("HumanBeing.id is generated automatically");
        if (args.containsKey("creationDate")) throw new IllegalModelFieldException("HumanBeing.creationDate is generated automatically");

        try {
            if (args.containsKey("name")) entity.setName(args.get("name"));
            if (args.containsKey("car")) entity.setCar(Car.parseCar(args.get("car")));
            if (args.containsKey("mood")) entity.setMood(Mood.parseMood(args.get("mood")));
            if (args.containsKey("weaponType")) entity.setWeaponType(WeaponType.parseWeaponType(args.get("weaponType")));
            if (args.containsKey("coordinates")) entity.setCoordinates(Coordinates.parseCoordinates(args.get("coordinates")));
            if (args.containsKey("hasToothpick")) entity.setHasToothpick(Boolean.parseBoolean(args.get("hasToothpick")));
            if (args.containsKey("realHero")) entity.setRealHero(Boolean.parseBoolean(args.get("realHero")));
            if (args.containsKey("impactSpeed")) entity.setImpactSpeed(Integer.parseInt(args.get("impactSpeed")));
        }
        catch (ModelFieldException | IllegalArgumentException e){
            throw new BuilderException("Builder can't build object: " + " " + e.getMessage());
        }
        return entity;
    }

    public HumanBeing get(){
        HumanBeing temp = currentHuman;
        currentHuman = null;
        return temp;
    }
}
