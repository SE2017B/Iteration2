package a_star;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

public class HospitalMap{
    private Dictionary<String, Node> map;
    private List<Node> frontier;
    private List<Node> explored;

    public HospitalMap(){}

    public void addNode(String id, Node node){
        map.put(id, node);
    }

    public ArrayList<Node> findPath(Node start, Node end){
        //TODO
        //Also determine heuristic..should be manhattan but we can also use euclydian...up to the team
        return getNodesAsArrayList();

    }

    public void setDefault(Node defaultNode){
        //TODO
        //also ask what this does. Default = kiosk location?
    }

    public int getDistance(Node start, Node end){
        //Gonna assume this is euclydian
        double xDeltaSquared = Math.pow((end.getX()-start.getX()), 2);
        double yDeltaSquared = Math.pow((end.getY()-start.getY()), 2);
        double distance = Math.sqrt(xDeltaSquared + yDeltaSquared);
        return (int)Math.round(distance);
    }

    public ArrayList<Node> getNodesAsArrayList(){
        //STUB
        ArrayList<Node> stub = new ArrayList<Node>();
        Node a = new Node(400,200);
        a.setName("a");
        stub.add(a);
        Node b = new Node(600,200);
        b.setName("b");
        stub.add(b);
        Node c = new Node(600,400);
        c.setName("c");
        stub.add(c);
        Node d = new Node(400,400);
        d.setName("d");
        stub.add(d);
        return stub;

    }
}