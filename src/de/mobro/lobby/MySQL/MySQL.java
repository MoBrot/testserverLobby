package de.mobro.lobby.MySQL;

import java.sql.*;

public class MySQL {

    public static Connection con;

    public static void connect() {
        if(!isConnected()) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/playercoins?autoReconnect=true", "PlayerCoins", "gtDk@.RGKj5NmeyE");
                System.out.println("[System] MySQL Verbindung erfolgreich!");
            } catch (SQLException e){
                System.out.println("[System] MySQL Verbindung fehlgeschlagen! Fehler: "+ e.getMessage());
            }
        }

    }
    public static void close() {
        try {
            if(con != null) {
                con.close();
                System.out.println("[System] MySQL Verbindung getrennt!");

            }

        } catch (SQLException e) {
            System.out.println("[System] MySQL Verbindung trennung fehlgeschlagen! Fehler: "+ e.getMessage());
        }
    }

    public static boolean isConnected() {
        return(con == null ? false : true);
    }

    public static void insert(String sql) {
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void update(String qry) {
        try {
            PreparedStatement ps =  con.prepareStatement(qry);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public static ResultSet getResult(String qry) {
        try {
            PreparedStatement ps = con.prepareStatement(qry);
            return ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static boolean getExist(String tabelle, String arg1, String arg2) {
        System.out.println("getExist");
        ResultSet rs = getResult("SELECT COUNT(*) FROM "+tabelle+" WHERE "+arg1+"='"+arg2+"'");
        int i = 0;
        try {
            while(rs.next()) {
                i = rs.getInt("COUNT(*)");
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        if(i >= 1) {
            return true;
        } else {
            return false;

        }

    }


}
