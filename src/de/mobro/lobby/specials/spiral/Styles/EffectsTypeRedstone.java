package de.mobro.lobby.specials.spiral.Styles;


import de.mobro.lobby.Main;
import de.mobro.lobby.specials.spiral.ParticleData;
import de.mobro.lobby.utils.ParticleUtils;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class EffectsTypeRedstone {

    private int taskID;
    private final Player player;

    public EffectsTypeRedstone(Player player){
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

                location = player.getLocation();
                first = location.clone().add(Math.cos(var), Math.sin(var) + 1, Math.sin(var));
                second = location.clone().add(Math.cos(var + Math.PI), Math.sin(var) + 1, Math.sin(var + Math.PI));
                ParticleUtils particle = new ParticleUtils(EnumParticle.REDSTONE, first, true, 0f, 0f, 0f, 0, 20);
                particle.sendAll();
                ParticleUtils particle2 = new ParticleUtils(EnumParticle.REDSTONE, second, true, 0f, 0f, 0f, 0, 20);
                particle2.sendAll();
            }
        }, 0, 1);
    }
}

