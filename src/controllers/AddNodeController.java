package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddNodeController implements ControllableScreen{
    private ScreenController parent;
    private String nodeID;
    private int x;
    private int y;
    private String floor;
    private String building;
    private String nodeType;
    private String name;

    public void setParentController(ScreenController parent){
        this.parent = parent;
    }


    @FXML
    private Label txtfldX;

    @FXML
    private Label txtfldY;

    @FXML
    private Label menubtnFloor;

    @FXML
    private Button btnEnter;

    @FXML
    private Button btnCancel;

    @FXML
    private MenuButton floorDropDown;

    @FXML
    private MenuItem floor3;

    @FXML
    private MenuItem floor2;

    @FXML
    private MenuItem floor1;

    @FXML
    private MenuItem floorG;

    @FXML
    private MenuItem floorL1;

    @FXML
    private MenuItem FloorL2;

    @FXML
    private TextField txtfldID;

    @FXML
    private Label menubtnBuilding;

    @FXML
    private MenuButton buildingDropDown;

    @FXML
    private MenuItem buildingBTM;

    @FXML
    private MenuItem buildingShapiro;

    @FXML
    private MenuItem buldingTower;

    @FXML
    private MenuItem building45F;

    @FXML
    private MenuItem building15F;

    @FXML
    private Label menubtnNodeType;

    @FXML
    private MenuButton nodeTypeDropDown;

    @FXML
    private MenuItem nTypeHALL;

    @FXML
    private MenuItem nTypeELEV;

    @FXML
    private MenuItem nTypeREST;

    @FXML
    private MenuItem nTypeSTAI;

    @FXML
    private MenuItem nTypeDEPT;

    @FXML
    private MenuItem nTypeLABS;

    @FXML
    private MenuItem nTypeINFO;

    @FXML
    private MenuItem nTypeCONF;

    @FXML
    private MenuItem nTypeEXIT;

    @FXML
    private MenuItem nTypeRETL;

    @FXML
    private MenuItem nTypeSERV;

    @FXML
    private TextField txtfldName;

    public void init(){}

    public void enterPressed(ActionEvent e){
        System.out.println("Enter Pressed");
        parent.setScreen(ScreenController.AdminMenuID);
    }

    public void cancelPressed(ActionEvent e){
        System.out.println("Cancel Pressed");
        parent.setScreen(ScreenController.AdminMenuID);
    }

    @FXML
    void buildingSelected(ActionEvent e)
    {
        System.out.println("Building Selected");
        String building = ((MenuItem) e.getSource()).getText();
        buildingDropDown.setText(building);
    }

    @FXML
    void floorSelected(ActionEvent e)
    {
        System.out.println("Floor Selected");
        String floor = ((MenuItem) e.getSource()).getText();
        floorDropDown.setText(floor);
    }

    @FXML
    void nodeTypeSelected(ActionEvent e)
    {
        System.out.println("Node Type Selected");
        String nodeType = ((MenuItem) e.getSource()).getText();
        nodeTypeDropDown.setText(nodeType);
    }
    @FXML
    void filledNodeID(ActionEvent e)
    {
        String typedNodeID = txtfldID.getText();
        System.out.println(typedNodeID);
    }



    }
