package de.mobro.lobby.listener;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import java.util.ArrayList;
import java.util.UUID;

public class CancelListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event){
        event.setCancelled(false);
    }

   @EventHandler
    public void onInteract(PlayerInteractEvent event) {
       if(event.getClickedBlock() == null) return;

       if(event.getClickedBlock() != null)
        event.setCancelled(true);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event){
        event.setCancelled(true);
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent event){event.setCancelled(true);}
    @EventHandler
    public void onPick(PlayerPickupItemEvent event){
        event.setCancelled(true);
    }
    @EventHandler
    public void onDrop(PlayerDropItemEvent event){ event.setCancelled(true); }
    @EventHandler
    public void onInv(InventoryClickEvent event){ event.setCancelled(true); }

    @EventHandler
    public void ondamage(EntityDamageEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onFood(FoodLevelChangeEvent event){
        event.setCancelled(true);
    }
    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event){
        event.setCancelled(true);
    }
    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event){
        event.setCancelled(true);
    }
    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onFall(PlayerItemDamageEvent event) {event.setCancelled(true);}


}
