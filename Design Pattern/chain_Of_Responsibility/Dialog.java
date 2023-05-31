package chain_Of_Responsibility;

public class Dialog extends Widget {
    public Dialog(HelpHandler handler, int topic) {
        super(null, topic);
        setHandler(handler, topic);
    }

    public void handleHelp() {
        if (hasHelp()) {
            // offer help on the dialog
        } else {
            super.handleHelp();
        }
    }
}

