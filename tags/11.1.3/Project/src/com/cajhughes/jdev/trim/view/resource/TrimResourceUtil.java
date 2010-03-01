package com.cajhughes.jdev.trim.view.resource;

import java.util.ResourceBundle;

public final class TrimResourceUtil {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("com.cajhughes.jdev.trim.Resource");

    public static String getString(final String key) {
        return bundle.getString(key);
    }
}
