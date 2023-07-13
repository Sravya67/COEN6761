
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Robot {
List<int[]> way = new ArrayList<>();
int x_Coordinate, y_Coordinate;
   boolean Pendown;
   int[][] Room;
   int Room_size;
   
   
   final String NORTH = "north", EAST = "east", SOUTH = "south",WEST = "west" ;
   String Direction = NORTH;

   
   Scanner Temp = new Scanner(System.in);


public static void main(String[] args) {
    Robot robo = new Robot();
    System.out.println("Welcome to the Robot Simulator");
    System.out.println("------------------------------------------");
    System.out.println("Initial Setup: Robot is at position (0, 0), facing North, with the pen up");
    System.out.println("------------------------------------------");
    System.out.println("Please enter a command to proceed");
    robo.executeCommands();
   }

   public void executeCommands() {
       while (true) {
           System.out.print("Enter Command>: ");
           String userInput = Temp.nextLine();
           processCommand(userInput);
       }
   }

    public void processCommand(String command) {
        String[] values = command.split(" ");
        String action = values[0].toUpperCase();
        int positions = 0;

        switch (action) {
            case "I":
                if (values.length < 2) {
                    System.out.println("Error: Two values are required to process the 'I' command.");
                    break;
                }
                if (!(values[1].matches("\\d+"))) {
                    System.out.println("Error: Only integer values are accepted for the 'I' command.");
                    break;
                }
                if (Integer.parseInt(values[1]) <= 1) {
                    System.out.println("Error: The value for 'I' command should be greater than 1.");
                    break;
                }
                Room_size = Integer.parseInt(values[1]);
                Room = new int[Room_size][Room_size];
                for (int i = 0; i < Room_size; i++) {
                    for (int j = 0; j < Room_size; j++) {
                        Room[i][j] = 0;
                    }
                }
                x_Coordinate = 0;
                y_Coordinate = 0;
                Pendown = false;
                Direction = NORTH;
                System.out.println("Room is set to " + Room_size + "x" + Room_size + ".");
                break;

            case "C":
                if (values.length > 1) {
                    System.out.println("Error: The 'C' command does not accept any additional values.");
                    break;
                }
                System.out.println("Position: " + x_Coordinate + ", " + y_Coordinate + " - Pen: " + (Pendown ? "down" : "up") + " - Facing: " + Direction);
                break;

            case "D":
                if (Room_size == 0) {
                    System.out.println("Error: Initialize the room before executing the 'D' command.");
                    break;
                }
                if (values.length > 1) {
                    System.out.println("Error: The 'D' command does not accept any additional values.");
                    break;
                }
                Pendown = true;
                System.out.println("Pen is down.");
                updateRoom();
                break;

            case "U":
                if (Room_size == 0) {
                    System.out.println("Error: Initialize the room before executing the 'U' command.");
                    break;
                }
                if (values.length > 1) {
                    System.out.println("Error: The 'U' command does not accept any additional values.");
                    break;
                }
                Pendown = false;
                System.out.println("Pen is up.");
                updateRoom();
                break;

            case "M":
                if (Room_size == 0) {
                    System.out.println("Error: Initialize the room before executing the 'M' command.");
                    break;
                }
                if (values.length < 2) {
                    System.out.println("Error: Two values are required to process the 'M' command.");
                    break;
                }
                if (!(values[1].matches("\\d+"))) {
                    System.out.println("Error: Only integer values are accepted for the 'M' command.");
                    break;
                }
                positions = Integer.parseInt(values[1]);
                if (positions < 1) {
                    System.out.println("Error: The value for 'M' command should be at least 1.");
                    break;
                }
                move(positions);
                break;

            case "L":
                if (values.length > 1) {
                    System.out.println("Error: The 'L' command does not accept any additional values.");
                    break;
                }
                Move_Left();
                break;

            case "R":
                if (values.length > 1) {
                    System.out.println("Error: The 'R' command does not accept any additional values.");
                    break;
                }
                Move_Right();
                break;

            case "P":
                if (values.length > 1) {
                    System.out.println("Error: The 'P' command does not accept any additional values.");
                    break;
                }
                updateRoom();
                Room_Map();
                break;

            case "Q":
                if (values.length > 1) {
                    System.out.println("Error: The 'Q' command does not accept any additional values.");
                    break;
                }
                System.out.println("Terminating the robot.");
                System.exit(0);
                break;

            default:
                System.out.println("Error: Command not supported.");
                break;
        }
    }

    public void move(int positions) {
        updateRoom();
        int temp_X = x_Coordinate;
        int temp_Y = y_Coordinate;

        switch (Direction) {
            case NORTH:
                temp_Y = y_Coordinate + positions;
                if (temp_Y >= Room_size) {
                    System.out.println("Error: Movement outside the room is not allowed.");
                    break;
                }
                break;

            case EAST:
                temp_X = x_Coordinate + positions;
                if (temp_X >= Room_size) {
                    System.out.println("Error: Movement outside the room is not allowed.");
                    break;
                }
                break;

            case SOUTH:
                temp_Y = y_Coordinate - positions;
                if (temp_Y < 0) {
                    System.out.println("Error: Movement outside the room is not allowed.");
                    break;
                }
                break;

            case WEST:
                temp_X = x_Coordinate - positions;
                if (temp_X < 0) {
                    System.out.println("Error: Movement outside the room is not allowed.");
                    break;
                }
                break;
        }

        if (temp_X >= 0 && temp_X < Room_size && temp_Y >= 0 && temp_Y < Room_size) {
            int xDiff = temp_X - x_Coordinate;
            int yDiff = temp_Y - y_Coordinate;

            if (Pendown) {
                for (int i = 0; i <= positions; i++) {
                    way.add(new int[]{x_Coordinate + i * xDiff / positions, y_Coordinate + i * yDiff / positions});
                }
                x_Coordinate = temp_X;
                y_Coordinate = temp_Y;
                System.out.println("Moved to position: " + x_Coordinate + ", " + y_Coordinate + "\n");
                way.add(new int[]{x_Coordinate, y_Coordinate});
                updateRoom();
            } else {
                System.out.println("The robot is moving freely as the pen is up. The new coordinates are: " + temp_X + ", " + temp_Y + " (not updated in the room map)\n");
                x_Coordinate = temp_X;
                y_Coordinate = temp_Y;
            }
        }
    }

    public void Move_Right() {
        switch (Direction) {
            case NORTH:
                Direction = EAST;
                break;
            case EAST:
                Direction = SOUTH;
                break;
            case SOUTH:
                Direction = WEST;
                break;
            case WEST:
                Direction = NORTH;
                break;
        }
        System.out.println("Rotated right. The robot is currently facing: " + Direction);
    }

    public void Move_Left() {
        switch (Direction) {
            case NORTH:
                Direction = WEST;
                break;
            case WEST:
                Direction = SOUTH;
                break;
            case SOUTH:
                Direction = EAST;
                break;
            case EAST:
                Direction = NORTH;
                break;
        }
        System.out.println("Rotated left. The robot is currently facing: " + Direction);
    }

    public void updateRoom() {
        if (Pendown) {
            Room[x_Coordinate][y_Coordinate] = 1;
        }
    }

    public void Room_Map() {
        System.out.println();
        for (int j = Room_size - 1; j >= 0; j--) {
            System.out.print(" " + String.format("%02d", j) + " ");
            for (int i = 0; i < Room_size; i++) {
                boolean hold = false;
                for (int[] pos : way) {
                    if (i == pos[0] && j == pos[1]) {
                        System.out.print(" * ");
                        hold = true;
                        break;
                    }
                }
                if (!hold) {
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }
        System.out.print("    ");
        for (int i = 0; i < Room_size; i++) {
            System.out.print(String.format(" %02d", i));
        }
        System.out.println();
    }
}
