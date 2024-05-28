package com.dam.tfg.MotoMammiApplicationASG.tasks;

import com.dam.tfg.MotoMammiApplicationASG.Models.AccidentPartDTO;
import com.dam.tfg.MotoMammiApplicationASG.services.AccidentPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class AccidentPartFileTask {

    @Autowired
    private AccidentPartService accidentPartService;

    //@Value("${parts.file.path}")
    private String partsFilePath = "C:\\Users\\Alex\\Desktop\\MotoMammiApplicationASG\\src\\main\\resources\\MM_insurance_parts_{codprov}_{date}.dat";

    //@Value("${default.codprov}")
    private String defaultCodprov = "01";

    @Scheduled(cron = "${cron.expression.parts}")
    public void loadAccidentPartsFromFile() {
        String defaultDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        readAccidentPartsFile(defaultCodprov, defaultDate);
    }

    public void readAccidentPartsFile(String codprov, String date) {
        String filePath = partsFilePath.replace("{codprov}", codprov).replace("{date}", date);

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            List<AccidentPartDTO> accidentParts = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 6) {
                    AccidentPartDTO accidentPartDTO = new AccidentPartDTO();
                    accidentPartDTO.setDescription(data[0]);
                    accidentPartDTO.setAccidentDate(new SimpleDateFormat("yyyy-MM-dd").parse(data[1]));
                    accidentPartDTO.setLocation(data[2]);
                    accidentPartDTO.setDriverName(data[3]);
                    accidentPartDTO.setDriverLicense(data[4]);
                    accidentPartDTO.setVehicleRegistration(data[5]);

                    accidentParts.add(accidentPartDTO);
                } else {
                    System.err.println("Formato invalido: " + line);
                }
            }

            for (AccidentPartDTO accidentPart : accidentParts) {
                accidentPartService.saveAccidentPart(accidentPart);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public List<AccidentPartDTO> getAccidentPartsFromFile(String codprov, String date) {
        String filePath = partsFilePath.replace("{codprov}", codprov).replace("{date}", date);
        List<AccidentPartDTO> accidentParts = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 6) {
                    AccidentPartDTO accidentPartDTO = new AccidentPartDTO();
                    accidentPartDTO.setDescription(data[0]);
                    accidentPartDTO.setAccidentDate(new SimpleDateFormat("yyyy-MM-dd").parse(data[1]));
                    accidentPartDTO.setLocation(data[2]);
                    accidentPartDTO.setDriverName(data[3]);
                    accidentPartDTO.setDriverLicense(data[4]);
                    accidentPartDTO.setVehicleRegistration(data[5]);

                    accidentParts.add(accidentPartDTO);
                } else {
                    System.err.println("Formato invalido: " + line);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return accidentParts;
    }
}
