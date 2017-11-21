/*
* Software Engineering 3733, Worcester Polytechnic Institute
* Team H
* Code produced for Iteration 2
* Original author(s): Nicholas Fajardo, Meghana Bhatia
* The following code
*/

package DepartmentSubsystem;

import map.Node;
import java.util.Date;

public class ServiceRequest{
    private Service service;
    private int requestID;
    private Node location;
    private Date date;
    private Staff assignedPersonnel;

    public ServiceRequest(Service service, int requestID, Node location, Date date, Staff assignedPersonnel) {
        this.service = service;
        this.requestID = requestID;
        this.location = location;
        this.date = date;
        this.assignedPersonnel = assignedPersonnel;
    }

    //Getters and Setters
    public Service getService() {
        return service;
    }
    public void setService(Service service) {
        this.service = service;
    }
    public int getRequestID() {
        return requestID;
    }
    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }
    public Node getLocation() {
        return location;
    }
    public void setLocation(Node location) {
        this.location = location;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public Staff getAssignedPersonnel() {
        return assignedPersonnel;
    }
    public void setAssignedPersonnel(Staff assignedPersonnel) {
        this.assignedPersonnel = assignedPersonnel;
    }
}