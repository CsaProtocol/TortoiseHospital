package me.csaprotocol.tortoisehospital.fxmlcontrollers;

import io.github.palexdev.materialfx.controls.MFXProgressSpinner;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import me.csaprotocol.tortoisehospital.Main;
import me.csaprotocol.tortoisehospital.controllers.ControllerOrchestrator;
import me.csaprotocol.tortoisehospital.controllers.GUIUtilsController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class userMenu implements Initializable {

    @FXML private BorderPane mainSceneHolder;

    //First FXML column
    @FXML private Button statisticsButton;
    @FXML private Button hospitalButton;
    @FXML private VBox subScrollCenter;
    @FXML private VBox subScrollTank;
    @FXML private TextField searchTurtleTxtField;

    @FXML private Label tankLabel;
    @FXML private ScrollPane tankScrollPane;

    //Second FXML column
    @FXML private Button searchTurtleButton;
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
    @FXML private Pane subSceneHolder;
    @FXML private Pane thirdColumn;
    @FXML private Pane fourthColumn;
    @FXML private MFXProgressSpinner progressSpinner;

    //First FXML column methods
    public void addCenterButton(String CenterID) {
        GUIUtilsController guic = new GUIUtilsController();
        subScrollCenter.getChildren().add(guic.addCenterButton(CenterID));
    }

    public void addTankButton(Integer TankID) {
        GUIUtilsController guic = new GUIUtilsController();
        subScrollTank.getChildren().add(guic.addTankButton(TankID.toString()));
    }

    public void clearTanks() {
        subScrollTank.getChildren().clear();
    }

    //Second FXML column methods
    @FXML public void onSearchTurtleButtonClick(MouseEvent event) {
        ControllerOrchestrator co = new ControllerOrchestrator();
        co.showTurtlesGUIbySearch(searchTurtleTxtField.getText());
    }

    @FXML void onAddTurtleClick(MouseEvent event) {
        ControllerOrchestrator co = new ControllerOrchestrator();
        co.showNewTurtleGUI(new Stage());
    }

    public void addTurtleButton(String TurtleID, String TurtleName) {
        GUIUtilsController guic = new GUIUtilsController();
        subScrollTurtle.getChildren().add(guic.addTurtleButton(TurtleID, TurtleName));
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
        try {
            Image img = new Image(Main.class.getResource("resources/images/center_icon.png").toString());
            this.selectedObjectImg.setImage(img);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
    public void setSelectedImgToTank() {
        try {
            Image img = new Image(Main.class.getResource("resources/images/tankLogo.png").toString());
            this.selectedObjectImg.setImage(img);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    //Third FXML column methods
    public void showSpinner(boolean visibility) {
        progressSpinner.setVisible(visibility);
    }

    public void showThirdColumn(FXMLLoader fxmlLoader) {
        try {
            subSceneHolder.getChildren().remove(thirdColumn);
            Pane subSceneToLoad = fxmlLoader.load();
            subSceneHolder.getChildren().add(subSceneToLoad);
            subSceneToLoad.prefHeight(subSceneHolder.getHeight());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showFourthColumn(FXMLLoader fxmlLoader) {
        try {
            subSceneHolder.getChildren().remove(fourthColumn);
            Pane subSceneToLoad = fxmlLoader.load();
            subSceneHolder.getChildren().add(subSceneToLoad);
            subSceneToLoad.prefHeight(subSceneHolder.getHeight());
            subSceneToLoad.setLayoutX(363);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Initialization
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        scrollPaneTurtle.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPaneTurtle.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        hospitalButton.setVisible(false);

        GUIUtilsController guic = new GUIUtilsController();
        guic.popOverCreation(addTurtleButton, "First access for a new turtle");
    }

    public void setTankVisibility(boolean visibility) {
        tankLabel.setVisible(visibility);
        tankScrollPane.setVisible(visibility);
    }

    @FXML
    void onHospitalButtonClick(MouseEvent event) {
        setTankVisibility(true);
        hospitalButton.setVisible(false);
        statisticsButton.setVisible(true);
        ControllerOrchestrator co = new ControllerOrchestrator();
        co.showSubSceneTurtlePanel();
    }

    @FXML
    void onStatisticsClick(MouseEvent event) {
        setTankVisibility(false);
        hospitalButton.setVisible(true);
        statisticsButton.setVisible(false);
        ControllerOrchestrator co = new ControllerOrchestrator();
        co.showSubSceneStatsPanel();
    }
}
