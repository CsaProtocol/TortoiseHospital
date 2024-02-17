package me.csaprotocol.tortoisehospital.fxmlcontrollers;

import io.github.palexdev.materialfx.controls.MFXProgressSpinner;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import me.csaprotocol.tortoisehospital.Main;
import me.csaprotocol.tortoisehospital.controllers.EventController;
import me.csaprotocol.tortoisehospital.fxmlcontrollers.usermenu.turtleButton;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class userMenu implements Initializable {

    //First FXML column
    @FXML private Button statisticsButton;
    @FXML private VBox subScrollCenter;
    @FXML private VBox subScrollTank;
    @FXML private TextField searchTurtleTxtField;

    //Second FXML column
    @FXML private Button addTurtleButton;
    @FXML private ScrollPane scrollPaneTurtle;
    @FXML private VBox subScrollTurtle;
    @FXML private ImageView selectedObjectImg;
    @FXML private Label selectedObjectFirstLabel;
    @FXML private Label selectedObjectSecondLabel;
    @FXML private Label selectedObjectThirdLabel;
    @FXML private Label selectedObjectFourthLabel;
    @FXML private Label selectedObjectFifthLabel;

    //Third FXML column
    @FXML private MFXProgressSpinner progressSpinner;

    //First FXML column methods
    public void addCenterButton(String CenterID) {
        Button newButton = new Button();
        try {
            newButton = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("resources/fxml/usermenuResources/centerButton.fxml")));
            newButton.setText(CenterID);
        } catch (IOException e) {
            e.printStackTrace();
        }
        subScrollCenter.getChildren().add(newButton);
    }

    public void addTankButton(Integer TankID) {
        Button newButton = new Button();
        try {
            newButton = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("resources/fxml/usermenuResources/tankButton.fxml")));
            newButton.setText(TankID.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        subScrollTank.getChildren().add(newButton);
    }

    public void clearTanks() {
        subScrollTank.getChildren().clear();
    }

    //Second FXML column methods
    public void addTurtleButton(String TurtleID, String TurtleName) {
        Node newButton = new Button();
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(Main.class.getResource("resources/fxml/usermenuResources/turtleButton.fxml")));
            newButton = loader.load();
            turtleButton co = loader.getController();
            co.setIdTurtleLabel(TurtleID);
            co.setNameTurtleLabel(TurtleName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        subScrollTurtle.getChildren().add(newButton);
    }

    public void clearTurtles() {
        subScrollTurtle.getChildren().clear();
    }

    //Setters for selected Center/Tank
    public void setFirstLabel(String toSet) {
        this.selectedObjectFirstLabel.setText(toSet);
    }
    public void setSecondLabel(String toSet) {
        this.selectedObjectSecondLabel.setText(toSet);
    }
    public void setThirdLabel(String toSet) { this.selectedObjectThirdLabel.setText(toSet); }
    public void setFourthLabel(String toSet, boolean visibility) { this.selectedObjectFourthLabel.setText(toSet);
        this.selectedObjectFourthLabel.setVisible(visibility); }
    public void setFifthLabel(String toSet, boolean visibility) { this.selectedObjectFifthLabel.setText(toSet);
        this.selectedObjectFifthLabel.setVisible(visibility); }
    public void setSelectedImgToCenter() {
        Image img = new Image(Main.class.getResource("resources/images/center_icon.png").toString());
        this.selectedObjectImg.setImage(img);
    }
    public void setSelectedImgToTank() {
        Image img = new Image(Main.class.getResource("resources/images/tankLogo.png").toString());
        this.selectedObjectImg.setImage(img);
    }


    //Third FXML column methods
    public void showSpinner() {
        progressSpinner.setVisible(!progressSpinner.isVisible());
    }

    //Initialization
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        scrollPaneTurtle.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPaneTurtle.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }
}
