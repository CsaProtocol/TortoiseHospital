package me.csaprotocol.tortoisehospital.fxmlcontrollers;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialogBuilder;
import io.github.palexdev.materialfx.dialogs.MFXStageDialog;
import io.github.palexdev.mfxresources.fonts.MFXFontIcon;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import me.csaprotocol.tortoisehospital.controllers.ControllerOrchestrator;

import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.Callable;

public class dialogUtil {

    public dialogUtil() {}

    public void showDialogDeleteMeasurement(MouseEvent eventToConsume, Pane content, LocalDate date) {
        Object source = eventToConsume.getSource();
        if(!(source instanceof Button sourceButton)) {
            throw new RuntimeException("The source of the event must be a Button");
        }

        MFXGenericDialog dialogContent = MFXGenericDialogBuilder.build()
            .setContentText("Are you sure you want to delete this measurement?")
            .makeScrollable(false)
            .get();
        MFXStageDialog dialog = MFXGenericDialogBuilder.build(dialogContent)
            .toStageDialogBuilder()
            .initOwner(sourceButton.getScene().getWindow())
            .setDraggable(true)
            .setTitle("Dialogs Preview")
            .setOwnerNode(content)
            .setScrimOwner(true)
            .get();

        dialogContent.addActions(
            Map.entry(new MFXButton("Confirm"), e -> {
                ControllerOrchestrator co = new ControllerOrchestrator();
                dialog.close();
                co.handleMeasurementDeletion(date);
            }),
            Map.entry(new MFXButton("Cancel"), e -> dialog.close())
        );

        dialogContent.setMaxSize(400, 200);

        MFXFontIcon infoIcon = new MFXFontIcon("fas-circle-info", 18);
        dialogContent.setHeaderIcon(infoIcon);
        dialogContent.setHeaderText("Delete Measurement");
        dialog.showDialog();
    }

    public void showDialogDeleteTurtle(MouseEvent eventToConsume, Pane content) {
        dialogCreation(eventToConsume, content, "Are you sure you want to delete this turtle?", "Delete Turtle", () -> {
            ControllerOrchestrator co = new ControllerOrchestrator();
            co.handleTurtleDeletion();
            return null;
        });
    }

    public void showDialogReleaseTurtle(MouseEvent eventToConsume, Pane content) {
        dialogCreation(eventToConsume, content, "Are you sure you want to release this turtle?", "Release Turtle", () -> {
            ControllerOrchestrator co = new ControllerOrchestrator();
            co.handleTurtleRelease();
            return null;
        });
    }

    public void showDialogDeleteMedicalRecord(MouseEvent eventToConsume, Pane content) {
        dialogCreation(eventToConsume, content, "Are you sure you want to delete this medical record?", "Delete Medical Record", () -> {
            ControllerOrchestrator co = new ControllerOrchestrator();
            co.handleMedicalRecordDeletion();
            return null;
        });
    }

    public void showDialogDeleteExamination(MouseEvent eventToConsume, Pane content) {
        dialogCreation(eventToConsume, content, "Are you sure you want to delete this examination?", "Delete Examination", () -> {
            ControllerOrchestrator co = new ControllerOrchestrator();
            co.handleExaminationDeletion();
            return null;
        });
    }

    public void dialogCreation(MouseEvent eventToConsume, Pane content, String message, String headerText, Callable<Void> function) {
        Object source = eventToConsume.getSource();
        if(!(source instanceof Button sourceButton)) {
            throw new RuntimeException("The source of the event must be a Button");
        }

        MFXGenericDialog dialogContent = MFXGenericDialogBuilder.build()
            .setContentText(message)
            .makeScrollable(false)
            .get();

        MFXStageDialog dialog = MFXGenericDialogBuilder.build(dialogContent)
            .toStageDialogBuilder()
            .initOwner(sourceButton.getScene().getWindow())
            .setDraggable(true)
            .setTitle("Dialogs Preview")
            .setOwnerNode(content)
            .setScrimOwner(true)
            .get();

        dialogContent.addActions(
            Map.entry(new MFXButton("Confirm"), event -> {
                try {
                    dialog.close();
                    function.call();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }),
            Map.entry(new MFXButton("Cancel"), e -> dialog.close())
        );

        dialogContent.setMaxSize(400, 200);

        MFXFontIcon infoIcon = new MFXFontIcon("fas-circle-info", 18);
        dialogContent.setHeaderIcon(infoIcon);
        dialogContent.setHeaderText(headerText);
        dialog.showDialog();
    }

}
