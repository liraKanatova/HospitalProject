package peaksoft.exception;

public class AppointmentException extends RuntimeException{
    public AppointmentException() {
        super();
    }

    public AppointmentException(String message) {
        super(message);
    }
}
