package Day2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // ready file
        File f = new File("/Users/jonathanm./Desktop/More.../Personal Projects/Advent Code/Day2/input.txt");
        FileReader fr = new FileReader(f);
        BufferedReader bfr = new BufferedReader(fr);

        // initializations
        String line = bfr.readLine();
        int score = 0;

        // loop through input
        while (line != null) {
            String[] moves = line.split(" ");
            
            // special win case
            if (moves[0].charAt(0) == 'C' && convertToABC(moves[1].charAt(0)) == 'A') {
                score += ResultEnum.WIN.value;
                score += convertToScore(moves[1].charAt(0));
                // System.out.println("special win. score += + " + ResultEnum.WIN.value + " + " + convertToScore(moves[1].charAt(0)) + " = " + score);

            // win
            } else if (moves[0].charAt(0) < convertToABC(moves[1].charAt(0))) {
                score += ResultEnum.WIN.value;
                score += convertToScore(moves[1].charAt(0));
                // System.out.println("win. score += + " + ResultEnum.WIN.value + " + " + convertToScore(moves[1].charAt(0)) + " = " + score);

            // draw
            } else if (moves[0].charAt(0) == convertToABC(moves[1].charAt(0))) {
                score += ResultEnum.DRAW.value;
                score += convertToScore(moves[1].charAt(0));
                // System.out.println("draw. score += + " + ResultEnum.DRAW.value + " + " + convertToScore(moves[1].charAt(0)) + " = " + score);
            
            // lose
            } else {
                score += convertToScore(moves[1].charAt(0));
                // System.out.println("lose. score += + 0 + " + convertToScore(moves[1].charAt(0)) + " = " + score);
            }

            line = bfr.readLine();
        }

        System.out.println("Total score is " + score + ".");
    }

    private static char convertToABC(char input) {
        switch(input) {
            case 'X':
                return 'A';
            case 'Y':
                return 'B';
            case 'Z':
                return 'C';
            default:
                System.out.println("Error. Returning E.");
                return 'E';
        }
    }

    private static int convertToScore(char input) {
        switch (input) {
            case 'X':
                return 1;
            case 'Y':
                return 2;
            case 'Z':
                return 3;
            default:
                System.out.println("Error. Returning -1.");
                return -1;
        }
    }
}