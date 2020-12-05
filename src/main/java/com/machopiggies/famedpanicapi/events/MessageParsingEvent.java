package com.machopiggies.famedpanicapi.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.Map;

public class MessageParsingEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private final String identifier;
    private String message;
    private final Map<String, String> placeholders;

    public MessageParsingEvent(String identifier, String message, Map<String, String> placeholders) {
        this.identifier = identifier;
        this.message = message;
        this.placeholders = placeholders;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getPlaceholders() {
        return placeholders;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
