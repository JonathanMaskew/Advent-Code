package Day3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // ready file
        File f = new File("/Users/jonathanm./Desktop/More.../Personal Projects/Advent Code/Day3/input.txt");
        FileReader fr = new FileReader(f);
        BufferedReader bfr = new BufferedReader(fr);

        // partOne(bfr);
        partTwo(bfr);
    }

    private static void partOne(BufferedReader bfr) throws IOException {
        int totalPriority = 0;
        String line = bfr.readLine();

        while (line != null) {
            int halfIndex = line.length() / 2;

            String packOne = line.substring(0, halfIndex);
            String packTwo = line.substring(halfIndex);

            boolean shouldBreak = false;
            for (int i = 0; i < packOne.length(); i++) {
                for (int j = 0; j < packTwo.length(); j++) {
                    if (packOne.charAt(i) == packTwo.charAt(j)) {
                        char commonChar = packOne.charAt(i);

                        if (commonChar < 97) {
                            totalPriority += commonChar - 64 + 26;
                        } else {
                            totalPriority += commonChar - 96;
                        }

                        shouldBreak = true;
                        break;
                    }
                }
                if (shouldBreak) break;
            }

            line = bfr.readLine();
        }

        System.out.println("The sum of priorities of items in each compartment of each rucksack is " + totalPriority + ".");
    }

    private static void partTwo(BufferedReader bfr) {

    }
}
