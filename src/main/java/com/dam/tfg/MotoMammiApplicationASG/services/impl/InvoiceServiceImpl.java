package com.dam.tfg.MotoMammiApplicationASG.services.impl;

import com.dam.tfg.MotoMammiApplicationASG.Models.InvoiceDTO;
import com.dam.tfg.MotoMammiApplicationASG.Repositories.InvoiceRepository;
import com.dam.tfg.MotoMammiApplicationASG.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Value("${invoices.directory.path}")
    private String invoicesDirectoryPath;

    @Override
    public List<InvoiceDTO> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public InvoiceDTO createInvoice(InvoiceDTO invoiceDTO) {
        InvoiceDTO savedInvoice = invoiceRepository.save(invoiceDTO);
        saveInvoiceToFile(savedInvoice);
        return savedInvoice;
    }

    private void saveInvoiceToFile(InvoiceDTO invoice) {
        String filePath = invoicesDirectoryPath + "/invoice_" + invoice.getId() + ".txt";
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("Id: " + invoice.getId() + "\n");
            writer.write("Fecha: " + invoice.getFecha() + "\n");
            writer.write("Nombre Empresa: " + invoice.getNombreEmpresa() + "\n");
            writer.write("CIF Empresa: " + invoice.getCifEmpresa() + "\n");
            writer.write("Dirección Empresa: " + invoice.getDireccionEmpresa() + "\n");
            writer.write("Nombre Usuario: " + invoice.getNombreUsuario() + "\n");
            writer.write("Apellidos Usuario: " + invoice.getApellidosUsuario() + "\n");
            writer.write("Dirección Usuario: " + invoice.getDireccionUsuario() + "\n");
            writer.write("Tipo Seguro: " + invoice.getTipoSeguro() + "\n");
            writer.write("Tipo Vehículo: " + invoice.getTipoVehiculo() + "\n");
            writer.write("Fecha Registro: " + invoice.getFechaRegistro() + "\n");
            writer.write("Fecha Fin Contrato: " + invoice.getFechaFinContrato() + "\n");
            writer.write("Coste: " + invoice.getCoste() + "\n");
            writer.write("IVA: " + invoice.getIva() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
