package me.csaprotocol.tortoisehospital.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class Measurement {

    private float weight;
    private float length;
    private float width;
    private LocalDate date;

    @Override
    public String toString() {
        return "Measurement{" +
                "weight=" + weight +
                ", length=" + length +
                ", width=" + width +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Measurement measurement = (Measurement) obj;
        return Float.compare(measurement.weight, weight) == 0 &&
                Float.compare(measurement.length, length) == 0 &&
                Float.compare(measurement.width, width) == 0 &&
                date.equals(measurement.date);
    }

}
