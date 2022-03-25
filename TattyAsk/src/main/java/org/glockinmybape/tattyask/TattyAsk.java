package org.glockinmybape.tattyask;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.glockinmybape.tattyask.commands.AnswerCommand;
import org.glockinmybape.tattyask.commands.AskCommand;
import org.glockinmybape.tattyask.commands.CommentCommand;
import org.glockinmybape.tattyask.commands.TattyAskCommand;
import org.glockinmybape.tattyask.listeners.OnJoinListener;

import java.util.logging.Logger;

public class TattyAsk extends JavaPlugin implements Listener {
    public static TattyAsk instance;
    private static final Logger log = Logger.getLogger("Minecraft");

    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();
        this.sendStartedInfo();
        this.getServer().getPluginManager().registerEvents(new OnJoinListener(), this);
        this.getServer().getPluginCommand("ask").setExecutor(new AskCommand());
        this.getServer().getPluginCommand("answer").setExecutor(new AnswerCommand());
        this.getServer().getPluginCommand("comment").setExecutor(new CommentCommand());
        this.getServer().getPluginCommand("tattyask").setExecutor(new TattyAskCommand());
    }

    private void sendStartedInfo() {
        log.info("§b");
        log.info("§b .----------------------------------------------------------. ");
        log.info("§b| .-------------------------------------------------------. |");
        log.info("§b| |             \t\t\t\t\t\t");
        log.info("§b| |            §7Плагин: §bTattyAsk§8| §7Версия: §b1.0                ");
        log.info("§b| |        §7Создан для §bTattyWorld §8- §7Разработал: §bglockinmybape\t");
        log.info("§b| |                    §bvk.com/TattyWorld");
        log.info("§b| |             \t\t\t\t\t\t");
        log.info("§b| '-------------------------------------------------------'§b|");
        log.info("§b'-----------------------------------------------------------'");
        log.info("§b");

    }
}
