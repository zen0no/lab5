package console.commands;

/**
 * Class of command that outputs any collection element whose creationDate field value is the maximum
 */

import console.exceptions.ConsoleException;
import console.exceptions.IncorrectArgumentConsoleException;
import process.dataClasses.HumanBeing;
import process.repositories.Repository;

import java.util.List;

public class MaxByCreationDateCommand implements Command{
    Repository<HumanBeing> repository;

    public MaxByCreationDateCommand(Repository<HumanBeing> repository) {
        this.repository = repository;
    }

    @Override
    public boolean execute(List<String> args) throws ConsoleException {
        if (!validateArguments(args)){
            throw new IncorrectArgumentConsoleException("Incorrect argument exception for MaxByCreationDateCommand");
        }
        try{
            List<HumanBeing> elements = repository.query();
            System.out.println(elements.stream().max((o1, o2) -> o1.getCreationDate().compareTo(o2.getCreationDate())).get());
            return true;
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public String getDescription() {
        return "outputs collection element with maximum creationDate value";
    }

    @Override
    public String getName() {
        return "max_by_creation";
    }

    @Override
    public boolean validateArguments(List<String> args) throws ConsoleException {
        return args.size() == 0;
    }
}
