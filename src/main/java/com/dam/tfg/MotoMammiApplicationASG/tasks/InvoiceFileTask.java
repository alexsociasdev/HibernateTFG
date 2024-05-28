package com.dam.tfg.MotoMammiApplicationASG.tasks;

import com.dam.tfg.MotoMammiApplicationASG.Models.InvoiceDTO;
import com.dam.tfg.MotoMammiApplicationASG.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class InvoiceFileTask {

    @Autowired
    private InvoiceService invoiceService;

    //@Value("${invoices.file.path}")
    private String invoicesFilePath = "C:\\Users\\Alex\\Desktop\\MotoMammiApplicationASG\\src\\main\\resources\\MM_invoices_{codprov}_{date}.dat";

    //@Value("${default.codprov}")
    private String defaultCodprov = "01";

    @Scheduled(cron = "${cron.expression.invoices}")
    public void generateMonthlyInvoiceFile() {
        String defaultDate = new SimpleDateFormat("yyyyMM").format(new java.util.Date());
        writeInvoicesToFile(defaultCodprov, defaultDate);
    }

    public void writeInvoicesToFile(String codprov, String date) {
        String filePath = invoicesFilePath.replace("{codprov}", codprov).replace("{date}", date);

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append("Id,Fecha,Nombre Empresa,CIF Empresa,Dirección Empresa,Nombre Usuario,Apellidos Usuario,Dirección Usuario,Tipo Seguro,Tipo Vehículo,Fecha Registro,Fecha Fin Contrato,Coste,IVA\n");

            List<InvoiceDTO> invoices = invoiceService.getAllInvoices();
            for (InvoiceDTO invoice : invoices) {
                writer.append((char) invoice.getId()).append(",");
                writer.append((CharSequence) invoice.getFecha()).append(",");
                writer.append(invoice.getNombreEmpresa()).append(",");
                writer.append(invoice.getCifEmpresa()).append(",");
                writer.append(invoice.getDireccionEmpresa()).append(",");
                writer.append(invoice.getNombreUsuario()).append(",");
                writer.append(invoice.getApellidosUsuario()).append(",");
                writer.append(invoice.getDireccionUsuario()).append(",");
                writer.append(invoice.getTipoSeguro()).append(",");
                writer.append(invoice.getTipoVehiculo()).append(",");
                writer.append((CharSequence) invoice.getFechaRegistro()).append(",");
                writer.append((CharSequence) invoice.getFechaFinContrato()).append(",");
                writer.append(String.valueOf(invoice.getCoste())).append(",");
                writer.append(String.valueOf(invoice.getIva())).append("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<InvoiceDTO> getInvoicesForMonth(String codprov, String date) {
        String filePath = invoicesFilePath.replace("{codprov}", codprov).replace("{date}", date);
        List<InvoiceDTO> invoices = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                InvoiceDTO invoice = new InvoiceDTO();
                invoice.setId(Integer.parseInt(fields[0]));
                invoice.setFecha(Date.valueOf(fields[1]));
                invoice.setNombreEmpresa(fields[2]);
                invoice.setCifEmpresa(fields[3]);
                invoice.setDireccionEmpresa(fields[4]);
                invoice.setNombreUsuario(fields[5]);
                invoice.setApellidosUsuario(fields[6]);
                invoice.setDireccionUsuario(fields[7]);
                invoice.setTipoSeguro(fields[8]);
                invoice.setTipoVehiculo(fields[9]);
                invoice.setFechaRegistro(Date.valueOf(fields[10]));
                invoice.setFechaFinContrato(Date.valueOf(fields[11]));
                invoice.setCoste(Double.parseDouble(fields[12]));
                invoice.setIva(Double.parseDouble(fields[13]));

                invoices.add(invoice);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return invoices;
    }
}
