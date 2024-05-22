package com.dam.tfg.MotoMammiApplicationASG.Requests;
import com.dam.tfg.MotoMammiApplicationASG.Models.*;

import java.util.List;

public class CustomerRequest {
    private CustomerDTO customer;
    private List<VehicleDTO> vehicles;

    public CustomerRequest() {}

    public CustomerRequest(CustomerDTO customer, List<VehicleDTO> vehicles) {
        this.customer = customer;
        this.vehicles = vehicles;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public List<VehicleDTO> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleDTO> vehicles) {
        this.vehicles = vehicles;
    }
}
