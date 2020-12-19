package com.machopiggies.famedpanicapi.misc;

public class APISettings {
    public boolean enabled;
    public boolean canAccessTokens;
    public boolean canChangeSafemode;
    public boolean canChangePanicking;
    public boolean canChangeInspector;

    public APISettings(boolean enabled, boolean canAccessTokens, boolean canChangeSafemode, boolean canChangePanicking, boolean canChangeInspector) {
        this.enabled = enabled;
        this.canAccessTokens = canAccessTokens;
        this.canChangeSafemode = canChangeSafemode;
        this.canChangePanicking = canChangePanicking;
        this.canChangeInspector = canChangeInspector;
    }
}
