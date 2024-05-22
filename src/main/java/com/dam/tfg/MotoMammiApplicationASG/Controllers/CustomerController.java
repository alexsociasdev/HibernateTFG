package com.dam.tfg.MotoMammiApplicationASG.Controllers;

import com.dam.tfg.MotoMammiApplicationASG.Models.CustomerDTO;
import com.dam.tfg.MotoMammiApplicationASG.Requests.CustomerRequest;
import com.dam.tfg.MotoMammiApplicationASG.Models.VehicleDTO;
import com.dam.tfg.MotoMammiApplicationASG.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PostMapping
    public String createCustomer(@RequestBody CustomerRequest customerRequest) {
        if (customerService.customerExistsByDni(customerRequest.getCustomer().getDni())) {
            return "Cliente ya registrado con DNI: " + customerRequest.getCustomer().getDni();
        }
        customerService.createCustomer(customerRequest.getCustomer(), customerRequest.getVehicles());
        return "Cliente creado exitosamente.";
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomerById(@PathVariable int id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping("/dni/{dni}")
    public CustomerDTO getCustomerByDni(@PathVariable String dni) {
        return customerService.getCustomerByDni(dni);
    }

    @PutMapping("/{id}")
    public CustomerDTO updateCustomer(@PathVariable int id, @RequestBody CustomerDTO customerDTO) {
        return customerService.updateCustomer(id, customerDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
    }
}
