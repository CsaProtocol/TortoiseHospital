package me.csaprotocol.tortoisehospital.fxmlcontrollers;

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
    @FXML private Button addRecordButton;
    @FXML private Button addTurtleButton;
    @FXML private Button addTurtleButton1;
    @FXML private Button addTurtleButton2;
    @FXML private VBox centerScroll;
    @FXML private ScrollPane scrollPaneTurtle;
    @FXML private VBox turtleScroll;
    @FXML private TextField searchTurtleTxtField;
    @FXML private FlowPane selectedTurtle;
    @FXML private Button statsButton;
    @FXML private VBox tankScroll;
    @FXML private Label firstLabelSelected;
    @FXML private Label secondLabelSelected;
    @FXML private Label thirdLabelSelected;
    @FXML private Label fourthLabelSelected;
    @FXML private Label fifthLabelSelected;
    @FXML private ImageView selectedCenterTank;

    public void addCenterButton(String CenterID) {
        Button newButton = new Button();
        try {
            newButton = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("resources/fxml/usermenuResources/centerButton.fxml")));
            newButton.setText(CenterID);
        } catch (IOException e) {
            e.printStackTrace();
        }
        centerScroll.getChildren().add(newButton);
    }

    public void addTankButton(Integer TankID) {
        Button newButton = new Button();
        try {
            newButton = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("resources/fxml/usermenuResources/tankButton.fxml")));
            newButton.setText(TankID.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        tankScroll.getChildren().add(newButton);
    }

    public void clearTanks() {
        tankScroll.getChildren().clear();
    }

    public void clearTurtles() {
        turtleScroll.getChildren().clear();
    }

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
        turtleScroll.getChildren().add(newButton);
    }

    public void throwTurtleClickEvent() {
        EventController co = new EventController();
        co.throwTurtleEvent();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        scrollPaneTurtle.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPaneTurtle.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }


    //Setters for selected Center/Tank
    public void setFirstLabel(String toSet) {
        this.firstLabelSelected.setText(toSet);
    }
    public void setSecondLabel(String toSet) {
        this.secondLabelSelected.setText(toSet);
    }
    public void setThirdLabel(String toSet) { this.thirdLabelSelected.setText(toSet); }
    public void setFourthLabel(String toSet, boolean visibility) { this.fourthLabelSelected.setText(toSet);
        this.fourthLabelSelected.setVisible(visibility); }
    public void setFifthLabel(String toSet, boolean visibility) { this.fifthLabelSelected.setText(toSet);
        this.fifthLabelSelected.setVisible(visibility);}
    public void setSelectedImgToCenter() {
        Image img = new Image(Main.class.getResource("resources/images/center_icon.png").toString());
        this.selectedCenterTank.setImage(img);
    }
    public void setSelectedImgToTank() {
        Image img = new Image(Main.class.getResource("resources/images/tankLogo.png").toString());
        this.selectedCenterTank.setImage(img);
    }
}
