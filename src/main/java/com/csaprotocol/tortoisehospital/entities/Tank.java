package me.csaprotocol.tortoisehospital.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor @Getter @Setter
public class Tank {

    private int TankID;
    private String centerID;
    private int capacity;

    @Override
    public String toString() {
        return "Tank{" +
                "TankID=" + TankID +
                ", centerID=" + centerID +
                ", capacity=" + capacity +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Tank tank = (Tank) obj;
        return TankID == tank.TankID &&
                capacity == tank.capacity &&
                centerID.equals(tank.centerID);
    }

}
