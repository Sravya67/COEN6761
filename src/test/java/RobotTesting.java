import java.io.ByteArrayOutputStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        Assertions.assertEquals("Position: 0, 0 - Pen: up - Facing: north\n", outContent.toString());
    }


    // Test case for putting the pen down (command "D"):

    @Test
    public void testPenDown() {
        Robot robot = new Robot();
        robot.processCommand("I 10");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        robot.processCommand("D");
        Assertions.assertEquals("Pen is down.\n", outContent.toString());
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
        Assertions.assertEquals("Moved to position: 0, 4\n\n", outContent.toString());
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
        Assertions.assertEquals("Rotated right. The robot is currently facing: east\n", outContent.toString());
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
        assertEquals("Position: 0, 0 - Pen: down - Facing: east\n", outContent.toString());
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
       assertEquals("Moved to position: 3, 0\n\n",outContent.toString());
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
        assertEquals("Position: 3, 0 - Pen: down - Facing: east\n", outContent.toString());
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
