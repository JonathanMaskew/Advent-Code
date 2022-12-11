package Day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Knot> knots = new ArrayList<>();

    // static int tXPos = 0;
    // static int tYPos = 0;

    // static int hXPos = 0;
    // static int hYPos = 0;

    static ArrayList<String> tailPositions = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("/Users/jonathanm./Desktop/More.../Personal Projects/Advent Code/Day9/input.txt");
        Scanner s = new Scanner(f);

        knots.add(new Knot());
        tailPositions.add("0,0");

        // partOne(s);
        partTwo(s);
    }

    // Answer: 6354
    private static void partOne(Scanner s) {
        Knot newKnot = new Knot();
        newKnot.prevKnot = knots.get(0);
        knots.add(newKnot);

        parseInput(s);
    }

    // Answer:
    private static void partTwo(Scanner s) {
        for (int i = 1; i < 10; i++) {
            Knot newKnot = new Knot();
            newKnot.prevKnot = knots.get(i - 1);

            knots.add(newKnot);
        }

        parseInput(s);
    }

    private static void parseInput(Scanner s) {
        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] lineSplit = line.split(" ");

            if (lineSplit[0].equals("U")) {
                for (int i = 0; i < Integer.parseInt(lineSplit[1]); i++) {
                    knots.get(0).yPos++;
                    ensureAdjacency();
                }

            } else if (lineSplit[0].equals("D")) {
                for (int i = 0; i < Integer.parseInt(lineSplit[1]); i++) {
                    knots.get(0).yPos--;
                    ensureAdjacency();
                }

            } else if (lineSplit[0].equals("L")) {
                for (int i = 0; i < Integer.parseInt(lineSplit[1]); i++) {
                    knots.get(0).xPos--;
                    ensureAdjacency();
                }

            } else if (lineSplit[0].equals("R")) {
                for (int i = 0; i < Integer.parseInt(lineSplit[1]); i++) {
                    knots.get(0).xPos++;
                    ensureAdjacency();
                }

            }
        }

        System.out.println("The tail travelled to " + tailPositions.size() + " positions.");
    }

    private static void ensureAdjacency() {
        for (int i = 1; i < knots.size(); i++) {
            boolean movedTail = false;

            if ((Math.abs((knots.get(i).prevKnot.xPos - knots.get(i).xPos)) == 1 && Math.abs(knots.get(i).prevKnot.yPos - knots.get(i).yPos) > 1) 
            || (Math.abs((knots.get(i).prevKnot.xPos - knots.get(i).xPos)) > 1 && Math.abs(knots.get(i).prevKnot.yPos - knots.get(i).yPos) == 1)) {

                if (knots.get(i).prevKnot.xPos - knots.get(i).xPos > 0 && knots.get(i).prevKnot.yPos - knots.get(i).yPos > 0) {
                    knots.get(i).yPos++;
                    knots.get(i).xPos++;
                } else if (knots.get(i).prevKnot.xPos - knots.get(i).xPos > 0 && knots.get(i).prevKnot.yPos - knots.get(i).yPos < 0) {
                    knots.get(i).yPos--;
                    knots.get(i).xPos++;
                } else if (knots.get(i).prevKnot.xPos - knots.get(i).xPos < 0 && knots.get(i).prevKnot.yPos - knots.get(i).yPos > 0) {
                    knots.get(i).yPos++;
                    knots.get(i).xPos--;
                } else if (knots.get(i).prevKnot.xPos - knots.get(i).xPos < 0 && knots.get(i).prevKnot.yPos - knots.get(i).yPos < 0) {
                    knots.get(i).yPos--;
                    knots.get(i).xPos--;
                }

                movedTail = true;
            }

            if (Math.abs(knots.get(i).prevKnot.xPos - knots.get(i).xPos) > 1) {

                if (knots.get(i).prevKnot.yPos == knots.get(i).yPos) {
                    if (knots.get(i).prevKnot.xPos - knots.get(i).xPos > 0) {
                        knots.get(i).xPos++;
                    }

                    if (knots.get(i).prevKnot.xPos - knots.get(i).xPos < 0) {
                        knots.get(i).xPos--;
                    }
                }

                movedTail = true;
            }

            if (Math.abs(knots.get(i).prevKnot.yPos - knots.get(i).yPos) > 1) {

                if (knots.get(i).prevKnot.xPos == knots.get(i).xPos) {
                    if (knots.get(i).prevKnot.yPos - knots.get(i).yPos > 0) {
                        knots.get(i).yPos++;
                    }

                    if (knots.get(i).prevKnot.yPos - knots.get(i).yPos < 0) {
                        knots.get(i).yPos--;
                    }
                }

                movedTail = true;
            }

            if (movedTail && i == knots.size() - 1) {
                String newPosition = knots.get(i).xPos + "," + knots.get(i).yPos;

                if (!tailPositions.contains(newPosition)) {
                    tailPositions.add(newPosition);
                }
            }
        }
    }
}
