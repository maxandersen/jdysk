///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS org.jline:jline-terminal:3.21.0 
//DEPS com.github.oshi:oshi-core-java11:6.8.0
//DEPS org.slf4j:slf4j-simple:1.7.21

// NOTE: OSHI dependencies are resolved at runtime by JBang. If you see import errors in your IDE, run the script with JBang to resolve them.

import java.util.Arrays;
import java.util.List;

import oshi.SystemInfo;
import oshi.software.os.OSFileStore;

public class Dysk {
    public static void main(String[] args) {
        SystemInfo si = new SystemInfo();
        List<OSFileStore> stores = si.getOperatingSystem().getFileSystem().getFileStores();
        System.out.printf("%-20s %-8s %-12s %-12s %-18s %-12s %-20s\n", "Filesystem", "Type", "Total", "Used", "Use", "Free", "Mount Point");
        for (OSFileStore store : stores) {
            if (filterVolume(store.getVolume())) {
                continue;
            }
            String name = capName(store.getVolume(), 20);
            String type = store.getType();
            long total = store.getTotalSpace();
            long free = store.getUsableSpace();
            long used = total - free;
            double percent = total > 0 ? (double) used / total : 0.0;
            String mount = store.getMount();
            String usageBar = visualBar(percent);
            System.out.printf("%-20s %-8s %-12s %-12s %-18s %-12s %-20s\n",
                name,
                type,
                humanReadable(total),
                humanReadable(used),
                usageBar,
                humanReadable(free),
                mount
            );
        }
    }

    private static String visualBar(double percent) {
        int width = 10;
        int usedBlocks = (int) Math.round(percent * width);
        int freeBlocks = width - usedBlocks;
        StringBuilder bar = new StringBuilder();
        bar.append("\u001B[31m"); // Red for used
        for (int i = 0; i < usedBlocks; i++) bar.append('█');
        bar.append("\u001B[32m"); // Green for free
        for (int i = 0; i < freeBlocks; i++) bar.append('█');
        bar.append("\u001B[0m"); // Reset
        bar.append(String.format(" %3.0f%%", percent * 100));
        return bar.toString();
    }

    private static String capName(String name, int maxLen) {
        if (name == null) return "";
        if (name.length() <= maxLen) return name;
        return name.substring(0, maxLen - 1) + "…";
    }

    private static String humanReadable(long bytes) {
        if (bytes < 0) return "-";
        String[] units = {"B", "K", "M", "G", "T", "P"};
        int unit = 0;
        double value = bytes;
        while (value >= 1024 && unit < units.length - 1) {
            value /= 1024;
            unit++;
        }
        return String.format("%.1f%s", value, units[unit]);
    }

    private final static String[] FILTERED_VOLUMES = {
            // Annoying timemachine volumes on macos
            "com.apple.TimeMachine."
    };
    /**
     * Return true for known annoying volume names
     * @param name - OSFileStore#getVolume
     * @return true if the volume name starts with a known filtered volume type
     */
    private static boolean filterVolume(String name) {
        return Arrays.stream(FILTERED_VOLUMES).anyMatch(name::startsWith);
    }
} 