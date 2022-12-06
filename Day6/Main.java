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

        partOne(bfr);
        // partTwo(bfr);
    }

    private static void partOne(BufferedReader bfr) throws IOException {
        String line = bfr.readLine();

        int indexInString = 1;
        while (line != null) {
            ArrayList<Character> tempString = new ArrayList<>();
            tempString.add(line.charAt(0));

            int length = 0;
            while (length <= 4) {
                System.out.println(tempString);
                boolean append = true;
                for (int i = 0; i < tempString.size(); i++) {
                    if (line.charAt(indexInString) == tempString.get(i)) {
                        tempString.clear();
                        length = 0;
                        append = false;
                        break;

                    }
                }

                if (append) {
                    tempString.add(line.charAt(indexInString));
                    indexInString++;
                    length++;
                }
            }

            line = bfr.readLine();
        }

        System.out.println((indexInString - 1) + " chars processed before 4 unique chars appeared.");
    }

    private static void partTwo() {

    }
}
