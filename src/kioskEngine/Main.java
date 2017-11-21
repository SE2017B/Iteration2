/*
* Software Engineering 3733, Worcester Polytechnic Institute
* Team H
* Code produced for Iteration1
* Original author(s): Erica Snow, Vojta Mosby, Tyrone Patterson
* The following code
*/

package kioskEngine;

import database.*;
import map.HospitalMap;
import map.Node;
import controllers.ScreenController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import DepartmentSubsystem.ServiceRequest;
import DepartmentSubsystem.Staff;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        ScreenController myScreenController = new ScreenController();
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

    public static void main(String[] args) throws SQLException {
        try {
            DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        mainDatabase testConnection1 = new mainDatabase();


        nodeDatabase.createNodeTable();
        edgeDatabase.createEdgeTable();

        nodeDatabase.readNodeCSV("MapAnodes.csv");
        nodeDatabase.readNodeCSV("MapBnodes.csv");
        nodeDatabase.readNodeCSV("MapCnodes.csv");
        nodeDatabase.readNodeCSV("MapDnodes.csv");
        nodeDatabase.readNodeCSV("MapEnodes.csv");
        nodeDatabase.readNodeCSV("MapFnodes.csv");
        nodeDatabase.readNodeCSV("MapGnodes.csv");
        nodeDatabase.readNodeCSV("MapHnodes.csv");
        nodeDatabase.readNodeCSV("MapInodes.csv");
        nodeDatabase.readNodeCSV("MapWnodes.csv");
        nodeDatabase.insertNodesFromCSV();

        edgeDatabase.readEdgesCSV("MapAedges.csv");
        edgeDatabase.readEdgesCSV("MapBedges.csv");
        edgeDatabase.readEdgesCSV("MapCedges.csv");
        edgeDatabase.readEdgesCSV("MapDedges.csv");
        edgeDatabase.readEdgesCSV("MapEedges.csv");
        edgeDatabase.readEdgesCSV("MapFedges.csv");
        edgeDatabase.readEdgesCSV("MapGedges.csv");
        edgeDatabase.readEdgesCSV("MapHedges.csv");
        edgeDatabase.readEdgesCSV("MapIedges.csv");
        edgeDatabase.readEdgesCSV("MapWedges.csv");
        edgeDatabase.insertEdgesFromCSV();

        nodeDatabase.queryAllNodes();
        edgeDatabase.queryAllEdges();
        nodeDatabase.cntNodes();

        launch(args);

        nodeDatabase.outputNodesCSV();
        edgeDatabase.outputEdgesCSV();
    }
}
