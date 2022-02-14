package process.exceptions;

import process.model.Model;

public class EmptyStringModelFieldException extends ModelFieldException {
    public EmptyStringModelFieldException(String description){
        super(description);
    }
}
