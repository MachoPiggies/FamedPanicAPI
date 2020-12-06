package com.machopiggies.famedpanicapi;

import com.machopiggies.famedpanicapi.elements.PanicRegister;
import com.machopiggies.famedpanicapi.events.PanickingUpdateEvent;
import com.machopiggies.famedpanicapi.events.SafemodeChangedEvent;
import com.machopiggies.famedpanicapi.misc.APISettings;
import com.machopiggies.famedpanicapi.misc.PanicData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class FamedPanicAPI extends JavaPlugin {

    private static boolean a(Cache a) {
        try {
            FamedPanicAPI.a = a;
        } catch (Exception b) {
            return false;
        }
        return true;
    }

    private static Cache a;

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
        return a.safemode;
    }

    /**
     * Sets the safemode status
     *
     * @param value represents the new safemode value
     *
     */
    public static void setSafemode(boolean value) {
        a.safemode = value;
        Bukkit.getPluginManager().callEvent(new SafemodeChangedEvent(value));
    }

    private static class Cache {
        public boolean safemode;
        public boolean debug;
        public Settings settings;
        public ActionPreferences prefs;
        public TitleSettings titleSettings;
        public APISettings apiSettings;

        public Cache(boolean safemode, boolean debug, Settings settings, ActionPreferences prefs, TitleSettings titleSettings, APISettings apiSettings) {
            this.safemode = safemode;
            this.debug = debug;
            this.settings = settings;
            this.prefs = prefs;
            this.titleSettings = titleSettings;
            this.apiSettings = apiSettings;
        }

        public static class Settings {
            public boolean bungee;
            public boolean showTitle;
            public boolean savePanicking;
            public long defaultCooldown;
            public boolean allowStaffTeleport;
            public GuiMenuSettings guis;
            public PanicInspectorSettings panicInspector;

            public Settings(boolean bungee, boolean showTitle, boolean savePanicking,
                            long defaultCooldown, boolean allowStaffTeleport,

                            boolean useGuis, boolean useBorder, String borderColor,
                            ChatColor titleColor, ChatColor defaultColor,

                            boolean usePanicInspector, String vanishCmd, String unvanishCmd,
                            int kickDelay, boolean inspectorAlert) {
                this.bungee = bungee;
                this.showTitle = showTitle;
                this.savePanicking = savePanicking;
                this.defaultCooldown = defaultCooldown;
                this.allowStaffTeleport = allowStaffTeleport;
                guis = new GuiMenuSettings(
                        useGuis,
                        useBorder,
                        borderColor,
                        titleColor,
                        defaultColor
                );
                panicInspector = new PanicInspectorSettings(
                        usePanicInspector,
                        vanishCmd,
                        unvanishCmd,
                        kickDelay,
                        inspectorAlert
                );
            }

            public static class PanicInspectorSettings {
                public boolean enabled;
                public String vanishCmd;
                public String unvanishCmd;
                public int kickDelay;
                public boolean inspectorAlert;

                public PanicInspectorSettings(boolean enabled, String vanishCmd, String unvanishCmd, int kickDelay, boolean inspectorAlert) {
                    this.enabled = enabled;
                    this.vanishCmd = vanishCmd;
                    this.unvanishCmd = unvanishCmd;
                    this.kickDelay = kickDelay;
                    this.inspectorAlert = inspectorAlert;
                }
            }

            public static class GuiMenuSettings {
                public boolean enabled;
                public boolean useBorder;
                public String borderColor;
                public ChatColor titleColor;
                public ChatColor defaultColor;

                public GuiMenuSettings(boolean enabled, boolean useBorder, String borderColor, ChatColor titleColor, ChatColor defaultColor) {
                    this.enabled = enabled;
                    this.useBorder = useBorder;
                    this.borderColor = borderColor;
                    this.titleColor = titleColor;
                    this.defaultColor = defaultColor;
                }
            }
        }

        public static class ActionPreferences {
            public boolean disableMovement;
            public boolean stopOpening;
            public boolean stopDropping;
            public boolean stopPickup;
            public boolean stopInventoryMoving;
            public boolean stopWorldInteraction;
            public boolean stopDamager;
            public boolean stopDamagee;
            public List<Command> stopCommands;
            public boolean stopChat;

            public ActionPreferences(boolean disableMovement,
                                     boolean stopOpening, boolean stopDropping, boolean stopPickup,
                                     boolean stopInventoryMoving, boolean stopWorldInteraction,
                                     boolean stopDamager, boolean stopDamagee, List<Command> stopCommands,
                                     boolean stopChat) {
                this.disableMovement = disableMovement;
                this.stopOpening = stopOpening;
                this.stopDropping = stopDropping;
                this.stopPickup = stopPickup;
                this.stopInventoryMoving = stopInventoryMoving;
                this.stopWorldInteraction = stopWorldInteraction;
                this.stopDamager = stopDamager;
                this.stopDamagee = stopDamagee;
                this.stopCommands = stopCommands;
                this.stopChat = stopChat;
            }
        }

        public static class TitleSettings {
            public String title;
            public String subtitle;

            public TitleSettings(String title, String subtitle) {
                this.title = title;
                this.subtitle = subtitle;
            }
        }
    }
}
