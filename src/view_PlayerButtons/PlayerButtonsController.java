package view_PlayerButtons;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PlayerButtonsController {

    @FXML
    Slider sliderTime;

    @FXML
    ChoiceBox choiceSpeed;

    @FXML
    ChoiceBox choiceAlgo;

    @FXML
    TextField miliSec;

    @FXML
    TextField seconds;

    @FXML
    TextField minutes;

    // public DoubleProperty rate;
    public BooleanProperty onOpenCSVTrain, onOpenCSVTest, onOpenXML, onPlay, onPause, onSpeed, onStop,
            onRewind, onForward, onAnomalyDetector;

    public PlayerButtonsController() {
        onOpenCSVTrain = new SimpleBooleanProperty();
        onOpenCSVTest = new SimpleBooleanProperty();
        onOpenXML = new SimpleBooleanProperty();
        onAnomalyDetector = new SimpleBooleanProperty();
        onPlay = new SimpleBooleanProperty();
        onPause = new SimpleBooleanProperty();
        onSpeed = new SimpleBooleanProperty();
        onStop = new SimpleBooleanProperty();
        onRewind = new SimpleBooleanProperty();
        onForward = new SimpleBooleanProperty();

        onOpenCSVTest.setValue(false);
        onOpenCSVTrain.setValue(false);
        onOpenXML.setValue(false);
        onAnomalyDetector.setValue(false);
        onPlay.setValue(false);
        onPause.setValue(false);
        onSpeed.setValue(false);
        onStop.setValue(false);
        onRewind.setValue(false);
        onForward.setValue(false);
    }

    public void init() {
        ObservableList<Double> speedList = FXCollections.observableArrayList(0.5, 1.0, 1.5, 2.0, 2.5);
        choiceSpeed.setItems(speedList);
        ObservableList<String> algoList = FXCollections.observableArrayList("Linear regression", "Z Score", "Hybrid");
        choiceAlgo.setItems(algoList);
    }

    public void onOpenCSVTrain() {
        System.out.println(onOpenCSVTrain.getValue());
        if(onOpenCSVTrain.getValue()){
            onOpenCSVTrain.setValue(false);
        }
        else {
            onOpenCSVTrain.setValue(true);
        }
    }

    public void onOpenCSVTest() {
        if(onOpenCSVTest.getValue()){
            onOpenCSVTest.setValue(false);
        }
        else {
            onOpenCSVTest.setValue(true);
        }
    }

    public void onOpenXML() {
        if(onOpenXML.getValue()){
            onOpenXML.setValue(false);
        }
        else {
            onOpenXML.setValue(true);
        }
    }
    public void onAnomalyDetector(){
        if(onAnomalyDetector.getValue()){
            onAnomalyDetector.setValue(false);
        }
        else {
            onAnomalyDetector.setValue(true);
        }
    }

    public void onPlay() {
        if(onPlay.getValue()){
            onPlay.setValue(false);
        }
        else {
            onPlay.setValue(true);
        }
    }

    public void onPause() {
        if(onPause.getValue()){
            onPause.setValue(false);
        }
        else {
            onPause.setValue(true);
        }
    }

    public void onStop() {
        if(onStop.getValue()){
            onStop.setValue(false);
        }
        else {
            onStop.setValue(true);
        }
    }

    public void onRewind() {
        if(onRewind.getValue()){
            onRewind.setValue(false);
        }
        else {
            onRewind.setValue(true);
        }
    }

    public void onForward() {
        if(onForward.getValue()){
            onForward.setValue(false);
        }
        else {
            onForward.setValue(true);
        }
    }
}
