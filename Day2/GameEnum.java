package Day2;

public enum GameEnum {
    A(1),
    B(2),
    C(3);

    public final int value;

    private GameEnum(int value) {
        this.value = value;
    }
}