import console.CommandManager;
import process.repositories.HumanBeingRepository;


public class Application {
    public static void main(){
        HumanBeingRepository repository = new HumanBeingRepository();
        CommandManager commandManager = new CommandManager(System.in, repository);
        commandManager.read();
    }
}
