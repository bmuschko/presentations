package de.muschko.core;

import org.apache.commons.lang.WordUtils;

public final class Helper {
    private Helper() {}

    public static String capitalize(String str) {
        return WordUtils.capitalize(str);
    }
}