package org.glockinmybape.tattyask.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.glockinmybape.tattyask.utils.TattyUtils;

public class OnJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        if (event.getPlayer().hasPermission(TattyUtils.getSetting("join-message-permission"))) {
            Player player = event.getPlayer();
            TattyUtils.sendJoinMessage(player);
        }

    }
}
