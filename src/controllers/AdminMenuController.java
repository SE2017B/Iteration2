package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import service.Staff;

public class AdminMenuController implements ControllableScreen{
    private ScreenController parent;

    public void setParentController(ScreenController parent){
        this.parent = parent;
    }
    @FXML
    private Button btnService;

    @FXML
    private Label nameLabel;

    @FXML
    private ListView requestList;

    @FXML
    private Button btnComplete;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnCancel;

    public void init(){}

    public void servicePressed(ActionEvent e){
        System.out.println("Service Pressed");
        parent.setScreen(ScreenController.RequestID);
    }

    public void completePressed(ActionEvent e){
        System.out.println("Complete Pressed");
    }

    public void editPressed(ActionEvent e){
        System.out.println("Edit Pressed");
        parent.setScreen(ScreenController.AddNodeID);
    }

    public void cancelPressed(ActionEvent e){
        System.out.println("Cancel Pressed");
        parent.setScreen(ScreenController.MainID);
    }

    private void setName (String name){
        nameLabel.setText(name);
    }

    public void setForStaff(Staff member){
        setName(member.getFullName());

    }


}
