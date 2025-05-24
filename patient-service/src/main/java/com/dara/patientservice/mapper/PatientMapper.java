package com.dara.patientservice.mapper;

import com.dara.patientservice.dto.PatientRequestDto;
import com.dara.patientservice.dto.PatientResponseDto;
import com.dara.patientservice.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    @Mapping(source = "date_of_birth", target = "dateOfBirth", dateFormat = "yyyy-MM-dd")
    PatientResponseDto toPatientResponseDto(Patient patient);
    @Mapping(source = "dateOfBirth", target = "date_of_birth", dateFormat = "yyyy-MM-dd")
    @Mapping(source = "registerDate", target = "registered_date", dateFormat = "yyyy-MM-dd")
    Patient toPatient(PatientRequestDto patientDto);

}
