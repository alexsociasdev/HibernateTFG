package com.dam.tfg.MotoMammiApplicationASG.services.impl;

import com.dam.tfg.MotoMammiApplicationASG.Models.InvoiceDTO;
import com.dam.tfg.MotoMammiApplicationASG.Repositories.InvoiceRepository;
import com.dam.tfg.MotoMammiApplicationASG.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public List<InvoiceDTO> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public InvoiceDTO createInvoice(InvoiceDTO invoiceDTO) {
        return invoiceRepository.save(invoiceDTO);
    }
}
