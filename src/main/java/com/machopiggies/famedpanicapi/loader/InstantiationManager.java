package com.machopiggies.famedpanicapi.loader;

import com.machopiggies.famedpanicapi.FamedPanicAPI;
import com.machopiggies.famedpanicapi.observer.Observer;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;

import java.util.Objects;

public class InstantiationManager extends Observer {

    public void payload(DataPayload payload) {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        String callerFilePath = Objects.requireNonNull(getClass().getClassLoader().getResource(stackTrace[1].getClassName().replace('.', '/') + ".class")).toString();
        Bukkit.getLogger().info(callerFilePath);
    }

    public static class DataPayload {
        public boolean safemode;

        public DataPayload(boolean safemode) {
            this.safemode = safemode;
        }
    }
}
