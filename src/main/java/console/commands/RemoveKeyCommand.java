package console.commands;

/**
 * Class of command that removes an element from the collection by its key
 */

import console.exceptions.ConsoleException;
import console.exceptions.IncorrectArgumentConsoleException;
import process.dataClasses.HumanBeing;
import process.repositories.HumanBeingRepository;
import process.repositories.Repository;
import process.specifications.HumanBeingSpecifications;

import java.util.List;

public class RemoveKeyCommand implements Command {

    Repository<HumanBeing> repository;

    public RemoveKeyCommand(Repository<HumanBeing> repository) {
        this.repository = repository;
    }

    /**
     * Constructor of class
     */

    @Override
    public boolean execute(List<String> args) throws ConsoleException {
        if (!validateArguments(args)) {
            throw new IncorrectArgumentConsoleException("");
        }
        try {
            int id = Integer.parseInt(args.get(0));
            repository.removeEntity(repository.query(HumanBeingSpecifications.Id(id)));
            return true;
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public String getDescription() {
        return "removes an element from the collection by its key";
    }

    @Override
    public String getName() {
        return "remove_key";
    }

    @Override
    public boolean validateArguments(List<String> args) throws ConsoleException {
        if (args.size() == 1) {
            try {
                Integer.parseInt(args.get(0));
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }
}
