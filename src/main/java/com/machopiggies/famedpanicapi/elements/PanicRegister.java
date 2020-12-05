package com.machopiggies.famedpanicapi.elements;

import com.machopiggies.famedpanicapi.misc.PanickedPlayer;

import java.util.ArrayList;
import java.util.List;

public class PanicRegister {

    public static List<PanickedPlayer> panicking;

    static {
        panicking = new ArrayList<>();
    }
}
