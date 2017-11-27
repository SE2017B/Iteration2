package TestSearch;

import map.HospitalMap;
import map.Node;
import org.junit.Before;
import org.junit.Test;
import search.BeamSearch;

import static org.junit.Assert.assertEquals;

public class BeamSearchTest {
    private Node N1 = new Node("A1", "0", "0", "L1", "Tower", "Bathroom", "Long1", "Short1", "H");
    private Node N2 = new Node("A2", "0", "200", "L1", "Tower", "Desk", "Long2", "Short2", "H");
    private Node N3 = new Node("A3", "0", "400", "L1", "Tower", "Desk", "Long3", "Short3", "H");
    private Node N4 = new Node("A4", "100", "100", "L1", "Tower", "Desk", "Long4", "Short4", "H");
    private Node N5 = new Node("A5", "100", "300", "L1", "Tower", "Stairs", "Long5", "Short5", "H");
    private Node N6 = new Node("A6", "200", "0", "L1", "Tower", "Desk", "Long6", "Short6", "H");
    private Node N7 = new Node("A7", "200", "100", "L1", "Tower", "Desk", "Long7", "Short7", "H");
    private Node N8 = new Node("A8", "200", "300", "L1", "Tower", "Bathroom", "Long8", "Short8", "H");
    private Node N9 = new Node("A9", "200", "400", "L1", "Tower", "Elevator", "Long9", "Short9", "H");
    private Node N10 = new Node("A10", "300", "100", "L1", "Tower", "Desk", "Long10", "Short10", "H");
    private Node N11 = new Node("A11", "300", "300", "L1", "Tower", "Bathroom", "Long11", "Short11", "H");
    private Node N12 = new Node("A12", "400", "0", "L1", "Tower", "Desk", "Long12", "Short12", "H");
    private Node N13 = new Node("A13", "400", "100", "L1", "Tower", "Desk", "Long13", "Short13", "H");
    private Node N14 = new Node("A14", "400", "300", "L1", "Tower", "Desk", "Long14", "Short14", "H");
    private Node N15 = new Node("A15", "400", "400", "L1", "Tower", "Stairs", "Long15", "Short15", "H");
    private Node N16 = new Node("A16", "600", "0", "L1", "Tower", "Office", "Long16", "Short16", "H");
    private Node N17 = new Node("A17", "600", "100", "L1", "Tower", "Office", "Long17", "Short17", "H");
    private Node N18 = new Node("A18", "500", "300", "L1", "Tower", "Bathroom", "Long18", "Short18", "H");
    private Node N19 = new Node("A19", "500", "400", "L1", "Tower", "Stairs", "Long19", "Short19", "H");
    private Node N20 = new Node("A20", "700", "100", "L1", "Tower", "Elevator", "Long20", "Short20", "H");
    private Node N21 = new Node("A21", "700", "300", "L1", "Tower", "Stairs", "Long21", "Short21", "H");
    private Node N22 = new Node("A22", "700", "400", "L1", "Tower", "Desk", "Long22", "Short22", "H");
    private Node N23 = new Node("A23", "800", "400", "L1", "Tower", "Desk", "Long23", "Short23", "H");

