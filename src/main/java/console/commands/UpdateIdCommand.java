package console.commands;

/**
 * Class of command that updates the value of a collection item whose id is equal to the specified one
 */

import console.exceptions.ConsoleException;
import console.exceptions.IncorrectArgumentConsoleException;
import process.dataClasses.HumanBeing;
import process.repositories.Repository;
import process.specifications.HumanBeingSpecifications;
import process.utils.HumanBeingBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateIdCommand implements Command{
    Repository<HumanBeing> repository;

    /**
     * Constructor of class
     * @param repository
     */
    public UpdateIdCommand(Repository<HumanBeing> repository) {
        this.repository = repository;
    }

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
            for(String s: args.subList(1, args.size() - 1)){
                fields.put(s.split("=")[0], s.split("=")[1]);
            }
            repository.updateEntity(builder.update(h, fields));
            return true;
        } catch (RuntimeException e) {
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
        for(String s: args){
            if (!s.contains("=")) return false;
        }
        return true;
    }


}
