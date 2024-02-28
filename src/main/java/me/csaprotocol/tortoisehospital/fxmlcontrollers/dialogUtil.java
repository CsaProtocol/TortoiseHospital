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

public class dialogUtil {

    public dialogUtil() {}

    public void showDialogDeleteTurtle(MouseEvent eventToConsume, Pane content) {
        Object source = eventToConsume.getSource();
        if(!(source instanceof Button sourceButton)) {
            throw new RuntimeException("The source of the event must be a Button");
        }

        MFXGenericDialog dialogContent = MFXGenericDialogBuilder.build()
            .setContentText("Are you sure you want to delete this turtle?")
            .makeScrollable(true)
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
            Map.entry(new MFXButton("Confirm"), event2 -> {
                ControllerOrchestrator co = new ControllerOrchestrator();
                dialog.close();
                co.handleTurtleDeletion();
            }),
            Map.entry(new MFXButton("Cancel"), event2 -> dialog.close())
        );

        dialogContent.setMaxSize(400, 200);

        MFXFontIcon infoIcon = new MFXFontIcon("fas-circle-info", 18);
        dialogContent.setHeaderIcon(infoIcon);
        dialogContent.setHeaderText("Delete Turtle");
        dialog.showDialog();
    }

    public void showDialogReleaseTurtle(MouseEvent eventToConsume, Pane content) {
        Object source = eventToConsume.getSource();
        if(!(source instanceof Button sourceButton)) {
            throw new RuntimeException("The source of the event must be a Button");
        }

        MFXGenericDialog dialogContent = MFXGenericDialogBuilder.build()
            .setContentText("Are you sure you want to release this turtle?")
            .makeScrollable(true)
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
            Map.entry(new MFXButton("Confirm"), event2 -> {
                ControllerOrchestrator co = new ControllerOrchestrator();
                dialog.close();
                co.handleTurtleRelease();
            }),
            Map.entry(new MFXButton("Cancel"), event2 -> dialog.close())
        );

        dialogContent.setMaxSize(400, 200);

        MFXFontIcon infoIcon = new MFXFontIcon("fas-circle-info", 18);
        dialogContent.setHeaderIcon(infoIcon);
        dialogContent.setHeaderText("Release Turtle");
        dialog.showDialog();
    }

    public void showDialogDeleteMeasurement(MouseEvent eventToConsume, Pane content, LocalDate date) {
        Object source = eventToConsume.getSource();
        if(!(source instanceof Button sourceButton)) {
            throw new RuntimeException("The source of the event must be a Button");
        }

        MFXGenericDialog dialogContent = MFXGenericDialogBuilder.build()
            .setContentText("Are you sure you want to delete this measurement?")
            .makeScrollable(true)
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
            Map.entry(new MFXButton("Confirm"), event2 -> {
                ControllerOrchestrator co = new ControllerOrchestrator();
                dialog.close();
                co.handleMeasurementDeletion(date);
            }),
            Map.entry(new MFXButton("Cancel"), event2 -> dialog.close())
        );

        dialogContent.setMaxSize(400, 200);

        MFXFontIcon infoIcon = new MFXFontIcon("fas-circle-info", 18);
        dialogContent.setHeaderIcon(infoIcon);
        dialogContent.setHeaderText("Delete Measurement");
        dialog.showDialog();
    }

    public void showDialogDeleteMedicalRecord(MouseEvent eventToConsume, Pane content) {
        Object source = eventToConsume.getSource();
        if(!(source instanceof Button sourceButton)) {
            throw new RuntimeException("The source of the event must be a Button");
        }

        MFXGenericDialog dialogContent = MFXGenericDialogBuilder.build()
            .setContentText("Are you sure you want to delete this medical record?")
            .makeScrollable(true)
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
            Map.entry(new MFXButton("Confirm"), event2 -> {
                ControllerOrchestrator co = new ControllerOrchestrator();
                dialog.close();
                co.handleMedicalRecordDeletion();
            }),
            Map.entry(new MFXButton("Cancel"), event2 -> dialog.close())
        );

        dialogContent.setMaxSize(400, 200);

        MFXFontIcon infoIcon = new MFXFontIcon("fas-circle-info", 18);
        dialogContent.setHeaderIcon(infoIcon);
        dialogContent.setHeaderText("Delete Medical Record");
        dialog.showDialog();
    }

    public void showDialogDeleteExamination(MouseEvent eventToConsume, Pane content) {
        Object source = eventToConsume.getSource();
        if(!(source instanceof Button sourceButton)) {
            throw new RuntimeException("The source of the event must be a Button");
        }

        MFXGenericDialog dialogContent = MFXGenericDialogBuilder.build()
            .setContentText("Are you sure you want to delete this examination?")
            .makeScrollable(true)
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
            Map.entry(new MFXButton("Confirm"), event2 -> {
                ControllerOrchestrator co = new ControllerOrchestrator();
                dialog.close();
                co.handleExaminationDeletion();
            }),
            Map.entry(new MFXButton("Cancel"), event2 -> dialog.close())
        );

        dialogContent.setMaxSize(400, 200);

        MFXFontIcon infoIcon = new MFXFontIcon("fas-circle-info", 18);
        dialogContent.setHeaderIcon(infoIcon);
        dialogContent.setHeaderText("Delete Examination");
        dialog.showDialog();
    }
}
