package me.csaprotocol.tortoisehospital.entities;

import lombok.Getter;
import lombok.Setter;
import me.csaprotocol.tortoisehospital.entities.enums.status;

import java.time.LocalDate;

@Getter @Setter
public class Examination {

    private status head_status;
    private status eyes_status;
    private status tail_status;
    private status fins_status;
    private status neck_status;
    private status beak_status;
    private status nose_status;
    private String vet_notes;
    private LocalDate date;

}
