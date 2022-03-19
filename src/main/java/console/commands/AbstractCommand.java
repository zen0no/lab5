package console.commands;

import process.utils.HumanBeingBuilder;

import java.util.Scanner;

public abstract class AbstractCommand implements Command {
    protected Scanner scanner;
    protected HumanBeingBuilder builder;

    public void setScanner(Scanner scanner){
        this.scanner = scanner;
    }
    public void setBuilder(HumanBeingBuilder builder){this.builder = builder;}
}
