package me.csaprotocol.tortoisehospital.entities;

import lombok.Getter;
import lombok.Setter;
import me.csaprotocol.tortoisehospital.entities.enums.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

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
    private LocalDateTime date;

    @Override
    public String toString() {
        return "Examination{" +
                "head_status=" + head_status +
                ", eyes_status=" + eyes_status +
                ", tail_status=" + tail_status +
                ", fins_status=" + fins_status +
                ", neck_status=" + neck_status +
                ", beak_status=" + beak_status +
                ", nose_status=" + nose_status +
                ", vet_notes='" + vet_notes + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Examination)) return false;

        Examination examination = (Examination) o;

        if (head_status != examination.head_status) return false;
        if (eyes_status != examination.eyes_status) return false;
        if (tail_status != examination.tail_status) return false;
        if (fins_status != examination.fins_status) return false;
        if (neck_status != examination.neck_status) return false;
        if (beak_status != examination.beak_status) return false;
        if (nose_status != examination.nose_status) return false;
        if (!Objects.equals(vet_notes, examination.vet_notes)) return false;
        return Objects.equals(date, examination.date);
    }

}
