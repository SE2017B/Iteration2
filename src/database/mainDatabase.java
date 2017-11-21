/*
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class mainDatabase {

    private static final String DRIVER="org.apache.derby.jdbc.EmbeddedDriver";

    private static final String JDBC_URL_MAP="jdbc:derby:hospitalMapDB;create=true";
    private static final String JDBC_URL_STAFF="jdbc:derby:hospitalStaffDB;create=true";

    private static Connection conn1;
    private static Connection conn2;





    /*
    public mainDatabase() throws SQLException {

        // Connect to embedded database
        try {
            conn1 = DriverManager.getConnection(JDBC_URL_MAP);
            conn1.setAutoCommit(false);

            if (conn1 != null) {
                System.out.println("Hospital Map Database Connected!");
            }
            conn1.close();
        } catch (SQLException e) {
            Logger.getLogger(mainDatabase.class.getName()).log(Level.SEVERE, null, e);
        }
        // Connect to embedded database
        try {
            conn2 = DriverManager.getConnection(JDBC_URL_STAFF);
            conn2.setAutoCommit(false);

            if (conn2 != null) {
                System.out.println("Hospital Staff Database Connected!");
            }
            conn2.close();
        } catch (SQLException e) {
            Logger.getLogger(mainDatabase.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    */


/*
    ///////////////////////////////////////////////////////////////////////////////
    // Main Database Function
    ///////////////////////////////////////////////////////////////////////////////
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

        nodeDatabase.outputNodesCSV();
        edgeDatabase.outputEdgesCSV();
        nodeDatabase.queryAllNodes();
        edgeDatabase.queryAllEdges();
        nodeDatabase.cntNodes();

    }
}
*/