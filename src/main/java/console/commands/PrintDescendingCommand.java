package console.commands;

/**
 * Class of command that displays the elements of the collection in descending order
 */

import console.exceptions.ConsoleException;
import process.dataClasses.HumanBeing;
import process.repositories.Repository;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PrintDescendingCommand implements Command {
    Repository<HumanBeing> repository;

    /**
     * Constructor of class
     *
     * @param repository
     */
    public PrintDescendingCommand(Repository<HumanBeing> repository) {
        this.repository = repository;
    }


    /**
     * Method to print descending sorted collection
     */
    @Override
    public boolean execute(List<String> args) throws ConsoleException {
        try {
            List<HumanBeing> query = repository.query();
            Collections.reverse(query);
            for (HumanBeing h : query) {
                System.out.println(h);
            }
        } catch (RuntimeException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    @Override
    public String getDescription() {
        return "displays the elements of the collection in descending order";
    }

    @Override
    public String getName() {
        return "print_descending";
    }

    @Override
    public boolean validateArguments(List<String> args) throws ConsoleException {
        return args.size() == 0;
    }
}
