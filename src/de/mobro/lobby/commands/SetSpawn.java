package de.mobro.lobby.commands;

import de.mobro.lobby.utils.LocationUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawn implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
        Player player = (Player) cs;
        if (player.hasPermission("lobby.*")) {
            LocationUtil.saveLocation("Lobby.Spawn", player.getLocation());
            player.sendMessage("spawn.set");
        } else
            player.sendMessage("lobby.noperms");
        return false;
    }
}