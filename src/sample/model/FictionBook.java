package sample.model;
import java.time.LocalDate;
import java.util.Date;

public class FictionBook extends Book{
    public enum Genre{
        fairyTail,
        loveStory,
        novel,
        poetry;
    }
    private Genre genre;

    public FictionBook(String name, LocalDate publishingDate, String publishingCompany, boolean takeawayPermission, AdditionalInfo.Quality quality, String authorName, int pageNumber, int rage, Genre genre) {
        super(name, publishingDate, publishingCompany, takeawayPermission, quality, authorName, pageNumber, rage);
        this.genre = genre;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
