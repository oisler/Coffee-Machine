package machine.enums;

public enum MainAction {
    BUY("buy"),
    FILL("fill"),
    TAKE("take"),
    REMAINING("remaining"),
    EXIT("exit");

    public final String label;

    MainAction(String label) {
        this.label = label;
    }
}
