package de.mobro.lobby.specials;

import de.mobro.lobby.utils.ItemBuilder;
import de.mobro.lobby.utils.PlayerSendServer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class LobbyChange implements Listener {


    public void Lobbys(Player player) {
        Inventory inv = Bukkit.createInventory(null, 9 * 3, "Lobbys");

        for (int i = 0; i < 27; i++) {
            inv.setItem(i, new ItemStack(new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).setName(ChatColor.DARK_GRAY + "/-/").build()));
        }

        for(int o = 9; o < 18; o++){
            inv.setItem(o, new ItemStack(new ItemBuilder(Material.AIR, 1, 7).setName(ChatColor.DARK_GRAY + "/-/").build()));
        }

            inv.setItem(12, new ItemStack(new ItemBuilder(Material.INK_SACK, 1, 10).setName("§7Spieler Lobby").build()));
            inv.setItem(14, new ItemStack(new ItemBuilder(Material.INK_SACK, 1, 7).setName("§6Premium Lobby").build()));


        player.openInventory(inv);
    }


    @EventHandler
    public void ShowLobbys(PlayerInteractEvent event) {
        if(event.getItem() == null) return;

        if (event.getItem().getType().equals(Material.TRIPWIRE_HOOK) && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            Lobbys(event.getPlayer());
        }
    }

    @EventHandler
    public void Lobbychoose(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();
        if (event.getClickedInventory().getTitle().equals("Lobbys")) {
            event.setCancelled(true);
            switch (event.getCurrentItem().getItemMeta().getDisplayName()) {

                case "§7Spieler Lobby":
                    if(player.getServer().equals("lobby")){
                        player.sendMessage("Du befindest dich bereits auf diesem Server");
                    }else
                        PlayerSendServer.sendPlayerToServer(player, "lobby");
                    break;
                case "§6Premium Lobby":
                    if(player.hasPermission("rank.premium")){
                            PlayerSendServer.sendPlayerToServer(player, "Plobby");
                    }else
                        player.sendMessage("Du hast keine Rechte um auf diese Lobby zu conecten");
                    break;
                default:
                    break;
            }
        }
    }
}