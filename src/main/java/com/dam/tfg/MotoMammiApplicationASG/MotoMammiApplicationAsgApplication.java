package com.dam.tfg.MotoMammiApplicationASG;

import com.dam.tfg.MotoMammiApplicationASG.Models.CustomerDTO;
import com.dam.tfg.MotoMammiApplicationASG.Models.VehicleDTO;
import com.dam.tfg.MotoMammiApplicationASG.services.CustomerService;
import com.dam.tfg.MotoMammiApplicationASG.services.impl.CustomerServiceImpl;
import com.dam.tfg.MotoMammiApplicationASG.tasks.CustomerFileTask;
import com.dam.tfg.MotoMammiApplicationASG.tasks.VehicleFileTask;
import com.dam.tfg.MotoMammiApplicationASG.tasks.InvoiceFileTask;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
public class MotoMammiApplicationAsgApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MotoMammiApplicationAsgApplication.class, args);
		CustomerService customerService = context.getBean(CustomerServiceImpl.class);
		CustomerFileTask customerFileTask = context.getBean(CustomerFileTask.class);
		VehicleFileTask vehicleFileTask = context.getBean(VehicleFileTask.class);
		InvoiceFileTask invoiceFileTask = context.getBean(InvoiceFileTask.class);
	}


	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(authorizeRequests ->
						authorizeRequests
								.anyRequest().permitAll()
				);
		return http.build();
	}
}
