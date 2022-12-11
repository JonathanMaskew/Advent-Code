package Day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int tXPos = 0;
    static int tYPos = 0;

    static int hXPos = 0;
    static int hYPos = 0;

    static ArrayList<String> tailPositions = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("/Users/jonathanm./Desktop/More.../Personal Projects/Advent Code/Day9/input.txt");
        Scanner s = new Scanner(f);

        partOne(s);
        // partTwo(s);
    }

    // Answer:
    private static void partOne(Scanner s) {
        tailPositions.add("0,0");

        while (s.hasNextLine()) {
            System.out.println("(" + hXPos + ", " + hYPos + ")");
            String line = s.nextLine();
            String[] lineSplit = line.split(" ");
            
            if (lineSplit[0].equals("U")) {
                for (int i = 0; i < Integer.parseInt(lineSplit[1]); i++) {
                    hYPos++;
                    ensureAdjacency();
                }
                
            } else if (lineSplit[0].equals("D")) {
                for (int i = 0; i < Integer.parseInt(lineSplit[1]); i++) {
                    hYPos--;
                    ensureAdjacency();
                }

            } else if (lineSplit[0].equals("L")) {
                for (int i = 0; i < Integer.parseInt(lineSplit[1]); i++) {
                    hXPos--;
                    ensureAdjacency();
                }

            } else if (lineSplit[0].equals("R")) {
                for (int i = 0; i < Integer.parseInt(lineSplit[1]); i++) {
                    hXPos++;
                    ensureAdjacency();
                }

            }
        }

        for (String string : tailPositions) {
            System.out.println(string);
        }

        System.out.println("The tail travelled to " + tailPositions.size() + " positions.");
    }

    // Answer:
    private static void partTwo(Scanner s) {

    }

    private static void ensureAdjacency() {
        boolean movedTail = false;
        System.out.println("(" + hXPos + ", " + hYPos + ")");

        if ((Math.abs((hXPos - tXPos)) == 1 && Math.abs(hYPos - tYPos) > 1) || (Math.abs((hXPos - tXPos)) > 1 && Math.abs(hYPos - tYPos) == 1)) {
            System.out.println("not adjacent diagonal");

            if (hXPos - tXPos > 0 && hYPos - tYPos > 0) {
                tYPos++;
                tXPos++;
            } else if (hXPos - tXPos > 0 && hYPos - tYPos < 0) {
                tYPos--;
                tXPos++;
            } else if (hXPos - tXPos < 0 && hYPos - tYPos > 0) {
                tYPos++;
                tXPos--;
            } else if (hXPos - tXPos < 0 && hYPos - tYPos < 0) {
                tYPos--;
                tXPos--;
            }

            movedTail = true;
        }

        if (Math.abs(hXPos - tXPos) > 1) {
            System.out.println("not adjacent lr");

            if (hYPos == tYPos) {
                if (hXPos - tXPos > 0) {
                    System.out.println("move right");
                    tXPos++;
                }

                if (hXPos - tXPos < 0) {
                    System.out.println("move left");
                    tXPos--;
                }
            }

            movedTail = true;
        }

        if (Math.abs(hYPos - tYPos) > 1) {
            System.out.println("not adjacent ud");

            if (hXPos == tXPos) {
                if (hYPos - tYPos > 0) {
                    System.out.println("move up");
                    tYPos++;
                }

                if (hYPos - tYPos < 0) {
                    System.out.println("move down");
                    tYPos--;
                }
            }

            movedTail = true;
        }

        if (movedTail) {
            String newPosition = tXPos + "," + tYPos;

            if (!tailPositions.contains(newPosition)) {
                tailPositions.add(newPosition);
            }
        }
    }
}
