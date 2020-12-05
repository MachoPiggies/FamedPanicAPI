package com.machopiggies.famedpanicapi;

import com.machopiggies.famedpanicapi.elements.PanicRegister;
import com.machopiggies.famedpanicapi.events.SafemodeChangedEvent;
import com.machopiggies.famedpanicapi.misc.PanicData;
import com.machopiggies.famedpanicapi.misc.PanickedPlayer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

public class FamedPanicAPI extends JavaPlugin {

    /**
     * Instantiates the API with the information it needs for the base methods. This should not be being accessed by you
     *
     * @param data represents the incoming data payload from the FamedPanic plugin
     *
     * @return the success status of the payload
     */
    private static boolean payload(Cache data) {
        try {
            FamedPanicAPI.data = data;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private static Cache data;

    /**
     * Gets a list of all panicking players
     *
     * @return List<PanicData> of panicking players
     */
    public static List<PanicData> getPanicking() {
        return PanicRegister.panicking;
    }

    /**
     * Adds player to panicking list
     *
     * @param data represents the PlayerData being added
     *
     * @return true if addition successful
     */
    public static boolean addPanicking(PanicData data) {
        return PanicRegister.panicking.add(data);
    }

    public static boolean removePanicking(PanicData data) {
        return PanicRegister.panicking.remove(data);
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
