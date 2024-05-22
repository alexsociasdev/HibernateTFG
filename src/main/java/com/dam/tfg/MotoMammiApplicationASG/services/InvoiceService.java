package com.dam.tfg.MotoMammiApplicationASG.services;

import com.dam.tfg.MotoMammiApplicationASG.Models.InvoiceDTO;
import java.util.List;

public interface InvoiceService {
    List<InvoiceDTO> getAllInvoices();
    InvoiceDTO createInvoice(InvoiceDTO invoiceDTO);
}
