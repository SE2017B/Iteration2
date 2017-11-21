/*
* Software Engineering 3733, Worcester Polytechnic Institute
* Team H
* Code produced for Iteration1
* Original author(s): Travis Norris, Andrey Yuzvik
* The following code
*/

package controllers;

import com.jfoenix.controls.JFXTextField;
import exceptions.InvalidNodeException;
import map.HospitalMap;
import map.Node;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class PathController implements ControllableScreen{
    private ScreenController parent;
    private ArrayList<Node> path;

    private ArrayList<Line> lines;


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

    @FXML
    private JFXTextField startChoice;

    @FXML
    private JFXTextField endChoice;

    @FXML
    private Pane mapPane;

    @FXML
    private Label failLabel;


    //Methods start here
    public void init()
    {
        path = new ArrayList<Node>();
        lines = new ArrayList<Line>();
        onShow();

    }

    public void onShow(){
        //Update the nodes in the map

        //update the items in the checklist


        //remove any previous paths from the display
        for (Line line: lines) {
            line.setVisible(false);
            mapPane.getChildren().remove(line);
        }
        lines = new ArrayList<>();
    }

    public void diplayPath(ArrayList<Node> path){
        if( path.size() <= 1){
            System.out.println("NO PATH FOUND");
            failLabel.setVisible(true);
        } else if(path.size() > 1) {
            failLabel.setVisible(false);
            for (int i = 0; i < path.size() - 1; i++) {
                Line line = new Line();
                Node start = path.get(i);
                Node end = path.get(i + 1);
                line.setLayoutX((start.getX())/2);
                line.setLayoutY((start.getY())/2);


                line.setEndX((end.getX() - start.getX())/2);
                line.setEndY((end.getY() - start.getY())/2);

                line.setVisible(true);
                line.setStrokeWidth(5);
                mapPane.getChildren().add(line);
                lines.add(line);

            }
        }
        else{
            System.out.println("ERROR: No Path Found");
        }
    }
    public void clearPaths(){
        for(Line line : lines){
            line.setVisible(false);
            mapPane.getChildren().remove(line);
        }
    }


    public void enterPressed(ActionEvent e) throws InvalidNodeException
    {
        System.out.println("Enter Pressed");
        //Remove last path from screen
    }

    public void cancelPressed(ActionEvent e)
    {
        System.out.println("Cancel Pressed");
        clearPaths();
        parent.setScreen(ScreenController.MainID);
    }

    public void stairsPressed(ActionEvent e)
    {

        System.out.println("Checked off stairs");
    }

}
