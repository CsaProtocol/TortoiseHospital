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

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "access_date=" + access_date +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", release_date=" + release_date +
                ", internalID=" + internalID +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        MedicalRecord medicalRecord = (MedicalRecord) obj;
        return Double.compare(medicalRecord.latitude, latitude) == 0 &&
                Double.compare(medicalRecord.longitude, longitude) == 0 &&
                access_date.equals(medicalRecord.access_date) &&
                release_date.equals(medicalRecord.release_date) &&
                internalID.equals(medicalRecord.internalID);
    }

}
