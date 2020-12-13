package com.machopiggies.famedpanicapi.events;

import com.machopiggies.famedpanicapi.misc.PanicData;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

public class PlayerPanicEvent extends FPAEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final PanicData data;

    private boolean cancelled;

    public PlayerPanicEvent(PanicData data) {
        this.data = data;
    }

    public PanicData getData() {
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
