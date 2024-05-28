package com.dam.tfg.MotoMammiApplicationASG.tasks;

import com.dam.tfg.MotoMammiApplicationASG.Models.CustomerDTO;
import com.dam.tfg.MotoMammiApplicationASG.Models.VehicleDTO;
import com.dam.tfg.MotoMammiApplicationASG.services.CustomerService;
import com.dam.tfg.MotoMammiApplicationASG.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class VehicleFileTask {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private CustomerService customerService;

    //@Value("${vehicles.file.path}")
    private String vehiclesFilePath = "C:\\Users\\Alex\\Desktop\\MotoMammiApplicationASG\\src\\main\\resources\\MM_insurance_vehicles_{codprov}_{date}.dat";

    //@Value("${default.codprov}")
    private String defaultCodprov = "01";

    @Scheduled(cron = "${cron.expression.vehicles}")
    public void loadVehicleFile() {
        String defaultDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        readVehiclesFile(defaultCodprov, defaultDate);
    }

    public void readVehiclesFile(String codprov, String date) {
        String filePath = vehiclesFilePath.replace("{codprov}", codprov).replace("{date}", date);

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
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

    public List<VehicleDTO> getVehiclesFromFile(String codprov, String date) {
        String filePath = vehiclesFilePath.replace("{codprov}", codprov).replace("{date}", date);
        List<VehicleDTO> vehicles = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        return vehicles;
    }
}
