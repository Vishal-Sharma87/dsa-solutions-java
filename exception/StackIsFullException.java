package exception;

public class StackIsFullException extends RuntimeException{

    public StackIsFullException(String message){
        super(message);
    }

}
