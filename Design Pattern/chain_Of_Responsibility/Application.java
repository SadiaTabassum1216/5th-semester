package chain_Of_Responsibility;

public class Application extends HelpHandler {
    public Application(int topic) {
        super(null, topic);
    }

    public void handleHelp() {
        // show a list of help topics
    }
}