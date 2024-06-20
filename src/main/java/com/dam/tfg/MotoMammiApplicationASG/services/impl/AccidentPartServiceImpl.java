package com.dam.tfg.MotoMammiApplicationASG.services.impl;

import com.dam.tfg.MotoMammiApplicationASG.Models.AccidentPartDTO;
import com.dam.tfg.MotoMammiApplicationASG.Models.CustomerDTO;
import com.dam.tfg.MotoMammiApplicationASG.Models.InvoiceDTO;
import com.dam.tfg.MotoMammiApplicationASG.Repositories.AccidentPartRepository;
import com.dam.tfg.MotoMammiApplicationASG.Repositories.CustomerRepository;
import com.dam.tfg.MotoMammiApplicationASG.services.AccidentPartService;
import com.dam.tfg.MotoMammiApplicationASG.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AccidentPartServiceImpl implements AccidentPartService {

    @Autowired
    private AccidentPartRepository accidentPartRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private InvoiceService invoiceService;

    // Ruta para guardar las facturas
    private static final String INVOICES_DIRECTORY_PATH = "C:\\Users\\Alex\\Desktop\\MotoMammiApplicationASG\\invoices";

    @Override
    public List<AccidentPartDTO> getAllAccidentParts() {
        return accidentPartRepository.findAll();
    }

    @Override
    public AccidentPartDTO getAccidentPartById(Long id) {
        Optional<AccidentPartDTO> optionalAccidentPart = accidentPartRepository.findById(id);
        return optionalAccidentPart.orElse(null);
    }

    @Override
    @Transactional
    public AccidentPartDTO saveAccidentPart(AccidentPartDTO accidentPartDTO) {
        return accidentPartRepository.save(accidentPartDTO);
    }

    @Override
    @Transactional
    public void deleteAccidentPart(Long id) {
        accidentPartRepository.deleteById(id);
    }

    @Override
    @Transactional
    public AccidentPartDTO createAccidentPart(AccidentPartDTO accidentPartDTO) {
        AccidentPartDTO savedAccidentPart = accidentPartRepository.save(accidentPartDTO);
        generateInvoiceForAccidentPart(savedAccidentPart);
        return savedAccidentPart;
    }

    @Override
    @Transactional
    public AccidentPartDTO updateAccidentPart(Long id, AccidentPartDTO accidentPartDTO) {
        if (accidentPartRepository.existsById(id)) {
            accidentPartDTO.setId(id);
            return accidentPartRepository.save(accidentPartDTO);
        }
        return null;
    }

    private void generateInvoiceForAccidentPart(AccidentPartDTO accidentPart) {
        Long customerId = accidentPart.getCustomerId();
        if (customerId == null) {
            throw new IllegalArgumentException("Customer ID cannot be null");
        }

        Optional<CustomerDTO> customerOpt = customerRepository.findById(customerId);

        if (customerOpt.isPresent()) {
            CustomerDTO customer = customerOpt.get();
            InvoiceDTO invoice = new InvoiceDTO();
            invoice.setFecha(new Date(System.currentTimeMillis()));
            invoice.setNombreEmpresa("Nombre de la Empresa");
            invoice.setCifEmpresa("CIF de la Empresa");
            invoice.setDireccionEmpresa("Dirección de la Empresa");
            invoice.setNombreUsuario(customer.getName());
            invoice.setApellidosUsuario(customer.getFirstName() + " " + customer.getLastName());
            invoice.setDireccionUsuario(customer.getPostalCode() + ", " + customer.getStreetType() + " " + customer.getNumberStreet() + ", " + customer.getCity());
            invoice.setTipoSeguro("Tipo de Seguro");
            invoice.setTipoVehiculo("Tipo de Vehículo");
            invoice.setFechaRegistro(new Date(System.currentTimeMillis()));
            invoice.setFechaFinContrato(new Date(System.currentTimeMillis() + 31556952000L)); // 1 año después
            invoice.setCoste(100.00); // Ejemplo de coste
            invoice.setIva(21.00); // Ejemplo de IVA

            invoiceService.createInvoice(invoice);

            // Guardar la factura en el directorio especificado
            saveInvoiceToFile(invoice);
        } else {
            throw new RuntimeException("Customer not found for ID: " + customerId);
        }
    }

    private void saveInvoiceToFile(InvoiceDTO invoice) {
        String fileName = INVOICES_DIRECTORY_PATH + "/Invoice_" + invoice.getId() + ".txt";

        try (FileWriter writer = new FileWriter(fileName)) {
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
