package exceptions;

public class MessageNotReceivedException extends RuntimeException {
    public MessageNotReceivedException(String message) {
        super(message);
    }
}
