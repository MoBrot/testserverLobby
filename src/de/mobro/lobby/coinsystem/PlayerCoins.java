package de.mobro.lobby.coinsystem;

import de.mobro.lobby.MySQL.MySQL;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlayerCoins {



        public static void setPlayercoins(Player player, int coins){

            if(!MySQL.getExist("playermaincoins", "puuid", player.getUniqueId().toString())) {
                try {
                    System.out.println("empty");
                    PreparedStatement ps = MySQL.con.prepareStatement("INSERT INTO playermaincoins (coins,puuid) VALUES (?,?)");
                    ps.setInt(1, 0);
                    ps.setString(2, player.getUniqueId().toString());

                    ps.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else{

                    System.out.println("Full");

                    MySQL.update("UPDATE playermaincoins SET coins = '"+coins+"' WHERE puuid = '"+player.getUniqueId().toString()+"'");



            }
    }

        public static int getPlayerCoins(Integer playercoins){



            return playercoins;

        }
}
