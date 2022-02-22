package console.commands;

/**
 * Class of command that inserts new element with the specified key
 */

import console.exceptions.ConsoleException;
import console.exceptions.IncorrectArgumentConsoleException;
import process.dataClasses.HumanBeing;
import process.exceptions.BuilderException;
import process.repositories.Repository;
import process.utils.HumanBeingBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InsertCommand implements Command{
    Repository<HumanBeing> repository;

    public InsertCommand(Repository<HumanBeing> repository) {
        this.repository = repository;
    }

    @Override
    public boolean execute(List<String> args) throws ConsoleException {
        if (!validateArguments(args)){
            throw new IncorrectArgumentConsoleException("Incorrect format for InsertCommand");
        }
        try{
            HumanBeingBuilder builder = new HumanBeingBuilder();
            builder.create(repository.getPrimaryKeyCounter());
            Map<String, String> builderArgs = new HashMap<>();
            for(String s: args){
                String[] temp = s.split("=");
                builderArgs.put(temp[0], temp[1]);
            }
            builder.build(builderArgs);
            HumanBeing h = builder.get();
            repository.insertEntity(h);
            System.out.println("Added: " + h.toString());
            return true;
        }
        catch (BuilderException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean validateArguments(List<String> args) throws ConsoleException {
        for(String s: args){
            if (!s.contains("=")) return false;
        }
        return true;
    }

    @Override
    public String getDescription() {
        return """
                inserts new element with the specified key.
                format: insert {element}
                element = {field1=value1 field2=value2 ...}
                required fields:\s
                name: String
                coordinates: x:y (x: Integer < 673, y: Integer)
                impactSpeed: Integer
                car: Boolean
                realHero: Boolean
                hasToothpick: Boolean (Nullable)
                weaponType: String (Options:
                    axe,
                    pistol,
                    knife,
                    bat)
                mood: String (Options:
                    sadness,
                    sorrow,
                    gloom,
                    apathy,
                    frenzy)
                
                """;
    }

    @Override
    public String getName() {
        return "insert";
    }
}
