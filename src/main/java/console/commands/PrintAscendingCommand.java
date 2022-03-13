package console.commands;

/**
 * Class of command that displays the elements of the collection in ascending order
 */

import console.exceptions.ConsoleException;
import console.exceptions.IncorrectArgumentConsoleException;
import process.dataClasses.HumanBeing;
import process.repositories.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PrintAscendingCommand extends AbstractCommand {
    Repository<HumanBeing> repository;

    /**
     * Constructor of class
     * @param repository
     */
    public PrintAscendingCommand(Repository<HumanBeing> repository) {
        this.repository = repository;
    }


    /**
     * Method to print ascending sorted collection
     */
    @Override
    public boolean execute(List<String> args) throws ConsoleException {
        if (!validateArguments(args)){
            throw new IncorrectArgumentConsoleException("Incorrect argument exception");
        }
        try {
            List<HumanBeing> col = repository.query();
            Collections.sort(col);
            for(HumanBeing h: col) {
                System.out.println(h);
            }
        } catch (RuntimeException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    @Override
    public String getDescription() {
        return "displays the elements of the collection in ascending order";
    }

    @Override
    public String getName() {
        return "print_ascending";
    }

    @Override
    public boolean validateArguments(List<String> args) throws ConsoleException {
        return args.size() == 0;
    }
}
