import java.io.File;

public class ConcreteSubject extends Subject {
    private File file;

    public ConcreteSubject(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public void monitor() {
        long lastModifiedTime = getFile().lastModified();

        while (true) {
            long newModifiedTime = getFile().lastModified();
            if (newModifiedTime != lastModifiedTime) {
                lastModifiedTime = newModifiedTime;
                notifyObservers();
            }
        }
    }

    
}
