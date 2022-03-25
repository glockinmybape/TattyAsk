package org.glockinmybape.tattyask.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.glockinmybape.tattyask.TattyAsk;

import java.util.ArrayList;
import java.util.Iterator;
import org.glockinmybape.tattyask.TattyAsk;

public class TattyUtils {
    public static TattyUtils instance;
    private static FileConfiguration config;

    public static void reloadConfig() {
        config = TattyAsk.instance.getConfig();
    }

    private static String format(String string) {
        return ChatColor.translateAlternateColorCodes('&', string.replace("%prefix%", getSetting("plugin-prefix")));
    }

    public static String getSetting(String settingName) {
        return TattyAsk.instance.getConfig().getString("settings." + settingName);
    }

    public static int getIntSetting(String settingName) {
        return TattyAsk.instance.getConfig().getInt("settings." + settingName);
    }

    public static String getMessage(String messageName) {
        return format(TattyAsk.instance.getConfig().getString("messages." + messageName));
    }

    public static String getMessage(String messageName, String playerName, String question, int ID) {
        return format(TattyAsk.instance.getConfig().getString("messages." + messageName).replace("%player%", playerName).replace("%question%", question).replace("%id%", ID + ""));
    }

    public static String getMessage(String messageName, String adminName, String answer) {
        return format(TattyAsk.instance.getConfig().getString("messages." + messageName).replace("%admin%", adminName).replace("%answer%", answer));
    }

    public static String getMessage(String messageName, String adminName, int ID, String answer) {
        return format(TattyAsk.instance.getConfig().getString("messages." + messageName).replace("%admin%", adminName).replace("%answer%", answer).replace("%id%", ID + ""));
    }

    public static void sendListMessage(Player player, String messageName) {
        Iterator var2 = config.getStringList("messages." + messageName).iterator();

        while (var2.hasNext()) {
            String message = (String) var2.next();
            player.sendMessage(format(message));
        }

    }

    public static void sendJoinMessage(Player player) {
        Iterator var1 = config.getStringList("messages.on-moder-join").iterator();

        while (var1.hasNext()) {
            String message = (String) var1.next();
            player.sendMessage(format(message.replace("%player%", player.getName()).replace("%count%", Storage.count + "")));
        }

    }

    public static void sendInfo(Player player) {
        ArrayList<String> INFO_MESSAGE = new ArrayList();
        INFO_MESSAGE.add("§d⎛ ");
        INFO_MESSAGE.add("§d⎜ §b§lTattyAsk");
        INFO_MESSAGE.add("§d⎜ §7Студия: §fTattyInc");
        INFO_MESSAGE.add("§d⎝");
        Iterator var2 = INFO_MESSAGE.iterator();

        while (var2.hasNext()) {
            String message = (String) var2.next();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }

    }

    public static void sendMessageToHelpers(String message) {
        Iterator var1 = Bukkit.getOnlinePlayers().iterator();

        while (var1.hasNext()) {
            Player player = (Player) var1.next();
            if (player.hasPermission(getSetting("new-question-alert-permission"))) {
                player.sendMessage(format(message));
            }
        }

    }

    static {
        config = TattyAsk.instance.getConfig();
    }
}
