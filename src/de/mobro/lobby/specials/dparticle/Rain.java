package de.mobro.lobby.specials.dparticle;

import de.mobro.lobby.Main;
import de.mobro.lobby.utils.ParticleUtils;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;

public class Rain {
/*
    private boolean onoff;
    private Integer waterorlava;
    private Player p;

    public void Rain(boolean turnonoff, Player player, Integer waterxlava) {
        this.p = player;
        this.waterorlava = waterxlava;
        this.onoff = turnonoff;

        if (turnonoff == true && this.waterorlava == 1) {

            Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(Main.class), new Runnable() {
                @Override
                public void run() {
                    ParticleUtils particlesecond = new ParticleUtils(EnumParticle.CLOUD, player.getLocation().add(0, 2.4f, 0), true, 0.3f, 0f, 0.3f, 0, 5);
                    particlesecond.sendAll();
                    ParticleUtils particlethird = new ParticleUtils(EnumParticle.WATER_DROP, player.getLocation().add(0, 2.2f, 0), true, 0.28f, 0f, 0.28f, 0.001f, 13);
                    particlethird.sendAll();
                }
            }, 0, 2);
        }

        if (turnonoff == true && this.waterorlava == 2) {

            Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(Main.class), new Runnable() {
                @Override
                public void run() {
                    ParticleUtils particlesecond = new ParticleUtils(EnumParticle.CLOUD, player.getLocation().add(0, 2.4f, 0), true, 0.3f, 0f, 0.3f, 0, 5);
                    particlesecond.sendAll();
                    ParticleUtils particlethird = new ParticleUtils(EnumParticle.DRIP_LAVA, player.getLocation().add(0, 2.2f, 0), true, 0.28f, 0f, 0.28f, 0.001f, 13);
                    particlethird.sendAll();
                }
            }, 0, 2);
        }
    }

 */

    private static BukkitTask scheduler;
    public static final HashMap<Player, Boolean> dropEffect = new HashMap<>();

    public static void start() {
        scheduler = new BukkitRunnable() {

            @Override
            public void run() {
                dropEffect.forEach((player, water) -> {
                    if (water) {
                        new ParticleUtils(EnumParticle.CLOUD, player.getLocation().add(0, 2.4f, 0), true, 0.3f, 0f, 0.3f, 0, 5)
                                .sendAll();
                        new ParticleUtils(EnumParticle.WATER_DROP, player.getLocation().add(0, 2.2f, 0), true, 0.28f, 0f, 0.28f, 0.001f, 16)
                                .sendAll();
                    } else {
                        new ParticleUtils(EnumParticle.CLOUD, player.getLocation().add(0, 2.4f, 0), true, 0.45f, 0.15f, 0.45f, 0, 6)
                                .sendAll();
                        new ParticleUtils(EnumParticle.DRIP_LAVA, player.getLocation().add(0, 2.2f, 0), true, 0.28f, 0f, 0.28f, 0.001f, 13)
                                .sendAll();
                    }
                });
            }

        }.runTaskTimerAsynchronously(Main.getPlugin(Main.class), 2, 2);
    }

    public static void stop() {
        if(scheduler != null) scheduler.cancel();
    }

}

