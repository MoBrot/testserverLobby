package de.mobro.lobby.specials.spiral;

import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ParticleData {

    private static Map<UUID, Integer> Spiral = new HashMap<UUID, Integer>();
    private final UUID uuid;

    public ParticleData(UUID uuid){
        this.uuid = uuid;
    }

    public void setID(int id){
        Spiral.put(uuid, id);
    }

    public int getID(){
        return Spiral.get(uuid);
    }

    public boolean hasID(){
        if(Spiral.containsKey(uuid))
            return true;
        return false;
    }

    public void removeID(){
        Spiral.remove(uuid);
    }

    public void endTask(){
        if(getID() == 1)
            return;

        Bukkit.getScheduler().cancelTask(getID());
    }

    public static boolean hasFakeID(UUID uuid){
        if(Spiral.containsKey(uuid))
            if(Spiral.get(uuid) == 1)
                return true;
            return false;
    }
}
