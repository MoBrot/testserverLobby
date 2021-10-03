package de.mobro.lobby.specials.spiral.Styles;

import de.mobro.lobby.Main;
import de.mobro.lobby.specials.spiral.ParticleData;
import de.mobro.lobby.utils.ParticleUtils;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class EffectsTypeFlames{

    private int taskID;
    private final Player player;

    public EffectsTypeFlames(Player player){
        this.player = player;
    }

    public void startSpiral(){

        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(Main.class), new Runnable() {

            double var = 0;
            Location location, first, second;
            ParticleData particleData = new ParticleData(player.getUniqueId());

            @Override
            public void run() {
                if (!(particleData.hasID())) {
                    particleData.setID(taskID);
                }
                var += Math.PI / 16;

                ParticleUtils particlelavaaddon = new ParticleUtils(EnumParticle.LAVA, player.getLocation(), true, 0f, 0f, 0f, 0, 2);
                particlelavaaddon.sendAll();

                location = player.getLocation();
                first = location.clone().add(Math.cos(var), Math.sin(var) + 1, Math.sin(var));
                second = location.clone().add(Math.cos(var + Math.PI), Math.sin(var) + 1, Math.sin(var + Math.PI));
                ParticleUtils particle = new ParticleUtils(EnumParticle.FLAME, first, true, 0f, 0f, 0f, 0, 7);
                particle.sendAll();
                ParticleUtils particle2 = new ParticleUtils(EnumParticle.FLAME, second, true, 0f, 0f, 0f, 0, 7);
                particle2.sendAll();
            }
        }, 0, 2);
    }
}



