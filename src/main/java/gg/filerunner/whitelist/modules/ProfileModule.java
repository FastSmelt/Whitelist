package gg.filerunner.whitelist.modules;

import gg.filerunner.whitelist.Whitelist;
import gg.filerunner.whitelist.profile.WhitelistProfile;
import gg.filerunner.whitelist.util.CC;
import gg.filerunner.whitelist.util.module.Module;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ProfileModule extends Module {

    public ProfileModule() {
        super("ProfileModule", Whitelist.getInstance());

    }

    @EventHandler
    public void onPreLogin(AsyncPlayerPreLoginEvent event) {
        if (event.getLoginResult() == AsyncPlayerPreLoginEvent.Result.ALLOWED) {
            Whitelist.getInstance().getProfileManager().createProfile(event.getUniqueId(), event.getName());
        }

        for (OfflinePlayer player : Bukkit.getWhitelistedPlayers()) {
            if (player.hasPlayedBefore()) {
                if (player.getUniqueId().equals(event.getUniqueId())) return;
            } else {
                if (player.getName().equals(event.getName())) return;
            }
        }
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        WhitelistProfile profile = Whitelist.getInstance().getProfileManager().getProfile(player);

        if (profile == null) {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, CC.RED + "Your data failed to load for Whitelist. Try logging in again.");
        } else if (event.getResult() != PlayerLoginEvent.Result.ALLOWED) {
            Whitelist.getInstance().getProfileManager().removeProfile(player);
        }

        if (Bukkit.getServer().hasWhitelist()) {
            if (profile.getStatistics().getCredits() < 1) {
                event.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, "Credits: " + profile.getStatistics().getCredits());
            }

            return;
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        WhitelistProfile profile = Whitelist.getInstance().getProfileManager().getProfile(player);

        if (profile == null) {
            return;
        }

        profile.save(false);
        Whitelist.getInstance().getProfileManager().removeProfile(player);
    }
}
