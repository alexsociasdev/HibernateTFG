package com.dam.tfg.MotoMammiApplicationASG.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.persistence.*;

import com.dam.tfg.MotoMammiApplicationASG.Models.CustomerIntermediate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CustomerIntermediateRepository extends JpaRepository<CustomerIntermediate, Long> {

}
