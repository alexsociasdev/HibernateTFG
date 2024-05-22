package com.dam.tfg.MotoMammiApplicationASG.Repositories;

import com.dam.tfg.MotoMammiApplicationASG.Models.VehicleDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<VehicleDTO, Integer> {
    VehicleDTO findByCarPlate(String carPlate);
}