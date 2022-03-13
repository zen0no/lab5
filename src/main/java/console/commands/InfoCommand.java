package console.commands;

/**
 * Class of command that returns information about Collection
 */

import console.exceptions.ConsoleException;
import console.exceptions.IncorrectArgumentConsoleException;
import process.dataClasses.HumanBeing;
import process.repositories.Repository;

import java.util.List;

public class InfoCommand extends AbstractCommand{
    private Repository<HumanBeing> repository;

    public InfoCommand(Repository<HumanBeing> repository) {
        this.repository = repository;
    }

    @Override
    public boolean execute(List<String> args) throws ConsoleException {
        if (!validateArguments(args)){
            throw new IncorrectArgumentConsoleException("InfoCommand does not require arguments");
        }

        try{
            System.out.println("Collection type: " + repository.getTypeName());
            System.out.println("Initialization date: " + repository.getInitDate());
            System.out.println("Collection size: " + repository.query().size());
            return true;
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public String getDescription() {
        return "returns information about Collection (type, date of initialization, quantity of elements etc.)";
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public boolean validateArguments(List<String> args) throws ConsoleException {
        return args.size() == 0;
    }
}
