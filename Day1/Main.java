package Day1;
import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        // ready file
        File f = new File("/Users/jonathanm./Desktop/More.../Personal Projects/Advent Code/Day1/input.txt");
        FileReader fr = new FileReader(f);
        BufferedReader bfr = new BufferedReader(fr);

        // initializations
        Elf newElf = new Elf();
        ArrayList<Elf> elfList = new ArrayList<Elf>();

        String line = bfr.readLine();
        int tempCals = 0;

        // loop through input
        while (line != null) {
            // if separator line, store elf
            if (line.length() == 0) {
                newElf.setCarryingCals(tempCals);
                elfList.add(newElf);

                tempCals = 0;
                newElf = new Elf();

                // else, add to current elf's calories
            } else {
                tempCals += Integer.parseInt(line);
            }

            // read next line
            line = bfr.readLine();
        }

        // find elves carrying the most calories
        int first = elfList.get(0).getCarryingCals();
        for (int i = 1; i < elfList.size(); i++) {
            if (elfList.get(i).getCarryingCals() > first) {
                first = elfList.get(i).getCarryingCals();
            }
        }

        int second = elfList.get(0).getCarryingCals();
        for (int i = 1; i < elfList.size(); i++) {
            if (elfList.get(i).getCarryingCals() > second && elfList.get(i).getCarryingCals() < first) {
                second = elfList.get(i).getCarryingCals();
            }
        }

        int third = elfList.get(0).getCarryingCals();
        for (int i = 1; i < elfList.size(); i++) {
            if (elfList.get(i).getCarryingCals() > third && elfList.get(i).getCarryingCals() < second) {
                third = elfList.get(i).getCarryingCals();
            }
        }

        System.out.println("The elf carrying the most calories has " + first + " calories.");
        System.out.println("The top three elves are carrying " + third + " + " + second + " + " + first + " = " + (third + second + first) + " calories.");
    }
}