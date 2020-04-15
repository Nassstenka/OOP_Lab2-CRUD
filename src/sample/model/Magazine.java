package sample.model;
import java.time.LocalDate;
import java.util.Date;

public class Magazine extends PrintedEdition{
    public enum AgeCategory {
        forKids,
        forTeenagers,
        forAdults;
    }
    private AgeCategory ageCategory;

    public Magazine(String name, LocalDate publishingDate, String publishingCompany, boolean takeawayPermission, AdditionalInfo.Quality quality, AgeCategory ageCategory) {
        super(name, publishingDate, publishingCompany, takeawayPermission, quality);
        this.ageCategory = ageCategory;
    }

    public Magazine(){

    }

    public AgeCategory getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(AgeCategory ageCategory) {
        this.ageCategory = ageCategory;
    }
}
