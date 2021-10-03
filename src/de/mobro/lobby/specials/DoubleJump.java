package de.mobro.lobby.specials;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

public class DoubleJump implements Listener {


    @EventHandler
    public void onFly(PlayerToggleFlightEvent event) {
        Player p = event.getPlayer();
        if(p.getGameMode() == GameMode.SURVIVAL) {
            event.setCancelled(true);
            p.setAllowFlight(false);
            p.setFlying(false);
            p.setVelocity(p.getLocation().getDirection().multiply(2).add(new Vector(0, 1.5, 0)));
            p.playSound(p.getLocation(), Sound.ENDERDRAGON_WINGS, 2F ,1F);
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player p = event.getPlayer();
        if (p.getGameMode() == GameMode.SURVIVAL) {
            if (p.getLocation().add(0, -1, 0).getBlock().getType() != Material.AIR) {
                p.setAllowFlight(true);
                p.setFlying(false);
            }
        }
    }


}
