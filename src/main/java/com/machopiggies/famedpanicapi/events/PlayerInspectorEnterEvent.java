package com.machopiggies.famedpanicapi.events;

import com.machopiggies.famedpanicapi.misc.InspectorData;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

public class PlayerInspectorEnterEvent extends FPAEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final InspectorData data;

    private boolean cancelled;

    public PlayerInspectorEnterEvent(InspectorData data) {
        this.data = data;
    }

    public InspectorData getData() {
        return data;
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
