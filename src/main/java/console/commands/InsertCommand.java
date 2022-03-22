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
import process.exceptions.BuilderIsBusyException;
import process.exceptions.ModelFieldException;
import process.repositories.Repository;
import process.utils.HumanBeingBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InsertCommand extends AbstractCommand{
    private final Repository<HumanBeing> repository;
    private HumanBeingBuilder builder = new HumanBeingBuilder();

    public InsertCommand(Repository<HumanBeing> repository) {
        this.repository = repository;
    }

    @Override
    public boolean execute(List<String> args) throws ConsoleException {
        if (!validateArguments(args)){
            throw new IncorrectArgumentConsoleException("Incorrect format for InsertCommand");
        }
        try {
            HumanBeingBuilder builder = new HumanBeingBuilder();
            builder.create(args.get(0));

            System.out.println("Enter element values:");
            for (String field : HumanBeing.getFields()) {

                Map<String, String> fieldArgs = new HashMap<>();
                if (field.equals("car")) {
                    for (String carField : Car.getFields()) {
                        System.out.println("HumanBeing.car." + carField + ":");
                        if (scanner.hasNextLine()) {
                            fieldArgs.put(carField, scanner.nextLine());
                        }
                    }
                } else if (field.equals("coordinates")) {
                    for (String corField : Coordinates.getFields()) {
                        System.out.println("HumanBeing.coordinates." + corField + ":");
                        if (scanner.hasNextLine()) {
                            fieldArgs.put(corField, scanner.nextLine());
                        }
                    }

                } else {
                    System.out.println("HumanBeing." + field);
                    if (scanner.hasNextLine()) {
                        fieldArgs.put("value", scanner.nextLine());
                    }
                }
                try {
                    builder.build(field, fieldArgs);
                } catch (ModelFieldException e) {
                    System.out.println(e.getMessage());
                    return false;
                }
            }
            HumanBeing h = builder.get();
            repository.insertEntity(h);
            System.out.println("Added: " + h.toString());
            return true;

        }

        catch (BuilderIsBusyException e){
            builder.clear();
            return false;
        }
        catch (BuilderException e){
            builder.clear();
            System.out.println(e.getMessage());
            return false;
        }

    }

    @Override
    public boolean validateArguments(List<String> args) throws ConsoleException {
        return args.size() == 1 && !repository.containsPrimaryKey((args.get(0)));
    }

    @Override
    public String getDescription() {
        return "inserts new element with the specified key";
    }

    @Override
    public String getName() {
        return "insert";
    }
}
