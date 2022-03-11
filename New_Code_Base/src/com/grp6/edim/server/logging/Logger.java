package com.grp6.edim.server.logging;

public class Logger {
    public static void log(String message, LogLevel level) {
        System.out.println(level.name() + " : " + message);
    }
}
