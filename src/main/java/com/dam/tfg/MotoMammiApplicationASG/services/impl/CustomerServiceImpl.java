package com.dam.tfg.MotoMammiApplicationASG.services.impl;

import com.dam.tfg.MotoMammiApplicationASG.Models.CustomerDTO;
import com.dam.tfg.MotoMammiApplicationASG.Models.VehicleDTO;
import com.dam.tfg.MotoMammiApplicationASG.Repositories.CustomerRepository;
import com.dam.tfg.MotoMammiApplicationASG.Repositories.VehicleRepository;
import com.dam.tfg.MotoMammiApplicationASG.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO, List<VehicleDTO> vehicles) {
        CustomerDTO savedCustomer = customerRepository.save(customerDTO);
        for (VehicleDTO vehicle : vehicles) {
            vehicle.setCustomer(savedCustomer);
            vehicleRepository.save(vehicle);
        }
        return savedCustomer;
    }

    @Override
    public CustomerDTO getCustomerById(int id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public CustomerDTO getCustomerByDni(String dni) {
        return customerRepository.findByDni(dni);
    }

    @Override
    public CustomerDTO updateCustomer(int id, CustomerDTO customerDTO) {
        CustomerDTO existingCustomer = customerRepository.findById(id).orElse(null);
        if (existingCustomer != null) {
            existingCustomer.setName(customerDTO.getName());
            existingCustomer.setFirstName(customerDTO.getFirstName());
            existingCustomer.setLastName(customerDTO.getLastName());
            existingCustomer.setBirthDate(customerDTO.getBirthDate());
            existingCustomer.setPostalCode(customerDTO.getPostalCode());
            existingCustomer.setStreetType(customerDTO.getStreetType());
            existingCustomer.setCity(customerDTO.getCity());
            existingCustomer.setNumberStreet(customerDTO.getNumberStreet());
            existingCustomer.setPhoneNumber(customerDTO.getPhoneNumber());
            existingCustomer.setDni(customerDTO.getDni());
            existingCustomer.setLicenceType(customerDTO.getLicenceType());
            existingCustomer.setEmail(customerDTO.getEmail());
            existingCustomer.setGender(customerDTO.getGender());
            return customerRepository.save(existingCustomer);
        }
        return null;
    }

    @Override
    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }

    @Override
    public boolean customerExistsByDni(String dni) {
        return customerRepository.findByDni(dni) != null;
    }
}
