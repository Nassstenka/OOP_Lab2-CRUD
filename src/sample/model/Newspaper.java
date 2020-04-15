package sample.model;
import java.time.LocalDate;

public class Newspaper extends PrintedEdition{
    public enum NewspaperType {
        Local,
        Regional,
        State,
        International;
    }
    private NewspaperType type;
    private boolean isColorful;

    public Newspaper(String name, LocalDate publishingDate, String publishingCompany, boolean takeawayPermission, AdditionalInfo.Quality quality, NewspaperType type, boolean isColorful) {
        super(name, publishingDate, publishingCompany, takeawayPermission, quality);
        this.type = type;
        this.isColorful = isColorful;
    }

    public Newspaper() {
    }

    public void setType(NewspaperType type) {
        this.type = type;
    }

    public void setColorful(boolean colorful) {
        isColorful = colorful;
    }

    public NewspaperType getType() {
        return type;
    }

    public boolean isColorful() {
        return isColorful;
    }
}
