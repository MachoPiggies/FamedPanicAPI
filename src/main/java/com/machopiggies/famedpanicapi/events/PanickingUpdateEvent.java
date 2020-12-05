package com.machopiggies.famedpanicapi.events;

import com.machopiggies.famedpanicapi.misc.PanicData;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PanickingUpdateEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private final boolean add;
    private final PanicData data;
    private final Player player;
    private final Player remover;

    public PanickingUpdateEvent(boolean add, PanicData data) {
        this.add = add;
        this.data = data;
        this.player = data.player;
        this.remover = null;
    }

    public PanickingUpdateEvent(boolean add, Player player) {
        this.add = add;
        this.data = null;
        this.player = player;
        this.remover = null;
    }

    public PanickingUpdateEvent(boolean add, PanicData data, Player remover) {
        this.add = add;
        this.data = data;
        this.player = data.player;
        this.remover = remover;
    }

    public PanickingUpdateEvent(boolean add, Player player, Player remover) {
        this.add = add;
        this.data = null;
        this.player = player;
        this.remover = remover;
    }

    public boolean isAdd() {
        return add;
    }

    public PanicData getData() {
        return data;
    }

    public Player getPlayer() {
        return player;
    }

    public Player getRemover() {
        return remover;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
