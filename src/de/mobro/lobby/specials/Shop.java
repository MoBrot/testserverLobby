package de.mobro.lobby.specials;

import de.mobro.lobby.MySQL.MySQL;
import de.mobro.lobby.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class Shop implements Listener {

    @EventHandler
    public void CHOSSERCLIKNEXTOPEN(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();
        if (event.getClickedInventory().getTitle().equals("ShopMainMenu")) {
            event.setCancelled(true);

            UUID uuid = event.getWhoClicked().getUniqueId();

            switch (event.getCurrentItem().getType()) {

                case FISHING_ROD:
                    Inventory buyablegadgets = Bukkit.createInventory(null, 9 * 4, "Gadgets");

                    // SELECT * FROM <Wo> WHERE <Bedingung>
                    ResultSet booleans = MySQL.getResult("SELECT * FROM `pshopeditemsmain` WHERE `puuid` = '" + uuid + "'");
                    boolean enderpearlbuyed = false;

                    try {
                        if (booleans.next()) {
                            enderpearlbuyed = booleans.getBoolean(3);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    buyablegadgets.setItem(9, new ItemStack(new ItemBuilder(Material.FISHING_ROD, 1, 0).setLore("  ").setLore(ChatColor.YELLOW + "× Dieses Gadgets ist gratis!").setLore("  ").setLore(ChatColor.GREEN + "× Klicken um dieses Gadget auszuwählen").setLore("  ").setName("§c").build()));

                    if (enderpearlbuyed || player.hasPermission("Operator")) {
                        buyablegadgets.setItem(10, new ItemStack(new ItemBuilder(Material.ENDER_PEARL, 1, 0).setLore("  ").setLore(ChatColor.YELLOW + "× Du hast dieses Gadget gekauft!").setLore("  ").setLore(ChatColor.GREEN + "× Klicken um dieses Gadget auszuwählen").setLore("  ").setName("§c").build()));

                    } else buyablegadgets.setItem(10, new ItemStack(new ItemBuilder(Material.ENDER_PEARL, 1, 0).setLore("  ").setLore(ChatColor.YELLOW + "× Dieses Gadget kostet 2000coins!").setLore("  ").setLore(ChatColor.GREEN + "× Zum kaufen Klicken").setLore("  ").setName("§c").build()));

                    player.closeInventory();
                    player.openInventory(buyablegadgets);
                    break;
                default:
                    break;

            }
        }
    }

    @EventHandler
    public void buy(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();
        if (event.getClickedInventory().getTitle().equals("Gadgets")) {
            event.setCancelled(true);

            ResultSet rs = MySQL.getResult("SELECT * FROM `pshopeditemsmain` WHERE '" + player.getUniqueId() + "'");
            boolean enderpearlbuyed = false;

            ResultSet booleans = MySQL.getResult("SELECT `puuid`, `rod`, `enderpearl` FROM `pshopeditemsmain` WHERE '" + player.getUniqueId() + "'");

            try {
                if (booleans.next()) {
                    enderpearlbuyed = booleans.getBoolean(3);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            int coins = 0;

            try {
                if (rs != null && rs.next()) {
                    coins = rs.getInt("coins");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            switch (event.getCurrentItem().getType()) {

                case ENDER_PEARL:
                    if (coins >= 2000 && !enderpearlbuyed) {
                        MySQL.update("UPDATE `pshopeditemsmain` SET `enderpearl`='1' WHERE `puuid` = '" + player.getUniqueId() + "'");
                        player.sendMessage("Cheese stinkt");
                    }

                    if(enderpearlbuyed){
                        Inventory inventory = player.getInventory();
                        inventory.setItem(2, new ItemBuilder(Material.ENDER_PEARL, 1, 0).setName("§d§lEnderPearl").build());
                        inventory.setItem(6, new ItemBuilder(Material.ENDER_PEARL, 1, 0).setName("§d§lEnderPearl").build());

                    }
                    break;
                default:
                    break;

            }
        }
    }
}
