package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Labeled;
import service.ServiceRequest;
import service.Staff;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RequestController implements ControllableScreen{
    private ScreenController parent;

    private ArrayList<Staff> staff;
    public void setParentController(ScreenController parent){
        this.parent = parent;
    }

    @FXML
    private Button btncreate;

    @FXML
    private Button btncancel;

    @FXML
    private MenuButton serviceDropDown;

    @FXML
    private ChoiceBox staffDropDown;

    @FXML
    private MenuButton hourDropDown;

    @FXML
    private MenuButton minDropDown;

    @FXML
    private MenuButton meridDropDown;

    @FXML
    private DatePicker dateMenu;

    public void init(){
    }

    public void createPressed(ActionEvent e){
        System.out.println("Create Pressed");
        parent.setScreen(ScreenController.AdminMenuID);
    }
    public void cancelPressed(ActionEvent e){
        System.out.println("Cancel Pressed");
        parent.setScreen(ScreenController.AdminMenuID);
    }


    public void serviceSelected(ActionEvent e){
        System.out.println("Service Selected");
        String serviceType = ((MenuItem) e.getSource()).getText();
        serviceDropDown.setText(serviceType);
        staff = ServiceRequest.getStaffForServiceType(serviceType);
        ArrayList<String> staffNames = new ArrayList<String>();
        for(Staff member : staff){
            staffNames.add(member.getFullName());
        }
        if(staffNames.size() != 0) {
            System.out.println(FXCollections.observableList(staffNames));
            staffDropDown.setItems(FXCollections.observableList(staffNames));
            staffDropDown.setDisable(false);
        }

    }

    public void hourSelected(ActionEvent e){
        System.out.println("Hour Selected");
        String hour = ((MenuItem) e.getSource()).getText();
        hourDropDown.setText(hour);
    }

    public void minSelected(ActionEvent e){
        System.out.println("Min Selected");
        String min = ((MenuItem) e.getSource()).getText();
        minDropDown.setText(min);
    }

    public void meridSelected(ActionEvent e){
        System.out.println("Merid Selected");
        String merid = ((MenuItem) e.getSource()).getText();
        meridDropDown.setText(merid);
    }

    public void dateSelected(ActionEvent e){
        System.out.println("Date Selected");
    }
}