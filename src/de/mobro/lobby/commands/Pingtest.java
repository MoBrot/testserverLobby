package de.mobro.lobby.commands;

import net.minecraft.server.v1_8_R3.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Pingtest implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {

        if (cs instanceof Player) {
            if (args.length == 0) {
                Player player = (Player) cs;
                player.sendMessage("§7[§dLobby§7] -" + " §aDu hast ein Ping von: §6" + getPing(player));
            }
            else if (args.length == 1) {
                if (Bukkit.getPlayer(args[0]) != null) {
                    cs.sendMessage(args[0] + "§7[§dLobby§7] -" + " §aDu hast ein Ping von: §6" + getPing(Bukkit.getPlayer(args[0])));
                } else {
                    cs.sendMessage("§7[§dLobby§7] -" + " §aDer Spieler ist nicht auf dem Server!");
                }
            }
        }

        return true;
    }

    public static Integer getPing(Player player) {
        CraftPlayer pc = (CraftPlayer) player;
        EntityPlayer ping = pc.getHandle();
        return ping.ping;

    }

}
