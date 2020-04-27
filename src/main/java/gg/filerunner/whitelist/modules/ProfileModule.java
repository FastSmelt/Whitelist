package gg.filerunner.whitelist.modules;

import gg.filerunner.whitelist.Whitelist;
import gg.filerunner.whitelist.profile.WhitelistProfile;
import gg.filerunner.whitelist.util.CC;
import gg.filerunner.whitelist.util.module.Module;

import org.bukkit.Bukkit;
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

        System.out.println("ProfileModule loaded...");
    }

    @EventHandler
    public void onPreLogin(AsyncPlayerPreLoginEvent event) {
        if (event.getLoginResult() == AsyncPlayerPreLoginEvent.Result.ALLOWED) {
            Whitelist.getInstance().getProfileManager().createProfile(event.getUniqueId(), event.getName());
        }
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        WhitelistProfile profile = Whitelist.getInstance().getProfileManager().getProfile(player);

        if (profile == null) {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, CC.RED + "Your data failed to load for KitPvP. Try logging in again.");
        } else if (event.getResult() != PlayerLoginEvent.Result.ALLOWED) {
            Whitelist.getInstance().getProfileManager().removeProfile(player);
        }

        if (Bukkit.getServer().hasWhitelist()) {
            if (profile.getStatistics().getCredits() == 0) {
                event.setKickMessage("conifg...");
            } else {
                event.allow();
            }
            return;
        }
    }


    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        if (Bukkit.getServer().hasWhitelist()) {

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
