package peaksoft.exception;

public class PatientException extends RuntimeException{
    public PatientException() {
        super();
    }

    public PatientException(String message) {
        super(message);
    }
}
