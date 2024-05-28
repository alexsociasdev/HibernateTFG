package com.dam.tfg.MotoMammiApplicationASG.tasks;

import com.dam.tfg.MotoMammiApplicationASG.Models.CustomerDTO;
import com.dam.tfg.MotoMammiApplicationASG.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class CustomerFileTask {

    @Autowired
    private CustomerService customerService;

    //@Value("${customers.file.path}")
    private String customersFilePath = "C:\\Users\\Alex\\Desktop\\MotoMammiApplicationASG\\src\\main\\resources\\MM_insurance_customers_{codprov}_{date}.dat";

    //@Value("${default.codprov}")
    private String defaultCodprov = "01";

    @Scheduled(cron = "${cron.expression.customers}")
    public void loadCustomerFile() {
        String defaultDate = new SimpleDateFormat("yyyyMMdd").format(new java.util.Date());
        readCustomerFile(defaultCodprov, defaultDate);
    }

    public void readCustomerFile(String codprov, String date) {
        String filePath = customersFilePath.replace("{codprov}", codprov).replace("{date}", date);

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            List<CustomerDTO> customers = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                CustomerDTO customer = new CustomerDTO();
                customer.setName(fields[0]);
                customer.setFirstName(fields[1]);
                customer.setLastName(fields[2]);
                customer.setBirthDate(Date.valueOf(fields[3]));
                customer.setPostalCode(fields[4]);
                customer.setStreetType(fields[5]);
                customer.setCity(fields[6]);
                customer.setNumberStreet(Integer.parseInt(fields[7]));
                customer.setPhoneNumber(fields[8]);
                customer.setDni(fields[9]);
                customer.setLicenceType(fields[10]);
                customer.setEmail(fields[11]);
                customer.setGender(fields[12]);

                customers.add(customer);
            }

            for (CustomerDTO customer : customers) {
                if (!customerService.customerExistsByDni(customer.getDni())) {
                    customerService.createCustomer(customer, new ArrayList<>());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<CustomerDTO> getCustomersFromFile(String codprov, String date) {
        String filePath = customersFilePath.replace("{codprov}", codprov).replace("{date}", date);
        List<CustomerDTO> customers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                CustomerDTO customer = new CustomerDTO();
                customer.setName(fields[0]);
                customer.setFirstName(fields[1]);
                customer.setLastName(fields[2]);
                customer.setBirthDate(Date.valueOf(fields[3]));
                customer.setPostalCode(fields[4]);
                customer.setStreetType(fields[5]);
                customer.setCity(fields[6]);
                customer.setNumberStreet(Integer.parseInt(fields[7]));
                customer.setPhoneNumber(fields[8]);
                customer.setDni(fields[9]);
                customer.setLicenceType(fields[10]);
                customer.setEmail(fields[11]);
                customer.setGender(fields[12]);

                customers.add(customer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return customers;
    }
}
