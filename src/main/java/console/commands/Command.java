package console.commands;

import console.exceptions.ConsoleException;

import java.util.List;
import java.util.Scanner;

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

    void setScanner(Scanner scanner);

    boolean validateArguments(List<String> args) throws ConsoleException;

}