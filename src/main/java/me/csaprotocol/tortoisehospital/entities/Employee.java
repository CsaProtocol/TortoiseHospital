package me.csaprotocol.tortoisehospital.entities;

import lombok.Getter;
import lombok.Setter;
import me.csaprotocol.tortoisehospital.entities.enums.Profile;

import java.time.LocalDate;

@Getter @Setter
public class Employee {

    private String ID;
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private Profile profileType;

}
