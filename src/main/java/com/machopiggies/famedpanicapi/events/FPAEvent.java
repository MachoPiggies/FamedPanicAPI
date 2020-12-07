package com.machopiggies.famedpanicapi.events;

import org.bukkit.event.Event;

public abstract class FPAEvent extends Event {
    boolean isBefore;

    abstract boolean isBefore();
}
