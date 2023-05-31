package chain_Of_Responsibility;

public class Button extends Widget {
    public Button(Widget parent, int topic) {
        super(parent, topic);
    }

    public void handleHelp() {
        if (hasHelp()) {
            // offer help on the button
        } else {
            super.handleHelp();
        }
    }
}