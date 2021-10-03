package de.mobro.lobby.specials;

import de.mobro.lobby.utils.ParticleUtils;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.UUID;

public class Particles implements Listener {

    @EventHandler
    public static void playerMoved(PlayerMoveEvent event) {
        Player p = event.getPlayer();
        UUID uuid = p.getUniqueId();

        if (LobbyItems.particlewhile.get(uuid) == null) return;

            if(!(LobbyItems.particlewhile.get(uuid).equals(EnumParticle.SLIME))) {
                if (LobbyItems.particlewhile.containsKey(p.getUniqueId())) {
                    ParticleUtils particle = new ParticleUtils(LobbyItems.particlewhile.get(uuid), p.getLocation().add(0, 1, 0), true, 0.6f, 0.2f, 0.6f, 0, LobbyItems.pamount.get(uuid));
                    particle.sendAll();
                }
            }

        if(LobbyItems.particlewhile.get(uuid).equals(EnumParticle.SLIME)) {
            ParticleUtils particle = new ParticleUtils(LobbyItems.particlewhile.get(uuid), p.getLocation().add(0, 0.2f, 0), true, 0.2f, 0.2f, 0.2f, 0, LobbyItems.pamount.get(uuid));
            particle.sendAll();
        }

    }
}

