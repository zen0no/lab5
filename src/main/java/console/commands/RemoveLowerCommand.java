package console.commands;

/**
 * Class of command that removes all elements smaller than the specified one from the collection
 */

import console.exceptions.ConsoleException;
import console.exceptions.IncorrectArgumentConsoleException;
import process.dataClasses.HumanBeing;
import process.repositories.Repository;
import process.specifications.HumanBeingSpecifications;

import java.util.HashMap;
import java.util.List;

public class RemoveLowerCommand extends AbstractCommand {
    Repository<HumanBeing> repository;

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
            int id = Integer.parseInt(args.get(0));
            HumanBeing h = repository.query(HumanBeingSpecifications.Id(id)).get(0);
            repository.removeEntity(repository.query(HumanBeingSpecifications.Lower(h)));
            return true;
        } catch (RuntimeException e) {
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
        return "remove_lover";
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
