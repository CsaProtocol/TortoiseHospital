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

    public Turtle(String ID, String name, String species, Sex sex) {
        this.ID = ID;
        this.name = name;
        this.species = species;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Turtle{" +
            "ID='" + ID + '\'' +
            ", name='" + name + '\'' +
            ", species='" + species + '\'' +
            ", sex='" + sex + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Turtle turtle = (Turtle) obj;
        return ID.equals(turtle.ID) &&
            name.equals(turtle.name) &&
            species.equals(turtle.species) &&
            sex.equals(turtle.sex);
    }
}
