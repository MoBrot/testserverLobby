package de.mobro.lobby.specials;

import de.mobro.lobby.Main;
import de.mobro.lobby.specials.dparticle.Crit;
import de.mobro.lobby.specials.dparticle.EmeraldParticles;
import de.mobro.lobby.specials.dparticle.Rain;
import de.mobro.lobby.specials.spiral.ParticleData;
import de.mobro.lobby.specials.spiral.Styles.*;
import de.mobro.lobby.utils.ItemBuilder;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class LobbyItems implements Listener {


    public void CHOOSE(Player player) {
        Inventory inv = Bukkit.createInventory(null, 9 * 3, "× LobbyItems ×");

        for (int i = 0; i < 27; i++) {
            inv.setItem(i, new ItemStack(new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).setName(ChatColor.DARK_GRAY + "/-/").build()));
        }

        inv.setItem(11, new ItemStack(new ItemBuilder(Material.FIREWORK, 1, 0).setName(ChatColor.LIGHT_PURPLE + "Gadgets").build()));
        inv.setItem(13, new ItemStack(new ItemBuilder(Material.EMERALD, 1, 0).setLore(ChatColor.YELLOW + "× Kaufe dir neue Items").setName(ChatColor.GOLD + "§lCoin-shop").build()));
        inv.setItem(15, new ItemStack(new ItemBuilder(Material.BLAZE_POWDER, 1, 0).setName(ChatColor.LIGHT_PURPLE + "Autonicker").build()));

        player.openInventory(inv);
    }


    @EventHandler
    public void ITEMOPENERA(PlayerInteractEvent event) {
        if(event.getItem() == null) return;

        if (event.getItem().getType().equals(Material.NAME_TAG) && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            CHOOSE(event.getPlayer());
        }
    }

    @EventHandler
    public void CHOSSERCLIKNEXTOPEN(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();
        if (event.getClickedInventory().getTitle().equals("× LobbyItems ×")) {
            event.setCancelled(true);
            switch (event.getCurrentItem().getType()) {

                case FIREWORK:

                    Inventory inv = Bukkit.createInventory(null, 9 * 3, " ");

                    for (int i = 0; i < 27; i++) {
                        inv.setItem(i, new ItemStack(new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).setName(ChatColor.DARK_GRAY + "/-/").build()));
                    }

                    inv.setItem(11, new ItemStack(new ItemBuilder(Material.LEATHER_CHESTPLATE, 1, 0).setLore("  ").setLore(ChatColor.YELLOW + "× Nur für Premium+ spieler").setName("§dKleidung").build()));
                    inv.setItem(15, new ItemStack(new ItemBuilder(Material.RED_ROSE, 1, 0).setName("§dPartikel").build()));

                    player.openInventory(inv);
                    break;

                case EMERALD:

                    Inventory shop = Bukkit.createInventory(null, 9*5, "ShopMainMenu");

                    final int[] count = {0};
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if(player == null || !shop.getViewers().contains(player)) {
                                this.cancel();
                                return;
                            }

                            ItemStack armoricon =  new ItemBuilder(Material.LEATHER_HELMET, 1, 0).setName("§d§lKleidung").build();

                            LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) armoricon.getItemMeta();
                            leatherArmorMeta.setColor(Color.WHITE);
                            armoricon.setItemMeta(leatherArmorMeta);

                            shop.setItem(count[0], new ItemStack(new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).setName(ChatColor.DARK_GRAY + "/-/").build()));
                            if(count[0] == 22)shop.setItem(22, new ItemStack(new ItemBuilder(Material.FISHING_ROD, 1, 0).setName("§d§lGadgets").build()));
                            if(count[0] == 14)shop.setItem(14, new ItemStack(new ItemBuilder(Material.BLAZE_POWDER, 1, 0).setName("§d§lParticles").build()));
                            if(count[0] == 12)shop.setItem(12, armoricon);
                            if(count[0] == 12);

                            player.playSound(player.getLocation(), Sound.CLICK, 1, 1);
                            count[0]++;
                            if(count[0] >= shop.getSize()) this.cancel();
                        }
                    }.runTaskTimerAsynchronously(Main.getInstance(), 0, 1);

                    player.openInventory(shop);

                    break;
                case BLAZE_POWDER:
                    player.sendMessage("soon");
                    break;
                default:
                    break;
            }
        }
    }



    private Integer time = 5;

    Inventory inventory = Bukkit.createInventory(null, 9 * 5, "Particles");

    @EventHandler
    public void PARTIKEL(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();
        if (event.getClickedInventory().getTitle().equals(" ")) {
            event.setCancelled(true);

            switch (event.getCurrentItem().getType()) {

                case RED_ROSE:

                    if(player.hasPermission("Team")) {
                        for (int i = 0; i < 45; i++) {
                            inventory.setItem(i, new ItemStack(new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).setName(ChatColor.DARK_GRAY + "/-/").build()));
                        }

                        for (int i = 9; i < 36; i++) {
                            inventory.setItem(i, new ItemStack(new ItemBuilder(Material.AIR, 1, 0).setName(ChatColor.DARK_GRAY + "/-/").build()));
                        }

                        inventory.setItem(9, new ItemStack(new ItemBuilder(Material.NOTE_BLOCK, 1, 0).setName("§dNoten").build()));
                        inventory.setItem(10, new ItemStack(new ItemBuilder(Material.INK_SACK, 1, 13).setName("§dLiLa").build()));
                        inventory.setItem(11, new ItemStack(new ItemBuilder(Material.FIREBALL, 1, 0).setName("§cFire").build()));
                        inventory.setItem(12, new ItemStack(new ItemBuilder(Material.SNOW_BALL, 1, 0).setName("§f§lWhite").build()));
                        inventory.setItem(13, new ItemStack(new ItemBuilder(Material.WATER_BUCKET, 1, 0).setName("§bRegen §7(§7Wasser§7)").build()));
                        inventory.setItem(14, new ItemStack(new ItemBuilder(Material.LAVA_BUCKET, 1, 0).setName("§bRegen §7(§7Lava§7)").build()));
                        inventory.setItem(15, new ItemStack(new ItemBuilder(Material.FLINT_AND_STEEL, 1, 0).setName("§4§lLava").build()));
                        inventory.setItem(16, new ItemStack(new ItemBuilder(Material.SLIME_BALL, 1, 0).setName("§aSlime").build()));
                        inventory.setItem(17, new ItemStack(new ItemBuilder(Material.IRON_HOE, 1, 0).setName("§fSnowy").build()));
                        inventory.setItem(18, new ItemStack(new ItemBuilder(Material.EMERALD, 1, 0).setName("§aGreen").build()));
                        inventory.setItem(19, new ItemStack(new ItemBuilder(Material.WOOD_SWORD, 1, 0).setName("§7Crits").setEnchantment(Enchantment.DAMAGE_ALL, 5).build()));
                        inventory.setItem(35, new ItemStack(new ItemBuilder(Material.BEACON, 1, 0).setName("test").build()));

                        inventory.setItem(44, new ItemStack(new ItemBuilder(Material.INK_SACK, 1, 0).setName("§6Spiralen settings §7[§4§lOnly Admins§7]").build()));
                        inventory.setItem(40, new ItemStack(new ItemBuilder(Material.BARRIER, 1, 0).setName("§4Keine Partikel").build()));
                        player.openInventory(inventory);


                    }else {
                        player.sendMessage(Main.Prefix + "Du musst im Team sein für gratis Partikel!");
                        player.closeInventory();
                    }
                    break;

                case LEATHER_CHESTPLATE:
                    if (player.hasPermission("Prime")) {
                        Inventory AmorPre = Bukkit.createInventory(null, 9 * 3, "    ");

                        for (int i = 0; i < 27; i++) AmorPre.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).setName("§7/-/").build());

                        AmorPre.setItem(11, new ItemBuilder(Material.DIAMOND_CHESTPLATE, 1, 0).setName("§dBrustPlatten").build());
                        AmorPre.setItem(13, new ItemBuilder(Material.GOLD_LEGGINGS, 1, 0).setName("§dHosen").build());
                        AmorPre.setItem(15, new ItemBuilder(Material.DIAMOND_BOOTS, 1, 0).setName("§dBoots").build());
                        AmorPre.setItem(22, new ItemBuilder(Material.DIAMOND, 1, 0).setName("§dHeads").setSkull(null).build());

                        player.closeInventory();
                        player.openInventory(AmorPre);
                        break;
                    }else player.sendMessage(Main.Prefix + "Du benötigst mindestens den Premium rang."); player.closeInventory();
                default:
                    break;
            }
        }
    }

    @EventHandler
    public void PreAmor(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();
        UUID uuid = player.getUniqueId();
        if (event.getClickedInventory().getName().equals("    ")) {
            event.setCancelled(true);

            Inventory Gadjets = Bukkit.createInventory(null, InventoryType.BREWING, "Boots");
            Inventory PreHeadInvFriends = Bukkit.createInventory(null, InventoryType.HOPPER, "Wich Head would you Like?");

            switch (event.getCurrentItem().getType()) {

                case DIAMOND_BOOTS:
                    Gadjets.setItem(0, new ItemBuilder(Material.GOLD_BOOTS, 1, 0).setName("§6Spieler Schuhe").setEnchantment(Enchantment.LUCK, 1).build());                     Gadjets.setItem(1, new ItemBuilder(Material.DIAMOND_BOOTS, 1, 0).setName("§dIced OUT Boots").setEnchantment(Enchantment.LUCK, 1).build());                     Gadjets.setItem(2, new ItemBuilder(Material.LEATHER_BOOTS, 1, 0).setName("§4TeamBoots").setEnchantment(Enchantment.LUCK, 1).build());                      Gadjets.setItem(3, new ItemBuilder(Material.BARRIER, 1, 0).setName("§4Boots Deaktivieren").build());                      player.closeInventory();                     player.openInventory(Gadjets);                     break;                 default:                     break;

                case SKULL_ITEM:

                    PreHeadInvFriends.setItem(0, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 6).setName("§d/-/").setEnchantment(Enchantment.LUCK, 1).build());
                    PreHeadInvFriends.setItem(2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 6).setName("§d/-/").setEnchantment(Enchantment.LUCK, 1).build());
                    PreHeadInvFriends.setItem(4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 6).setName("§d/-/").setEnchantment(Enchantment.LUCK, 1).build());

                    PreHeadInvFriends.setItem(1, new ItemBuilder(Material.PUMPKIN, 1, 6).setName("§6Friend skulls").build());
                    PreHeadInvFriends.setItem(3, new ItemBuilder(Material.MELON_BLOCK, 1, 6).setName("§dAndere Heads").build());

                               player.openInventory(PreHeadInvFriends);                     break;
            }
        }
    }

    @EventHandler
    public void PumpkinInv(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();
        UUID uuid = player.getUniqueId();
        if (event.getClickedInventory().getName().equals("Wich Head would you Like?")) {
            event.setCancelled(true);

            Inventory HeadInvFriends = Bukkit.createInventory(null, InventoryType.BREWING, "Heads");
            Inventory RandomInv = Bukkit.createInventory(null, InventoryType.BREWING, "Heads ");

            switch (event.getCurrentItem().getType()) {

                case PUMPKIN:

                    HeadInvFriends.setItem(0, new ItemBuilder(Material.DIAMOND, 1, 0).setName("§6xSpw´s Head").setSkull("xSpw").build());                    HeadInvFriends.setItem(1, new ItemBuilder(Material.DIAMOND, 1, 0).setName("§6xSpw_´s Head").setSkull("xSpw_").build());                     HeadInvFriends.setItem(2, new ItemBuilder(Material.DIAMOND, 1, 0).setName("§6LordBusch´s Head").setSkull("LordBusch").build());
                    HeadInvFriends.setItem(3, new ItemBuilder(Material.BARRIER, 1, 0).setName("§4Kopf entfernen").build());
                    player.closeInventory();
                    player.openInventory(HeadInvFriends);
                    break;

                case MELON_BLOCK:

                    RandomInv.setItem(0, new ItemBuilder(Material.ICE, 1, 0).setName("§6Kühler Kopf").setLore(ChatColor.AQUA + "× Dieser Kopf ist nur für VIP´s").build());                    RandomInv.setItem(1, new ItemBuilder(Material.GLASS, 1, 15).setName("§fGlas Klare Sicht").build());                     RandomInv.setItem(2, new ItemBuilder(Material.MOB_SPAWNER, 1, 0).setName("§6Monster Kopf").setLore(ChatColor.DARK_RED + "× Dieser Kopf ist nur für Operatoren").build());
                    RandomInv.setItem(3, new ItemBuilder(Material.BARRIER, 1, 0).setName("§4Kopf entfernen").build());
                    player.closeInventory();
                    player.openInventory(RandomInv);
                    break;
            }
        }
    }


    @EventHandler
    public void PumpkinInvCLICK(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();
        UUID uuid = player.getUniqueId();
        if (event.getClickedInventory().getName().equals("Heads")) {
            event.setCancelled(true);

            ItemStack xSpw = new ItemStack(new ItemBuilder(Material.DIAMOND, 1, 0).setName("§6xSpw´s Head").setSkull("xSpw").build());
            ItemStack xSpw_ = new ItemStack(new ItemBuilder(Material.DIAMOND, 1, 0).setName("§6xSpw_´s Head").setSkull("xSpw_").build());
            ItemStack LordBusch = new ItemStack(new ItemBuilder(Material.DIAMOND, 1, 0).setName("§6LordBusch´s Head").setSkull("LordBusch").build());

            switch (event.getCurrentItem().getItemMeta().getDisplayName()) {
                case "§6xSpw´s Head":
                    player.getInventory().setHelmet(xSpw);
                    player.closeInventory();
                    break;
                case "§6xSpw_´s Head":
                    player.getInventory().setHelmet(xSpw_);
                    player.closeInventory();
                    break;
                case "§6LordBusch´s Head":
                    player.getInventory().setHelmet(LordBusch);
                    player.closeInventory();
                    break;
                case "§4Kopf entfernen":
                    player.getInventory().setHelmet(null);
                    player.closeInventory();
                    break;

            }
        }
    }


    @EventHandler
    public void MELONEinInClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();
        UUID uuid = player.getUniqueId();
        if (event.getClickedInventory().getName().equals("Heads ")) {
            event.setCancelled(true);

            ItemStack icehead = new ItemStack(new ItemBuilder(Material.ICE, 1, 0).setName("§bKühler Kopf").build());
            ItemStack spawnhead = new ItemStack(new ItemBuilder(Material.MOB_SPAWNER, 1, 0).setName("§6Monster Kopf").build());
            ItemStack glasshead = new ItemStack(new ItemBuilder(Material.GLASS, 1, 15).setName("§fGlas Klare Sicht").build());

            switch (event.getCurrentItem().getItemMeta().getDisplayName()) {

                case "§6Kühler Kopf":
                    if (player.hasPermission("VIP")) {
                        player.getInventory().setHelmet(icehead);
                        player.closeInventory();
                    }else player.sendMessage(Main.Prefix + "Du hast für diesen Kopf keine Rechte!"); player.closeInventory();
                    break;
                case "§6Monster Kopf":
                    if (player.hasPermission("Operator")) {
                        player.getInventory().setHelmet(spawnhead);
                        player.closeInventory();

                    }else player.sendMessage(Main.Prefix + "Du hast für diesen Kopf keine Rechte!"); player.closeInventory();
                    break;

                case "§4Kopf entfernen":
                    player.getInventory().setHelmet(null);
                    player.closeInventory();
                    break;

                case "§fGlas Klare Sicht":
                    player.getInventory().setHelmet(glasshead);
                    player.closeInventory();
                     break;
            }
        }
    }


    //
    //
    //Partikel
    //
