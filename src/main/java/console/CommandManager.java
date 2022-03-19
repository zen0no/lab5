package console;

import console.commands.AbstractCommand;
import console.commands.Command;
import console.exceptions.CommandManagerExitException;
import console.exceptions.ConsoleException;
import console.exceptions.IncorrectArgumentConsoleException;
import process.dataClasses.HumanBeing;
import process.repositories.Repository;
import process.utils.HumanBeingBuilder;

import java.io.InputStream;
import java.util.*;

public class CommandManager {
    private final Scanner scanner;
    private final HumanBeingBuilder builder;
    private final ArrayList<Command> executedCommands = new ArrayList<>();
    private final Map<String, Command> availableCommands = new HashMap<>();
    private final Repository<HumanBeing> repository;

    /**
     * Class constructor
     * @param inputStream Stream to command args read from
     * @param repository Data storage
     */
    public CommandManager(InputStream inputStream, Repository<HumanBeing> repository, HumanBeingBuilder builder) {
        this.scanner = new Scanner(inputStream);
        Command help = new Help();
        Command history = new History();
        this.repository = repository;
        this.builder = builder;
        this.availableCommands.put(help.getName(), help);
        this.availableCommands.put(history.getName(), history);
    }

    /**
     * @param commands Commands which manager can execute
     */
    public void addAvailableCommands(Map<String, Command> commands){
        for (Map.Entry<String, Command> c: commands.entrySet())
        {
            c.getValue().setScanner(scanner);
            c.getValue().setBuilder(builder);
        }
        this.availableCommands.putAll(commands);
    }

    /**
     * Method to read InputStream
     */


    public void read(){
        while (scanner.hasNextLine()){
            try {
                String s = scanner.nextLine();
                List<String> args = Arrays.asList(s.split(" "));
                Command command = availableCommands.get(args.get(0));
                command.execute(args.subList(1, args.size()));
                executedCommands.add(command);
            }
            catch (CommandManagerExitException e){
                System.out.println(e.getMessage());
                break;
            }
            catch (ConsoleException e) {
                System.out.println(e.getMessage());
            }
            catch (NullPointerException e){
                System.out.println("Unknown command");
            }
        }
    }

    /**
     * Class of command that shows information about all of available commands
     */
    private class Help extends AbstractCommand{
        @Override
        public boolean execute(List<String> args) throws ConsoleException {
            if (!validateArguments(args)){
                throw new IncorrectArgumentConsoleException("Command help has no arguments");
            }
            for (Map.Entry<String, Command> c: availableCommands.entrySet()){
                System.out.println(c.getKey());
                System.out.println(c.getValue().getDescription());
            }
            return true;
        }

        @Override
        public boolean validateArguments(List<String> args){
            return (args.size() == 0);
        }

        @Override
        public String getDescription(){
            return "shows information about all of available commands";
        }

        @Override
        public String getName(){
            return "help";
        }
    }

    /**
     * Class of command that shows the last 11 commands
     */
    private class History extends AbstractCommand {

        @Override
        public boolean execute(List<String> args) throws ConsoleException {
            if (!validateArguments(args)) {
                throw new IncorrectArgumentConsoleException("Command history has no arguments");
            }
            if (executedCommands.size() <= 11) {
                for (Command c : Set.copyOf(executedCommands)) {
                    System.out.println(c.getName());
                }
                return true;
            }

            int size = executedCommands.size();
            ArrayList<Command> commandsSublist = (ArrayList<Command>) executedCommands.subList(size - 12, size - 1);
            for (Command c : Set.copyOf(commandsSublist)) {
                System.out.println(c.getName());
                System.out.println(c.getDescription());
            }
            return true;
        }

        @Override
        public String getDescription() {
            return "shows the last 11 commands";
        }

        @Override
        public String getName() {
            return "history";
        }

        @Override
        public boolean validateArguments(List<String> args) throws ConsoleException {
            return (args.size() == 0);
        }
    }

}

