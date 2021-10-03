package de.mobro.lobby.specials;

import de.mobro.lobby.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import java.util.ArrayList;

public class PlayerHider implements Listener {

    public static ArrayList<Player> visible = new ArrayList<>();

    @EventHandler
    public void Hider(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(player.getItemInHand() == null) return;

        if (player.getItemInHand().getType().equals(Material.SLIME_BALL) && event.getAction().equals(Action.RIGHT_CLICK_AIR) || player.getItemInHand().getType().equals(Material.SLIME_BALL) && event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            for (Player p : Bukkit.getOnlinePlayers()){
                visible.add(p);
                player.hidePlayer(p);
                player.getInventory().setItem(4, new ItemBuilder(Material.MAGMA_CREAM, 1 , 0).setName("§d§lSpieler versteckt").build());
            }
        } else if (player.getItemInHand().getType().equals(Material.MAGMA_CREAM) && event.getAction().equals(Action.RIGHT_CLICK_AIR) || player.getItemInHand().getType().equals(Material.MAGMA_CREAM) && event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                visible.remove(p);
                player.showPlayer(p);
                player.getInventory().setItem(4, new ItemBuilder(Material.SLIME_BALL, 1 , 0).setName("§d§lSpieler angezeigt").build());
            }
        }
    }
}