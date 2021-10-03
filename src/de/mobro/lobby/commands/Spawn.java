package de.mobro.lobby.commands;

import de.mobro.lobby.utils.LocationUtil;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender cs, Command c, String label, String[] strings) {
        if(cs instanceof Player) {
            Player player = (Player) cs;
            if(strings.length == 0){
                player.teleport(LocationUtil.getLocation("Lobby.Spawn"));
                player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 2F, 1F);
            }else{
                player.sendMessage("§7[§dLobby§7] -" + " §abenutzte hier für /spawn!");
            }
        }
        return false;
    }
}
