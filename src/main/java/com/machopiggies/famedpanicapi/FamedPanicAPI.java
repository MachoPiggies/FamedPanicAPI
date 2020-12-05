package com.machopiggies.famedpanicapi;

import com.machopiggies.famedpanicapi.elements.PanicRegister;
import com.machopiggies.famedpanicapi.misc.PanickedPlayer;

import java.util.List;

public class FamedPanicAPI {

    public List<PanickedPlayer> getPanicking() {
        return PanicRegister.panicking;
    }

    public boolean addPanicking(PanickedPlayer player) {
        return PanicRegister.panicking.add(player);
    }

    public boolean removePanicking(PanickedPlayer player) {
        return PanicRegister.panicking.remove(player);
    }



    public void test() {

    }
}
