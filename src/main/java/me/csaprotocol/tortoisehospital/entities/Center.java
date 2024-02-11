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

}