    private Node N1_2 = new Node("A1_2", "0", "0", "L2", "Tower", "Bathroom", "Long1_2", "Short1_2", "H");
    private Node N2_2 = new Node("A2_2", "0", "200", "L2", "Tower", "Desk", "Long2_2", "Short2_2", "H");
    private Node N3_2 = new Node("A3_2", "0", "400", "L2", "Tower", "Desk", "Long3_2", "Short3_2", "H");
    private Node N4_2 = new Node("A4_2", "100", "100", "L2", "Tower", "Desk", "Long4_2", "Short4_2", "H");
    private Node N5_2 = new Node("A5_2", "100", "300", "L2", "Tower", "Stairs", "Long5_2", "Short5_2", "H");
    private Node N6_2 = new Node("A6_2", "200", "0", "L2", "Tower", "Desk", "Long6_2", "Short6_2", "H");
    private Node N7_2 = new Node("A7_2", "200", "100", "L2", "Tower", "Desk", "Long7_2", "Short7_2", "H");
    private Node N8_2 = new Node("A8_2", "200", "300", "L2", "Tower", "Bathroom", "Long8_2", "Short8_2", "H");
    private Node N9_2 = new Node("A9_2", "200", "400", "L2", "Tower", "Elevator", "Long9_2", "Short9_2", "H");
    private Node N10_2 = new Node("A10_2", "300", "100", "L2", "Tower", "Desk", "Long10_2", "Short10_2", "H");
    private Node N11_2 = new Node("A11_2", "300", "300", "L2", "Tower", "Bathroom", "Long11_2", "Short11_2", "H");
    private Node N12_2 = new Node("A12_2", "400", "0", "L2", "Tower", "Desk", "Long12_2", "Short12_2", "H");
    private Node N13_2 = new Node("A13_2", "400", "100", "L2", "Tower", "Desk", "Long13_2", "Short13_2", "H");
    private Node N14_2 = new Node("A14_2", "400", "300", "L2", "Tower", "Desk", "Long14_2", "Short14_2", "H");
    private Node N15_2 = new Node("A15_2", "400", "400", "L2", "Tower", "Stairs", "Long15_2", "Short15_2", "H");
    private Node N16_2 = new Node("A16_2", "600", "0", "L2", "Tower", "Office", "Long16_2", "Short16_2", "H");
    private Node N17_2 = new Node("A17_2", "600", "100", "L2", "Tower", "Office", "Long17_2", "Short17_2", "H");
    private Node N18_2 = new Node("A18_2", "500", "300", "L2", "Tower", "Bathroom", "Long18_2", "Short18_2", "H");
    private Node N19_2 = new Node("A19_2", "500", "400", "L2", "Tower", "Stairs", "Long19_2", "Short19_2", "H");
    private Node N20_2 = new Node("A20_2", "700", "100", "L2", "Tower", "Elevator", "Long20_2", "Short20_2", "H");
    private Node N21_2 = new Node("A21_2", "700", "300", "L2", "Tower", "Stairs", "Long21_2", "Short21_2", "H");
    private Node N22_2 = new Node("A22_2", "700", "400", "L2", "Tower", "Desk", "Long22_2", "Short22_2", "H");
    private Node N23_2 = new Node("A23_2", "800", "400", "L2", "Tower", "Desk", "Long23_2", "Short23_2", "H");


    private BeamSearch search = new BeamSearch(4);

    @Before
    public void initialize() {
        //Creating nodes for the map

        //Add connections for nodeOne
        N1.addConnection(N2);
        N1.addConnection(N6);

        //Add connections for nodeTwo
        N2.addConnection(N3);

        //Add connections for nodeThree
        N3.addConnection(N9);

        //Add connections for nodeFour
        N4.addConnection(N7);
        N4.addConnection(N5);

        //Add connections for nodeFive
        N5.addConnection(N8);

        //Add connections for nodeSix

        //Add connections for nodeSeven
        N7.addConnection(N6);
        N7.addConnection(N8);
        N7.addConnection(N10);

        //Add connections for nodeEight
        N8.addConnection(N9);

        //Add connections for nodeNine
        N9.addConnection(N15);
        N9.addConnection(N9_2);

        //Add connections for nodeTen
        N10.addConnection(N11);

        //Add connections for nodeEleven
        N11.addConnection(N14);

        //Add connections for nodeTwelve
        N12.addConnection(N13);
        N12.addConnection(N16);

        //Add connections for nodeThirteen
        N13.addConnection(N14);
        N13.addConnection(N17);

        //Add connections for nodeFourteen
        N14.addConnection(N15);
        N14.addConnection(N18);

        //Add connections for nodeFifteen

        //Add connections for nodeSixteen
        N16.addConnection(N17);

        //Add connections for nodeSeventeen
        N17.addConnection(N20);

        //Add connections for nodeEighteen
        N18.addConnection(N19);
        N18.addConnection(N21);

        //Add connections for nodeNineteen
        N19.addConnection(N22);

        //Add connections for nodeTwenty
        N20.addConnection(N21);
        N20.addConnection(N20_2);

        //Add connections for nodeTwentyOne
        N21.addConnection(N23);
        N21.addConnection(N22);

        //Add connections for nodeTwentyTwo

        //Add connections for nodeTwentyThree

        //Add connections for nodeOne_1
        N1_2.addConnection(N2_2);
        N1_2.addConnection(N6_2);

        //Add connections for nodeTwo_2
        N2_2.addConnection(N3_2);

        //Add connections for nodeThree_2
        N3_2.addConnection(N9_2);

        //Add connections for nodeFour_2
        N4_2.addConnection(N7_2);
        N4_2.addConnection(N5_2);

        //Add connections for nodeFive_2
        N5_2.addConnection(N8_2);

        //Add connections for nodeSix_2

        //Add connections for nodeSeven_2
        N7_2.addConnection(N6_2);
        N7_2.addConnection(N8_2);
        N7_2.addConnection(N10_2);

        //Add connections for nodeEight_2
        N8_2.addConnection(N9_2);

        //Add connections for nodeNine_2
        N9_2.addConnection(N15_2);

        //Add connections for nodeTen_2
        N10_2.addConnection(N11_2);

        //Add connections for nodeEleven_2
        N11_2.addConnection(N14_2);

        //Add connections for nodeTwelve_2
        N12_2.addConnection(N13_2);
        N12_2.addConnection(N16_2);

        //Add connections for nodeThirteen_2
        N13_2.addConnection(N14_2);
        N13_2.addConnection(N17_2);

        //Add connections for nodeFourteen_2
        N14_2.addConnection(N15_2);
        N14_2.addConnection(N18_2);

        //Add connections for nodeFifteen_2

        //Add connections for nodeSixteen_2
        N16_2.addConnection(N17_2);

        //Add connections for nodeSeventeen_2
        N17_2.addConnection(N20_2);

        //Add connections for nodeEighteen_2
        N18_2.addConnection(N19_2);
        N18_2.addConnection(N21_2);

        //Add connections for nodeNineteen_2
        N19_2.addConnection(N22_2);

        //Add connections for nodeTwenty_2
        N20_2.addConnection(N21_2);

        //Add connections for nodeTwentyOne_2
        N21_2.addConnection(N23_2);
        N21_2.addConnection(N22_2);

        //Add connections for nodeTwentyTwo_2

        //Add connections for nodeTwentyThree_2

    }

