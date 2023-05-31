package chain_Of_Responsibility;

public class HelpHandler {
    public static final int NO_HELP_TOPIC = -1;
    private HelpHandler successor;
    private int topic;

    public HelpHandler(HelpHandler successor, int topic) {
        this.successor = successor;
        this.topic = topic;
    }

    public boolean hasHelp() {
        return topic != NO_HELP_TOPIC;
    }

    public void setHandler(HelpHandler successor, int topic) {
        this.successor = successor;
        this.topic = topic;
    }

    public void handleHelp() {
        if (successor != null) {
            successor.handleHelp();
        }
    }
}
