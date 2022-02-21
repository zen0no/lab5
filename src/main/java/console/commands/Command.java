package console.commands;

import console.exceptions.ConsoleException;

import java.util.List;

public interface Command {

    /**
     * Returns "true" if command completed successfully, returns "false" if command failed
     * @param args
     * @return
     * @throws ConsoleException
     */
    boolean execute(List<String> args) throws ConsoleException;

    /**
     * Method to get description of command
     * @return
     */
    String getDescription();

    /**
     * Method to get name of command
     * @return
     */
    String getName();

    boolean validateArguments(List<String> args) throws ConsoleException;

}