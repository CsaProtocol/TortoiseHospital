package me.csaprotocol.tortoisehospital.entities;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Center {

    private String ID;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String province;
    private String city;
    private String CAP;
    private boolean dismissed;

    public Center (String ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    @Override
    public String toString() {
        return ID + " - " + name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        if (obj == this) return true;
        Center center = (Center) obj;
        return center.ID.equals(this.ID) && center.name.equals(this.name)
            && center.email.equals(this.email) && center.phone.equals(this.phone)
            && center.address.equals(this.address) && center.province.equals(this.province)
            && center.city.equals(this.city) && center.CAP.equals(this.CAP)
            && center.dismissed == this.dismissed;
    }
}
