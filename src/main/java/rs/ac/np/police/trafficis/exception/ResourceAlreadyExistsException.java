package rs.ac.np.police.trafficis.exception;

public class ResourceAlreadyExistsException extends RuntimeException {

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }

    public ResourceAlreadyExistsException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s sa %s '%s' već postoji", resourceName, fieldName, fieldValue));
    }
}