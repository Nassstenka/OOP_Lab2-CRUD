package sample.model;

import java.time.LocalDate;

public abstract class Book extends PrintedEdition{
    protected String authorName;
    protected int pageNumber;
    protected int rage;

    public Book(String name, LocalDate publishingDate, String publishingCompany, boolean takeawayPermission, AdditionalInfo.Quality quality, String authorName, int pageNumber, int rage) {
        super(name, publishingDate, publishingCompany, takeawayPermission, quality);
        this.authorName = authorName;
        this.pageNumber = pageNumber;
        this.rage = rage;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setRage(int rage) {
        this.rage = rage;
    }

    public String getAuthorName() {
        return authorName;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getRage() {
        return rage;
    }
}
