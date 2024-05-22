package com.dam.tfg.MotoMammiApplicationASG.services;

import com.dam.tfg.MotoMammiApplicationASG.Models.VehicleDTO;
import java.util.List;

public interface VehicleService {
    List<VehicleDTO> getAllVehicles();
    VehicleDTO saveVehicle(VehicleDTO vehicleDTO);
    VehicleDTO getVehicleById(int id);
    VehicleDTO getVehicleByCarPlate(String carPlate);
    VehicleDTO updateVehicle(int id, VehicleDTO vehicleDTO);
    void deleteVehicle(int id);
}
