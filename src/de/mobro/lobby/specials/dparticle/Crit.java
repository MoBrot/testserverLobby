package de.mobro.lobby.specials.dparticle;

import de.mobro.lobby.Main;
import de.mobro.lobby.utils.ParticleUtils;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;

public class Crit {

    private static BukkitTask scheduler;
    public static final HashMap<Player, Boolean> dropEffectCrits = new HashMap<>();

    public static void start() {
        scheduler = new BukkitRunnable() {

            @Override
            public void run() {
                dropEffectCrits.forEach((player, water) -> {
                    if (water) {
                        new ParticleUtils(EnumParticle.CRIT, player.getLocation().add(0, 1f, 0), true, 0.4f, 0.5f, 0.4f, 0, 14)
                                .sendAll();
                    }
                });
            }

        }.runTaskTimerAsynchronously(Main.getPlugin(Main.class), 7, 7);
    }

    public static void stop() {
        if(scheduler != null) scheduler.cancel();
    }
}
