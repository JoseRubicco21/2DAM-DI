package graphics_manager.exceptions;
import graphics_manager.log.AnsiColor;

public class InvalidAxisException extends Exception {
    public InvalidAxisException(String message) {
        super(String.format("%s%s%s%n", AnsiColor.RED, message, AnsiColor.RESET));
    }
}
