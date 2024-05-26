package com.dam.tfg.MotoMammiApplicationASG.tasks;

import com.dam.tfg.MotoMammiApplicationASG.Models.AccidentPartDTO;
import com.dam.tfg.MotoMammiApplicationASG.services.AccidentPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class AccidentPartFileTask {

    @Autowired
    private AccidentPartService accidentPartService;

    @Scheduled(cron = "${cron.expression.parts}")
    public void loadAccidentPartsFromFile() {
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String filePath = "src/main/resources/MM_insurance_parts_01_" + date + ".dat";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Read line: " + line); // Imprimir la línea leída
                String[] data = line.split(",");
                if (data.length == 6) {
                    AccidentPartDTO accidentPartDTO = new AccidentPartDTO();
                    accidentPartDTO.setDescription(data[0]);
                    accidentPartDTO.setAccidentDate(new SimpleDateFormat("yyyy-MM-dd").parse(data[1]));
                    accidentPartDTO.setLocation(data[2]);
                    accidentPartDTO.setDriverName(data[3]);
                    accidentPartDTO.setDriverLicense(data[4]);
                    accidentPartDTO.setVehicleRegistration(data[5]);

                    // Imprimir datos para depuración
                    System.out.println("Saving AccidentPart: " + accidentPartDTO.toString());

                    accidentPartService.saveAccidentPart(accidentPartDTO);
                    System.out.println("Saved AccidentPart: " + accidentPartDTO.toString());
                } else {
                    System.err.println("Invalid data format: " + line);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
