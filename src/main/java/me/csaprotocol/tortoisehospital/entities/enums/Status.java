package me.csaprotocol.tortoisehospital.entities.enums;

import javafx.scene.paint.Color;
import lombok.Getter;

@Getter
public enum Status {
    Compromised(1, Color.RED),
    DeepWounds(2, Color.ORANGE),
    SuperficialWounds(3, Color.CYAN),
    Normal(4, Color.GREEN),
    Perfect(5, Color.WHITE);

    private final int value;
    private final Color color;

    Status(int value, Color color) {
        this.value = value;
        this.color = color;
    }
}
