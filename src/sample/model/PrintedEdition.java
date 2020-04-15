package sample.model;

import java.time.LocalDate;

public abstract class PrintedEdition {
    protected String name;
    protected LocalDate publishingDate;
    protected AdditionalInfo additionalInfo;

    public PrintedEdition(String name, LocalDate publishingDate, String publishingCompany, boolean takeawayPermission, AdditionalInfo.Quality quality) {
        this.name = name;
        this.publishingDate = publishingDate;
        this.additionalInfo = new AdditionalInfo(publishingCompany, takeawayPermission, quality);
    }

    public PrintedEdition(){

    }

    public String getName() {
        return name;
    }

    public AdditionalInfo getAdditionalInfo() {
        return additionalInfo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdditionalInfo(AdditionalInfo additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public LocalDate getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(LocalDate publishingDate) {
        this.publishingDate = publishingDate;
    }
}
