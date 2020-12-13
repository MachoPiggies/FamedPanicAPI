package com.machopiggies.famedpanicapi.events;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

public class PlayerUnpanicEvent extends FPAEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final CommandSender causer;

    private boolean cancelled;

    public PlayerUnpanicEvent(Player player, CommandSender causer) {
        this.player = player;
        this.causer = causer;
    }

    public Player getPlayer() {
        return player;
    }

    public CommandSender getCauser() {
        return causer;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    @Override
    boolean isBefore() {
        return isBefore;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
