package com.machopiggies.famedpanicapi.events;

import com.machopiggies.famedpanicapi.misc.InspectorData;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

public class PlayerInspectorLeaveEvent extends FPAEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final InspectorData data;
    private final InspectorData.RemoveReason reason;
    private int delay;

    private boolean cancelled;

    public PlayerInspectorLeaveEvent(Player player, InspectorData data, int reason, int delay) {
        this.player = player;
        this.data = data;
        this.reason = InspectorData.RemoveReason.values()[reason];
        this.delay = delay;
    }

    public Player getPlayer() {
        return player;
    }

    public InspectorData getData() {
        return data;
    }

    public InspectorData.RemoveReason getReason() {
        return reason;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
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
