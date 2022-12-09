package Day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import Day7.Node.nodeType;

public class Main {
    static ArrayList<Node> nodesLessThan = new ArrayList<>();
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("/Users/jonathanm./Desktop/More.../Personal Projects/Advent Code/Day7/input.txt");
        Scanner s = new Scanner(f);

        partOne(s);
        // partTwo(s);
    }

    // Answer: 1491614
    private static void partOne(Scanner s) {
        String line = s.nextLine();

        Node start = new Node("/", nodeType.FOLDER);

        Node currNode = start;
        Stack<Node> prevNodes = new Stack<Node>();

        while (s.hasNextLine()) {
            line = s.nextLine();
            String[] lineComp = line.split(" ");

            if (lineComp[0].equals("$")) {
                if (lineComp[1].equals("cd")) {
                    if (lineComp[2].equals("..")) {
                        currNode = prevNodes.pop();

                    } else {
                        Node searchResult = findChildDir(lineComp[2], currNode);
                        if (searchResult != null) {
                            System.out.println("Found " + searchResult.name + " in current tree.");
                            prevNodes.push(currNode);
                            currNode = searchResult;
                        }
                        
                    }
                    System.out.println("Went in " + currNode.name);

                }

            } else if (lineComp[0].equals("dir")) {
                Node newNode = new Node(lineComp[1], nodeType.FOLDER);
                currNode.children.add(newNode);
                System.out.println("Added " + currNode.children.get(currNode.children.size() - 1).name + " as a child of " + currNode.name);
                
            } else {
                Node newNode = new Node(lineComp[1], nodeType.FILE, Integer.parseInt(lineComp[0]));
                currNode.children.add(newNode);
                System.out.println("Added " + currNode.children.get(currNode.children.size() - 1).name + " of size " + currNode.children.get(currNode.children.size() - 1).size + " as a child of " + currNode.name);
            }
        }

        adjustDirectorySizes(start);
        printTotal();
        partTwo(s, start);
    }

    private static void partTwo(Scanner s, Node start) {
        int needToFree = 30000000 - (70000000 - start.size);
        System.out.println("need to free " + needToFree);

        ArrayList<Node> possibleToDelete = new ArrayList<>();
        findPossibleDelDirs(needToFree, start, possibleToDelete);

        int smallestDir = possibleToDelete.get(0).size;
        for (Node node : possibleToDelete) {
            System.out.println("node of size " + node.size);
            if (node.size < smallestDir) {
                smallestDir = node.size;
            }
        }

        System.out.println("The smallest directory that would free enough storage for the update is of size " + smallestDir + ".");
    }

    private static void adjustDirectorySizes(Node start) {
        Node currNode = start;
        // System.out.println("Parent: " + currNode.name + " of size " + currNode.size);

        // Get to rightmost child (so we can work back up tree for determine directory sizes)
        for (int i = 0; i < currNode.children.size(); i++) {
            adjustDirectorySizes(currNode.children.get(i));
        }

        // if it's a directory, total the sizes of nested items
        if (currNode.type == nodeType.FOLDER) {
            int dirSize = 0;
            for (int i = 0; i < currNode.children.size(); i++) {
                dirSize += currNode.children.get(i).size;
            }

            currNode.size = dirSize;

            if (currNode.size <= 100000) {
                nodesLessThan.add(currNode);
            }
        }

        // the rest is purely just for printing (for debugging)
        System.out.println("Parent: " + currNode.name + " has size " + currNode.size);

        for (int i = 0; i < currNode.children.size(); i++) {
            System.out.println("\t" + currNode.children.get(i).name + " of size " + currNode.children.get(i).size);
        }

        System.out.println();
    }

    private static Node findChildDir(String name, Node start) {
        Node currNode = start;

        // if we've found the node, return it
        if (currNode.name.equals(name) && currNode.type == nodeType.FOLDER) {
            return currNode;
        }

        // work down tree, recursively, searching for node
        for (int i = 0; i < currNode.children.size(); i++) {
            Node returnVal = findChildDir(name, currNode.children.get(i));

            // if the node has been found, we want to return that node and stop searching
            if (returnVal != null) {
                return returnVal;
            }
        }

        return null;
    }

    private static void findPossibleDelDirs(int needed, Node start, ArrayList<Node> possibleNodes) {
        Node currNode = start;

        // if we've found a potential node, add it to array
        if (currNode.type == nodeType.FOLDER && currNode.size >= needed) {
            possibleNodes.add(currNode);
        }

        // work down tree, recursively, searching for nodes
        for (int i = 0; i < currNode.children.size(); i++) {
            findPossibleDelDirs(needed, currNode.children.get(i), possibleNodes);
        }
    }

    private static void printTotal() {
        int total = 0;
        for (int i = 0; i < nodesLessThan.size(); i++) {
            total += nodesLessThan.get(i).size;
        }
        System.out.println("Sum of directories with size less than 100000: " + total);
    }
}
