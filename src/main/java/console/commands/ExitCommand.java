package console.commands;

/**
 * Class of command that shuts down the application
 */

import console.exceptions.CommandManagerExitException;
import console.exceptions.ConsoleException;
import console.exceptions.IncorrectArgumentConsoleException;
import process.dataClasses.HumanBeing;
import process.repositories.Repository;

import java.util.List;

public class ExitCommand implements Command{

    private Repository<HumanBeing> repository;

    public ExitCommand(Repository<HumanBeing> repository) {
        this.repository = repository;
    }

    /**
     * Method to exit application without saving to file
     * @param args
     */
    @Override
    public boolean execute(List<String> args) throws CommandManagerExitException {
        if (!validateArguments(args)) {
            throw new IncorrectArgumentConsoleException("Incorrect format for ExitCommand");
        }
            throw new CommandManagerExitException("exit signal");
    }

    @Override
    public String getDescription() {
        return "shuts down the application";
    }

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public boolean validateArguments(List<String> args) throws ConsoleException {
        return args.size() == 0;
    }
}
