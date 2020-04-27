package gg.filerunner.whitelist.modules.commands;

import gg.filerunner.whitelist.Whitelist;
import gg.filerunner.whitelist.profile.WhitelistProfile;
import gg.filerunner.whitelist.util.CC;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TokensCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        Player player = (Player) sender;
        WhitelistProfile profile = Whitelist.getInstance().getProfileManager().getProfile(player);

        if (args.length == 0) {
            player.sendMessage(new String[]{
                    "",
                    CC.RED + "/tokens check (personal credits)",
                    ""
            });
        }

        if (args[0].equalsIgnoreCase("check")) {
            player.sendMessage(CC.PINK + "Credits: " + profile.getStatistics().getCredits());
        } else if (args[0].equalsIgnoreCase("add")) {

        }

        return false;
    }
}
