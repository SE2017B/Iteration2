/*
* Software Engineering 3733, Worcester Polytechnic Institute
* Team H
* Code produced for Iteration1
* Original author(s): Erica Snow, Vojta Mosby, Tyrone Patterson
* The following code
*/

package kioskEngine;

import DepartmentSubsystem.DepartmentSubsystem;
import database.mainDatabase;
import map.HospitalMap;
import map.Node;
import controllers.ScreenController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import DepartmentSubsystem.ServiceRequest;
import DepartmentSubsystem.Staff;

public class Main extends Application {
    private static KioskEngine engine = new KioskEngine();
    @Override
    public void start(Stage primaryStage) throws Exception{
        ScreenController myScreenController = new ScreenController(engine);
        myScreenController.loadScreen(ScreenController.AddNodeID, ScreenController.AddNodeFile);
        myScreenController.loadScreen(ScreenController.LogoutID, ScreenController.LogoutFile);
        myScreenController.loadScreen(ScreenController.MainID, ScreenController.MainFile);
        myScreenController.loadScreen(ScreenController.PathID, ScreenController.PathFile);
        myScreenController.loadScreen(ScreenController.RequestID, ScreenController.RequestFile);
        myScreenController.loadScreen(ScreenController.LoginID, ScreenController.LoginFile);

        myScreenController.setScreen(ScreenController.MainID);

        Group root = new Group();
        root.getChildren().addAll(myScreenController);
        Scene scene = new Scene(root, 1280,800);
        String  style= getClass().getResource("/fxml/SceneStyle.css").toExternalForm();
        scene.getStylesheets().add(style);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        mainDatabase.readNodeCSV("MapHnodes.csv");
        mainDatabase.readNodeCSV("MapWnodes.csv");

        mainDatabase.readEdgesCSV("MapHedges.csv");
        mainDatabase.readEdgesCSV("MapWedges.csv");

        Staff testAdmin =  new Staff("test","","Admin","Admin Test",1234, engine.getService("Food"));
        Staff testStaff =  new Staff("testStaff","","food stuff","Staff Test",1234, engine.getService("Food"));

        engine.addStaffLogin(testStaff, "Food");
        engine.addStaffLogin(testAdmin, "Food");

        DepartmentSubsystem DSS = DepartmentSubsystem.getSubsystem();
        DSS.setup();

        for(Node node : HospitalMap.getNodes()){
            HospitalMap.addNode(node);
        }

        Node stub = new Node("1234567890","1000","400","01","Tower","ELEV","STUB","STUB");


        ServiceRequest req = new ServiceRequest(engine.getService("Food"),1,stub,"This is a test");
        req.giveRequest();


        launch(args);


        mainDatabase.outputNodesCSV();
        mainDatabase.outputEdgesCSV();
    }
}
