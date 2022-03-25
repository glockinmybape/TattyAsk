package org.glockinmybape.tattyask.commands;

import java.util.Iterator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.glockinmybape.tattyask.types.Question;
import org.glockinmybape.tattyask.utils.TattyUtils;
import org.glockinmybape.tattyask.utils.Storage;

public class AnswerCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Команда доступна только для игроков!");
        }

        Player player = (Player)sender;
        if (!player.hasPermission(TattyUtils.getSetting("answer-permission"))) {
            player.sendMessage(TattyUtils.getMessage("no-perms"));
            return true;
        } else {
            Iterator var11;
            Question question;
            if (args.length == 0) {
                if (Storage.questions.values().isEmpty()) {
                    player.sendMessage(TattyUtils.getMessage("no-open-questions"));
                    return true;
                } else {
                    player.sendMessage(TattyUtils.getMessage("questions-list"));
                    var11 = Storage.questions.values().iterator();

                    while(var11.hasNext()) {
                        question = (Question)var11.next();
                        if (!question.isAnswered()) {
                            player.sendMessage(TattyUtils.getMessage("questions-list-pattern", question.getAsker().getName(), question.getQuestion(), question.getId()));
                        }
                    }

                    return true;
                }
            } else if (args.length == 1 && args[0].equals("help")) {
                TattyUtils.sendListMessage(player, "help-message");
                return true;
            } else if (args[0].equalsIgnoreCase("list")) {
                if (Storage.questions.values().isEmpty()) {
                    player.sendMessage(TattyUtils.getMessage("no-open-questions"));
                    return true;
                } else {
                    player.sendMessage(TattyUtils.getMessage("questions-list"));
                    var11 = Storage.questions.values().iterator();

                    while(var11.hasNext()) {
                        question = (Question)var11.next();
                        if (!question.isAnswered()) {
                            player.sendMessage(TattyUtils.getMessage("questions-list-pattern", question.getAsker().getName(), question.getQuestion(), question.getId()));
                        }
                    }

                    return true;
                }
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
                } else if (((Question)Storage.questions.get(ID)).isAnswered()) {
                    return true;
                } else {
                    Player asker = ((Question)Storage.questions.get(ID)).getAsker();
                    StringBuilder answer = new StringBuilder();

                    for(int k = 1; k < args.length; ++k) {
                        answer.append(args[k]).append(" ");
                    }

                    asker.sendMessage(TattyUtils.getMessage("question-answered", player.getName(), answer.toString()));
                    TattyUtils.sendMessageToHelpers(TattyUtils.getMessage("question-answered-admins", player.getName(), ((Question)Storage.questions.get(ID)).getId(), answer.toString()));
                    Storage.requesters.remove(asker.getName());
                    ((Question)Storage.questions.get(ID)).setAnswered();
                    ((Question)Storage.questions.get(ID)).setAnswer(answer.toString());
                    --Storage.count;
                    if (Storage.lastID % TattyUtils.getIntSetting("questions-limit") == 0) {
                        Storage.questions.clear();
                    }

                    return true;
                }
            }
        }
    }
}
