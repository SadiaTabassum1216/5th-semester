package chain_Of_Responsibility;

public class Main {
    public static void main(String[] args) {
        final int PRINT_TOPIC = -1;
        final int PAPER_ORIENTATION_TOPIC = 2;
        final int APPLICATION_TOPIC = 3;

        Application application = new Application(APPLICATION_TOPIC);
        Dialog dialog = new Dialog(application, PRINT_TOPIC);
        Button button = new Button(dialog, PAPER_ORIENTATION_TOPIC);
    }
}