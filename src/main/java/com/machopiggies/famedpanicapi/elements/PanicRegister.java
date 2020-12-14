package com.machopiggies.famedpanicapi.elements;

import com.machopiggies.famedpanicapi.misc.PanicData;

import java.util.ArrayList;
import java.util.List;

public class PanicRegister {

    public static List<PanicData> panicking;

    static {
        panicking = new ArrayList<>();
    }
}
