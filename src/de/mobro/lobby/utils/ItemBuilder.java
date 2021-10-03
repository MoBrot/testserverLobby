package de.mobro.lobby.utils;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemBuilder {

    private Material material;
    private int amount;
    private int subid;
    private String name;
    private String skullOwner;

    private List<String> lores = new ArrayList<>();
    private HashMap<Enchantment, Integer> enchantments = new HashMap<>();



    public ItemBuilder(Material material, int amount, int subid){
        this.material = material;
        this.amount = amount;
        this.subid = subid;
    }
    public ItemBuilder setName(String name){
        this.name = name;
        return this;
    }
    public ItemBuilder setLore(String lore){
        this.lores.add(lore);
        return this;
    }
    public ItemBuilder setEnchantment(Enchantment enchantment, int level){
        this.enchantments.put(enchantment, level);
        return this;
    }
    public ItemBuilder setSkull(String skullOwner){
        this.subid = 3;
        this.material = Material.SKULL_ITEM;
        this.skullOwner = skullOwner;
        return  this;
    }
    public ItemStack build() {
        ItemStack itemStack = new ItemStack(this.material, this.amount, (byte)this.subid);
        if(this.material == Material.AIR){
            return itemStack;}
        ItemMeta itemMeta = itemStack.getItemMeta();
        if(this.name != null){
            itemMeta.setDisplayName(this.name);}
        if(material == Material.SKULL_ITEM && skullOwner != null){
            ((SkullMeta)itemMeta).setOwner(this.skullOwner);
        }
        if(enchantments.size() > 0){
            for(Enchantment enchantment : this.enchantments.keySet()){
                itemMeta.addEnchant(enchantment, this.enchantments.get(enchantment), true);
            }
        }
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.setLore(this.lores);
        itemMeta.spigot().setUnbreakable(true);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}