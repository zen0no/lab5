package console.commands;

/**
 * Class of command that replaces the value by key if the new value is greater than the old one
 */

import console.exceptions.ConsoleException;
import console.exceptions.IncorrectArgumentConsoleException;
import process.dataClasses.Car;
import process.dataClasses.Coordinates;
import process.dataClasses.HumanBeing;
import process.repositories.Repository;
import process.specifications.HumanBeingSpecifications;
import process.utils.HumanBeingBuilder;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReplaceIfGreaterCommand extends AbstractCommand {
    Repository<HumanBeing> repository;

    public ReplaceIfGreaterCommand(Repository<HumanBeing> repository) {
        this.repository = repository;
    }

    /**
     * Constructor of class
     */


    @Override
    public boolean execute(List<String> args) throws ConsoleException {
        if (!validateArguments(args)) {
            throw new IncorrectArgumentConsoleException("Incorrect argument exception");
        }
        try {

            String key = args.get(0);
            HumanBeing h = repository.query(HumanBeingSpecifications.PrimaryKey(key)).get(0);
            if (h == null){
                System.out.println("");
                return false;
            }

            System.out.println("Enter element values:");
            for(String field: HumanBeing.getFields())
            {
                Map<String, String> fieldArgs = new HashMap<>();
                if (field.equals("car"))
                {
                    for (String carField : Car.getFields()) {
                        System.out.println("HumanBeing.Car" + carField + ":");
                        if (scanner.hasNextLine()) {
                            fieldArgs.put(carField, scanner.nextLine());
                        }
                    }
                }
                if (field.equals("coordinates")) {
                    for (String corField : Coordinates.getFields()) {
                        System.out.println("HumanBeing.Coordinates" + corField + ":");
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
                builder.build(field, fieldArgs);
            }
            HumanBeing updated = builder.get();
            if (updated.compareTo(h) > 0) repository.insertEntity(h);
            System.out.println("Added: " + h.toString());
            return true;

        }

        catch (RuntimeException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public String getDescription() {
        return "replaces the value by key if the new value is greater than the old one" +
                "format: ";
    }

    @Override
    public String getName() {
        return "replace_if_greater";
    }

    @Override
    public boolean validateArguments(List<String> args){
        return args.size() == 1;
    }
}
