package com.machopiggies.famedpanicapi;

import com.google.gson.JsonObject;
import com.machopiggies.famedpanicapi.elements.InspectorRegister;
import com.machopiggies.famedpanicapi.elements.PanicRegister;
import com.machopiggies.famedpanicapi.misc.APISettings;
import com.machopiggies.famedpanicapi.misc.InspectorData;
import com.machopiggies.famedpanicapi.misc.PanicData;
import com.machopiggies.famedpanicapi.misc.Request;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.nio.charset.StandardCharsets;
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
        JsonObject loc = new JsonObject();
        loc.addProperty("a", data.location.getWorld().getUID().toString());
        loc.addProperty("b", true);
        loc.addProperty("c", data.location.getX());
        loc.addProperty("d", data.location.getY());
        loc.addProperty("e", data.location.getZ());
        loc.addProperty("f", data.location.getYaw());
        loc.addProperty("g", data.location.getPitch());

        JsonObject speed = new JsonObject();
        speed.addProperty("a", data.settings.speed);
        speed.addProperty("b", data.settings.flyspeed);
        speed.addProperty("d", data.settings.flying);
        speed.addProperty("c", data.settings.allowedFlying);

        JsonObject obj = new JsonObject();
        obj.addProperty("a", data.player.getUniqueId().toString());
        obj.add("b", loc);
        obj.add("c", speed);
        obj.addProperty("d", data.time);

        Bukkit.getPluginManager().callEvent(new Request(Request.A.b, obj.toString().getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * Removes a player from the panicking list, action sender is set to console
     *
     * @param player represents the Player being removed
     */
    public static void removePanicking(Player player) {
        JsonObject obj = new JsonObject();
        obj.addProperty("a", player.getUniqueId().toString());

        Bukkit.getPluginManager().callEvent(new Request(Request.A.b, obj.toString().getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * Removes a player from the panicking list with a given player
     *
     * @param player represents the Player being removed
     * @param staff represents the Player removing the panic, when null, will default to console
     */
    public static void removePanicking(Player player, Player staff) {
        JsonObject obj = new JsonObject();
        obj.addProperty("a", player.getUniqueId().toString());
        obj.addProperty("b", staff != null ? staff.getUniqueId().toString() : "null");

        Bukkit.getPluginManager().callEvent(new Request(Request.A.c, obj.toString().getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * Gets a list of all panicking players
     *
     * @return List<PanicData> of panicking players
     */
    public static List<InspectorData> getInspectors() {
        return InspectorRegister.inspectors;
    }

    /**
     * Makes a player inspect another
     *
     * @param player represents the Player being made an inspector
     * @param target represents the Player to inspect
     */
    public static void addInspector(Player player, Player target) {
        JsonObject obj = new JsonObject();
        obj.addProperty("a", player.getUniqueId().toString());

        JsonObject obj1 = new JsonObject();
        obj1.addProperty("a", player.getUniqueId().toString());
        obj1.addProperty("b", target.getUniqueId().toString());

        obj.add("b", obj1);

        Bukkit.getPluginManager().callEvent(new Request(Request.A.e, obj.toString().getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * Removes a player from inspector mode
     *
     * @param player represents the Player being removed from inspector mode
     * @param reason represents the {@link com.machopiggies.famedpanicapi.misc.InspectorData.RemoveReason} the player is being removed
     */
    public static void removeInspector(Player player, InspectorData.RemoveReason reason) {
        JsonObject obj = new JsonObject();
        obj.addProperty("a", player.getUniqueId().toString());
        obj.addProperty("b", reason.ordinal());

        Bukkit.getPluginManager().callEvent(new Request(Request.A.f, obj.toString().getBytes(StandardCharsets.UTF_8)));
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
        JsonObject obj = new JsonObject();
        obj.addProperty("a", value);

        Bukkit.getPluginManager().callEvent(new Request(Request.A.d, obj.toString().getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * Removes a player from inspector mode
     *
     * @deprecated This method is dangerous and should only be used as a last resort
     *
     * @param player represents the Player being removed from panic mode
     */
    public static void resetPanic(Player player) {
        JsonObject obj = new JsonObject();
        obj.addProperty("a", player.getUniqueId().toString());

        Bukkit.getPluginManager().callEvent(new Request(Request.A.g, obj.toString().getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * Removes a player from inspector mode
     *
     * @deprecated This method is dangerous and should only be used as a last resort
     *
     * @param player represents the Player being removed from inspector mode
     */
    public static void resetInspector(Player player) {
        JsonObject obj = new JsonObject();
        obj.addProperty("a", player.getUniqueId().toString());

        Bukkit.getPluginManager().callEvent(new Request(Request.A.h, obj.toString().getBytes(StandardCharsets.UTF_8)));
    }

    private static class Cache {
        public boolean safemode;
        public boolean debug;
        public Settings settings;
        public ActionPreferences prefs;
        public TitleSettings titleSettings;
        public AuthSecrets auth;
        public AuthPrefs authPrefs;
        public APISettings apiSettings;

        public Cache(boolean safemode, boolean debug, Settings settings, ActionPreferences prefs, TitleSettings titleSettings, AuthSecrets auth, AuthPrefs authPrefs, APISettings apiSettings) {
            this.safemode = safemode;
            this.debug = debug;
            this.settings = settings;
            this.prefs = prefs;
            this.titleSettings = titleSettings;
            this.auth = auth;
            this.authPrefs = authPrefs;
            this.apiSettings = apiSettings;
        }

        private static class Settings {
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

            public Settings(boolean bungee, boolean showTitle, boolean savePanicking,
                            long defaultCooldown, boolean allowStaffTeleport, GuiMenuSettings guis,
                            PanicInspectorSettings panicInspector) {
                this.bungee = bungee;
                this.showTitle = showTitle;
                this.savePanicking = savePanicking;
                this.defaultCooldown = defaultCooldown;
                this.allowStaffTeleport = allowStaffTeleport;
                this.guis = guis;
                this.panicInspector = panicInspector;
            }

            private static class PanicInspectorSettings {
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

            private static class GuiMenuSettings {
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

        private static class ActionPreferences {
            public boolean disableMovement;
            public boolean stopOpening;
            public boolean stopDropping;
            public boolean stopPickup;
            public boolean stopInventoryMoving;
            public boolean stopWorldInteraction;
            public boolean stopDamager;
            public boolean stopDamagee;
            public List<Command> stopCommands;

            public ActionPreferences(boolean disableMovement,
                                     boolean stopOpening, boolean stopDropping, boolean stopPickup,
                                     boolean stopInventoryMoving, boolean stopWorldInteraction,
                                     boolean stopDamager, boolean stopDamagee, List<Command> stopCommands) {
                this.disableMovement = disableMovement;
                this.stopOpening = stopOpening;
                this.stopDropping = stopDropping;
                this.stopPickup = stopPickup;
                this.stopInventoryMoving = stopInventoryMoving;
                this.stopWorldInteraction = stopWorldInteraction;
                this.stopDamager = stopDamager;
                this.stopDamagee = stopDamagee;
                this.stopCommands = stopCommands;
            }
        }

        private static class AuthSecrets {
            public String discord;
            public String slack;

            public AuthSecrets(String discord, String slack) {
                this.discord = discord;
                this.slack = slack;
            }
        }

        private static class AuthPrefs {
            public Discord discord;
            public Slack slack;

            public AuthPrefs(boolean discordEnabled, String discordWebhookURL, boolean useEmbed,
                             String embedAltEnter, String embedAltLeave, String color,

                             boolean slackEnabled, String slackWebhookURL, boolean useBlock,
                             String blockAltEnter, String blockAltLeave
            ) {
                discord = new Discord(discordEnabled, discordWebhookURL, useEmbed, embedAltEnter, embedAltLeave, color);
                slack = new Slack(slackEnabled, slackWebhookURL, useBlock, blockAltEnter, blockAltLeave);
            }

            public AuthPrefs(Discord discord, Slack slack) {
                this.discord = discord;
                this.slack = slack;
            }

            private static class Discord {
                public boolean enabled;
                public String webhookURL;
                public boolean useEmbed;
                public String embedAltEnter;
                public String embedAltLeave;
                public String color;

                public Discord(boolean enabled, String webhookURL, boolean useEmbed, String embedAltEnter, String embedAltLeave, String color) {
                    this.enabled = enabled;
                    this.webhookURL = webhookURL;
                    this.useEmbed = useEmbed;
                    this.embedAltEnter = embedAltEnter;
                    this.embedAltLeave = embedAltLeave;
                    this.color = color;
                }
            }

            private static class Slack {
                public boolean enabled;
                public String webhookURL;
                public boolean useBlock;
                public String blockAltEnter;
                public String blockAltLeave;

                public Slack(boolean enabled, String webhookURL, boolean useBlock, String blockAltEnter, String blockAltLeave) {
                    this.enabled = enabled;
                    this.webhookURL = webhookURL;
                    this.useBlock = useBlock;
                    this.blockAltEnter = blockAltEnter;
                    this.blockAltLeave = blockAltLeave;

                }
            }
        }

        private static class TitleSettings {
            public String title;
            public String subtitle;

            public TitleSettings(String title, String subtitle) {
                this.title = title;
                this.subtitle = subtitle;
            }
        }
    }
}
