package me.csaprotocol.tortoisehospital.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class MedicalRecord {

    private LocalDate access_date;
    private double latitude;
    private double longitude;
    private LocalDate release_date = null;
    private String internalID;

}
