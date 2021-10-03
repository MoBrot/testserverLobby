package de.mobro.lobby.coinsystem;

import de.mobro.lobby.Main;
import de.mobro.lobby.MySQL.MySQL;
import de.mobro.lobby.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class CoinSystem implements Listener {

    public void PREINV(Player player) {
        Inventory inv = Bukkit.createInventory(null, 9, "Coins");

        inv.setItem(4, new ItemStack(new ItemBuilder(Material.GOLD_INGOT, 1, 0).setName("§7 ~ §6Los! §7 ~    §7[§41.500Coins§7]").build()));

        inv.setItem(0, new ItemStack(new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).setName("§7/-/").build()));
        inv.setItem(1, new ItemStack(new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).setName("§7/-/").build()));
        inv.setItem(2, new ItemStack(new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).setName("§7/-/").build()));
        inv.setItem(3, new ItemStack(new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).setName("§7/-/").build()));

        inv.setItem(5, new ItemStack(new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).setName("§7/-/").build()));
        inv.setItem(6, new ItemStack(new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).setName("§7/-/").build()));
        inv.setItem(7, new ItemStack(new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).setName("§7/-/").build()));
        inv.setItem(8, new ItemStack(new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).setName("§7/-/").build()));

        player.openInventory(inv);
    }


    @EventHandler
    private void ChestInteract(PlayerInteractEvent event) {
        if(event.getClickedBlock() == null) return;

        if (event.getClickedBlock().getType().equals(Material.ENDER_CHEST)) {
            PREINV(event.getPlayer());
        }
    }

    @EventHandler
    public void PREINVGUICLICK(InventoryClickEvent event) {
        UUID uuid = event.getWhoClicked().getUniqueId();


        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();
        if (event.getClickedInventory().getTitle().equals("Coins")) {
            event.setCancelled(true);

            ResultSet rs = MySQL.getResult("SELECT * FROM `playermaincoins` WHERE '"+uuid+"'");

            int coins = 0;

            try {
                if (rs != null && rs.next()) {
                    coins = rs.getInt("coins");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            switch (event.getCurrentItem().getType()) {
                case GOLD_INGOT:
                    if (coins >= 1500) {

                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 2F, 1F);

                        MySQL.update("UPDATE `playermaincoins` SET `coins` = '" + (coins - 1500) + "' WHERE `puuid` = '"+player.getUniqueId().toString()+"'");

                        Inventory inventory = Bukkit.createInventory(null, 9 * 6, "Win - Win");

                        for (int i = 0; i < 54; i++) {
                            inventory.setItem(i, new ItemStack(new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).setName(ChatColor.DARK_GRAY + "/-/").build()));
                        }

                        inventory.setItem(0, new ItemStack(new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 8).setName(ChatColor.GRAY + "/-/").build()));
                        inventory.setItem(9 - 1, new ItemStack(new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 8).setName(ChatColor.GRAY + "/-/").build()));
                        inventory.setItem(44 + 1, new ItemStack(new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 8).setName(ChatColor.GRAY + "/-/").build()));
                        inventory.setItem(53, new ItemStack(new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 8).setName(ChatColor.GRAY + "/-/").build()));

                        for (int i = 10; i < 17; i++) {
                            inventory.setItem(i, new ItemStack(new ItemBuilder(Material.GOLD_INGOT, 1, 0).setName("§7§k§lccc" + ChatColor.GOLD + "  §l???  " + "§7§k§lccc").build()));
                        }
                        for (int i = 19; i < 26; i++) {
                            inventory.setItem(i, new ItemStack(new ItemBuilder(Material.GOLD_INGOT, 1, 0).setName("§7§k§lccc" + ChatColor.GOLD  + "  §l???  " + "§7§k§lccc").build()));
                        }
                        for (int i = 28; i < 35; i++) {
                            inventory.setItem(i, new ItemStack(new ItemBuilder(Material.GOLD_INGOT, 1, 0).setName("§7§k§lccc" + ChatColor.GOLD + "  §l???  " + "§7§k§lccc").build()));
                        }
                        for (int i = 37; i < 44; i++) {
                            inventory.setItem(i, new ItemStack(new ItemBuilder(Material.GOLD_INGOT, 1, 0).setName("§7§k§lccc" + ChatColor.GOLD + "  §l???  " + "§7§k§lccc").build()));
                        }

                        player.openInventory(inventory);

                        } else {
                        player.sendMessage("§7[§dLobby§7] -" + " §aDu benötigst dafür 1.500 coins!");
                        player.closeInventory();
                    }
                    break;
            }
        }
    }


    @EventHandler
    public void MAININV(InventoryClickEvent event) throws SQLException {
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();
        if (event.getClickedInventory().getTitle().equals("Win - Win")) {
            event.setCancelled(true);
            int s = 0;
            int giftet = 0;
                if (event.getCurrentItem().getType() == Material.GOLD_INGOT) {

                    Random r = new Random();

                    giftet = r.nextInt(100) + 1;

                    if (giftet <= 86) giftet = r.nextInt(750) + 200;player.playSound(player.getLocation(), Sound.FIREWORK_LAUNCH, 2F, 1F);
                    if (giftet <= 99) giftet = r.nextInt(1555) + 1000;player.playSound(player.getLocation(), Sound.FIREWORK_LAUNCH, 2F, 1F);
                    if (giftet < 99) giftet = 10000;player.playSound(player.getLocation(), Sound.FIREWORK_LAUNCH, 2F, 1F);

                    player.sendMessage(Main.Prefix + "Du hast " + giftet + " §agewonnen!");
                    ResultSet select = MySQL.getResult("SELECT `coins`, `puuid` FROM `playermaincoins` WHERE puuid = '"+player.getUniqueId().toString()+"'");
                    try {
                        while(select.next()) {
                            s = select.getInt("coins");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    int t = s + giftet;

                    MySQL.update("UPDATE `playermaincoins` SET `coins`='"+ t +"'WHERE puuid = '"+player.getUniqueId().toString()+"'");
                    player.getScoreboard().getTeam("coins").setPrefix("§7» §6" + t);

                    player.closeInventory();

            }
        }
    }
}