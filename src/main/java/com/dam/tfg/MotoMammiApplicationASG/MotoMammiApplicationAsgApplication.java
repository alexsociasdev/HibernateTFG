package com.dam.tfg.MotoMammiApplicationASG;

import com.dam.tfg.MotoMammiApplicationASG.tasks.HibernateMetodos;
import com.dam.tfg.MotoMammiApplicationASG.tasks.ReadInfoFileTask;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.dam.tfg.MotoMammiApplicationASG.Models.Provider;

@SpringBootApplication
@EnableScheduling
public class MotoMammiApplicationAsgApplication {
	public static ReadInfoFileTask Rf = new ReadInfoFileTask();
	public static HibernateMetodos Hm = new HibernateMetodos();

	public static void main(String[] args) {
		//SpringApplication.run(MotoMammiApplicationAsgApplication.class, args);

		List<Provider> provActivos = HibernateMetodos.activeProviders();
		provActivos.forEach(System.out::println);
	}
	public static boolean probarConexion(String nombreBD, String usuario, String contrasena) {
		String url = "jdbc:mysql://localhost:3306/" + nombreBD + "?serverTimezone=UTC";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}

		try (Connection conexion = DriverManager.getConnection(url, usuario, contrasena)) {
			if (conexion != null) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
