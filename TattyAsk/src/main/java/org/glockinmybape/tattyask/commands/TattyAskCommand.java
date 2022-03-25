package org.glockinmybape.tattyask.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.glockinmybape.tattyask.TattyAsk;
import org.glockinmybape.tattyask.utils.TattyUtils;

public class TattyAskCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Команда доступна только для игроков!");
        }

        Player player = (Player)sender;
        if (!player.hasPermission(TattyUtils.getSetting("tatty-permission"))) {
            player.sendMessage(TattyUtils.getMessage("no-perms"));
            return true;
        } else if (args.length == 0) {
            player.sendMessage(TattyUtils.getMessage("tatty-usage"));
            return true;
        } else if (args[0].equalsIgnoreCase("reload")) {
            TattyUtils.instance.reloadConfig();
            TattyUtils.reloadConfig();
            player.sendMessage(TattyUtils.getMessage("plugin-reloaded"));
            return true;
        } else if (args[0].equalsIgnoreCase("help")) {
            TattyUtils.sendListMessage(player, "help-message");
            return true;
        } else if (args[0].equalsIgnoreCase("info")) {
            TattyUtils.sendInfo(player);
            return true;
        } else {
            return true;
        }
    }
}
