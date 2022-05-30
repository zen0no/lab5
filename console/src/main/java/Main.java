import commands.*;
import manager.CommandManager;
import server.ServerConnection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Main {
    public static void main(String[] args){
        ServerConnection.getConnection();
        CommandManager commandManager = new CommandManager();
        Map<String, Command> commands = new HashMap<>();

        Command c = new ClearCommand();
        commands.put(c.getName(), c);

        c = new ExecuteScriptCommand(new HashSet<>(), 0);
        commands.put(c.getName(), c);

        c = new ExitCommand();
        commands.put(c.getName(), c);

        c = new InsertCommand();
        commands.put(c.getName(), c);

        c = new InfoCommand();
        commands.put(c.getName(), c);

        c = new MaxByCreationDateCommand();
        commands.put(c.getName(), c);

        c = new PrintAscendingCommand();
        commands.put(c.getName(), c);

        c = new PrintDescendingCommand();
        commands.put(c.getName(), c);

        c = new RemoveKeyCommand();
        commands.put(c.getName(), c);

        c = new RemoveLowerCommand();
        commands.put(c.getName(), c);

        c = new ReplaceIfGreaterCommand();
        commands.put(c.getName(), c);

        c = new ShowCommand();
        commands.put(c.getName(), c);

        c = new UpdateCommand();
        commands.put(c.getName(), c);

        commandManager.addAvailableCommands(commands);

        commandManager.read();

    }
}

