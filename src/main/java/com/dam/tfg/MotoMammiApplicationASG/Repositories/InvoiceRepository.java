package com.dam.tfg.MotoMammiApplicationASG.Repositories;

import com.dam.tfg.MotoMammiApplicationASG.Models.InvoiceDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<InvoiceDTO, Integer> {
}
