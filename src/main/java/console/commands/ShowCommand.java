package console.commands;

/**
 * Class of command that shows collection for user
 */

import console.exceptions.ConsoleException;
import console.exceptions.IncorrectArgumentConsoleException;
import process.dataClasses.HumanBeing;
import process.repositories.Repository;

import java.util.HashMap;
import java.util.List;

public class ShowCommand implements Command {
    Repository<HumanBeing> repository;

    /**
     * Constructor of class
     *
     * @param repository
     */
    public ShowCommand(Repository<HumanBeing> repository) {
        this.repository = repository;
    }

    /**
     * Method to show collection
     */
    @Override
    public boolean execute(List<String> args) throws ConsoleException {
        if (!validateArguments(args)) {
            throw new IncorrectArgumentConsoleException("Incorrect argument exception");
        }
        try {
            List<HumanBeing> col = repository.query();
            if (col.isEmpty()) System.out.println("Collection is empty");
            for (HumanBeing h : col) {
                System.out.println(h.toString());
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return false;
        };
        return true;
    }

    @Override
    public String getDescription() {
        return "shows collection for user";
    }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public boolean validateArguments(List<String> args) throws ConsoleException {
        return args.size() == 0;
    }
}
