package console.commands;

/**
 * Class of command that replaces the value by key if the new value is greater than the old one
 */

import console.exceptions.ConsoleException;
import console.exceptions.IncorrectArgumentConsoleException;
import process.dataClasses.HumanBeing;
import process.repositories.Repository;
import process.specifications.HumanBeingSpecifications;
import process.utils.HumanBeingBuilder;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReplaceIfGreaterCommand implements Command {
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
            int id = Integer.parseInt(args.get(0));
            HumanBeing h = repository.query(HumanBeingSpecifications.Id(id)).get(0);
            HumanBeingBuilder builder = new HumanBeingBuilder();
            Map<String, String> fields = new HashMap<>();
            for (String s : args.subList(1, args.size())) {
                fields.put(s.split("=")[0], s.split("=")[1]);
            }
            for (Map.Entry<String, String> entry : fields.entrySet()) {
                if (h.compareToMap(entry) < 0) repository.updateEntity(builder.update(h, fields));
            }
            return true;
        } catch (RuntimeException e) {
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
    public boolean validateArguments(List<String> args) throws ConsoleException {
        if (args.size() == 2) {
            try {
                Integer.parseInt(args.get(0));
                return args.get(1).contains("=");
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }
}
