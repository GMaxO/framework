package exceptions;

public class SmsNotReceivedException extends RuntimeException{
    public SmsNotReceivedException(String message) {
        super(message);
    }
}
