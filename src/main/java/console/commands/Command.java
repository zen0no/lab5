package console.commands;

public abstract class Command {

    private String name;
    private String description;

    abstract boolean execute();


    public String getName()
    {
        return name;
    };

    public String getDescription() {
        return description;
    }
}
