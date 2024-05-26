package com.dam.tfg.MotoMammiApplicationASG.Controllers;

import com.dam.tfg.MotoMammiApplicationASG.tasks.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/readInfoFileASG/{resource}/{codprov}/{date}")
    public String readInfoFile(@PathVariable String resource, @PathVariable String codprov, @PathVariable String date) {
        switch (resource.toLowerCase()) {
            case "customers":
                customerFileTask.loadCustomerFile();
                break;
            case "vehicles":
                vehicleFileTask.loadVehicleFile();
                break;
            case "invoices":
                invoiceFileTask.generateMonthlyInvoiceFile();
                break;
            case "parts":
                accidentPartFileTask.loadAccidentPartsFromFile();
                break;
            default:
                return "Resource not found.";
        }
        return "File processed successfully.";
    }

    @PostMapping("/processInfoFileASG/{resource}/{codprov}/{date}")
    public String processInfoFile(@PathVariable String resource, @PathVariable String codprov, @PathVariable String date) {
        switch (resource.toLowerCase()) {
            case "customers":
                processCustomerFile(codprov, date);
                break;
            case "vehicles":
                processVehicleFile(codprov, date);
                break;
            case "invoices":
                processInvoiceFile(codprov, date);
                break;
            case "parts":
                processAccidentPartFile(codprov, date);
                break;
            default:
                return "Resource not found.";
        }
        return "Processing completed for resource: " + resource;
    }

    private void processCustomerFile(String codprov, String date) {
        customerFileTask.loadCustomerFile();
    }

    private void processVehicleFile(String codprov, String date) {
        vehicleFileTask.loadVehicleFile();
    }

    private void processInvoiceFile(String codprov, String date) {
        invoiceFileTask.generateMonthlyInvoiceFile();
    }

    private void processAccidentPartFile(String codprov, String date) {
        accidentPartFileTask.loadAccidentPartsFromFile();
    }
}
