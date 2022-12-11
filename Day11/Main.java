package Day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import Day11.Monkey.operations;

public class Main {
    static LinkedList<Monkey> monkeys = new LinkedList<>();

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("/Users/jonathanm./Desktop/More.../Personal Projects/Advent Code/Day11/input.txt");
        Scanner s = new Scanner(f);

        // partOne(s);
        partTwo(s);
    }

    // Answer:
    private static void partOne(Scanner s) {
        parseInput(s);
        monkeyBusiness(s, 20, true);
    }

    // Answer:
    private static void partTwo(Scanner s) {
        parseInput(s);
        monkeyBusiness(s, 1000, false);
    }

    private static void parseInput(Scanner s) {
        while (s.hasNextLine()) {
            String line = s.nextLine();

            if (line.contains("Monkey")) {
                String[] lineSplit = line.substring(0, line.length() - 1).split(" ");
                monkeys.add(new Monkey());
                continue;
            }

            if (line.contains("Starting")) {
                String[] lineSplit = line.substring(line.indexOf(":") + 2).split(", ");

                for (int i = 0; i < lineSplit.length; i++) {
                    monkeys.getLast().items.addLast(new Item(Integer.parseInt(lineSplit[i])));
                }

                continue;
            }

            if (line.contains("Operation")) {
                String[] lineSplit = line.substring(line.indexOf(":") + 2).split(" ");

                if (lineSplit[3].equals("*")) {
                    monkeys.getLast().operation = operations.MUL;
                } else if (lineSplit[3].equals("+")) {
                    monkeys.getLast().operation = operations.ADD;
                }

                if (lineSplit[4].equals("old")) {
                    monkeys.getLast().operation = operations.SQU;
                } else {
                    monkeys.getLast().operationNum = Integer.parseInt(lineSplit[4]);
                }

                continue;
            }

            if (line.contains("Test")) {
                String[] lineSplit = line.substring(line.indexOf(":") + 2).split(" ");

                monkeys.getLast().testDivisible = Integer.parseInt(lineSplit[2]);

                continue;
            }

            if (line.contains("true")) {
                String[] lineSplit = line.substring(line.indexOf(":") + 2).split(" ");

                monkeys.getLast().throwToTrue = Integer.parseInt(lineSplit[3]);

                continue;
            }

            if (line.contains("false")) {
                String[] lineSplit = line.substring(line.indexOf(":") + 2).split(" ");

                monkeys.getLast().throwToFalse = Integer.parseInt(lineSplit[3]);

                continue;
            }
        }
    }

    private static void monkeyBusiness(Scanner s, int rounds, boolean divide) {
        int round = 0;

        while (round < rounds) {
            for (int i = 0; i < monkeys.size(); i++) {
                Monkey currMonkey = monkeys.get(i);
                while (monkeys.get(i).items.size() > 0) {
                    Item currItem = currMonkey.items.removeFirst();
                    currMonkey.inspections++;

                    switch (monkeys.get(i).operation) {
                        case ADD:
                            currItem.worry += currMonkey.operationNum;
                            break;

                        case MUL:
                            currItem.worry *= currMonkey.operationNum;
                            break;

                        case SQU:
                            currItem.worry = currItem.worry * currItem.worry;
                            break;
                    }

                    if (divide) currItem.worry /= 3;

                    if (currItem.worry % currMonkey.testDivisible == 0) {
                        monkeys.get(currMonkey.throwToTrue).items.addLast(currItem);
                    } else {
                        monkeys.get(currMonkey.throwToFalse).items.addLast(currItem);
                    }
                }
            }

            round++;
        }

        printMonkeyBusiness();
    }

    private static void printMonkeyBusiness() {
        int max = monkeys.getFirst().inspections;
        int secMax = 0;

        for (int i = 0; i < monkeys.size(); i++) {
            System.out.println("Monkey " + i + " inspected " + monkeys.get(i).inspections + " items.");

            if (monkeys.get(i).inspections > max) {
                max = monkeys.get(i).inspections;
            }

            if (monkeys.get(i).inspections > secMax && monkeys.get(i).inspections < max) {
                secMax = monkeys.get(i).inspections;
            }
        }

        System.out.println("The two top monkeys inspected " + max + " and " + secMax + " items, for a monkey business level of " + (max * secMax) + ".");
    }
}
