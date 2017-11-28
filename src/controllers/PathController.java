/*
* Software Engineering 3733, Worcester Polytechnic Institute
* Team H
* Code produced for Iteration1
* Original author(s): Travis Norris, Andrey Yuzvik, Oluchukwu Okafor
* The following code
*/

package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXSlider;
import exceptions.InvalidNodeException;
import javafx.animation.Transition;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.MoveTo;
import javafx.util.Duration;
import map.FloorNumber;
import map.HospitalMap;
import map.Node;
import map.Path;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.animation.PathTransition;


import ui.AnimatedCircle;
import ui.MapViewer;
import ui.proxyImagePane;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class PathController implements ControllableScreen, Observer{
    private ScreenController parent;
    private ArrayList<Node> path;
    private HospitalMap map;
    private ArrayList<Shape> shapes;
    private String startType;
    private String startFloor;
    private String endType;
    private String endFloor;


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
    private ChoiceBox<Node> startNodeChoice;

    @FXML
    private ChoiceBox<Node> endNodeChoice;

    @FXML
    private Pane mapPane;

    @FXML
    private TitledPane textDirectionsPane;



    @FXML
    private ScrollPane mapScrollPane;

    @FXML
    private JFXSlider slideBarZoom;

    @FXML
    private AnchorPane buttonHolderPane;

    @FXML
    private MenuButton startTypeMenu;

    @FXML
    private MenuButton startFloorMenu;

    @FXML
    private MenuButton endTypeMenu;

    @FXML
    private MenuButton endFloorMenu;



    private MapViewer mapViewer;

    private FloorNumber currentFloor;// the current floor where the kiosk is.

    private ArrayList<FloorNumber> floors; //list of floors available


    private HashMap<FloorNumber, ArrayList<Shape>> pathShapes;

    private HashMap<FloorNumber,ArrayList<Integer>> EdgeNodes;

    @FXML
    private JFXListView<String> directionsList;
    //Methods start here
    public void init()
    {
        map = HospitalMap.getMap();
        path = new ArrayList<Node>();
        shapes = new ArrayList<Shape>();
        pathShapes = new HashMap<FloorNumber,ArrayList<Shape>>();
        EdgeNodes= new HashMap<FloorNumber,ArrayList<Integer>>();
        currentFloor = FloorNumber.FLOOR_ONE;
        mapViewer = new MapViewer(this);
        //set up floor variables
        floors = new ArrayList<FloorNumber>();
        //floors.add(FloorNumber.fromDbMapping("1"));
        //floors.add(FloorNumber.fromDbMapping("2"));
        //add the test background image




        mapPane.getChildren().add(mapViewer.getMapImage());
        buttonHolderPane.getChildren().add(mapViewer.getPane());
        mapViewer.setScale(2);
    }

    public void onShow(){
        startNodeChoice.setItems(FXCollections.observableList(
                map.getNodesBy(n -> !n.getType().equals("HALL"))));
        endNodeChoice.setItems(FXCollections.observableList(
                map.getNodesBy(n -> !n.getType().equals("HALL"))));
        startNodeChoice.setValue(map.getKioskLocation());
        //remove any previous paths from the display
        clearPaths();

        //lines = new ArrayList<>();
        startNodeChoice.setDisable(true);
        startNodeChoice.setValue(null);
        startFloorMenu.setDisable(true);
        startFloorMenu.setText("Floor");
        startTypeMenu.setText("Type");

        endNodeChoice.setDisable(true);
        endNodeChoice.setValue(null);
        endFloorMenu.setDisable(true);
        endFloorMenu.setText("Floor");
        endTypeMenu.setText("Type");
    }
    private Circle getPoint(int x, int y){
        Circle c = new AnimatedCircle();
        c.setCenterX(x/mapViewer.getScale());
        c.setCenterY(y/mapViewer.getScale());
        c.setVisible(true);
        c.setRadius(7/mapViewer.getScale());
        return c;
    }
    private void getVars(FloorNumber floor,Node n){
        //get actual values
        int X = n.getX()/(int)mapViewer.getScale();
        int Y = n.getY()/(int)mapViewer.getScale();
        ArrayList<Integer> ans = new ArrayList<Integer>();
        if(EdgeNodes.containsKey(floor)){
            if(EdgeNodes.get(floor).get(0)<X){
                EdgeNodes.get(floor).set(0,X);
            }
            if(EdgeNodes.get(floor).get(1)<Y){
                EdgeNodes.get(floor).set(1,Y);
            }
            if(EdgeNodes.get(floor).get(2)>X){
                EdgeNodes.get(floor).set(2,X);
            }
            if(EdgeNodes.get(floor).get(3)>Y){
                EdgeNodes.get(floor).set(3,Y);
            }
        }
        else{
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(X);
            temp.add(Y);
            temp.add(X);
            temp.add(Y);
            EdgeNodes.put(floor,temp);
        }
    }
    private double toScale(int v){
        return v*mapViewer.getScale();

    }
    private void controlScroller(FloorNumber floor){
        if(EdgeNodes.containsKey(floor)){
            //find the average distance between min and max
            double x = (EdgeNodes.get(floor).get(0)+EdgeNodes.get(floor).get(2))/2;
            double y = (EdgeNodes.get(floor).get(1)+EdgeNodes.get(floor).get(3))/2;
            System.out.println("X is at "+x);
            System.out.println("Y is at "+y);
            /**
             * //Todo: Resize map to fit path if needed
            double sx = positionVars.get(floor).get(0)-positionVars.get(floor).get(2);
            double sy = positionVars.get(floor).get(1)-positionVars.get(floor).get(3);
            focustopath(sx,sy);//focus to path
             **/
            mapScrollPane.setHvalue(x/5000);
            mapScrollPane.setVvalue(y/3500);
            System.out.println("Screen Adjusted");
        }
    }
    private void focustopath(double sx,double sy){
        //resize map to fit path
        double x =Math.abs(sx);
        double y =Math.abs(sy);
        if(y>2){
            mapViewer.setScale(4);
            System.out.println("hahahaddhahaiofefjisejfonfo;wiefjw");
        }
        //if(y>x){
            //mapImage.setScale(y/5000);
        //}
        //else{
            //mapImage.setScale(x/3500);
        //}
    }

    private void setPaths(Path path){
        clearPaths();
        Node node = null;
        FloorNumber current=null; // pointer to the current node of the floor
        ArrayList<Path> Paths = new ArrayList<>();
        for(int i=0;i<path.getPath().size();i++){
            //get first floor
            node = path.getPath().get(i);
            getVars(node.getFloor(),node);//check if the node is an edge node
            if(node.getFloor()!=current){
                Paths.add(new Path());//create new path for floor
                //create a new path for floors
                current = node.getFloor();//get new floor
                System.out.println("New Current Floor "+current);
                floors.add(current);
                if(i==0){
                    currentFloor=current;
                }
                //add to path
                Paths.get(Paths.size()-1).addToPath(node);
                //add point for current node
            }
            else if(path.getPath().get(i).getFloor()==current){
                //add to path
                Paths.get(Paths.size()-1).addToPath(node);

            }
        }
        //now animate all paths
        for(Path p: Paths){
            animateFloor(p.getPath().get(0).getFloor(),p);
        }

    }
    private void animateFloor(FloorNumber floor,Path path){
        System.out.println("Animating floor "+path.toString());
        //create new hashMap element for floor if none existes
        if(!pathShapes.containsKey(floor)){
            pathShapes.put(floor,new ArrayList<Shape>());
        }

        //represent first and last nodes with animated circles
        Circle newp = getPoint(path.getPath().get(0).getX(),path.getPath().get(0).getY());
        newp.setFill(Color.RED);
        pathShapes.get(floor).add(newp);
        //getVars(current, newp);
        mapPane.getChildren().add(newp);
        shapes.add(newp);
        //add to last node
        Circle lastp = getPoint(path.getPath().get(path.getPath().size()-1).getX(),path.getPath().get(path.getPath().size()-1).getY());
        pathShapes.get(floor).add(lastp);
        //getVars(current, newp);
        mapPane.getChildren().add(lastp);
        shapes.add(lastp);


        //indicator to follow the path
        Rectangle rect = new Rectangle (0,0, 10, 10);
        rect.setVisible(true);
        rect.setFill(Color.DODGERBLUE);

        //animation that moves the indicator
        PathTransition pathTransition = new PathTransition();

        //path to follow
        javafx.scene.shape.Path p = new javafx.scene.shape.Path();
        p.setStroke(Color.RED);
        //p.setVisible(false);//let animation move along our line
        mapPane.getChildren().addAll(rect,p);
        //add path and rect to shape hash map
        pathShapes.get(floor).add(rect);
        pathShapes.get(floor).add(p);
        //add all shapes to shape
        shapes.add(rect);
        shapes.add(p);
        //to remove the red line, remove p ^

        //starting point defined by MoveTo
        p.getElements().add(new MoveTo(path.getPath().get(0).getX()/mapViewer.getScale(), path.getPath().get(0).getY()/mapViewer.getScale()));

        //line movements along drawn lines
        for(int i=1;i<path.getPath().size();i++){
            p.getElements().add(new LineTo(path.getPath().get(i).getX()/mapViewer.getScale(), path.getPath().get(i).getY()/mapViewer.getScale()));
        }
        //define the animation actions
        System.out.println("The set distance is "+ path.getDistance());
        pathTransition.setDuration(Duration.millis(path.getDistance()/0.1));//make speed constant
        pathTransition.setNode(rect);
        pathTransition.setPath(p);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(Transition.INDEFINITE);
        pathTransition.play();

    }
    //method to switch between paths when toggling between floors
    private void switchPath(FloorNumber floor){
        hideShapes(shapes);
        if(pathShapes.containsKey(floor)){
            System.out.println("Switching to path " + floor);
            //add all in to mapPane
            System.out.println("Shapes are " + pathShapes.get(floor));
            showShapes(pathShapes.get(floor));
            //adjust screen
            controlScroller(floor);
            //Update Current floor
            currentFloor=floor;
        }
    }

    public void clearPaths(){
        for(Shape s: shapes){
            s.setVisible(false);
            mapPane.getChildren().remove(s);
        }
        //clear all lines and paths
        EdgeNodes = new HashMap<>();
        pathShapes = new HashMap<>();
        floors= new ArrayList<>();
        shapes=new ArrayList<>();
        System.out.println("All entities cleared");
    }

    public void hidePaths(ArrayList<Line> thislines){
        for(Line line : thislines){
            line.setVisible(false);
            System.out.println("Line has been hidden");
        }

    }
    public void hidePoints(ArrayList<Circle> thispoints){
        for(Circle c: thispoints){
            c.setVisible(false);
            System.out.println("Point has been hidden");
        }

    }
    public void hideShapes(ArrayList<Shape> thisshapes){
        for(Shape s: thisshapes){
            s.setVisible(false);
        }
        System.out.println("Shape has been hidden");

    }
    public void showPaths(ArrayList<Line> thislines){
        for(Line line : thislines){
            line.setVisible(true);
            System.out.println("Line has been shown");
        }
    }
    public void showPoints(ArrayList<Circle> thispoints){
        for(Circle c: thispoints){
            c.setVisible(true);
            System.out.println("Point has been shown");
        }
    }
    public void showShapes(ArrayList<Shape> thisshapes){
        for(Shape s: thisshapes){
            s.setVisible(true);
        }
        System.out.println("Shape has been shown");

    }

    public void setMapScale(double scale){
        calVars();
        //reposition map
        mapViewer.setScale(scale);
    }

    public void calVars(){
        //function to help reposition the screen on zoom
        for(FloorNumber f : floors){
            //Todo: Recalculate variables if need be
        }
    }



    private Path getPath(){
        return map.findPath(startNodeChoice.getValue(),endNodeChoice.getValue());
    }
    


    public void enterPressed(ActionEvent e) throws InvalidNodeException
    {
        if(!startNodeChoice.getValue().equals(null) && !endNodeChoice.getValue().equals(null)) {
            Path thePath = getPath();

            System.out.println(thePath.toString());
            //setLines(thePath);
            setPaths(thePath);
            System.out.println(floors);
            mapViewer.setButtonsByFloor(floors);
            //add background image
            System.out.println(thePath.getDirections());
            directionsList.setItems(FXCollections.observableList(thePath.findDirections()));
            textDirectionsPane.setVisible(true);
            textDirectionsPane.setExpanded(false);
            switchPath(currentFloor);
            System.out.println("Enter Pressed");
        }
    }


    public void cancelPressed(ActionEvent e)
    {
        System.out.println("Cancel Pressed");
        clearPaths();
        parent.setScreen(ScreenController.MainID,"RIGHT");
    }

    public void stairsPressed(ActionEvent e)
    {
        System.out.println("Checked off stairs");
    }

    public void update(Observable o, Object arg){
        if(arg instanceof FloorNumber) {
            FloorNumber floor = (FloorNumber) arg;
            currentFloor = floor;//update Current floor
            System.out.println("Updating");
            switchPath(floor);
        }
    }

    //when + button is pressed zoom in map
    public void zinPressed(ActionEvent e){
        System.out.println("Zoom In Pressed");
        slideBarZoom.setValue(slideBarZoom.getValue()+0.2);
        setMapScale(4-slideBarZoom.getValue());
        //redraw animation o ensure that it is well positioned
        switchPath(currentFloor);
        controlScroller(currentFloor);
    }

    //when - button pressed zoom out map
    public void zoutPressed(ActionEvent e){
        slideBarZoom.setValue(slideBarZoom.getValue()-0.2);
        setMapScale(4-slideBarZoom.getValue());
        //redraw animation to ensure that it is well positioned
        switchPath(currentFloor);
        controlScroller(currentFloor);
    }



    public void startTypeSelected(ActionEvent e){
        startType = ((MenuItem)e.getSource()).getText();
        startTypeMenu.setText(startType);
        startFloorMenu.setDisable(false);
        if(!startFloor.equals(null)){
            startChosen();
        }
    }

    public void startFloorSelected(ActionEvent e){
        startFloor = ((MenuItem)e.getSource()).getText();
        startFloorMenu.setText(startFloor);
        startChosen();
    }

    private void startChosen(){

        String filter = "";
        if(startType.equals("Restroom")){
            filter = "REST";
        }
        else if (startType.equals("Retail")){
            filter = "RETL";
        }
        else if (startType.equals("Exits")){
            filter = "EXIT";
        }
        else if (startType.equals("Stairs")){
            filter = "STAI";
        }
        else if (startType.equals("Elevators")){
            filter = "ELEV";
        }
        else {
            filter = "INFO";
        }
        final String f = filter.toString();
        if(startFloor.equals("ALL")){
            startNodeChoice.setItems(FXCollections.observableList(map.getNodesBy( n -> n.getType().equals(f))));
        }
        else{
            FloorNumber floor = FloorNumber.fromDbMapping(startFloor);
            startNodeChoice.setItems(FXCollections.observableList(map.getNodesBy( n -> n.getType().equals(f) && n.getFloor().equals(floor))));
        }
        startNodeChoice.setDisable(false);
    }


    public void endTypeSelected(ActionEvent e){
        endType = ((MenuItem)e.getSource()).getText();
        endTypeMenu.setText(startType);
        endFloorMenu.setDisable(false);
        if(!endFloor.equals(null)){
            endChosen();
        }
    }

    public void endFloorSelected(ActionEvent e){
        endFloor = ((MenuItem)e.getSource()).getText();
        endFloorMenu.setText(endFloor);
        endChosen();
    }

    private void endChosen(){

        String filter = "";
        if(endType.equals("Restroom")){
            filter = "REST";
        }
        else if (endType.equals("Retail")){
            filter = "RETL";
        }
        else if (endType.equals("Exits")){
            filter = "EXIT";
        }
        else if (endType.equals("Stairs")){
            filter = "STAI";
        }
        else if (endType.equals("Elevators")){
            filter = "ELEV";
        }
        else {
            filter = "INFO";
        }
        final String f = filter.toString();
        if(endFloor.equals("ALL")){
            endNodeChoice.setItems(FXCollections.observableList(map.getNodesBy( n -> n.getType().equals(f))));
        }
        else{
            FloorNumber floor = FloorNumber.fromDbMapping(endFloor);
            endNodeChoice.setItems(FXCollections.observableList(map.getNodesBy( n -> n.getType().equals(f) && n.getFloor().equals(floor))));
        }
        endNodeChoice.setDisable(false);
    }
}
