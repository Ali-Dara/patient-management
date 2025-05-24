package com.dara.patientservice.controller;

import com.dara.patientservice.dto.PatientRequestDto;
import com.dara.patientservice.dto.PatientResponseDto;
import com.dara.patientservice.dto.validators.CreatePatientValidatorsGroup;
import com.dara.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patient")
@Tag(name = "Patient", description = "Api for patient management")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    @Operation(summary = "Get All Patient")
    public ResponseEntity<List<PatientResponseDto>> getAllPatients(){
        List<PatientResponseDto> allPatient = patientService.getAllPatient();
        return ResponseEntity.ok(allPatient);
    }

    @PostMapping
    @Operation(summary = "Create Patient")
    public ResponseEntity<PatientResponseDto> createPatient(@Validated({Default.class, CreatePatientValidatorsGroup.class}) @RequestBody PatientRequestDto patientRequestDto){
        PatientResponseDto responseDto = patientService.createPatient(patientRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Patient")
    public ResponseEntity<PatientResponseDto> updatePatient(@Valid @PathVariable UUID id, @RequestBody PatientRequestDto patientRequestDto){
        PatientResponseDto patientResponseDto = patientService.updatePatient(id, patientRequestDto);
        return ResponseEntity.ok(patientResponseDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Patient")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id){
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

}
