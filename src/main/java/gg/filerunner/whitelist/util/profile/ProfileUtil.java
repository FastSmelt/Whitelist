package gg.filerunner.whitelist.util.profile;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class ProfileUtil {

    public static OfflinePlayer getOfflinePlayer(String name) {
        return Bukkit.getServer().getOfflinePlayer(name);
    }

    public static OfflinePlayer getOfflinePlayer(UUID uuid) {
        return Bukkit.getServer().getOfflinePlayer(uuid);
    }
}
