package me.csaprotocol.tortoisehospital.entities;

import lombok.Getter;
import lombok.Setter;
import me.csaprotocol.tortoisehospital.entities.enums.Status;

import java.time.LocalDate;

@Getter @Setter
public class Examination {

    private Status head_status;
    private Status eyes_status;
    private Status tail_status;
    private Status fins_status;
    private Status neck_status;
    private Status beak_status;
    private Status nose_status;
    private String vet_notes;
    private LocalDate date;

}
