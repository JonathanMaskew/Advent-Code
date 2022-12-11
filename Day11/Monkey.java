package Day11;

import java.util.LinkedList;

public class Monkey {
    public enum operations {
        ADD,
        MUL,
        SQU
    }

    LinkedList<Item> items;
    int throwToTrue;
    int throwToFalse;

    operations operation;
    int operationNum;
    int testDivisible;

    int inspections;

    public Monkey() {
        items = new LinkedList<>();

        this.inspections = 0;
    }
}
