package database;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileNotFoundException;

public class mainDatabase {

    private static final String DRIVER="org.apache.derby.jdbc.EmbeddedDriver";
    private static final String JDBC_URL="jdbc:derby:teamHDB;create=true";

    Connection conn;

    // Variables for storing nodes after reading csv file
    String[] nodeValues;
    ArrayList<String>nodeID=new ArrayList<String>();
    ArrayList<String>xCoord=new ArrayList<String>();
    ArrayList<String>yCoord=new ArrayList<String>();
    ArrayList<String>floor=new ArrayList<String>();
    ArrayList<String>building=new ArrayList<String>();
    ArrayList<String> nodeType=new ArrayList<String>();
    ArrayList<String>longName=new ArrayList<String>();
    ArrayList<String>shortName=new ArrayList<String>();
    ArrayList<String>teamAssigned=new ArrayList<String>();

    // Variables for storing edges after reading csv file
    String[] edgeValues;
    ArrayList<String>edgeID=new ArrayList<String>();
    ArrayList<String>startNode=new ArrayList<String>();
    ArrayList<String>endNode=new ArrayList<String>();


    public mainDatabase() throws SQLException {

        // Connect to embedded database
        try {

            this.conn = DriverManager.getConnection(JDBC_URL);
            conn.setAutoCommit(false);

            if (this.conn != null) {
                System.out.println("Connected!");
            }
            conn.close();
        }

        catch (SQLException e) {
            Logger.getLogger(mainDatabase.class.getName()).log(Level.SEVERE, null, e);
        }

        ///////////////////////////////////////////////////////////////////////////////
        // Read from Nodes CSV File and store columns in array lists
        ///////////////////////////////////////////////////////////////////////////////
        String nodeFile = "MapHnodes.csv";
        File nodefile = new File(nodeFile);

        try {
            Scanner inputStreamNodes = new Scanner(nodefile);
            while (inputStreamNodes.hasNext()) {

                String nodeData = inputStreamNodes.nextLine();
                nodeValues = nodeData.split(",");

                nodeID.add(nodeValues[0]);
                xCoord.add(nodeValues[1]);
                yCoord.add(nodeValues[2]);
                floor.add(nodeValues[3]);
                building.add(nodeValues[4]);
                nodeType.add(nodeValues[5]);
                longName.add(nodeValues[6]);
                shortName.add(nodeValues[7]);
                teamAssigned.add(nodeValues[8]);

            }
            inputStreamNodes.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ///////////////////////////////////////////////////////////////////////////////
        // Read from Edges CSV File and store columns in array lists
        ///////////////////////////////////////////////////////////////////////////////
        String edgesFile = "MapHedges.csv";
        File edgesfile = new File(edgesFile);

        try {
            Scanner inputStreamEdges = new Scanner(edgesfile);
            while (inputStreamEdges.hasNext()) {

                String edgeData = inputStreamEdges.nextLine();
                edgeValues = edgeData.split(",");

                edgeID.add(edgeValues[0]);
                startNode.add(edgeValues[1]);
                endNode.add(edgeValues[2]);
            }

            inputStreamEdges.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ///////////////////////////////////////////////////////////////////////////////
        // Create a table for the nodes
        ///////////////////////////////////////////////////////////////////////////////
        try {
            this.conn = DriverManager.getConnection(JDBC_URL);
            conn.setAutoCommit(false);

            DatabaseMetaData meta = conn.getMetaData();
            ResultSet res = meta.getTables(null, null, "MAPHNODES", null);

            if (!res.next()) {
                Statement stmtCreate1 = conn.createStatement();
                String createNodesTable = ("CREATE TABLE mapHNodes" +
                        "(nodeID VARCHAR(20)," +
                        "xCoord VARCHAR(20)," +
                        "yCoord VARCHAR(20)," +
                        "floor VARCHAR(20)," +
                        "buiding VARCHAR(20)," +
                        "nodeType VARCHAR(20)," +
                        "longName VARCHAR(50)," +
                        "shortName VARCHAR(30)," +
                        "teamAssigned VARCHAR(20))");

                int rsetCreate1 = stmtCreate1.executeUpdate(createNodesTable);
                System.out.println("Create Nodes table Successful!");

                conn.commit();
                stmtCreate1.close();
                conn.close();
            } else {
                Statement stmtDelete1 = conn.createStatement();
                String deleteNodesTable = ("DROP TABLE mapHNodes");
                int rsetDelete1 = stmtDelete1.executeUpdate(deleteNodesTable);
                stmtDelete1.close();

                Statement stmtCreate1 = conn.createStatement();
                String createNodesTable = ("CREATE TABLE mapHNodes" +
                        "(nodeID VARCHAR(20)," +
                        "xCoord VARCHAR(20)," +
                        "yCoord VARCHAR(20)," +
                        "floor VARCHAR(20)," +
                        "building VARCHAR(20)," +
                        "nodeType VARCHAR(20)," +
                        "longName VARCHAR(50)," +
                        "shortName VARCHAR(30)," +
                        "teamAssigned VARCHAR(20))");

                int rsetCreate1 = stmtCreate1.executeUpdate(createNodesTable);
                System.out.println("Create Nodes table Successful!");

                conn.commit();
                stmtCreate1.close();
                conn.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        ///////////////////////////////////////////////////////////////////////////////
        // Create a table for the edges
        ///////////////////////////////////////////////////////////////////////////////
        try {
            this.conn = DriverManager.getConnection(JDBC_URL);
            conn.setAutoCommit(false);

            DatabaseMetaData meta2 = conn.getMetaData();
            ResultSet res2 = meta2.getTables(null, null, "MAPHEDGES", null);

            if (!res2.next()) {
                Statement stmtCreate2 = conn.createStatement();
                String createEdgesTable = ("CREATE TABLE mapHEdges" +
                        "(edgeID VARCHAR(30)," +
                        "startNode VARCHAR(20)," +
                        "endNode VARCHAR(20))");

                int rsetCreate2 = stmtCreate2.executeUpdate(createEdgesTable);
                System.out.println("Create Edges table Successful!");

                conn.commit();
                stmtCreate2.close();
                conn.close();
            } else {
                Statement stmtDelete2 = conn.createStatement();
                String deleteNodesTable = ("DROP TABLE mapHEdges");
                int rsetDelete2 = stmtDelete2.executeUpdate(deleteNodesTable);
                stmtDelete2.close();

                Statement stmtCreate2 = conn.createStatement();
                String createEdgesTable = ("CREATE TABLE mapHEdges" +
                        "(edgeID VARCHAR(30)," +
                        "startNode VARCHAR(20)," +
                        "endNode VARCHAR(20))");

                int rsetCreate2 = stmtCreate2.executeUpdate(createEdgesTable);
                System.out.println("Create Edges table Successful!");

                conn.commit();
                stmtCreate2.close();
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ///////////////////////////////////////////////////////////////////////////////
        // Insert into nodes table using a prepared statement
        ///////////////////////////////////////////////////////////////////////////////
        try {
            this.conn = DriverManager.getConnection(JDBC_URL);
            conn.setAutoCommit(false);
            conn.getMetaData();

            PreparedStatement insertNode = conn.prepareStatement("INSERT INTO mapHNodes VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            for (int j = 1; j < nodeID.toArray().length; j++) {

                insertNode.setString(1, nodeID.toArray()[j].toString());
                insertNode.setString(2, xCoord.toArray()[j].toString());
                insertNode.setString(3, yCoord.toArray()[j].toString());
                insertNode.setString(4, floor.toArray()[j].toString());
                insertNode.setString(5, building.toArray()[j].toString());
                insertNode.setString(6, nodeType.toArray()[j].toString());
                insertNode.setString(7, longName.toArray()[j].toString());
                insertNode.setString(8, shortName.toArray()[j].toString());
                insertNode.setString(9, teamAssigned.toArray()[j].toString());

                insertNode.executeUpdate();
                System.out.println(j + ": Insert Node Successful!");
            }

            conn.commit();
            insertNode.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();// end try
        }

        ///////////////////////////////////////////////////////////////////////////////
        // Insert into edges table using a prepared statement
        ///////////////////////////////////////////////////////////////////////////////
        try {
            this.conn = DriverManager.getConnection(JDBC_URL);
            conn.setAutoCommit(false);
            conn.getMetaData();

            PreparedStatement insertEdge = conn.prepareStatement("INSERT INTO mapHEdges VALUES (?, ?, ?)");

            for (int j = 1; j < edgeID.toArray().length; j++) {

                insertEdge.setString(1, edgeID.toArray()[j].toString());
                insertEdge.setString(2, startNode.toArray()[j].toString());
                insertEdge.setString(3, endNode.toArray()[j].toString());

                insertEdge.executeUpdate();
                System.out.println(j + ": Insert Edge Successful!");
            }

            conn.commit();
            insertEdge.close();
            conn.close();

        } // end try
        catch (SQLException e) {
            e.printStackTrace();
        }

        ///////////////////////////////////////////////////////////////////////////////
        // Modify a node from the node table
        ///////////////////////////////////////////////////////////////////////////////

        // Modify item(s) from corresponding ArrayList

        // Modify item(s) from node table

        ///////////////////////////////////////////////////////////////////////////////
        // Modify an edge from the edge table
        ///////////////////////////////////////////////////////////////////////////////

        // Modify item(s) from corresponding ArrayList

        // Modify item(s) from edge table

        ///////////////////////////////////////////////////////////////////////////////
        // Delete a node from the node table
        ///////////////////////////////////////////////////////////////////////////////

        // Delete item(s) from corresponding ArrayList

        // Delete item(s) from node table

        ///////////////////////////////////////////////////////////////////////////////
        // Delete an edge from the edge table
        ///////////////////////////////////////////////////////////////////////////////

        // Delete item(s) from corresponding ArrayList

        // Delete item(s) from edge table

        ///////////////////////////////////////////////////////////////////////////////
        // Query all nodes from the node table
        ///////////////////////////////////////////////////////////////////////////////

        try {
            this.conn = DriverManager.getConnection(JDBC_URL);
            conn.setAutoCommit(false);
            conn.getMetaData();

            Statement selectAllNodes = conn.createStatement();
            String allNodes = "SELECT * FROM MAPHNODES";
            ResultSet rsetAllNodes = selectAllNodes.executeQuery(allNodes);

            String strNodeID = "";
            String strXCoord = "";
            String strYCoord = "";
            String strFloor = "";
            String strBuilding = "";
            String strNodeType = "";
            String strLongName = "";
            String strShortName = "";
            String strTeamAssigned = "";

            System.out.println("");
            System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-50s %-30s %-20s\n", "nodeID", "xcoord", "ycoord", "floor", "building", "nodeType", "longName", "shortName", "teamAssigned");

            //Process the results
            while (rsetAllNodes.next()) {
                strNodeID = rsetAllNodes.getString("nodeID");
                strXCoord = rsetAllNodes.getString("xcoord");
                strYCoord = rsetAllNodes.getString("ycoord");
                strFloor = rsetAllNodes.getString("floor");
                strBuilding = rsetAllNodes.getString("building");
                strNodeType = rsetAllNodes.getString("nodeType");
                strLongName = rsetAllNodes.getString("longName");
                strShortName = rsetAllNodes.getString("shortName");
                strTeamAssigned = rsetAllNodes.getString("teamAssigned");

                System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-50s %-30s %-20s\n", strNodeID, strXCoord, strYCoord, strFloor, strBuilding, strNodeType, strLongName, strShortName, strTeamAssigned);
            } // End While

            conn.commit();

            rsetAllNodes.close();
            selectAllNodes.close();
            conn.close();

        } // end try
        catch (SQLException e) {
            e.printStackTrace();
        }


        ///////////////////////////////////////////////////////////////////////////////
        // Query all edges from the edge table
        ///////////////////////////////////////////////////////////////////////////////

        try {
            this.conn = DriverManager.getConnection(JDBC_URL);
            conn.setAutoCommit(false);
            conn.getMetaData();

            Statement selectAllEdges = conn.createStatement();
            String allEdges = "SELECT * FROM MAPHEDGES";
            ResultSet rsetAllEdges = selectAllEdges.executeQuery(allEdges);

            String strEdgeID = "";
            String strStartNode = "";
            String strEndNode = "";

            System.out.println("");
            System.out.printf("%-30s %-20s %-20s\n", "edgeID", "startNode", "endNode");

            //Process the results
            while (rsetAllEdges.next()) {
                strEdgeID = rsetAllEdges.getString("edgeID");
                strStartNode = rsetAllEdges.getString("startNode");
                strEndNode = rsetAllEdges.getString("endNode");

                System.out.printf("%-30s %-20s %-20s\n", strEdgeID, strStartNode, strEndNode);
            } // End While

            conn.commit();

            rsetAllEdges.close();
            selectAllEdges.close();
            conn.close();

        } // end try
        catch (SQLException e) {
            e.printStackTrace();
        }

        ///////////////////////////////////////////////////////////////////////////////
        // Write to a output Nodes csv file
        ///////////////////////////////////////////////////////////////////////////////
        String outNodesFileName = "outputNodesTeamH.csv";

        try {
            FileWriter fw1 = new FileWriter(outNodesFileName, false);
            BufferedWriter bw1 = new BufferedWriter(fw1);
            PrintWriter pw1 = new PrintWriter(bw1);

            for (int j = 0; j < nodeID.toArray().length; j++) {

                pw1.println(nodeID.toArray()[j].toString() + "," +
                        xCoord.toArray()[j].toString() + "," +
                        yCoord.toArray()[j].toString() + "," +
                        floor.toArray()[j].toString() + "," +
                        building.toArray()[j].toString() + "," +
                        nodeType.toArray()[j].toString() + "," +
                        longName.toArray()[j].toString() + "," +
                        shortName.toArray()[j].toString() + "," +
                        teamAssigned.toArray()[j].toString()
                );
                System.out.println(j + ": Node Record Saved!");
            }
            pw1.flush();
            pw1.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        ///////////////////////////////////////////////////////////////////////////////
        // Write to a output Edges csv file
        ///////////////////////////////////////////////////////////////////////////////

        String outEdgesFileName = "outputEdgesTeamH.csv";

        try {
            FileWriter fw2 = new FileWriter(outEdgesFileName, false);
            BufferedWriter bw2 = new BufferedWriter(fw2);
            PrintWriter pw2 = new PrintWriter(bw2);

            for (int j = 0; j < edgeID.toArray().length; j++) {

                pw2.println(edgeID.toArray()[j].toString() + "," +
                        startNode.toArray()[j].toString() + "," +
                        endNode.toArray()[j].toString()
                );

                System.out.println(j + ": Edge Record Saved!");
            }
            pw2.flush();
            pw2.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {

        try {
            DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mainDatabase testConnection = new mainDatabase();
    }
}