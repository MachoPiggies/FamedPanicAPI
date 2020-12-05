package com.machopiggies.famedpanicapi;

import com.machopiggies.famedpanicapi.elements.PanicRegister;
import com.machopiggies.famedpanicapi.events.PanickingUpdateEvent;
import com.machopiggies.famedpanicapi.events.SafemodeChangedEvent;
import com.machopiggies.famedpanicapi.misc.PanicData;
import com.machopiggies.famedpanicapi.misc.PanickedPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
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
     * Adds a player to panicking list
     *
     * @param data represents the PlayerData being added
     */
    public static void addPanicking(PanicData data) {
        Bukkit.getPluginManager().callEvent(new PanickingUpdateEvent(true, data));
    }

    /**
     * Removes a player from the panicking list
     *
     * @param data represents the PlayerData being removed
     */
    public static void removePanicking(PanicData data) {
        Bukkit.getPluginManager().callEvent(new PanickingUpdateEvent(false, data));
    }

    /**
     * Removes a player from the panicking list
     *
     * @param player represents the Player being removed
     */
    public static void removePanicking(Player player) {
        Bukkit.getPluginManager().callEvent(new PanickingUpdateEvent(false, player));
    }

    /**
     * Gets safemode status
     *
     * @return boolean status of safemode
     */
    public static boolean getSafemode() {
        return data.safemode;
    }

    /**
     * Sets the safemode status
     *
     * @param value represents the new safemode value
     *
     */
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
