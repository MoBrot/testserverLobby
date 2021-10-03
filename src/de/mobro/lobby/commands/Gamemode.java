package de.mobro.lobby.commands;

import de.mobro.lobby.Main;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gamemode implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if(sender instanceof Player){
            Player player = (Player) sender;

         if(player.hasPermission("Operator")){
                 switch (strings[0].toLowerCase()){

                     case "1":
                         player.setGameMode(GameMode.CREATIVE);
                         player.sendMessage(Main.Prefix + "Dein gamemode wurde auf §cKreativ §agesetzt!");
                        break;
                     case "0":
                         player.setGameMode(GameMode.SURVIVAL);
                         player.sendMessage(Main.Prefix + "Dein gamemode wurde auf §csurvival §agesetzt!");
                         break;
                     case "3":
                         player.setGameMode(GameMode.SPECTATOR);
                         player.sendMessage(Main.Prefix + "Dein gamemode wurde auf §cKreativ §agesetzt!");
                         break;
                     default:
                         break;
                 }
             }
        }
        return false;
    }
}
