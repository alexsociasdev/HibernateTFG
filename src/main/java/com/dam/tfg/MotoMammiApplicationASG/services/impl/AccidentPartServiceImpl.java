package com.dam.tfg.MotoMammiApplicationASG.services.impl;

import com.dam.tfg.MotoMammiApplicationASG.Models.AccidentPartDTO;
import com.dam.tfg.MotoMammiApplicationASG.Repositories.AccidentPartRepository;
import com.dam.tfg.MotoMammiApplicationASG.services.AccidentPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AccidentPartServiceImpl implements AccidentPartService {

    @Autowired
    private AccidentPartRepository accidentPartRepository;

    @Override
    public List<AccidentPartDTO> getAllAccidentParts() {
        return accidentPartRepository.findAll();
    }

    @Override
    public AccidentPartDTO getAccidentPartById(Long id) {
        Optional<AccidentPartDTO> optionalAccidentPart = accidentPartRepository.findById(id);
        return optionalAccidentPart.orElse(null);
    }

    @Override
    @Transactional
    public AccidentPartDTO saveAccidentPart(AccidentPartDTO accidentPartDTO) {
        return accidentPartRepository.save(accidentPartDTO);
    }

    @Override
    @Transactional
    public void deleteAccidentPart(Long id) {
        accidentPartRepository.deleteById(id);
    }

    @Override
    @Transactional
    public AccidentPartDTO createAccidentPart(AccidentPartDTO accidentPartDTO) {
        return accidentPartRepository.save(accidentPartDTO);
    }

    @Override
    @Transactional
    public AccidentPartDTO updateAccidentPart(Long id, AccidentPartDTO accidentPartDTO) {
        if (accidentPartRepository.existsById(id)) {
            accidentPartDTO.setId(id);
            return accidentPartRepository.save(accidentPartDTO);
        }
        return null;
    }
}
