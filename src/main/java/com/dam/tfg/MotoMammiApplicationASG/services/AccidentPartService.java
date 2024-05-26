package com.dam.tfg.MotoMammiApplicationASG.services;

import com.dam.tfg.MotoMammiApplicationASG.Models.AccidentPartDTO;

import java.util.List;

public interface AccidentPartService {
    List<AccidentPartDTO> getAllAccidentParts();
    AccidentPartDTO getAccidentPartById(Long id);
    AccidentPartDTO saveAccidentPart(AccidentPartDTO accidentPartDTO);
    void deleteAccidentPart(Long id);
    AccidentPartDTO createAccidentPart(AccidentPartDTO accidentPartDTO);
    AccidentPartDTO updateAccidentPart(Long id, AccidentPartDTO accidentPartDTO);
}
