package console;

import console.commands.Command;
import console.exceptions.CommandManagerExitException;
import console.exceptions.ConsoleException;
import console.exceptions.IncorrectArgumentConsoleException;
import process.dataClasses.HumanBeing;
import process.repositories.HumanBeingRepository;
import process.repositories.Repository;

import java.io.InputStream;
import java.util.*;

public class CommandManager {
    private Scanner scanner;
    private ArrayList<Command> executedCommands = new ArrayList<>();
    private Map<String, Command> availableCommands;
    private Repository<HumanBeing> repository;

    /**
     * Constructor of class
     * @param inputStream
     * @param repository
     */
    public CommandManager(InputStream inputStream, Repository<HumanBeing> repository) {
        this.scanner = new Scanner(inputStream);
        Command help = new Help();
        Command history = new History();
        this.repository = repository;
        this.availableCommands.put(help.getName(), help);
        this.availableCommands.put(history.getName(), history);
    }

    public void addAvailableCommands(Map<String, Command> commands){
        this.availableCommands.putAll(commands);
    }

    /**
     * Method to read InputStream
     */


    public void read(){
        while (scanner.hasNext()){
            try {
                String s = scanner.next();
                List<String> args = Arrays.asList(s.split(" "));
                Command command = availableCommands.get(args.get(0));
                command.execute(args.subList(1, args.size()));
                executedCommands.add(command);
            }
            catch (ConsoleException e) {
                System.out.println(e.getMessage());
                break;
            }
        }
    }

    /**
     * Class of command that shows information about all of available commands
     */
    private class Help implements Command{
        @Override
        public boolean execute(List<String> args) throws ConsoleException {
            if (args.isEmpty()){
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
    private class History implements Command {

        @Override
        public boolean execute(List<String> args) throws ConsoleException {
            if (args.isEmpty()) {
                throw new IncorrectArgumentConsoleException("Command history has no arguments");
            }
            if (executedCommands.size() <= 11) {
                for (Command c : Set.copyOf(executedCommands)) {
                    System.out.println(c.getName());
                    System.out.println(c.getDescription());
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
            return "help";
        }

        @Override
        public boolean validateArguments(List<String> args) throws ConsoleException {
            return (args.size() == 0);
        }
    }

}

