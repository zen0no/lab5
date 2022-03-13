package console.commands;

import process.repository.Repository;

import java.util.Scanner;

public abstract class AbstractCommand implements Command {
    private Scanner scanner;

    public void setScanner(Scanner scanner){
        this.scanner = scanner;
    }
}
