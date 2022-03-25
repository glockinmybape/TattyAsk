package org.glockinmybape.tattyask.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.glockinmybape.tattyask.types.Question;
import org.glockinmybape.tattyask.utils.TattyUtils;
import org.glockinmybape.tattyask.utils.Storage;

public class AskCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Команда доступна только для игроков!");
        }

        Player player = (Player)sender;
        if (args.length <= 1) {
            player.sendMessage(TattyUtils.getMessage("ask-usage"));
            return true;
        } else {
            StringBuilder question = new StringBuilder();
            String[] var7 = args;
            int var8 = args.length;

            for(int var9 = 0; var9 < var8; ++var9) {
                String arg = var7[var9];
                question.append(arg).append(" ");
            }

            if (Storage.count >= TattyUtils.getIntSetting("questions-limit")) {
                player.sendMessage(TattyUtils.getMessage("too-many-questions"));
                return true;
            } else if (!Storage.requesters.contains(player.getName())) {
                player.sendMessage(TattyUtils.getMessage("question-send"));
                Storage.questions.put(Storage.lastID, new Question(player, question.toString(), Storage.lastID));
                TattyUtils.sendMessageToHelpers(TattyUtils.getMessage("new-question", player.getName(), question.toString(), Storage.lastID));
                Storage.requesters.add(player.getName());
                ++Storage.count;
                ++Storage.lastID;
                return true;
            } else {
                player.sendMessage(TattyUtils.getMessage("already-have-question"));
                return true;
            }
        }
    }
}
