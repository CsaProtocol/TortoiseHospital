package me.csaprotocol.tortoisehospital.entities.enums;

import javafx.scene.paint.Color;
import lombok.Getter;

@Getter
public enum Status {
    Compromised(Color.RED, 2),
    DeepWounds(Color.ORANGE, 3),
    SuperficialWounds(Color.CYAN, 4),
    Normal(Color.GREEN, 5),
    Perfect(Color.WHITE, 6);

    private final Color color;
    private final int value;

    Status(Color color, int value) {
        this.color = color;
        this.value = value;
    }
}
