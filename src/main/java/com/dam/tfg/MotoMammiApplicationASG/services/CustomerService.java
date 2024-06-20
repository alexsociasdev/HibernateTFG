package com.dam.tfg.MotoMammiApplicationASG.services;

import com.dam.tfg.MotoMammiApplicationASG.Models.CustomerDTO;
import com.dam.tfg.MotoMammiApplicationASG.Models.VehicleDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAllCustomers();
    CustomerDTO createCustomer(CustomerDTO customerDTO, List<VehicleDTO> vehicles);
    CustomerDTO getCustomerById(int id);
    CustomerDTO getCustomerByDni(String dni);
    CustomerDTO updateCustomer(int id, CustomerDTO customerDTO);
    void deleteCustomer(int id);
    boolean customerExistsByDni(String dni);

    boolean customerExistsByDni2(String dni);

    void transferCustomersToFinalTable();
}
