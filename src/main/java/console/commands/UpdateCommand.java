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
import process.exceptions.BuilderIsBusyException;
import process.repositories.Repository;
import process.specifications.HumanBeingSpecifications;
import process.utils.HumanBeingBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateCommand extends AbstractCommand{
    private final Repository<HumanBeing> repository;
    private final HumanBeingBuilder builder = new HumanBeingBuilder();

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
                        System.out.println("HumanBeing.car." + carField + ":");
                        if (scanner.hasNextLine()) {
                            fieldArgs.put(carField, scanner.nextLine());
                        }
                    }
                }
                else if (field.equals("coordinates")) {
                    for (String corField : Coordinates.getFields()) {
                        System.out.println("HumanBeing.coordinates." + corField + ":");
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
            repository.updateEntity(h);
            System.out.println("Updated: " + h.toString());
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
    public String getDescription() {
        return "updates the value of a collection item whose id is equal to the specified one";
    }

    @Override
    public String getName() {
        return "update";
    }

    @Override
    public boolean validateArguments(List<String> args) throws ConsoleException {
        return args.size() == 1 && repository.containsPrimaryKey(args.get(0));
    }


}
