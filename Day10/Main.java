package Day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import Day10.Command.commType;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("/Users/jonathanm./Desktop/More.../Personal Projects/Advent Code/Day10/input.txt");
        Scanner s = new Scanner(f);

        partOne(s);
        // partTwo(s);
    }

    // Answer:
    private static void partOne(Scanner s) {
        LinkedList<Command> stack = new LinkedList<>();

        int X = 1;
        int clock = 1;
        int clockCheck = 20;

        int sumOfStrengths = 0;

        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] lineSplit = line.split(" ");

            stack.addLast(new Command((lineSplit[0].equals("addx") ? commType.ADD : commType.NOOP), (lineSplit[0].equals("addx") ? Integer.parseInt(lineSplit[1]) : 0)));
        }

        while (!stack.isEmpty()) {
            if (clock == clockCheck) {
                sumOfStrengths += X * clock;
                clockCheck += 40;
            }

            Command currComm = stack.peekFirst();
            currComm.delay--;

            if (currComm.delay == 0) {
                X += currComm.value;
                stack.removeFirst();
            }

            clock++;
        }

        System.out.println("The sum of signal strengths is " + sumOfStrengths);
    }

    // Answer:
    private static void partTwo(Scanner s) {

    }
}
