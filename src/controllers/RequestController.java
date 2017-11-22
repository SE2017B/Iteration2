/*
* Software Engineering 3733, Worcester Polytechnic Institute
* Team H
* Code produced for Iteration1
* Original author(s): Travis Norris, Andrey Yuzvik
* The following code
*/

package controllers;

import DepartmentSubsystem.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import map.HospitalMap;
import map.Node;
import search.SearchStrategy;

import java.util.ArrayList;

public class RequestController implements ControllableScreen{
    private ScreenController parent;
    private HospitalMap map;
    private String serviceType;
    private String time;
    private Staff staffMember;
    private String date;
    private String nameServiceFile;
    private String nameDept;
    private String nameService;
    private String nameStaff;
    private String selectedAlg;
    private ArrayList<String> deps;
    private ArrayList<Service> serv;
    private DepartmentSubsystem depSub;



    private static int requestIDCount = 0;

    private ArrayList<Staff> staff;
    public void setParentController(ScreenController parent){
        this.parent = parent;
    }
    @FXML
    private Label staffNameLabel;

    @FXML
    private ChoiceBox<Department> choiceBoxDept;

    @FXML
    private MenuButton menuButtonAl;

    @FXML
    private JFXButton btncreate;

    @FXML
    private JFXButton btncancel;

    @FXML
    private JFXDatePicker dateMenu;

    @FXML
    private JFXTimePicker timeMenu;

    @FXML
    private ChoiceBox<Node> locationChoiceBox;

    @FXML
    private Pane servicePane1;

    @FXML
    private ChoiceBox<Service> choiceBoxService;

    @FXML
    private ChoiceBox<Staff> choiceBoxStaff;

    @FXML
    private Label lblSelectedService;

    @FXML
    private JFXButton btncreate1;

    @FXML
    private JFXButton btnserviceResCancel;

    @FXML
    private JFXListView<ServiceRequest> resolveServiceListView;

    @FXML
    private JFXButton btnLogOut;

    @FXML
    private JFXButton btnEditMap;



    public void init(){
        map = HospitalMap.getMap();
        //choiceBoxDept.setItems(FXCollections.observableList(testList));
        choiceBoxDept.valueProperty().addListener( (v, oldValue, newValue) -> deptSelected(newValue));
        choiceBoxService.valueProperty().addListener( (v, oldValue, newValue) -> servSelected(newValue));
        choiceBoxStaff.valueProperty().addListener( (v, oldValue, newValue) -> staffSelected(newValue));
        depSub = DepartmentSubsystem.getSubsystem();
    }

    public void onShow(){

        //Update the nodes in the map

        ArrayList<Node> nodes = map.getNodeMap();

        //todo populate list of requests upon login
        //resolveServiceListView.getItems().add(Department.getBacklog().values());
        choiceBoxDept.setItems(FXCollections.observableList(depSub.getDepartments()));


        //update the items in the checklist
        //locationChoiceBox.setItems(FXCollections.observableList(nodes));

    }
    @FXML

    public void resolveServicePressed(ActionEvent e)
    {
        //todo test?
        //resolveServiceListView.getItems().remove(selectedService);
        //List<Integer> selectedRequests = new ArrayList<Integer>(resolveServiceListView.getSelectionModel().getSelectedItems());
        resolveServiceListView.getItems().removeAll(resolveServiceListView.getSelectionModel().getSelectedItems());
        System.out.println("Requests " + (resolveServiceListView.getSelectionModel().getSelectedItems()) + "resolved");
    }

    public void requestCreatePressed(ActionEvent e)
    {
        //todo create the request
        //todo get Request ID
        //todo does this need to be added t listView or is it handled?
        requestIDCount++;
        //submitRequest(Service service, String time, String date, Node location, Staff person, int RID){
       // ServiceRequest nReq = new ServiceRequest(choiceBoxService.getValue(), requestIDCount, locationChoiceBox.getValue(), time, date, choiceBoxStaff.getValue());
        ServiceRequest nReq = new ServiceRequest(choiceBoxService.getValue(), requestIDCount, null, null, null, null);
        depSub.submitRequest(null, "1", "2", null, null, requestIDCount);
    }
    public void cancelPressed(ActionEvent e)
    {
        //todo clear the selected items
        System.out.println("Cancel Pressed");
        choiceBoxStaff.getItems().clear();
        choiceBoxService.getItems().clear();
        choiceBoxDept.getItems().clear();
        //JFXDatePicker.setTime();
        //date = JFXTimePicker
        //parent.setScreen(ScreenController.LoginID);
    }

    public void logoutPressed(ActionEvent e){
        System.out.println("Logout Pressed");
        parent.setScreen(ScreenController.LogoutID);
    }

    public void editPressed(ActionEvent e){
        System.out.println("Edit Pressed");
        parent.setScreen(ScreenController.AddNodeID);
    }

    public void selectAlgorithmPath(ActionEvent e)
    {
        //todo test?
        System.out.println("Algorithm Selected");
        selectedAlg = ((MenuItem)e.getSource()).getText();
        menuButtonAl.setText(selectedAlg);
    }

    public void deptSelected(Department newValue)
    {
        //todo fix deptSelected. Add listener. Test?
        nameDept = newValue.toString();
       // nameDept = choiceBoxDept.getValue().toString();
        choiceBoxDept.setDisable(false);
        choiceBoxService.setItems(FXCollections.observableList(depSub.getServices(nameDept)));


    }
    public void servSelected(Service newValue)
    {
        //todo fix deptSelected. Add listener. Test?
            nameService = newValue.toString();
            choiceBoxService.setDisable(false);
            choiceBoxStaff.setItems(FXCollections.observableList(depSub.getStaff(nameService)));
//            choiceBoxStaff.setDisable(false);
//            nameStaff = choiceBoxStaff.getSelectionModel().getSelectedItem().toString();
//            choiceBoxStaff.setDisable(false);

    }
    public void staffSelected(Staff newValue)
    {

        nameStaff = newValue.toString();

    }

    public void timeSelected(ActionEvent e) {
        //todo test?
        System.out.println("Time selescted");
        time = ((JFXTimePicker)e.getSource()).getValue().toString();
    }

    public void dateSelected(ActionEvent e){
        //todo test?
        System.out.println("Date Selected" );
        date = ((JFXDatePicker)e.getSource()).getValue().toString();
    }

//////////////////////////////////////////////////////////
    /////////           Settings Tab
    //////////////////////////////////////////////////////////

    @FXML
    private ChoiceBox<SearchStrategy> searchStrategyChoice;
    @FXML
    private JFXButton saveSettingsButton;

    public void saveSettingsPressed(ActionEvent e){
        if(searchStrategyChoice.getValue() != null){
            map.setSearchStrategy(searchStrategyChoice.getValue());
        }

    }
}
