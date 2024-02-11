package me.csaprotocol.tortoisehospital.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor @Getter @Setter
public class Tank {

    private int TankID;
    private String centerID;
    private int capacity;

}
