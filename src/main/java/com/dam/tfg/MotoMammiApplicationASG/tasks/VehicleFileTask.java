package com.dam.tfg.MotoMammiApplicationASG.tasks;

import com.dam.tfg.MotoMammiApplicationASG.Models.CustomerDTO;
import com.dam.tfg.MotoMammiApplicationASG.Models.VehicleDTO;
import com.dam.tfg.MotoMammiApplicationASG.services.CustomerService;
import com.dam.tfg.MotoMammiApplicationASG.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class VehicleFileTask {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private CustomerService customerService;

    @Scheduled(cron = "${cron.expression.vehicles}")
    public void loadVehicleFile() {
        String fileName = "C:\\Users\\Alex\\Desktop\\MotoMammiApplicationASG\\src\\main\\resources\\MM_insurance_vehicles_" + new SimpleDateFormat("yyyyMMdd").format(new java.util.Date()) + ".dat";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            List<VehicleDTO> vehicles = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                VehicleDTO vehicle = new VehicleDTO();
                vehicle.setDni(fields[0]);
                vehicle.setCarPlate(fields[1]);
                vehicle.setVehicleType(fields[2]);
                vehicle.setBrand(fields[3]);
                vehicle.setModel(fields[4]);

                vehicles.add(vehicle);
            }

            for (VehicleDTO vehicle : vehicles) {
                CustomerDTO customer = customerService.getCustomerByDni(vehicle.getDni());
                if (customer != null) {
                    vehicle.setCustomer(customer);
                    vehicleService.saveVehicle(vehicle);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
