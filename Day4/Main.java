package Day4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // ready file
        File f = new File("/Users/jonathanm./Desktop/More.../Personal Projects/Advent Code/Day4/input.txt");
        FileReader fr = new FileReader(f);
        BufferedReader bfr = new BufferedReader(fr);

        int overlap = 0;

        // partOne(bfr, overlap);
        partTwo(bfr, overlap);
    }

    private static void partOne(BufferedReader bfr, int overlap) throws IOException {
        String line = bfr.readLine();

        while (line != null) {
            String[] assignments = line.split(",");

            String[] rangeOne = assignments[0].split("-");
            String[] rangeTwo = assignments[1].split("-");

            if (convertToInt(rangeOne[0]) >= convertToInt(rangeTwo[0]) && convertToInt(rangeOne[1]) <= convertToInt(rangeTwo[1])
            || convertToInt(rangeOne[0]) <= convertToInt(rangeTwo[0]) && convertToInt(rangeOne[1]) >= convertToInt(rangeTwo[1])) {
                overlap++;
            }

            line = bfr.readLine();
        }

        System.out.println("The number of complete overlaps is " + overlap + ".");
    }

    private static void partTwo(BufferedReader bfr, int overlap) throws IOException {
        String line = bfr.readLine();

        while (line != null) {
            String[] assignments = line.split(",");

            String[] rangeOne = assignments[0].split("-");
            String[] rangeTwo = assignments[1].split("-");

            if (convertToInt(rangeOne[0]) >= convertToInt(rangeTwo[0]) && convertToInt(rangeOne[0]) <= convertToInt(rangeTwo[1])
            || convertToInt(rangeOne[0]) <= convertToInt(rangeTwo[0]) && convertToInt(rangeOne[1]) >= convertToInt(rangeTwo[0])) {
                overlap++;
            }

            line = bfr.readLine();
        }

        System.out.println("The number of complete overlaps is " + overlap + ".");
    }

    private static int convertToInt(String string) {
        return Integer.parseInt(string);
    }
}
