package com.dam.tfg.MotoMammiApplicationASG.Repositories;

import com.dam.tfg.MotoMammiApplicationASG.Models.CustomerDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerDTO, Integer> {
    CustomerDTO findByDni(String dni);
}