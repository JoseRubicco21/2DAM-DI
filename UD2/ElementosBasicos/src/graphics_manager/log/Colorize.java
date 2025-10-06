package graphics_manager.log;

public class Colorize {
    public static String colorize(String message, AnsiColor color) {
        return color + message + AnsiColor.RESET;
    }
}
