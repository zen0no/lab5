package process.exceptions;

public class BuilderIsBusyException  extends BuilderException{
    public BuilderIsBusyException(){
        super("Builder is busy");
    }
}
