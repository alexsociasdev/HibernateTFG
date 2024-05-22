package com.dam.tfg.MotoMammiApplicationASG.tasks;

import com.dam.tfg.MotoMammiApplicationASG.Models.InvoiceDTO;
import com.dam.tfg.MotoMammiApplicationASG.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class InvoiceFileTask {

    @Autowired
    private InvoiceService invoiceService;

    @Scheduled(cron = "0 0 0 1 * ?")
    public void generateMonthlyInvoiceFile() {
        String fileName = "C:\\Users\\Alex\\Desktop\\MotoMammiApplicationASG\\src\\main\\resources\\MM_invoices_CODPROV_" + new SimpleDateFormat("yyyyMM").format(new java.util.Date()) + ".dat";

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.append("Id,Fecha,Nombre Empresa,CIF Empresa,Dirección Empresa,Nombre Usuario,Apellidos Usuario,Dirección Usuario,Tipo Seguro,Tipo Vehículo,Fecha Registro,Fecha Fin Contrato,Coste,IVA\n");

            List<InvoiceDTO> invoices = invoiceService.getAllInvoices();
            for (InvoiceDTO invoice : invoices) {
                writer.append(invoice.getId() + ",");
                writer.append(invoice.getFecha() + ",");
                writer.append(invoice.getNombreEmpresa() + ",");
                writer.append(invoice.getCifEmpresa() + ",");
                writer.append(invoice.getDireccionEmpresa() + ",");
                writer.append(invoice.getNombreUsuario() + ",");
                writer.append(invoice.getApellidosUsuario() + ",");
                writer.append(invoice.getDireccionUsuario() + ",");
                writer.append(invoice.getTipoSeguro() + ",");
                writer.append(invoice.getTipoVehiculo() + ",");
                writer.append(invoice.getFechaRegistro() + ",");
                writer.append(invoice.getFechaFinContrato() + ",");
                writer.append(invoice.getCoste() + ",");
                writer.append(invoice.getIva() + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
