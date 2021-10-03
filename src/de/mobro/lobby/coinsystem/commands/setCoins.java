package de.mobro.lobby.coinsystem.commands;

import de.mobro.lobby.Main;
import de.mobro.lobby.MySQL.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setCoins implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender cs, Command command, String s, String[] args) {

        if (cs instanceof Player){
            Player player = (Player) cs;
         if(player.hasPermission("Operator")){
            if(args.length == 1) {
                int addcoinsown = Integer.parseInt(args[0]);
                MySQL.update("UPDATE `playermaincoins` SET `coins`='"+addcoinsown+"'WHERE puuid = '"+player.getUniqueId().toString()+"'");
                player.sendMessage(Main.Prefix + "Du hast deine coins auf " + addcoinsown + " §agesezt!");
                player.getScoreboard().getTeam("coins").setPrefix("§7» §6" + addcoinsown);
                return false;
            }

            Player other = Bukkit.getPlayer(args[0]);

            if(args.length == 2){
                int addcoinother = Integer.parseInt(args[1]);
                MySQL.update("UPDATE `playermaincoins` SET `coins`='"+addcoinother+"'WHERE puuid = '"+other.getUniqueId().toString()+"'");
                return false;
                }
            }
        }
            return false;
    }
}
