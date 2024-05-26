package com.dam.tfg.MotoMammiApplicationASG.Controllers;

import com.dam.tfg.MotoMammiApplicationASG.Models.AccidentPartDTO;
import com.dam.tfg.MotoMammiApplicationASG.services.AccidentPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appInsurance/v1/accidentparts")
public class AccidentPartController {

    @Autowired
    private AccidentPartService accidentPartService;

    @GetMapping("/")
    public ResponseEntity<List<AccidentPartDTO>> getAllAccidentParts() {
        List<AccidentPartDTO> accidentParts = accidentPartService.getAllAccidentParts();
        return new ResponseEntity<>(accidentParts, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<AccidentPartDTO> createAccidentPart(@RequestBody AccidentPartDTO accidentPartDTO) {
        AccidentPartDTO createdAccidentPart = accidentPartService.createAccidentPart(accidentPartDTO);
        return new ResponseEntity<>(createdAccidentPart, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccidentPartDTO> getAccidentPartById(@PathVariable Long id) {
        AccidentPartDTO accidentPartDTO = accidentPartService.getAccidentPartById(id);
        return new ResponseEntity<>(accidentPartDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccidentPartDTO> updateAccidentPart(@PathVariable Long id, @RequestBody AccidentPartDTO accidentPartDTO) {
        AccidentPartDTO updatedAccidentPart = accidentPartService.updateAccidentPart(id, accidentPartDTO);
        return new ResponseEntity<>(updatedAccidentPart, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccidentPart(@PathVariable Long id) {
        accidentPartService.deleteAccidentPart(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
