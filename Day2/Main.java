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

        int score = 0;

        // partOne(bfr, score);
        // partTwo(bfr, score);
    }

    private static void partOne(BufferedReader bfr, int score) throws IOException {
        // initializations
        String line = bfr.readLine();

        // loop through input
        while (line != null) {
            String[] moves = line.split(" ");
            
            // special win case
            if (moves[0].charAt(0) == 'C' && convertToABC(moves[1].charAt(0)) == 'A') {
                score += ResultEnum.WIN.value;
                score += convertToScore(moves[1].charAt(0));

            // special lose case
            } else if (moves[0].charAt(0) == 'A' && convertToABC(moves[1].charAt(0)) == 'C') {
                score += convertToScore(moves[1].charAt(0));

            // win
            } else if (moves[0].charAt(0) < convertToABC(moves[1].charAt(0))) {
                score += ResultEnum.WIN.value;
                score += convertToScore(moves[1].charAt(0));

            // draw
            } else if (moves[0].charAt(0) == convertToABC(moves[1].charAt(0))) {
                score += ResultEnum.DRAW.value;
                score += convertToScore(moves[1].charAt(0));
            
            // lose
            } else {
                score += convertToScore(moves[1].charAt(0));
            }

            line = bfr.readLine();
        }

        System.out.println("Total score is " + score + ".");
    }

    private static void partTwo(BufferedReader bfr, int score) throws IOException {
        // initializations
        String line = bfr.readLine();

        // loop through input
        while (line != null) {
            String[] moves = line.split(" ");

            switch (moves[1].charAt(0)) {
                case 'X':
                    switch (moves[0].charAt(0)) {
                        case 'A':
                            score += GameEnum.C.value;
                            break;
                        case 'B':
                            score += GameEnum.A.value;
                            break;
                        case 'C':
                            score += GameEnum.B.value;
                            break;
                    }
                    break;
                case 'Y':
                    switch (moves[0].charAt(0)) {
                        case 'A':
                            
                            score += GameEnum.A.value;
                            break;
                        case 'B':
                            score += GameEnum.B.value;
                            break;
                        case 'C':
                            score += GameEnum.C.value;
                            break;
                    }
                    score += ResultEnum.DRAW.value;
                    break;
                case 'Z':
                    switch (moves[0].charAt(0)) {
                        case 'A':
                            score += GameEnum.B.value;
                            break;
                        case 'B':
                            score += GameEnum.C.value;
                            break;
                        case 'C':
                            score += GameEnum.A.value;
                            break;
                    }
                    score += ResultEnum.WIN.value;
                    break;
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