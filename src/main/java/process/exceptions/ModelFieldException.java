package process.exceptions;


public class ModelFieldException extends RuntimeException{
    protected ModelFieldException(String description){
        super(description);
    }
}
