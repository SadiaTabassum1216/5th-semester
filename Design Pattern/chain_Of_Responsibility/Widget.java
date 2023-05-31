package chain_Of_Responsibility;

public class Widget extends HelpHandler {
    private Widget parent;

    public Widget(Widget parent, int topic) {
        super(parent, topic);
        this.parent = parent;
    }
}
