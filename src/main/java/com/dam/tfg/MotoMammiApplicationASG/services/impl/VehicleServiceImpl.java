package com.dam.tfg.MotoMammiApplicationASG.services.impl;

import com.dam.tfg.MotoMammiApplicationASG.Models.VehicleDTO;
import com.dam.tfg.MotoMammiApplicationASG.Repositories.VehicleRepository;
import com.dam.tfg.MotoMammiApplicationASG.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public VehicleDTO saveVehicle(VehicleDTO vehicleDTO) {
        return vehicleRepository.save(vehicleDTO);
    }

    @Override
    public VehicleDTO getVehicleById(int id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    @Override
    public VehicleDTO getVehicleByCarPlate(String carPlate) {
        return vehicleRepository.findByCarPlate(carPlate);
    }

    @Override
    public VehicleDTO updateVehicle(int id, VehicleDTO vehicleDTO) {
        VehicleDTO existingVehicle = vehicleRepository.findById(id).orElse(null);
        if (existingVehicle != null) {
            existingVehicle.setDni(vehicleDTO.getDni());
            existingVehicle.setCarPlate(vehicleDTO.getCarPlate());
            existingVehicle.setVehicleType(vehicleDTO.getVehicleType());
            existingVehicle.setBrand(vehicleDTO.getBrand());
            existingVehicle.setModel(vehicleDTO.getModel());
            return vehicleRepository.save(existingVehicle);
        }
        return null;
    }

    @Override
    public void deleteVehicle(int id) {
        vehicleRepository.deleteById(id);
    }
    @Override
    public VehicleDTO createVehicle(VehicleDTO vehicleDTO) {
        return vehicleRepository.save(vehicleDTO);
    }
}
