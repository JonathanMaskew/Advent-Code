package Day8;

public class Tree {
    int height;

    boolean visibleFromTop;
    boolean visibleFromRight;
    boolean visibleFromBottom;
    boolean visibleFromLeft;

    int topView;
    int rightView;
    int bottomView;
    int leftView;

    public Tree(int height) {
        this.height = height;
    }

    public int scenicScore() {
        return topView * rightView * bottomView * leftView;
    }
}
