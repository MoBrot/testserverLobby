package de.mobro.lobby.listener;

import de.mobro.lobby.MySQL.MySQL;
import de.mobro.lobby.coinsystem.PlayerCoins;
import de.mobro.lobby.specials.LobbyItems;
import de.mobro.lobby.specials.PlayerHider;
import de.mobro.lobby.utils.InventoryUtil;
import de.mobro.lobby.utils.ItemBuilder;
import de.mobro.lobby.utils.LocationUtil;
import de.mobro.lobby.utils.ScoreBoardUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionListener implements Listener {

    @EventHandler
    public void onConnect(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        event.setJoinMessage(null);

        InventoryUtil.setMaininv(player);
        ScoreBoardUtil.MainScoreBoard(player);
        player.teleport(LocationUtil.getLocation("Lobby.Spawn"));
        player.setLevel(2021);
        player.setFoodLevel(20);
        player.setMaxHealth(2);
        player.setHealth(2);
        player.sendMessage("§7[§dLobby§7] -" + " §aWilkommen zurück auf dem Testserver");


        if(!player.hasPlayedBefore()) {
            try {
                PreparedStatement ps = MySQL.con.prepareStatement("INSERT INTO playermaincoins (coins,puuid) VALUES (?,?)");
                ps.setInt(1, 0);
                ps.setString(2, player.getUniqueId().toString());

                PreparedStatement psshop = MySQL.con.prepareStatement("INSERT INTO `pshopeditemsmain`(`puuid`, `rod`, `enderpearl`) VALUES (?,?,?)");
                psshop.setString(1, player.getUniqueId().toString());
                psshop.setBoolean(2, false);
                psshop.setBoolean(3, false);

                ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        if (PlayerHider.visible.contains(player)) {
            player.getInventory().setItem(4, new ItemBuilder(Material.MAGMA_CREAM, 1, 0).setName("§d§lSpieler versteckt").build());
            for (Player all : Bukkit.getOnlinePlayers()) {
                if(PlayerHider.visible.contains(all)){
                    all.hidePlayer(player);
                }
                player.hidePlayer(all);
            }
        } else {
            player.getInventory().setItem(4, new ItemBuilder(Material.SLIME_BALL, 1, 0).setName("§d§lSpieler angezeigt").build());
            for (Player all : Bukkit.getOnlinePlayers()) {
                if (!PlayerHider.visible.contains(all)){
                    all.showPlayer(player);
                }
                player.showPlayer(all);
            }

        }
    }
}
