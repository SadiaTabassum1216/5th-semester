

import java.io.File;

public class Main {
    public static void main(String[] args) {
        
        File file = new File("E:\\Codes\\semester_5\\Intelij\\DP\\Observer\\src\\file.txt");
        ConcreteSubject subject = new ConcreteSubject(file);

        ConcreteObserver observer1 = new ConcreteObserver("Observer 1", subject);
        ConcreteObserver observer2 = new ConcreteObserver("Observer 2", subject);
        subject.register(observer1);
        subject.register(observer2);


        subject.monitor();


    }
}
