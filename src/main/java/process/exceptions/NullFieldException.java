package process.exceptions;

/**
 * Exception, thrown when data class fields is null
 */
public class NullFieldException extends ModelFieldException{

    public NullFieldException(String description){
        super(description);
    }
}
