package TaskModule1;

public class FileNotExistException extends Exception {
    public FileNotExistException(String message) {
        super(message);
    }
}
