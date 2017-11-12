package controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import service.ServiceRequest;
import service.Staff;

import java.util.ArrayList;

public class PathController implements ControllableScreen{
    private ScreenController parent;


    public void setParentController(ScreenController parent){
        this.parent = parent;
    }

    @FXML
    private Button btnenter;

    @FXML
    private Button ntncancel;

    @FXML
    private CheckBox chkstairs;

    @FXML
    private Label lblstart;

    @FXML
    private Label lblend;

    @FXML
    private Label lblstairs;

    @FXML
    private Label lbldir;

//    @FXML
//    private MenuButton startMenuB;
//
//    @FXML
//    private MenuButton endMenuB;

    @FXML
    private ChoiceBox<?> startChoice;

    @FXML
    private ChoiceBox<?> endChoice;


    //Methods start here
    public void init()
    {

    }

    public void startSelected(ActionEvent e){
//        //Start = ServiceRequest.getStaffForServiceType(serviceType);
//        ArrayList<String> destinations = new ArrayList<String>();
//        for( : destinations){
//            destinations.add(member.getFullName());
//        }
//        if(destinations.size() != 0) {
//            System.out.println(FXCollections.observableList(destinations));
//            startChoice.setItems(FXCollections.observableList(destinations));
//            startChoice.setDisable(false);
//        }

    }

    public void enterPressed(ActionEvent e)
    {
        System.out.println("Enter Pressed");
    }
    public void cancelPressed(ActionEvent e)
    {
        System.out.println("Cancel Pressed");
        parent.setScreen(ScreenController.MainID);
    }

    public void stairsPressed(ActionEvent e)
    {
        System.out.println("Checked off stairs");
    }

//    public void startSelected(ActionEvent e)
//    {
//        System.out.println("Start Selected");
//        String Start = ((MenuItem) e.getSource()).getText();
//        startMenuB.setText(Start);
//
//    }
//
//    public void endSelected(ActionEvent e)
//    {
//        System.out.println("End Selected");
//        String End = ((MenuItem) e.getSource()).getText();
//        endMenuB.setText(End);
//    }

}
