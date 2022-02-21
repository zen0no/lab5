package console.commands;

/*
 * Class of command that clears collection
 */

import console.exceptions.ConsoleException;
import console.exceptions.IncorrectArgumentConsoleException;
import process.dataClasses.HumanBeing;
import process.repositories.Repository;

import java.util.List;

public class ClearCommand implements Command {
    Repository<HumanBeing> repository;

    /**
     * Constructor of class
     * @param repository
     */
    public ClearCommand(Repository<HumanBeing> repository) {
        this.repository = repository;
    }

    /**
     * Method to clear collection
     */
    @Override
    public boolean execute(List<String> args) throws ConsoleException {
        if (!validateArguments(args)){
            throw new IncorrectArgumentConsoleException("ClearCommand does not require arguments");
        }
        try{
            repository.removeEntity(repository.query());
            return true;
        }
        catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean validateArguments(List<String> args){
        return args.size() > 0;
    }

    @Override
    public String getDescription()
    {
        return "clears collection";
    }

    @Override
    public String getName() {
        return "clear";
    }
}
