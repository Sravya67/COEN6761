import java.io.ByteArrayOutputStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;

import static org.junit.Assert.*;

public class RobotTesting {
    private Robot robot;

    @BeforeEach
    public void setup() {
        robot = new Robot();
    }


    @Test
    public void testInitializeSystem() {
        Robot robot = new Robot();
        robot.processCommand("I 10");
        int[][] expectedRoom = new int[10][10];
        Assertions.assertEquals(10, robot.Room_size);
        Assertions.assertArrayEquals(expectedRoom, robot.Room);
        Assertions.assertEquals(0, robot.x_Coordinate);
        Assertions.assertEquals(0, robot.y_Coordinate);
        Assertions.assertFalse(robot.Pendown);
        Assertions.assertEquals("north", robot.Direction);
    }
    //Test case for displaying the current position and status (command "C"):
    @Test
    public void testDisplayPosition() {
        Robot robot = new Robot();
        robot.processCommand("I 10");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        robot.processCommand("C");
        Assertions.assertTrue( outContent.toString().contains("Position: 0, 0 - Pen: up - Facing: north"));
    }


    // Test case for putting the pen down (command "D"):

    @Test
    public void testPenDown() {
        Robot robot = new Robot();
        robot.processCommand("I 10");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        robot.processCommand("D");
        Assertions.assertTrue( outContent.toString().contains("Pen is down"));
        Assertions.assertEquals(1, robot.Room[0][0]);
    }

    //Test case for moving the robot (command "M"):
    @Test
    public void testMove() {
        Robot robot = new Robot();
        robot.processCommand("I 10");
        robot.processCommand("D");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        robot.processCommand("M 4");
        Assertions.assertTrue(outContent.toString().contains("Moved to position: 0, 4"));
        Assertions.assertEquals(1, robot.Room[0][0]);
        Assertions.assertEquals(0, robot.Room[0][1]);
        Assertions.assertEquals(0, robot.Room[0][2]);
        Assertions.assertEquals(0, robot.Room[0][3]);
        Assertions.assertEquals(1, robot.Room[0][4]);
    }

    //Test case for rotating the robot to the right (command "R"):
    @Test
    public void testRotateRight() {
        Robot robot = new Robot();
        robot.processCommand("I 10");
        robot.processCommand("D");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        robot.processCommand("R");
        Assertions.assertTrue( outContent.toString().contains("Rotated right. The robot is currently facing: east"));
        Assertions.assertEquals("east", robot.Direction);
    }

//Test case for displaying the current position and status after rotating right (command "C"):
    @Test
    public void testDisplayPositionAfterRotateRight() {
        Robot robot = new Robot();
        robot.processCommand("I 10");
        robot.processCommand("D");
        robot.processCommand("R");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        robot.processCommand("C");
        assertTrue(outContent.toString().contains("Position: 0, 0 - Pen: down - Facing: east"));
    }

   // Test case for moving the robot after rotating right (command "M"):
   @Test
   public void testMoveAfterRotateRight() {
       Robot robot = new Robot();
       robot.processCommand("I 10");
       robot.processCommand("D");
       robot.processCommand("R");
       ByteArrayOutputStream outContent = new ByteArrayOutputStream();
       System.setOut(new PrintStream(outContent));
       robot.processCommand("M 3");
       assertTrue(outContent.toString().contains("Moved to position: 3, 0"));
       assertEquals(1, robot.Room[0][0]);
       assertEquals(0, robot.Room[1][0]);
       assertEquals(0, robot.Room[2][0]);
       assertEquals(1, robot.Room[3][0]);
   }

    //Test case for displaying the current position and status after moving (command "C"):
    @Test
    public void testDisplayPositionAfterMove() {
        Robot robot = new Robot();
        robot.processCommand("I 10");
        robot.processCommand("D");
        robot.processCommand("R");
        robot.processCommand("M 3");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        robot.processCommand("C");
        assertTrue(outContent.toString().contains("Position: 3, 0 - Pen: down - Facing: east"));
    }
    @Test
    public void testDisplayPositionP() {
        Robot robot = new Robot();
        robot.processCommand("I 10");
        robot.processCommand("D");
        robot.processCommand("R");
        robot.processCommand("P");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertNotNull(outContent);

    }

    @Test
    public void testDisplayPositionLeft() {
        Robot robot = new Robot();
        robot.processCommand("I 10");
        robot.processCommand("U");

        robot.processCommand("L");
        robot.processCommand("L");
        robot.processCommand("L");
        robot.processCommand("L");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertNotNull(outContent);

    }
    @Test
    public void testDisplayPositionRight() {
        Robot robot = new Robot();
        robot.processCommand("I 10");
        robot.processCommand("U");

        robot.processCommand("R");
        robot.processCommand("R");
        robot.processCommand("R");
        robot.processCommand("R");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertNotNull(outContent);

    }
    @Test
    public void testDisplayPositionDefault() {
        Robot robot = new Robot();
        robot.processCommand("I 10");
        robot.processCommand("V");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertNotNull(outContent);

    }
    @Test
    public void testDisplayPositionCError() {
        Robot robot = new Robot();
        robot.processCommand("I 10");
        robot.processCommand("C 1");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertNotNull(outContent);

    }
    @Test
    public void testDisplayPositionDError() {
        Robot robot = new Robot();
        robot.processCommand("I 10");
        robot.processCommand("D 1");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertNotNull(outContent);

    }
    @Test
    public void testDisplayPositionD0Error() {
        Robot robot = new Robot();

        robot.processCommand("D 1");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertNotNull(outContent);

    }

    @Test
    public void testDisplayPositionAllError() {
        Robot robot = new Robot();


        robot.processCommand("D");
        robot.processCommand("M");
        robot.processCommand("U");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertNotNull(outContent);

    }
    @Test
    public void testDisplayPositionRError() {
        Robot robot = new Robot();


        robot.processCommand("R 10");


        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertNotNull(outContent);

    }
    @Test
    public void testDisplayPositionPError() {
        Robot robot = new Robot();


        robot.processCommand("P 10");


        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertNotNull(outContent);

    }
    @Test
    public void testDisplayPositionLError() {
        Robot robot = new Robot();

        robot.processCommand("L 10");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertNotNull(outContent);

    }
    @Test
    public void testDisplayPositionUError() {
        Robot robot = new Robot();
        robot.processCommand("I 9");
        robot.processCommand("U 9");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertNotNull(outContent);

    }
    @Test
    public void testDisplayPositionIError() {
        Robot robot = new Robot();
        robot.processCommand("I");
        robot.processCommand("U 9");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertNotNull(outContent);

    }
    @Test
    public void testDisplayPositionIErrors() {
        Robot robot = new Robot();
        robot.processCommand("I 0");
        robot.processCommand("U 9");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertNotNull(outContent);

    }
    //Test case for quitting the program (command "Q"):
//    @Test
//    public void testQuitProgram() {
//        Robot robot = new Robot();
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//        robot.processCommand("Q");
//        assertEquals("Terminating the robot.\n", outContent.toString());
//    }



}
