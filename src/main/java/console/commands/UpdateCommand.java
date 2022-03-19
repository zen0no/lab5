package console.commands;

/**
 * Class of command that updates the value of a collection item whose id is equal to the specified one
 */

import console.exceptions.ConsoleException;
import console.exceptions.IncorrectArgumentConsoleException;
import process.dataClasses.Car;
import process.dataClasses.Coordinates;
import process.dataClasses.HumanBeing;
import process.exceptions.BuilderException;
import process.repositories.Repository;
import process.specifications.HumanBeingSpecifications;
import process.utils.HumanBeingBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateCommand extends AbstractCommand{
    Repository<HumanBeing> repository;

    /**
     * Constructor of class
     * @param repository
     */
    public UpdateCommand(Repository<HumanBeing> repository) {
        this.repository = repository;
    }

    @Override
    public boolean execute(List<String> args) throws ConsoleException {
        if (!validateArguments(args)){
            throw new IncorrectArgumentConsoleException("Incorrect format for UpdateCommand");
        }
        try
        {
            String key = args.get(0);
            HumanBeing h = repository.query(HumanBeingSpecifications.PrimaryKey(key)).get(0);
            if (h == null) {
                System.out.println("HumanBeing with this key was not found");
                return false;
            }
            builder.update(h);

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
            h = builder.get();
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
    public String getDescription() {
        return "updates the value of a collection item whose id is equal to the specified one";
    }

    @Override
    public String getName() {
        return "update";
    }

    @Override
    public boolean validateArguments(List<String> args) throws ConsoleException {
        if (args.size() <= 2) return false;
        for(String s: args){
            if (!s.contains("=")) return false;
        }
        return true;
    }


}
