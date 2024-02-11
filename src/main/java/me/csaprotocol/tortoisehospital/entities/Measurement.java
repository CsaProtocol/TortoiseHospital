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

}
