package com.dara.patientservice.exceptions;

public class PatientDoseNotExistException extends RuntimeException {
    public PatientDoseNotExistException(String message) {
        super(message);
    }
}
