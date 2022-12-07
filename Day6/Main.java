package Day6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        // ready file
        File f = new File("/Users/jonathanm./Desktop/More.../Personal Projects/Advent Code/Day6/input.txt");
        FileReader fr = new FileReader(f);
        BufferedReader bfr = new BufferedReader(fr);

        // partOne(bfr);
        partTwo(bfr);
    }

    // Answer: 1707
    private static void partOne(BufferedReader bfr) throws IOException {
        String line = bfr.readLine();

        int indexInString = 1;
        int indexOfStart = 0;

        ArrayList<Character> tempString = new ArrayList<>();
        tempString.add(line.charAt(0));

        int length = 1;

        while (length < 4) {
            boolean append = true;

            for (int i = 0; i < tempString.size(); i++) {
                if (line.charAt(indexInString) == tempString.get(i)) {
                    tempString.clear();
                    length = 0;
                    append = false;
                    indexInString = indexOfStart + 1;
                    indexOfStart = indexInString;
                    break;
                }
            }

            if (append) {
                tempString.add(line.charAt(indexInString));
                indexInString++;
                length++;
            }
        }

        System.out.println((indexInString) + " chars processed before 4 unique chars appeared.");
    }

    // Answer: 3697
    private static void partTwo(BufferedReader bfr) throws IOException {
        String line = bfr.readLine();

        int indexInString = 1;
        int indexOfStart = 0;

        ArrayList<Character> tempString = new ArrayList<>();
        tempString.add(line.charAt(0));
        
        int length = 1;

        while (length < 14) {
            boolean append = true;

            for (int i = 0; i < tempString.size(); i++) {
                if (line.charAt(indexInString) == tempString.get(i)) {
                    tempString.clear();
                    length = 0;
                    append = false;
                    indexInString = indexOfStart + 1;
                    indexOfStart = indexInString;
                    break;
                }
            }

            if (append) {
                tempString.add(line.charAt(indexInString));
                indexInString++;
                length++;
            }
        }

        System.out.println((indexInString) + " chars processed before 14 unique chars appeared.");
    }
}
