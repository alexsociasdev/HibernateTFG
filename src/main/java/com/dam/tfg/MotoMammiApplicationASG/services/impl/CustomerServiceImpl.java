package com.dam.tfg.MotoMammiApplicationASG.services.impl;

import com.dam.tfg.MotoMammiApplicationASG.Models.CustomerDTO;
import com.dam.tfg.MotoMammiApplicationASG.Models.CustomerIntermediate;
import com.dam.tfg.MotoMammiApplicationASG.Models.VehicleDTO;
import com.dam.tfg.MotoMammiApplicationASG.Repositories.CustomerIntermediateRepository;
import com.dam.tfg.MotoMammiApplicationASG.Repositories.CustomerRepository;
import com.dam.tfg.MotoMammiApplicationASG.Repositories.VehicleRepository;
import com.dam.tfg.MotoMammiApplicationASG.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private CustomerIntermediateRepository customerIntermediateRepository;

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
    private Date convertTimestampToDate(Timestamp timestamp) {
        if (timestamp != null) {
            return new Date(timestamp.getTime());
        }
        return null;
    }
    @Override
    public boolean customerExistsByDni2(String dni) {
        return customerRepository.findByDni(dni) != null;
    }


    @Transactional
    @Override
    public void transferCustomersToFinalTable() {
        List<CustomerIntermediate> intermediates = customerIntermediateRepository.findAll();
        List<CustomerDTO> customers = new ArrayList<>();

        for (CustomerIntermediate intermediate : intermediates) {
            if (!customerExistsByDni2(intermediate.getDni())) {
                CustomerDTO customer = new CustomerDTO();
                customer.setName(intermediate.getName());
                customer.setFirstName(intermediate.getFirstName());
                customer.setLastName(intermediate.getLastName());
                customer.setBirthDate(convertTimestampToDate((Timestamp) intermediate.getBirthDate()));
                customer.setPostalCode(intermediate.getPostalCode());
                customer.setStreetType(intermediate.getStreetType());
                customer.setCity(intermediate.getCity());
                customer.setNumberStreet(intermediate.getNumberStreet());
                customer.setPhoneNumber(intermediate.getPhoneNumber());
                customer.setDni(intermediate.getDni());
                customer.setLicenceType(intermediate.getLicenceType());
                customer.setEmail(intermediate.getEmail());
                customer.setGender(intermediate.getGender());

                customers.add(customer);
            }
        }

        customerRepository.saveAll(customers);
        customerIntermediateRepository.deleteAll();
    }
}
