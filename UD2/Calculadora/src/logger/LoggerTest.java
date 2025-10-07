package logger;

import logger.enums.Colors;

public class LoggerTest {
    public static void main(String[] args) {
        // Test different log levels
        Logger.header("Logger Test Demo");
        
        Logger.debug("This is a debug message");
        Logger.info("This is an info message");
        Logger.success("This is a success message");
        Logger.warning("This is a warning message");
        Logger.error("This is an error message");
        Logger.critical("This is a critical message");
        Logger.network("This is a network message");
        
        Logger.separator();
        
        // Test custom colors
        Logger.log("Custom red text", Colors.RED);
        Logger.log("Custom blue background", Colors.WHITE, Colors.BG_BLUE);
        Logger.log("Bold underlined text", Colors.YELLOW, Colors.BG_BLACK, Colors.BOLD);
        
        Logger.separator();
        
        // Test rainbow text
        Logger.rainbow("This is rainbow text!");
        
        Logger.separator();
        
        // Test progress bar
        System.out.println("Progress bar demo:");
        for (int i = 0; i <= 10; i++) {
            Logger.progressBar(i, 10, 20);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        Logger.separator();
        
        // Test with colors disabled
        Logger.setEnableColors(false);
        Logger.info("This message has colors disabled");
        Logger.setEnableColors(true);
        
        // Test with timestamp disabled
        Logger.setEnableTimestamp(false);
        Logger.info("This message has no timestamp");
        Logger.setEnableTimestamp(true);
        
        Logger.header("End of Demo");
    }
}