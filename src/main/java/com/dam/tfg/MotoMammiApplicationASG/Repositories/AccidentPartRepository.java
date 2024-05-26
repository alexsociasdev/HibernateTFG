package com.dam.tfg.MotoMammiApplicationASG.Repositories;

import com.dam.tfg.MotoMammiApplicationASG.Models.AccidentPartDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccidentPartRepository extends JpaRepository<AccidentPartDTO, Long> {
}
