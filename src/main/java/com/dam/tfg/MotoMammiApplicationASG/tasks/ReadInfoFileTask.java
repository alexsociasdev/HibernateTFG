package com.dam.tfg.MotoMammiApplicationASG.tasks;

import com.dam.tfg.MotoMammiApplicationASG.Models.Provider;
import com.dam.tfg.MotoMammiApplicationASG.MotoMammiApplicationAsgApplication;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;

@Component
public class ReadInfoFileTask {
    private static MotoMammiApplicationAsgApplication Mn = new MotoMammiApplicationAsgApplication();
    @Scheduled(cron = "${cron.task.getpart}")
    /*public void task(){
        try{
            System.out.println("\n Esta tarea se lanza cada " +
                    "5 segundos");
        }catch (Exception ex){
            System.err.println("Error "+ ex);
        }
    }*/

    public static ArrayList<Provider> activeProviders() {
        ArrayList<Provider> providers = new ArrayList<>();
        boolean resultadoConexion = Mn.probarConexion("MM_ASG", "root", "1349");
        if (resultadoConexion) {
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/MM_ASG?serverTimezone=UTC", "root", "1349");
                 PreparedStatement ps = conn.prepareStatement("SELECT * FROM MM_PROVIDERS WHERE SwiAct = TRUE")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Provider provider = new Provider(
                            rs.getInt("id"),
                            rs.getString("codprov"),
                            rs.getString("name"),
                            rs.getDate("dateIni"),
                            rs.getDate("dateEnd"),
                            rs.getBoolean("SwiAct")
                    );
                    providers.add(provider);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error de sql: " + e);
            }
        }
        return providers;
    }


}