//
    public static HashMap<UUID, EnumParticle> particlewhile = new HashMap<>();
    public static HashMap<UUID, EnumParticle> particlewhilesecond = new HashMap<>();
    public static HashMap<UUID, EnumParticle> particlewhilethird = new HashMap<>();

    public static HashMap<UUID, Integer> pamount = new HashMap<>();

    @EventHandler
    public void PARTICLECLICK(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();
        UUID uuid = player.getUniqueId();
        if (event.getClickedInventory().getName().equals("Particles")) {
            event.setCancelled(true);

            Inventory spiralsettings = Bukkit.createInventory(null, 9 * 3, "Spiral Settings");

            switch (event.getCurrentItem().getItemMeta().getDisplayName()) {

                case "§7Crits":
                    Rain.dropEffect.remove(player);
                    EmeraldParticles.dropEffectHappy.remove(player);
                    Crit.dropEffectCrits.put(player, true);
                    particlewhile.put(uuid, null);
                    break;
                case "§aGreen":
                    Rain.dropEffect.remove(player);
                    Crit.dropEffectCrits.remove(player);
                    EmeraldParticles.dropEffectHappy.put(player, true);
                    particlewhile.put(uuid, null);
                    break;
                case "test":
                    Rain.dropEffect.remove(player);
                    EmeraldParticles.dropEffectHappy.remove(player);
                    Crit.dropEffectCrits.remove(player);
                    particlewhile.put(uuid, EnumParticle.SNOW_SHOVEL);
                    pamount.put(uuid, 4);
                    particlewhilesecond.put(uuid, null);
                    particlewhilethird.put(uuid, null);
                    break;
                case "§6Spiralen settings §7[§4§lOnly Admins§7]":

                    for (int z = 0; z < 27 ; z++) spiralsettings.setItem(z, new ItemStack(new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).setName(ChatColor.DARK_GRAY + "/-/").build()));
                    for (int z2 = 9; z2 < 18 ; z2++) spiralsettings.setItem(z2, new ItemStack(new ItemBuilder(Material.AIR, 1, 7).setName(ChatColor.DARK_GRAY + "/-/").build()));

                    spiralsettings.setItem(9, new ItemStack(new ItemBuilder(Material.ICE, 1, 0).setName(ChatColor.AQUA + "Raini Spiral").build()));
                    spiralsettings.setItem(10, new ItemStack(new ItemBuilder(Material.REDSTONE, 1, 0).setName(ChatColor.RED + "Redstone Spiral").build()));
                    spiralsettings.setItem(11, new ItemStack(new ItemBuilder(Material.WOOD_SWORD, 1, 0).setName("§7Crit Spiral").setEnchantment(Enchantment.LUCK, 1).build()));
                    spiralsettings.setItem(12, new ItemStack(new ItemBuilder(Material.BLAZE_POWDER, 1, 0).setName(ChatColor.RED + "Redstone Spiral").build()));
                    spiralsettings.setItem(13, new ItemStack(new ItemBuilder(Material.EMERALD, 1, 0).setName("§aEmerald").build()));
                    spiralsettings.setItem(18, new ItemStack(new ItemBuilder(Material.ARROW, 1, 0).setName(ChatColor.DARK_GRAY + "Zu den Partikeln").build()));
                    spiralsettings.setItem(17, new ItemStack(new ItemBuilder(Material.BARRIER, 1, 0).setName(ChatColor.DARK_RED + "Spirale deaktivieren").build()));

                    player.closeInventory();
                    player.openInventory(spiralsettings);
                    break;

                case "§fSnowy":
                    Rain.dropEffect.remove(player);
                    EmeraldParticles.dropEffectHappy.remove(player);
                    Crit.dropEffectCrits.remove(player);
                    particlewhile.put(uuid, EnumParticle.SNOW_SHOVEL);
                    pamount.put(uuid, 4);
                    particlewhilesecond.put(uuid, null);
                    particlewhilethird.put(uuid, null);
                    break;
                case "§aSlime":
                    Rain.dropEffect.remove(player);
                    EmeraldParticles.dropEffectHappy.remove(player);
                    Crit.dropEffectCrits.remove(player);
                    particlewhile.put(uuid, EnumParticle.SLIME);
                    pamount.put(uuid, 2);
                    particlewhilesecond.put(uuid, null);
                    particlewhilethird.put(uuid, null);
                    break;
                case "§4§lLava":
                    Rain.dropEffect.remove(player);
                    EmeraldParticles.dropEffectHappy.remove(player);
                    Crit.dropEffectCrits.remove(player);
                    particlewhile.put(uuid, EnumParticle.LAVA);
                    pamount.put(uuid, 2);
                    particlewhilesecond.put(uuid, null);
                    particlewhilethird.put(uuid, null);
                    break;
                case "§bRegen §7(§7Wasser§7)":
                    /*Rain rainyw = new Rain();
                    rainyw.Rain(true, player, 1);*/
                    Rain.dropEffect.remove(player);
                    EmeraldParticles.dropEffectHappy.remove(player);
                    Crit.dropEffectCrits.remove(player);
                    Rain.dropEffect.put(player, true);
                    particlewhile.put(uuid, null);
                    break;
                case "§bRegen §7(§7Lava§7)":
                    /* Rain rainyl = new Rain();
                    rainyl.Rain(true, player, 2); */
                    Rain.dropEffect.remove(player);
                    EmeraldParticles.dropEffectHappy.remove(player);
                    Crit.dropEffectCrits.remove(player);
                    Rain.dropEffect.put(player, false);
                    particlewhile.put(uuid, null);
                    break;
                case "§f§lWhite":
                    Rain.dropEffect.remove(player);
                    EmeraldParticles.dropEffectHappy.remove(player);
                    Crit.dropEffectCrits.remove(player);
                    particlewhile.put(uuid, EnumParticle.FIREWORKS_SPARK);
                    pamount.put(uuid, 4);
                    particlewhilesecond.put(uuid, null);
                    particlewhilethird.put(uuid, null);
                    break;
                case "§cFire":
                    Rain.dropEffect.remove(player);
                    EmeraldParticles.dropEffectHappy.remove(player);
                    Crit.dropEffectCrits.remove(player);
                    particlewhile.put(uuid, EnumParticle.FLAME);
                    pamount.put(uuid, 1);
                    particlewhilesecond.put(uuid, null);
                    particlewhilethird.put(uuid, null);
                    break;
                case "§dLiLa":
                    Rain.dropEffect.remove(player);
                    EmeraldParticles.dropEffectHappy.remove(player);
                    Crit.dropEffectCrits.remove(player);
                    particlewhile.put(uuid, EnumParticle.SPELL_WITCH);
                    pamount.put(uuid, 4);
                    particlewhilesecond.put(uuid, null);
                    particlewhilethird.put(uuid, null);
                    break;
                case "§4Keine Partikel":
                    Rain.dropEffect.remove(player);
                    EmeraldParticles.dropEffectHappy.remove(player);
                    Crit.dropEffectCrits.remove(player);
                    particlewhile.put(uuid, null);
                    particlewhilesecond.put(uuid, null);
                    particlewhilethird.put(uuid, null);
                    break;
                case "§dNoten":
                    Rain.dropEffect.remove(player);
                    EmeraldParticles.dropEffectHappy.remove(player);
                    Crit.dropEffectCrits.remove(player);
                    particlewhile.put(uuid, EnumParticle.NOTE);
                    pamount.put(uuid, 2);
                    particlewhilesecond.put(uuid, null);
                    particlewhilethird.put(uuid, null);
                    break;
                default:
                    break;
            }


        }
    }

    @EventHandler
    public void SETTINGSCLICK(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();
        UUID uuid = player.getUniqueId();
        if (event.getClickedInventory().getName().equals("Spiral Settings")) {
            event.setCancelled(true);

            switch (event.getCurrentItem().getType()) {

                case REDSTONE:
                    ParticleData particleData = new ParticleData(player.getUniqueId());
                    if (particleData.hasID()) {
                        particleData.endTask();
                        particleData.removeID();
                    }
                    EffectsTypeRedstone spiraleffect = new EffectsTypeRedstone(player);
                    spiraleffect.startSpiral();

                    particlewhilesecond.put(uuid, null);
                    particlewhilethird.put(uuid, null);
                    particlewhile.put(uuid, null);
                    break;
                case ICE:
                    ParticleData particleDatawater = new ParticleData(player.getUniqueId());
                    if (particleDatawater.hasID()) {
                        particleDatawater.endTask();
                        particleDatawater.removeID();
                    }
                    EffectsTypeBubbles spiraleffectwater = new EffectsTypeBubbles(player);
                    spiraleffectwater.startSpiral();

                    particlewhilesecond.put(uuid, null);
                    particlewhilethird.put(uuid, null);
                    particlewhile.put(uuid, null);
                    break;
                case WOOD_SWORD:
                    ParticleData particleDatacrit = new ParticleData(player.getUniqueId());
                    if (particleDatacrit.hasID()) {
                        particleDatacrit.endTask();
                        particleDatacrit.removeID();
                    }
                    EffectsTypeCrits spiraleffectcrit = new EffectsTypeCrits(player);
                    spiraleffectcrit.startSpiral();

                    particlewhilesecond.put(uuid, null);
                    particlewhilethird.put(uuid, null);
                    particlewhile.put(uuid, null);
                    break;
                case BLAZE_POWDER:
                    ParticleData particleDataflame = new ParticleData(player.getUniqueId());
                    if (particleDataflame.hasID()) {
                        particleDataflame.endTask();
                        particleDataflame.removeID();
                    }
                    EffectsTypeFlames spiraleffectflame = new EffectsTypeFlames(player);
                    spiraleffectflame.startSpiral();

                    particlewhilesecond.put(uuid, null);
                    particlewhilethird.put(uuid, null);
                    particlewhile.put(uuid, null);
                    break;
                case EMERALD:
                    ParticleData Green = new ParticleData(player.getUniqueId());
                    if (Green.hasID()) {
                        Green.endTask();
                        Green.removeID();
                    }
                    EffectsTypeGreen green = new EffectsTypeGreen(player);
                    green.startSpiral();

                    particlewhilesecond.put(uuid, null);
                    particlewhilethird.put(uuid, null);
                    particlewhile.put(uuid, null);
                    break;
                case BARRIER:
                    ParticleData spicalbreak = new ParticleData(player.getUniqueId());
                    spicalbreak.endTask();
                    spicalbreak.removeID();
                    break;
                case ARROW:
                    player.closeInventory();
                    player.openInventory(inventory);
                break;
            }
        }
    }

    @EventHandler
    public void BLOCKINVINTERACT(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();
        if (event.getClickedInventory().getTitle().equals("Boots")) {
            event.setCancelled(true);

            ItemStack GoldenBoots = new ItemBuilder(Material.GOLD_BOOTS, 1, 0).setName("§6Gold Schuhe").setLore("   ").setLore(ChatColor.DARK_GRAY + "~" + ChatColor.DARK_RED + "Diese schuhen Lieben dich!" + ChatColor.DARK_GRAY + "~").setLore("   ").setEnchantment(Enchantment.LUCK, 1).build();
            ItemStack DiamondBoots = new ItemBuilder(Material.DIAMOND_BOOTS, 1, 0).setName("§bIced out Schuhe").setEnchantment(Enchantment.LUCK, 1).build();
            ItemStack LeatherBoots = new ItemBuilder(Material.LEATHER_BOOTS, 1, 0).setName("§dRainbow Schuhe").build();

            switch (event.getCurrentItem().getType()) {

                case GOLD_BOOTS:
                    player.getInventory().setBoots(GoldenBoots);
                    player.closeInventory();
                    break;
                case DIAMOND_BOOTS:
                    player.getInventory().setBoots(DiamondBoots);
                    player.closeInventory();
                    break;
                case LEATHER_BOOTS:
                    player.getInventory().setBoots(LeatherBoots);
                    player.closeInventory();
                    break;
                case BARRIER:
                    player.playSound(player.getLocation(), Sound.FIREWORK_BLAST, 1, 1);
                    player.closeInventory();
                    player.getInventory().setBoots(null);
                    break;
                default:
                    break;
            }


        }
    }

}