package com.dam.tfg.MotoMammiApplicationASG.Controllers;

import com.dam.tfg.MotoMammiApplicationASG.Models.AccidentPartDTO;
import com.dam.tfg.MotoMammiApplicationASG.Models.CustomerDTO;
import com.dam.tfg.MotoMammiApplicationASG.Models.InvoiceDTO;
import com.dam.tfg.MotoMammiApplicationASG.Models.VehicleDTO;
import com.dam.tfg.MotoMammiApplicationASG.tasks.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
                return "error en el método";
        }
        return "Archivo procesado";
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

    private void processCustomerFile(String codprov, String date) {
        customerFileTask.loadCustomerFile();
    }

    private void processVehicleFile(String codprov, String date) {
        vehicleFileTask.readVehiclesFile(codprov, date);
    }

    private void processInvoiceFile(String codprov, String date) {
        invoiceFileTask.generateMonthlyInvoiceFile();
    }

    private void processAccidentPartFile(String codprov, String date) {
        accidentPartFileTask.loadAccidentPartsFromFile();
    }
}
