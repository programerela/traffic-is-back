package rs.ac.np.police.trafficis.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s sa %s '%s' nije pronađen", resourceName, fieldName, fieldValue));
    }
}