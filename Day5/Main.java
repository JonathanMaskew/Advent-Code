package Day5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        // ready file
        File f = new File("/Users/jonathanm./Desktop/More.../Personal Projects/Advent Code/Day5/input.txt");
        FileReader fr = new FileReader(f);
        BufferedReader bfr = new BufferedReader(fr);

        LinkedList<Character>[] stackArray = null;

        // partOne(bfr, stackArray);
        partTwo(bfr, stackArray);
    }

    private static void partOne(BufferedReader bfr, LinkedList<Character>[] stackArray) throws IOException {
        String line = bfr.readLine();
        stackArray = new LinkedList[line.length() / 4 + 1];

        while (line.charAt(0) != ' ') {
            int stackArrayIndex = 0;

            int i = 1;
            while (i < line.length()) {
                if (line.charAt(i) >= 65 && line.charAt(i) <= 90) {
                    if (stackArray[stackArrayIndex] == null) {
                        LinkedList<Character> newStack = new LinkedList<Character>();
                        newStack.add(line.charAt(i));

                        stackArray[stackArrayIndex] = newStack;

                    } else {
                        stackArray[stackArrayIndex].push(line.charAt(i));
                    }

                    stackArrayIndex++;
                    i += 4;

                } else if (line.charAt(i) == ' ') {
                    stackArrayIndex++;
                    i += 4;
                }
            }            

            line = bfr.readLine();
        }

        bfr.readLine();
        line = bfr.readLine();

        while (line != null) {
            String[] parts = line.split(" ");
            for (int i = 0; i < Integer.parseInt(parts[1]); i++) {                
                stackArray[Integer.parseInt(parts[5]) - 1].addLast(stackArray[Integer.parseInt(parts[3]) - 1].pollLast());
            }

            line = bfr.readLine();
        }

        // print final stacks
        for (int i = 0; i < stackArray.length; i++) {
            System.out.println("Final stack: " + (i + 1) + " " + stackArray[i]);
        }
    }

    private static void partTwo(BufferedReader bfr, LinkedList<Character>[] stackArray) throws IOException {
        String line = bfr.readLine();
        stackArray = new LinkedList[line.length() / 4 + 1];

        while (line.charAt(0) != ' ') {
            int stackArrayIndex = 0;

            int i = 1;
            while (i < line.length()) {
                if (line.charAt(i) >= 65 && line.charAt(i) <= 90) {
                    if (stackArray[stackArrayIndex] == null) {
                        LinkedList<Character> newStack = new LinkedList<Character>();
                        newStack.add(line.charAt(i));

                        stackArray[stackArrayIndex] = newStack;

                        System.out.println("Pushing " + line.charAt(i) + " to " + (stackArrayIndex + 1));

                    } else {
                        stackArray[stackArrayIndex].push(line.charAt(i));
                        System.out.println("Pushing " + line.charAt(i) + " to " + (stackArrayIndex + 1));
                    }

                    stackArrayIndex++;
                    i += 4;

                } else if (line.charAt(i) == ' ') {
                    stackArrayIndex++;
                    i += 4;
                }
            }

            line = bfr.readLine();
        }

        for (int i = 0; i < stackArray.length; i++) {
            System.out.println("Stack: " + (i + 1) + " " + stackArray[i]);
        }

        bfr.readLine();
        line = bfr.readLine();

        while (line != null) {
            String[] parts = line.split(" ");
            for (int i = 0; i < Integer.parseInt(parts[1]); i++) {
                System.out.println("Stack from: " + stackArray[Integer.parseInt(parts[3]) - 1]);
                System.out.println("Stack to: " + stackArray[Integer.parseInt(parts[5]) - 1]);

                stackArray[Integer.parseInt(parts[5]) - 1]
                        .addLast(stackArray[Integer.parseInt(parts[3]) - 1].pollLast());
            }

            line = bfr.readLine();
        }

        // print final stacks
        for (int i = 0; i < stackArray.length; i++) {
            System.out.println("Final stack: " + (i + 1) + " " + stackArray[i]);
        }
    }
}
