package console.commands;

/**
 * Class of command that reads and executes the script from the specified file
 */

import console.CommandManager;
import console.exceptions.ConsoleException;
import console.exceptions.IncorrectArgumentConsoleException;
import process.dataClasses.HumanBeing;
import process.repositories.Repository;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExecuteScriptCommand implements Command{

    /**
     * Constructor of class
     */

    private Repository<HumanBeing> repository;

    public ExecuteScriptCommand(Repository<HumanBeing> repository) {
        this.repository = repository;
    }

    @Override
    public boolean execute(List<String> args) throws ConsoleException {
        if (!validateArguments(args)){
            throw new IncorrectArgumentConsoleException("Incorrect file path for ExecuteScriptCommand");
        }
        try{
            File f = new File(args.get(0));
            CommandManager tempManager = new CommandManager(new FileInputStream(f), this.repository);
            Map<String, Command> commands = new HashMap<>();

            Command c = new ClearCommand(repository);
            commands.put(c.getName(), c);

            c = new ExecuteScriptCommand(repository);
            commands.put(c.getName(), c);

            c = new ExitCommand(repository);
            commands.put(c.getName(), c);

            c = new InsertCommand(repository);
            commands.put(c.getName(), c);

            c = new InfoCommand(repository);
            commands.put(c.getName(), c);

            c = new MaxByCreationDateCommand(repository);
            commands.put(c.getName(), c);

            c = new PrintAscendingCommand(repository);
            commands.put(c.getName(), c);

            c = new PrintDescendingCommand(repository);
            commands.put(c.getName(), c);

            c = new RemoveKeyCommand(repository);
            commands.put(c.getName(), c);

            c = new RemoveLowerCommand(repository);
            commands.put(c.getName(), c);

            c = new ReplaceIfGreaterCommand(repository);
            commands.put(c.getName(), c);

            c = new SaveCommand(repository);
            commands.put(c.getName(), c);

            c = new ShowCommand(repository);
            commands.put(c.getName(), c);

            c = new UpdateCommand(repository);
            commands.put(c.getName(), c);


            tempManager.addAvailableCommands(commands);
            tempManager.read();
            return true;
        }
        catch (java.io.FileNotFoundException e){
            return false;
        }
    }

    @Override
    public boolean validateArguments(List<String> args) throws ConsoleException{
        if (args.size() == 1){
            return (new File(args.get(0))).exists();
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "reads and executes the script from the specified file";
    }

    @Override
    public String getName() {
        return "execute_script";
    }



}
