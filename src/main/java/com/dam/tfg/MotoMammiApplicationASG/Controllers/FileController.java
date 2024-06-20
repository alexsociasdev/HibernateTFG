package com.dam.tfg.MotoMammiApplicationASG.Controllers;

import com.dam.tfg.MotoMammiApplicationASG.Models.AccidentPartDTO;
import com.dam.tfg.MotoMammiApplicationASG.Models.CustomerIntermediate;
import com.dam.tfg.MotoMammiApplicationASG.Models.CustomerDTO;
import com.dam.tfg.MotoMammiApplicationASG.Models.InvoiceDTO;
import com.dam.tfg.MotoMammiApplicationASG.Models.VehicleDTO;
import com.dam.tfg.MotoMammiApplicationASG.tasks.*;
import com.dam.tfg.MotoMammiApplicationASG.Repositories.CustomerIntermediateRepository;
import com.dam.tfg.MotoMammiApplicationASG.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/appInsurance/v1")
public class FileController {

    @Autowired
    private CustomerFileTask customerFileTask;

    @Autowired
    private VehicleFileTask vehicleFileTask;

    @Autowired
    private InvoiceFileTask invoiceFileTask;

    @Autowired
    private AccidentPartFileTask accidentPartFileTask;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerIntermediateRepository customerIntermediateRepository;

    private String basePath = "C:\\Users\\Alex\\Desktop\\MotoMammiApplicationASG\\src\\main\\resources\\";

    @GetMapping("/readInfoFileASG/{resource}/{codprov}/{date}")
    public String readInfoFile(@PathVariable String resource, @PathVariable String codprov, @PathVariable String date) {
        switch (resource.toLowerCase()) {
            case "customers":
                customerFileTask.loadCustomerFile();
                break;
            case "vehicles":
                vehicleFileTask.readVehiclesFile(codprov, date);
                break;
            case "invoices":
                invoiceFileTask.generateMonthlyInvoiceFile();
                break;
            case "parts":
                accidentPartFileTask.loadAccidentPartsFromFile();
                break;
            default:
                return "error en el método";
        }
        return "Archivo procesado";
    }

    @PostMapping("/processInfoFileASG/{resource}/{codprov}/{date}")
    public ResponseEntity<String> processInfoFile(
            @PathVariable String resource,
            @PathVariable String codprov,
            @PathVariable String date) {

        switch (resource.toLowerCase()) {
            case "customers":
                return processCustomerFile(codprov, date);
            case "vehicles":
                return processVehicleFile(codprov, date);
            case "invoices":
                return processInvoiceFile(codprov, date);
            case "parts":
                return processAccidentPartFile(codprov, date);
            default:
                return ResponseEntity.badRequest().body("Unsupported resource type.");
        }
    }

    @GetMapping("/printVehiclesFile/{codprov}/{date}")
    public List<VehicleDTO> printVehiclesFile(@PathVariable String codprov, @PathVariable String date) {
        return vehicleFileTask.getVehiclesFromFile(codprov, date);
    }

    @GetMapping("/printAccidentPartFile/{codprov}/{date}")
    public List<AccidentPartDTO> printAccidentPartFile(@PathVariable String codprov, @PathVariable String date) {
        return accidentPartFileTask.getAccidentPartsFromFile(codprov, date);
    }

    @GetMapping("/printCustomersFile/{codprov}/{date}")
    public List<CustomerDTO> printCustomersFile(@PathVariable String codprov, @PathVariable String date) {
        return customerFileTask.getCustomersFromFile(codprov, date);
    }

    @GetMapping("/printInvoiceFile/{codprov}/{date}")
    public List<InvoiceDTO> printInvoiceFile(@PathVariable String codprov, @PathVariable String date) {
        return invoiceFileTask.getInvoicesForMonth(codprov, date);
    }

    @PostMapping("/CustomerToFinal")
    public ResponseEntity<String> transferCustomersToFinal() {
        customerService.transferCustomersToFinalTable();
        return ResponseEntity.ok("Customers transferred to final table and intermediate table cleared.");
    }

    private ResponseEntity<String> processCustomerFile(String codprov, String date) {
        String filePath = basePath + "MM_insurance_customers_" + codprov + "_" + date + ".dat";
        return processFile(filePath, "customers");
    }

    private ResponseEntity<String> processVehicleFile(String codprov, String date) {
        String filePath = basePath + "MM_insurance_vehicles_" + codprov + "_" + date + ".dat";
        return processFile(filePath, "vehicles");
    }

    private ResponseEntity<String> processInvoiceFile(String codprov, String date) {
        String filePath = basePath + "MM_insurance_invoices_" + codprov + "_" + date + ".dat";
        return processFile(filePath, "invoices");
    }

    private ResponseEntity<String> processAccidentPartFile(String codprov, String date) {
        String filePath = basePath + "MM_insurance_parts_" + codprov + "_" + date + ".dat";
        return processFile(filePath, "parts");
    }

    private ResponseEntity<String> processFile(String filePath, String resource) {
        File file = new File(filePath);
        if (!file.exists()) {
            return ResponseEntity.status(404).body("File not found: " + filePath);
        }

        try {
            switch (resource.toLowerCase()) {
                case "customers":
                    List<CustomerIntermediate> customers = parseCustomersFile(filePath);
                    customerIntermediateRepository.saveAll(customers);
                    break;
                case "vehicles":
                    // Lógica para procesar el archivo de vehículos
                    break;
                case "invoices":
                    // Lógica para procesar el archivo de facturas
                    break;
                case "parts":
                    // Lógica para procesar el archivo de partes de accidente
                    break;
                default:
                    return ResponseEntity.badRequest().body("Unsupported resource type.");
            }

            return ResponseEntity.ok(resource.substring(0, 1).toUpperCase() + resource.substring(1) + " processed and inserted into intermediate table.");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error processing file: " + e.getMessage());
        }
    }

    private List<CustomerIntermediate> parseCustomersFile(String filePath) throws IOException {
        List<CustomerIntermediate> customers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8))) {
            String line;
            // Omitir la primera línea que contiene los encabezados de las columnas
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                CustomerIntermediate customer = new CustomerIntermediate();
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
        }

        return customers;
    }
}