    @Test
    public void testSearch1(){    //Node 1 to Node 10
        String answer = search.findPath(N1, N10).toString();
        System.out.println(answer);
        assertEquals("[A1, A6, A7, A10]", answer);
    }
    @Test public void testSearch1Reverse(){    //Node 10 to Node 1
        String answer = search.findPath(N10, N1).toString();
        System.out.println(answer);
        assertEquals("[A10, A7, A6, A1]", answer);
    }

    @Test public void testSearch2(){    //Node 2 to Node 17
        String answer = search.findPath(N2, N17).toString();
        System.out.println(answer);
        assertEquals("[A2, A3, A9, A15, A14, A13, A17]", answer);
    }
    @Test public void testSearch2Reverse(){     //Node 17 to Node 2
        String answer = search.findPath(N17, N2).toString();
        System.out.println(answer);
        assertEquals("[A17, A13, A14, A15, A9, A3, A2]", answer);
    }

    @Test public void testSearch3(){    //Node 4 to Node 23
        String answer = search.findPath(N4, N23).toString();
        System.out.println(answer);
        assertEquals("[A4, A7, A10, A11, A14, A18, A21, A23]", answer);
    }
    @Test public void testSearch3Reverse(){
        String answer = search.findPath(N23, N4).toString();
        System.out.println(answer);
        assertEquals("[A23, A21, A18, A14, A11, A10, A7, A4]", answer);
    }

    @Test public void testSearch4(){    //Node 5 to Node 19
        String answer = search.findPath(N5, N19).toString();
        System.out.println(answer);
        assertEquals("[A5, A8, A9, A15, A14, A18, A19]", answer);
    }
    @Test public void testSearch4Reverse(){     //Node 19 to Node 5
        String answer = search.findPath(N19, N5).toString();
        System.out.println(answer);
        assertEquals("[A19, A18, A14, A11, A10, A7, A4, A5]", answer);
    }

    @Test public void testSearchFloor1(){   //Node 1 to Node 1_2
        String answer = search.findPath(N1, N1_2).toString();
        System.out.println(answer);
        assertEquals("[A1, A2, A3, A9, A9_2, A8_2, A7_2, A6_2, A1_2]", answer);
    }
    @Test public void testSearchFloor1Reverse(){    //Node 1_2 to Node 1
        String answer = search.findPath(N1_2, N1).toString();
        System.out.println(answer);
        assertEquals("[A1_2, A2_2, A3_2, A9_2, A9, A8, A7, A6, A1]", answer);
    }

    @Test public void testSearchFloor2(){   //Node 1 to Node 19_2
        String answer = search.findPath(N1, N19_2).toString();
        System.out.println(answer);
        assertEquals("[A1, A6, A7, A8, A9, A9_2, A15_2, A14_2, A18_2, A19_2]", answer);
    }
    @Test public void testSearchFloor2Reverse(){    //Node 19_2 to Node 1
        String answer = search.findPath(N19_2, N1).toString();
        System.out.println(answer);
        assertEquals("[A19_2, A18_2, A14_2, A15_2, A9_2, A9, A8, A7, A6, A1]", answer);
    }

    @Test public void testSearchFloor3(){   //Node 3 to Node 22_2
        String answer = search.findPath(N3, N22_2).toString();
        System.out.println(answer);
        assertEquals("[A3, A9, A9_2, A15_2, A14_2, A18_2, A19_2, A22_2]", answer);
    }
    @Test public void testSearchFloor3Reverse(){    //Node 22_2 to Node 3
        String answer= search.findPath(N22_2, N3).toString();
        System.out.println(answer);
        assertEquals("[A22_2, A21_2, A18_2, A14_2, A15_2, A9_2, A9, A3]", answer);
    }
}
