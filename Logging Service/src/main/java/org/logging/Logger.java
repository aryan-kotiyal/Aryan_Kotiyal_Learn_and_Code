package org.logging;

public class Logger {

    private static volatile Logger instance;

    private Logger() {
        System.out.println("Logger Initialized");
    }

    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    public void logInfo(String message) {
        System.out.println("[INFO] " + message);
    }

    public void logDebug(String message) {
        System.out.println("[DEBUG] " + message);
    }

    public void logError(String message) {
        System.err.println("[ERROR] " + message);
    }
}
