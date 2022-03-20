package console.commands;

/**
 * Class of command that inserts new element with the specified key
 */

import console.exceptions.ConsoleException;
import console.exceptions.IncorrectArgumentConsoleException;
import process.dataClasses.Car;
import process.dataClasses.Coordinates;
import process.dataClasses.HumanBeing;
import process.exceptions.BuilderException;
import process.exceptions.ModelFieldException;
import process.repositories.Repository;
import process.utils.HumanBeingBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InsertCommand extends AbstractCommand{
    private final Repository<HumanBeing> repository;

    public InsertCommand(Repository<HumanBeing> repository) {
        this.repository = repository;
    }

    @Override
    public boolean execute(List<String> args) throws ConsoleException {
        if (!validateArguments(args)){
            throw new IncorrectArgumentConsoleException("Incorrect format for InsertCommand");
        }
        try
        {
            HumanBeingBuilder builder = new HumanBeingBuilder();
            builder.create(args.get(0));

            System.out.println("Enter element values:");
            for(String field: HumanBeing.getFields())
            {

                Map<String, String> fieldArgs = new HashMap<>();
                if (field.equals("car"))
                {
                    for (String carField : Car.getFields()) {
                        System.out.println("HumanBeing.Car." + carField + ":");
                        if (scanner.hasNextLine()) {
                            fieldArgs.put(carField, scanner.nextLine());
                        }
                    }
                }
                else if (field.equals("coordinates")) {
                    for (String corField : Coordinates.getFields()) {
                        System.out.println("HumanBeing.Coordinates." + corField + ":");
                        if (scanner.hasNextLine()) {
                            fieldArgs.put(corField, scanner.nextLine());
                        }
                    }

                }
                else
                {
                    System.out.println("HumanBeing." + field);
                    if (scanner.hasNextLine())
                    {
                        fieldArgs.put("value", scanner.nextLine());
                    }
                }
                try
                {
                    builder.build(field, fieldArgs);
                }
                catch (ModelFieldException e){
                    System.out.println(e.getMessage());
                    return false;
                }
            }
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
        return args.size() == 1;
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
