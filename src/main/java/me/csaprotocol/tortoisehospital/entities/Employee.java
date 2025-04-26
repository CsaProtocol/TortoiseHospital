package me.csaprotocol.tortoisehospital.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class Employee {

    private String ID;
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private char profileType;

    @Override
    public String toString() {
        return "Employee{" +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", profileType=" + profileType +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || getClass() != obj.getClass()) return false;
        if(obj == this) return true;
        Employee employee = (Employee) obj;
        return employee.ID.equals(this.ID) && employee.name.equals(this.name)
            && employee.lastName.equals(this.lastName) && employee.birthDate.equals(this.birthDate)
            && employee.profileType == this.profileType;
    }

}
