package com.machopiggies.famedpanicapi.events;

import com.machopiggies.famedpanicapi.loader.InstantiationManager;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class IncomingPayloadEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private final InstantiationManager.DataPayload payload;

    public IncomingPayloadEvent(InstantiationManager.DataPayload payload) {
        this.payload = payload;
    }

    public InstantiationManager.DataPayload getPayload() {
        return payload;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
