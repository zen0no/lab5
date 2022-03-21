package console.commands;

/**
 * Class of command that removes all elements smaller than the specified one from the collection
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
import process.specifications.HumanBeingSpecifications;
import process.utils.HumanBeingBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoveLowerCommand extends AbstractCommand {
    private final Repository<HumanBeing> repository;
    private final HumanBeingBuilder builder = new HumanBeingBuilder();

    /**
     * Constructor of class
     */
    public RemoveLowerCommand(Repository<HumanBeing> repository) {
        this.repository = repository;
    }

    @Override
    public boolean execute(List<String> args) throws ConsoleException {
        if (!validateArguments(args)) {
            throw new IncorrectArgumentConsoleException("Incorrect argument exception");
        }
        try {
            builder.createTemp();
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
            System.out.println("Deleted entities:");
            List<HumanBeing> toDelete = repository.query(HumanBeingSpecifications.Lower(h));
            for (HumanBeing human: toDelete){
                System.out.println(human.toString());
            }
            repository.removeEntity(repository.query(HumanBeingSpecifications.Lower(h)));
            return true;
        }
        catch (BuilderIsBusyException e){
            builder.clear();
            return false;
        }
        catch (BuilderException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public String getDescription() {
        return "removes all elements smaller than the specified one from the collection";
    }

    @Override
    public String getName() {
        return "remove_lower";
    }

    @Override
    public boolean validateArguments(List<String> args) throws ConsoleException {
        return args.size() == 0;
    }
}
