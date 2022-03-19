package console.commands;

/**
 * Class of command that reads and executes the script from the specified file
 */

import console.CommandManager;
import console.exceptions.ConsoleException;
import console.exceptions.IncorrectArgumentConsoleException;
import process.dataClasses.HumanBeing;
import process.repositories.Repository;
import process.utils.Pair;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExecuteScriptCommand extends AbstractCommand{

    /**
     * Constructor of class
     */

    private final Repository<HumanBeing> repository;
    private final Set<Pair<String, Integer>> fileNames;
    private final int level;


    public ExecuteScriptCommand(Repository<HumanBeing> repository,Set<Pair<String, Integer>> fileNames, int level) {
        this.repository = repository;
        this.fileNames = fileNames;
        this.level = level;
    }

    @Override
    public boolean execute(List<String> args) throws ConsoleException {
        if (!validateArguments(args)){
            throw new IncorrectArgumentConsoleException("Incorrect file path for ExecuteScriptCommand");
        }
        try{

            String path = args.get(0);

            for (Pair<String, Integer> p: fileNames){
                if(p.getLeft().equals(path) && p.getRight() < level )throw new ConsoleException("This script already executed. Change it to avoid looping");
            }

            File f = new File(args.get(0));
            CommandManager tempManager = new CommandManager(new FileInputStream(f), this.repository, builder);
            Map<String, Command> commands = new HashMap<>();

            Command c = new ClearCommand(repository);
            commands.put(c.getName(), c);

            fileNames.add(new Pair<>(path, level));
            c = new ExecuteScriptCommand(repository, fileNames, level + 1);
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
