package Day7;

import java.util.ArrayList;

public class Node {
    public enum nodeType {
        FILE,
        FOLDER
    }

    String name;
    nodeType type;
    int size;
    ArrayList<Node> children;

    public Node(String name, nodeType type) {
        this.name = name;
        this.type = type;
        this.children = new ArrayList<Node>();
    }

    public Node(String name, nodeType type, int size) {
        this.name = name;
        this.type = type;
        this.children = new ArrayList<Node>();
        this.size = size;
    }
}
