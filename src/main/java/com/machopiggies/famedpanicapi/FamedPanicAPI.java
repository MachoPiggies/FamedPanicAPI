package com.machopiggies.famedpanicapi;

import com.machopiggies.famedpanicapi.elements.PanicRegister;
import com.machopiggies.famedpanicapi.events.SafemodeChangedEvent;
import com.machopiggies.famedpanicapi.loader.InstantiationManager;
import com.machopiggies.famedpanicapi.misc.PanickedPlayer;
import com.machopiggies.famedpanicapi.observer.Observer;
import com.machopiggies.famedpanicapi.observer.ObserverUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

public class FamedPanicAPI extends JavaPlugin {

    private static FamedPanicAPI core;
    private static List<Observer> observers;

    private static InstantiationManager instantiationManager;
    public static InstantiationManager getInstantiationManager() {
        return instantiationManager;
    }

    @Override
    public void onEnable() {
        core = this;

        ObserverUtil.activate(this, (observers = Arrays.asList(
                instantiationManager = new InstantiationManager()
        )));
    }

    private static Cache data;

    public static List<PanickedPlayer> getPanicking() {
        return PanicRegister.panicking;
    }

    public static boolean addPanicking(PanickedPlayer player) {
        return PanicRegister.panicking.add(player);
    }

    public static boolean removePanicking(PanickedPlayer player) {
        return PanicRegister.panicking.remove(player);
    }

    public static boolean getSafemode() {
        return data.safemode;
    }

    public static void setSafemode(boolean value) {
        data.safemode = value;
        Bukkit.getPluginManager().callEvent(new SafemodeChangedEvent(value));
    }

    private static class Cache {
        public boolean safemode;

        public Cache(boolean safemode) {
            this.safemode = safemode;
        }
    }
}
