package com.machopiggies.famedpanicapi.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;

public class SafemodeChangedEvent extends FPAEvent {

    private static final HandlerList handlers = new HandlerList();
    private final boolean newValue;

    public SafemodeChangedEvent(boolean newValue) {
        this.newValue = newValue;
    }

    public boolean getNewValue() {
        return newValue;
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
}
