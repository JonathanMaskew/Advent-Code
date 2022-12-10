package Day10;

public class Command {
    public enum commType {
        ADD,
        NOOP
    }

    commType type;
    int value;
    int delay;

    public Command(commType type, int value) {
        this.type = type;
        this.value = value;
        this.delay = this.type == commType.ADD ? 2 : 1;
    }
}
