# FamedPanicAPI

## Setup

---
#### Manual Setup

1. Download the latest release from here: https://github.com/MachoPiggies/FamedPanicAPI/blob/master/target/FamedPanicAPI-0.0.1.jar
2. Add the jar as a dependency in your project
3. Do not package or shade the API into your plugin as it will cause a version conflict (FamedPanic contains parts of FamedPanicAPI)
4. Add FamedPanicAPI as a depend or softdepend in your plugin.yml (ex. `depend: [FamedPanicAPI]` or `softdepend: [FamedPanicAPI]`)
5. API should be initialised when FamedPanic loads. You may need to enable debug mode to see this!

#### Maven Setup

1. Add the following repository to your `pom.xml`
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

2. Add the following dependency to your `pom.xml`
```xml
<dependencies>
    <dependency>
        <groupId>com.github.MachoPiggies</groupId>
        <artifactId>FamedPanicAPI</artifactId>
        <version>aae913cce6</version>
    </dependency>
</dependencies>
```

4. Add FamedPanicAPI as a depend or softdepend in your plugin.yml (ex. `depend: [FamedPanicAPI]` or `softdepend: [FamedPanicAPI]`)
5. API should be initialised when FamedPanic loads. You may need to enable debug mode to see this!

#### Gradle Setup

1. Add the following repository to your `pom.xml`
```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

2. Add the following dependency to your `pom.xml`
```groovy
dependencies {
    implementation 'com.github.MachoPiggies:FamedPanicAPI:Tag'
}
```

4. Add FamedPanicAPI as a depend or softdepend in your plugin.yml (ex. `depend: [FamedPanicAPI]` or `softdepend: [FamedPanicAPI]`)
5. API should be initialised when FamedPanic loads. You may need to enable debug mode to see this!

## Rubric

---

#### Methods

```java
public class FamedPanicAPI {
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
     * @return {@link java.util.List<com.machopiggies.famedpanicapi.misc.PanicData>} of panicking players
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
}
```

#### Events

```
MessageParsingEvent(String message, Map<String, String> placeholders)
PlayerPanicEvent(PanicData data)
PlayerUnpanicEvent(Player player, CommandSender causer)
PlayerInspectorEnterEvent(InspectorData data)
PlayerInspectorLeaveEvent(Player player, InspectorData data, int reason, int delay)
SafemodeChangedEvent(boolean newValue)
```