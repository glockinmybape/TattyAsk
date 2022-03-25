package org.glockinmybape.tattyask.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.glockinmybape.tattyask.types.Question;
import org.glockinmybape.tattyask.utils.TattyUtils;
import org.glockinmybape.tattyask.utils.Storage;

public class CommentCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Команда доступна только для игроков!");
        }

        Player player = (Player)sender;
        if (!player.hasPermission(TattyUtils.getSetting("comment-permission"))) {
            player.sendMessage(TattyUtils.getMessage("no-perms"));
            return true;
        } else if (args.length < 2) {
            player.sendMessage(TattyUtils.getMessage("comment-usage"));
            return true;
        } else {
            int ID;
            try {
                ID = Integer.parseInt(args[0]);
            } catch (NumberFormatException var10) {
                player.sendMessage(TattyUtils.getMessage("no-valid-id"));
                return true;
            }

            if (!Storage.questions.containsKey(ID)) {
                player.sendMessage(TattyUtils.getMessage("question-not-found"));
                return true;
            } else {
                Player asker = ((Question)Storage.questions.get(ID)).getAsker();
                StringBuilder answer = new StringBuilder();

                for(int k = 1; k < args.length; ++k) {
                    answer.append(args[k]).append(" ");
                }

                asker.sendMessage(TattyUtils.getMessage("question-commented", player.getName(), answer.toString()));
                TattyUtils.sendMessageToHelpers(TattyUtils.getMessage("question-commented-admins", player.getName(), ((Question)Storage.questions.get(ID)).getId(), answer.toString()));
                return true;
            }
        }
    }
}
