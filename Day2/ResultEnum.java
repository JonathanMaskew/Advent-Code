package Day2;

public enum ResultEnum {
    WIN(6),
    DRAW(3);

    public final int value;

    private ResultEnum(int value) {
        this.value = value;
    }
}
