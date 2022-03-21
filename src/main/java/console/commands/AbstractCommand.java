package console.commands;

import process.utils.HumanBeingBuilder;

import java.util.Scanner;

public abstract class AbstractCommand implements Command {
    protected Scanner scanner;

    public void setScanner(Scanner scanner){
        this.scanner = scanner;
    }
}
