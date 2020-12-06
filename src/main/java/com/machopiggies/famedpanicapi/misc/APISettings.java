package com.machopiggies.famedpanicapi.misc;

public class APISettings {
    public boolean enabled;
    public boolean canAccessTokens;
    public boolean canChangeSafemode;
    public boolean canChangePanicking;
    public boolean canChangeInspector;
    public boolean canChangeSettings;

    public APISettings(boolean enabled, boolean canAccessTokens, boolean canChangeSafemode, boolean canChangePanicking, boolean canChangeInspector, boolean canChangeSettings) {
        this.enabled = enabled;
        this.canAccessTokens = canAccessTokens;
        this.canChangeSafemode = canChangeSafemode;
        this.canChangePanicking = canChangePanicking;
        this.canChangeInspector = canChangeInspector;
        this.canChangeSettings = canChangeSettings;
    }
}
