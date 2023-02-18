package peaksoft.exception;

public class DoctorException extends RuntimeException{
    public DoctorException() {
        super();
    }

    public DoctorException(String message) {
        super(message);
    }
}
