package gg.filerunner.whitelist.profile.manager;

import gg.filerunner.whitelist.Whitelist;
import gg.filerunner.whitelist.profile.WhitelistProfile;

import lombok.RequiredArgsConstructor;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
public class ProfileManager {
    private final Whitelist plugin;
    private final Map<UUID, WhitelistProfile> profiles = new HashMap<>();

    public void createProfile(UUID id, String name) {
        WhitelistProfile profile = new WhitelistProfile(name, id);
        profiles.put(id, profile);
    }

    public WhitelistProfile getProfile(Player player) {
        return profiles.get(player.getUniqueId());
    }

    public void removeProfile(Player player) {
        profiles.remove(player.getUniqueId());
    }

    public void saveAllProfiles() {
        for (WhitelistProfile profile : profiles.values()) {
            profile.save(false);
        }
    }
}
