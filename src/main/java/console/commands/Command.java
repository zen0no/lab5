package console.commands;

public abstract class Command {

    private String name;
    private String description;

    boolean execute(){
        return true;
    }

}
