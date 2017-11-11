package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;
import javafx.scene.*;

public class FilterController implements ControllableScreen{
    private ScreenController parent;

    public void setParentController(ScreenController parent){
        this.parent = parent;
    }

    @FXML
    private CheckBox checkBoxBathrooms;

    @FXML
    private CheckBox checkBoxExits;

    @FXML
    private Button btncancel;

    @FXML
    private ImageView bathIcon1;

    @FXML
    private ImageView exitIcon1;
    @FXML
    private ImageView exitIcon2;

    @FXML
    public void cancelPressed(ActionEvent e)
    {
        System.out.println("Cancel Pressed");
        parent.setScreen(ScreenController.MainID);
    }
    @FXML
    public void bathRoomChecked(ActionEvent e)
    {
        if(checkBoxBathrooms.isSelected())
        {
            bathIcon1.setOpacity(1.0);
            System.out.println("bathroom is checked");
        }
        else
        {
            bathIcon1.setOpacity(0.0);
        }
    }
    @FXML
    public void exitChecked(ActionEvent e)
    {
        if(checkBoxExits.isSelected())
        {
            exitIcon1.setOpacity(1.0);
            exitIcon2.setOpacity(1.0);
            System.out.println("exits is checked");
        }
        else
        {
            exitIcon1.setOpacity(0.0);
            exitIcon2.setOpacity(0.0);
        }
    }

}
