package me.csaprotocol.tortoisehospital.entities;

import lombok.Getter;
import lombok.Setter;
import me.csaprotocol.tortoisehospital.entities.enums.Sex;

@Getter @Setter
public class Turtle {
    private String ID;
    private String name;
    private String species;
    private Sex sex;

    public Turtle(String ID, String name) {
        this.ID = ID;
        this.name = name;
    }
}
