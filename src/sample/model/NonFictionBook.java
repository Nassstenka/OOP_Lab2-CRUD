package sample.model;
import java.time.LocalDate;
import java.util.Date;

public class NonFictionBook extends Book{
    public enum Sphere{
        programming,
        business,
        science;
    }
    private Sphere sphere;

    public NonFictionBook(String name, LocalDate publishingDate, String publishingCompany, boolean takeawayPermission, AdditionalInfo.Quality quality, String authorName, int pageNumber, int rage, Sphere sphere) {
        super(name, publishingDate, publishingCompany, takeawayPermission, quality, authorName, pageNumber, rage);
        this.sphere = sphere;
    }

    public Sphere getSphere() {
        return sphere;
    }

    public void setSphere(Sphere sphere) {
        this.sphere = sphere;
    }
}
