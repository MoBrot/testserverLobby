package de.mobro.lobby.utils;

import net.minecraft.server.v1_8_R3.PlayerInventory;
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

public class InventoryUtil implements Listener {

    private static final String WATCHNAME = "Navigator";

    public static void setMaininv(Player player) {
        Inventory inventory = player.getInventory();
        inventory.clear();
        inventory.setItem(1, new ItemBuilder(Material.WATCH, 1, 0).setName("§d§lNavigator").build());
        inventory.setItem(7, new ItemBuilder(Material.COMPASS, 1, 0).setName("§d§lDeine Freunde §7(§8Soon§7)").setSkull(player.getName()).build());
        inventory.setItem(3, new ItemBuilder(Material.NAME_TAG, 1, 0).setName("§d§lLobbyItems").build());
        inventory.setItem(4, new ItemBuilder(Material.SLIME_BALL, 1, 0).setName("§d§lVerstecke Spieler").build());
        inventory.setItem(5, new ItemBuilder(Material.TRIPWIRE_HOOK, 1, 0).setName("§d§lLobbys").build());
    }


    public void WATCHER(Player player) {
        Inventory inv = Bukkit.createInventory(null, 9 * 5, WATCHNAME);

        for (int z = 0; z < 45 ; z++) inv.setItem(z, new ItemStack(new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).setName(ChatColor.DARK_GRAY + "/-/").build()));

        inv.setItem(9 + 5, new ItemStack(new ItemBuilder(Material.ENDER_CHEST, 1, 0).setName("§6§lCoinsystem").build()));
        inv.setItem(27 + 4 - 9, new ItemStack(new ItemBuilder(Material.ENDER_PEARL, 1, 0).setName("§d§lSpawn").setLore("   ").setLore("× Telepotiere dich zurück zum Spawn").setLore("   ").build()));
        inv.setItem(9 + 5 - 2, new ItemStack(new ItemBuilder(Material.STICK, 1, 0).setName("§c§lMLGRush").setLore("  ").setLore("× Spiele MLGRush mit deinen Freunden").build()));
        inv.setItem(9 + 5 + 18, new ItemStack(new ItemBuilder(Material.BOW, 1, 0).setName("§8§lBuildFFA").setLore("  ").setLore("× Spiele BuildFFa mit deinen Freunden").setLore("  ").build()));
        inv.setItem(9 + 5 + 18 - 2, new ItemStack(new ItemBuilder(Material.STONE_SWORD, 1, 0).setName("§9§lKitPVP").setLore("  ").setLore("× Spiele KitPVP mit deinen Freunden").setLore("  ").build()));

        player.openInventory(inv);
    }


    @EventHandler
    public void WATCHOPENER(PlayerInteractEvent event) {
        if(event.getItem() == null) return;

        if (event.getItem().getType().equals(Material.WATCH) && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            WATCHER(event.getPlayer());
        }
    }

    @EventHandler
    public void WATCHGUICLICK(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();
        if (event.getClickedInventory().getTitle().equals(WATCHNAME)) {
            event.setCancelled(true);
            switch (event.getCurrentItem().getType()) {
                case ENDER_CHEST:
                    player.teleport(LocationUtil.getLocation("Lobby.chest"));
                    player.closeInventory();
                    break;
                case ENDER_PEARL:
                    player.teleport(LocationUtil.getLocation("Lobby.Spawn"));
                    break;
                case BOW:

                    PlayerSendServer.sendPlayerToServer(player, "bffatest");

                    break;
                default:
                    break;
            }
        }
    }
}