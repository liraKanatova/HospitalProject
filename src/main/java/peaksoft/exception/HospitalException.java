package peaksoft.exception;

public class HospitalException extends RuntimeException{
    public HospitalException() {
        super();
    }

    public HospitalException(String message) {
        super(message);
    }
}
