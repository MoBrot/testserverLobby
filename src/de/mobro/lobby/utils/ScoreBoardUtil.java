package de.mobro.lobby.utils;

import de.mobro.lobby.MySQL.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ScoreBoardUtil {

    public static void MainScoreBoard(Player player){

        int s = 0;

        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("abcd","abcd");
        ResultSet select = MySQL.getResult("SELECT `coins`, `puuid` FROM `playermaincoins` WHERE puuid = '"+player.getUniqueId().toString()+"'");

        Team coinscore = board.getTeam("coins") == null ? board.registerNewTeam("coins") : board.getTeam("coins");

        try {
            while(select.next()) {
               s = select.getInt("coins");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("§d§lLobby");

        obj.getScore(" ").setScore(15);
        obj.getScore("§d§lDein Name").setScore(14);
        obj.getScore(  "§7» "+ChatColor.GOLD+player.getName()).setScore(13);
        obj.getScore("   ").setScore(12);
        obj.getScore("§d§lDeine Coins").setScore(11);
        obj.getScore("§6").setScore(10);
        obj.getScore("     ").setScore(9);
        obj.getScore("§d§lServer").setScore(8);
        obj.getScore("§7» "+"§6Lobby").setScore(7);
        obj.getScore( ChatColor.GRAY +"    -------").setScore(6);

        coinscore.addEntry("§6");
        coinscore.setPrefix("§7» §6" + s);

        player.setScoreboard(board);

    }
}
