//
// THIS CLASS IS USED TO COMMUNICATE BETWEEN THE API AND THE FAMEDPANIC PLUGIN
// YOU SHOULD HAVE NO REASON TO BE TRYING TO ACCESS/LISTEN/MANIPULATE THIS CLASS
//
// All functionality of this class is built into the API
// Using this will cause issues with events and functions of the API, affecting other plugins hooked into it
// To discourage use, this class has been obfuscated and no documentation on it will be officially provided
//

package com.machopiggies.famedpanicapi.misc;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class Request extends Event {

    private static final HandlerList handlers = new HandlerList();

    private enum A {
        a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z;
    }

    public final A a;
    public final byte[] b;
    public byte[] c;

    public Request(A a, byte[] b) {
        this.a = a;
        this.b = b;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
