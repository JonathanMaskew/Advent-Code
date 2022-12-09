package Day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("/Users/jonathanm./Desktop/More.../Personal Projects/Advent Code/Day8/input.txt");
        Scanner s = new Scanner(f);

        partOne(s);
        // partTwo(s);
    }

    // Answer: 1782
    private static void partOne(Scanner s) {
        ArrayList<ArrayList<Tree>> treeMatrix = new ArrayList<>();
        buildMatrix(s, treeMatrix);
        determineVisibility(treeMatrix);
        printMatrix(treeMatrix);
        System.out.println("The total number of visible tree is " + countVisible(treeMatrix) + ".");

        partTwo(s, treeMatrix);
    }

    // Answer: 
    private static void partTwo(Scanner s, ArrayList<ArrayList<Tree>> treeMatrix) {
        printMaxScenicScore(treeMatrix);
    }

    private static void buildMatrix(Scanner s, ArrayList<ArrayList<Tree>> treeMatrix) {
        int row = 0;

        while (s.hasNextLine()) {
            String line = s.nextLine();

            treeMatrix.add(new ArrayList<>());

            for (int i = 0; i < line.length(); i++) {
                Tree newTree = new Tree(Character.getNumericValue(line.charAt(i)));

                treeMatrix.get(row).add(newTree);
            }

            row++;
        }
    }

    private static void printMatrix(ArrayList<ArrayList<Tree>> treeMatrix) {
        for (int i = 0; i < treeMatrix.size(); i++) {
            for (int j = 0; j < treeMatrix.get(i).size(); j++) {
                System.out.print(treeMatrix.get(i).get(j).height);

                System.out.print(" ");

                if (treeMatrix.get(i).get(j).visibleFromTop) System.out.print("t");
                if (treeMatrix.get(i).get(j).visibleFromRight) System.out.print("r");
                if (treeMatrix.get(i).get(j).visibleFromBottom) System.out.print("b");
                if (treeMatrix.get(i).get(j).visibleFromLeft) System.out.print("l");

                System.out.print(" ");

                // System.out.print(treeMatrix.get(i).get(j).topView);
                // System.out.print(treeMatrix.get(i).get(j).rightView);
                // System.out.print(treeMatrix.get(i).get(j).bottomView);
                // System.out.print(treeMatrix.get(i).get(j).leftView);
                System.out.print(treeMatrix.get(i).get(j).scenicScore());

                System.out.print("\t\t");
            }
            System.out.println();
        }
    }

    private static void determineVisibility(ArrayList<ArrayList<Tree>> treeMatrix) {
        determineTopBottomVisibility(treeMatrix);
        determineLeftRightVisibility(treeMatrix);
    }

    private static void determineTopBottomVisibility(ArrayList<ArrayList<Tree>> treeMatrix) {
        // for the number of lines
        for (int line = 0; line < treeMatrix.size(); line++) {

            // go through each column
            for (int col = 0; col < treeMatrix.get(0).size(); col++) {

                // get the int at (line, col) to compare with rest of column
                Tree toCompare = treeMatrix.get(line).get(col);
                boolean topViewFound = false;
                boolean bottomViewFound = false;
                int view = 0;
                boolean isLess = true;
                // System.out.println("toCompare: " + toCompare.height);

                // for each row, compare the value at col with toCompare
                for (int row = 0; row < treeMatrix.size(); row++) {
                    // System.out.println("line = " + line + " row = " + row + " col = " + col + " is " + treeMatrix.get(row).get(col).height);

                    // if it's the first line, toCompare is visible from the top
                    if (line == 0) {
                        toCompare.visibleFromTop = true;
                        toCompare.topView = 0;
                        topViewFound = true;

                    // if it's the last line, toCompare is visible from the bottom
                    } else if (line == treeMatrix.size() - 1) {
                        toCompare.visibleFromBottom = true;
                        toCompare.bottomView = 0;
                        bottomViewFound = true;
                    }

                    // if row has caught up to line, that means checks up to now have been abovetoCompare
                    // so, if isLess is true, then toCompare is visible form top
                    if (row == line) {
                        if (isLess) {
                            toCompare.visibleFromTop = true;
                        }

                        toCompare.topView = view;
                        topViewFound = true;
                        view = -1;
                        isLess = true;

                    // otherwise, check if (row, col) is greater than toCompare
                    // if it is, then this tree is no visible from the bottom
                    } else if (treeMatrix.get(row).get(col).height >= toCompare.height) {
                        isLess = false;
                        if (!bottomViewFound && topViewFound) {
                            toCompare.bottomView = view + 1;
                            bottomViewFound = true;
                        }
                        view = 0;
                        // for efficiency, could add a break as there is no reason to continue
                        // however, implementation would need to be changed bc even if this is true forthe above toCompare, we still need to compare with below toCompare
                        // therefore, for now, I will unecessarily continue in order to make sure allthe necessities are checked
                    }

                    view++;
                }

                // if it's checked all rows and isLess is still true, that means toCompare is visible from the bottom
                if (isLess) {
                    toCompare.visibleFromBottom = true;
                }

                if (!bottomViewFound) {
                    toCompare.bottomView = view;
                }
            }
        }
    }

    private static void determineLeftRightVisibility(ArrayList<ArrayList<Tree>> treeMatrix) {
        // for the number of lines
        for (int row = 0; row < treeMatrix.size(); row++) {

            // go through each column
            for (int compCol = 0; compCol < treeMatrix.get(0).size(); compCol++) {
                boolean isLess = true;
                boolean rightViewFound = false;
                boolean leftViewFound = false;
                int view = 0;
                Tree toCompare = treeMatrix.get(row).get(compCol);
                // System.out.println(toCompare.height);

                // for each column, compare with the other columns on that line
                for (int col = 0; col < treeMatrix.get(0).size(); col++) {
                    // System.out.println("compCol = " + compCol + " row = " + row + " col = " + col + " is " + treeMatrix.get(row).get(col).height);

                    // if it's the first column, toCompare is visible from the left
                    if (compCol == 0) {
                        toCompare.visibleFromLeft = true;
                        toCompare.leftView = 0;
                        leftViewFound = true;

                    // if it's the last column, toCompare is visible from the right
                    } else if (compCol == treeMatrix.get(0).size() - 1) {
                        toCompare.visibleFromRight = true;
                        toCompare.rightView = 0;
                        rightViewFound = true;
                    }

                    // if col has caught up to compCol, that means checks up to now have been leftof toCompare
                    // so, if isLess is true, then toCompare is visible from left
                    if (compCol == col) {
                        if (isLess) {
                            toCompare.visibleFromLeft = true;
                        }

                        toCompare.leftView = view;
                        leftViewFound = true;
                        view = -1;
                        isLess = true;

                    // otherwise, check if (row, col) is greater than toCompare
                    // if it is, then this tree is not visible from the right
                    } else if (treeMatrix.get(row).get(col).height >= toCompare.height) {
                        isLess = false;
                        if (!rightViewFound && leftViewFound) {
                            toCompare.rightView = view + 1;
                            rightViewFound = true;
                        }
                        view = 0;

                        // for efficiency, could add a break as there is no reason to continue
                        // however, implementation would need to be changed bc even if this is true theabove toCompare, we still need to compare with below toCompare
                        // therefore, for now, I will unecessarily continue in order to make sure allthe necessities are checked
                    }

                    view++;
                }

                // if it's checked all columns and isLess is still true, that means toCompare is visible from the right
                if (isLess) {
                    toCompare.visibleFromRight = true;
                }

                if (!rightViewFound) {
                    toCompare.rightView = view;
                }
            }
        }
    }

    private static int countVisible(ArrayList<ArrayList<Tree>> treeMatrix) {
        int totalVisible = 0;

        for (int i = 0; i < treeMatrix.size(); i++) {
            for (int j = 0; j < treeMatrix.get(i).size(); j++) {
                if (treeMatrix.get(i).get(j).visibleFromTop
                || treeMatrix.get(i).get(j).visibleFromRight
                || treeMatrix.get(i).get(j).visibleFromBottom
                || treeMatrix.get(i).get(j).visibleFromLeft) {
                    totalVisible++;
                }
            }
        }

        return totalVisible;
    }

    private static void printMaxScenicScore(ArrayList<ArrayList<Tree>> treeMatrix) {
        int maxScenicScore = treeMatrix.get(0).get(0).scenicScore();

        for (int i = 0; i < treeMatrix.size(); i++) {
            for (int j = 0; j < treeMatrix.get(i).size(); j++) {
                if (treeMatrix.get(i).get(j).scenicScore() > maxScenicScore) {
                    maxScenicScore = treeMatrix.get(i).get(j).scenicScore();
                }
            }
        }

        System.out.println("The highest possible scenic score is " + maxScenicScore + ".");
    }
}
