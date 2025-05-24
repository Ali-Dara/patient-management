package com.dara.patientservice.service;

import com.dara.patientservice.dto.PatientRequestDto;
import com.dara.patientservice.dto.PatientResponseDto;
import com.dara.patientservice.exceptions.EmailAlreadyExistException;
import com.dara.patientservice.exceptions.PatientDoseNotExistException;
import com.dara.patientservice.mapper.PatientMapper;
import com.dara.patientservice.model.Patient;
import com.dara.patientservice.repository.PatientRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public PatientService(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    public List<PatientResponseDto> getAllPatient(){
        List<Patient> allPatient = patientRepository.findAll();
        return allPatient.stream().map(patientMapper::toPatientResponseDto).toList();
    }

    public PatientResponseDto createPatient(PatientRequestDto patientRequestDto){
        if(patientRepository.existsByEmail(patientRequestDto.getEmail())){
            throw new EmailAlreadyExistException("Email " + patientRequestDto.getEmail() + " already exist");
        }
        Patient  patient = patientRepository.save(patientMapper.toPatient(patientRequestDto));
        return patientMapper.toPatientResponseDto(patient);
    }

    public PatientResponseDto updatePatient(UUID id, PatientRequestDto patientRequestDto){
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new PatientDoseNotExistException("patient with id: " + id + " not find"));
        Patient updatedpatient  = patientMapper.toPatient(patientRequestDto);

        patient.setAddress(updatedpatient.getAddress());
        patient.setName(updatedpatient.getName());
        patient.setEmail(updatedpatient.getEmail());
        patient.setRegistered_date(updatedpatient.getRegistered_date());
        patient.setDate_of_birth(updatedpatient.getDate_of_birth());

        patientRepository.save(patient);
        return patientMapper.toPatientResponseDto(patient);
    }
}
