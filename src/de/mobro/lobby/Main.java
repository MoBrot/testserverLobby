package de.mobro.lobby;

import de.mobro.lobby.MySQL.MySQL;
import de.mobro.lobby.coinsystem.SetCoinChest;
import de.mobro.lobby.coinsystem.commands.setCoins;
import de.mobro.lobby.commands.Gamemode;
import de.mobro.lobby.commands.Pingtest;
import de.mobro.lobby.commands.SetSpawn;
import de.mobro.lobby.commands.Spawn;
import de.mobro.lobby.listener.CancelListener;
import de.mobro.lobby.listener.ConnectionListener;
import de.mobro.lobby.coinsystem.CoinSystem;
import de.mobro.lobby.specials.*;
import de.mobro.lobby.specials.dparticle.Crit;
import de.mobro.lobby.specials.dparticle.EmeraldParticles;
import de.mobro.lobby.specials.dparticle.Rain;
import de.mobro.lobby.utils.InventoryUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    public static String Prefix = "§7[§dLobby§7] - §a";

    @Override
    public void onEnable(){
        System.out.println("--------------------------");
        System.out.println(" ");
        System.out.println("       Lobby enable");
        System.out.println(" ");
        System.out.println("--------------------------");
        Bukkit.getPluginManager().registerEvents(new ConnectionListener(),this);
        Bukkit.getPluginManager().registerEvents(new CancelListener(),this);
        Bukkit.getPluginManager().registerEvents(new InventoryUtil(),this);
        Bukkit.getPluginManager().registerEvents(new CoinSystem(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerHider(),this);
        Bukkit.getPluginManager().registerEvents(new LobbyItems(),this);
        Bukkit.getPluginManager().registerEvents(new LobbyChange(),this);
        Bukkit.getPluginManager().registerEvents(new Particles(), this);
        Bukkit.getPluginManager().registerEvents(new DoubleJump(), this);
        Bukkit.getPluginManager().registerEvents(new Shop(), this);

        Bukkit.getPluginCommand("lobbysetspawn").setExecutor(new SetSpawn());
        Bukkit.getPluginCommand("lobbysetchest").setExecutor(new SetCoinChest());
        Bukkit.getPluginCommand("spawn").setExecutor(new Spawn());
        Bukkit.getPluginCommand("ping").setExecutor(new Pingtest());
        Bukkit.getPluginCommand("gm").setExecutor(new Gamemode());
        Bukkit.getPluginCommand("setcoins").setExecutor(new setCoins());

        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        MySQL.connect();

        instance = this;

        Rain.start();
        EmeraldParticles.start();
        Crit.start();

    }

    @Override
    public void onDisable() {
        Rain.stop();
        EmeraldParticles.stop();
        Crit.stop();

        MySQL.close();

    }

    public static Main getInstance() {
        return instance;
    }

    public static String getPrefix() { return Prefix; }

}
