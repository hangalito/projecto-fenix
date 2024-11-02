package com.fenix.projecto.util;

import java.lang.invoke.MethodHandles;
import java.util.logging.Level;

public class Logger {

    private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

    public static void info(String msg) {
        log.log(Level.INFO, msg);
    }

    public static void error(String msg) {
        log.log(Level.SEVERE, msg);
    }
}
