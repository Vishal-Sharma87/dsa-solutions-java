package exception;

public class QueueIsEmptyException extends RuntimeException {

    public QueueIsEmptyException(String message) {
        super(message);
    }

}
