

public class ConcreteObserver implements Observer {
    private String name;
    private ConcreteSubject subject;

    public ConcreteObserver(String name, ConcreteSubject subject) {
        this.name = name;
        this.subject = subject;
    }

    public void update() {
    	System.out.println(name + " has been notified of changes to file: " + subject.getFile().getName());
    }

}
