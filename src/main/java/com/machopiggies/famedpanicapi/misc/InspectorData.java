package com.machopiggies.famedpanicapi.misc;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.time.Instant;

public class InspectorData {
    public Player player;
    public Player target;
    public Location origin;
    public GameMode gamemode;
    public Long time;

    public InspectorData(Player player, Player target) {
        this.player = player;
        this.target = target;
        origin = player.getLocation();
        gamemode = player.getGameMode();
        time = Instant.now().getEpochSecond();
    }

    public enum RemoveReason {
        PANIC_CANCELLED,
        COMMAND,
        SERVER_CLOSE,
        QUIT,
        ERROR
    }
}
