package com.naveen.base;

public class GridConfig {

    /**
     * Reads GRID_URL from JVM system property (-DGRID_URL=...).
     * NOTE: Uses System.getProperty(), NOT System.getenv().
     * -DGRID_URL on Maven CLI sets a JVM property — getenv() would silently return null.
     */
    public static boolean isRemote() {
        String gridUrl = System.getProperty("GRID_URL");
        return gridUrl != null && !gridUrl.isEmpty();
    }

    public static String getGridUrl() {
        return System.getProperty("GRID_URL");
    }
}