package laba;

import console.CommandManager;
import console.commands.*;
import process.repositories.HumanBeingRepository;
import process.utils.HumanBeingBuilder;
import process.utils.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Application {
    public static void main(String[] args){
        System.out.println("hiiiii henlooooo giv mi one cummund plis.......");
        HumanBeingRepository repository = new HumanBeingRepository();
        HumanBeingBuilder builder =  new HumanBeingBuilder();
        builder.setIdCounter(repository.getPrimaryKeyCounter());
        CommandManager commandManager = new CommandManager(System.in, repository, builder);
        Map<String, Command> commands = new HashMap<>();

        Command c = new ClearCommand(repository);
        commands.put(c.getName(), c);

        c = new ExecuteScriptCommand(repository, new HashSet<>(), 0);
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

        commandManager.addAvailableCommands(commands);

        commandManager.read();

    }
}
