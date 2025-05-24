package com.dara.patientservice.controller;

import com.dara.patientservice.dto.PatientRequestDto;
import com.dara.patientservice.dto.PatientResponseDto;
import com.dara.patientservice.dto.validators.CreatePatientValidatorsGroup;
import com.dara.patientservice.service.PatientService;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDto>> getAllPatients(){
        List<PatientResponseDto> allPatient = patientService.getAllPatient();
        return ResponseEntity.ok(allPatient);
    }

    @PostMapping
    public ResponseEntity<PatientResponseDto> createPatient(@Validated({Default.class, CreatePatientValidatorsGroup.class}) @RequestBody PatientRequestDto patientRequestDto){
        PatientResponseDto responseDto = patientService.createPatient(patientRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDto> updatePatient(@Valid @PathVariable UUID id, @RequestBody PatientRequestDto patientRequestDto){
        PatientResponseDto patientResponseDto = patientService.updatePatient(id, patientRequestDto);
        return ResponseEntity.ok(patientResponseDto);
    }

}
