package petstore.exception;

public class AnimalNotFoundException extends RuntimeException {
    public AnimalNotFoundException(String name) {
        super("Animal not found : " + name);
    }
}
