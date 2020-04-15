package sample.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import sample.model.*;

import java.time.LocalDate;
import java.util.regex.Pattern;

import static sample.model.AdditionalInfo.Quality.*;
import static sample.model.FictionBook.Genre.*;
import static sample.model.Magazine.AgeCategory.*;
import static sample.model.Newspaper.NewspaperType.*;
import static sample.model.NonFictionBook.Sphere.*;

public class MainController {
    private ObservableList<PrintedEdition> editionData = FXCollections.observableArrayList();

    @FXML
    private TableView<PrintedEdition> tableEditions;
    @FXML
    private TableColumn<PrintedEdition, String> nameColumn;
    @FXML
    private TableColumn<PrintedEdition, String> companyColumn;
    @FXML
    private TableColumn<PrintedEdition, Boolean> takeawayColumn;
    @FXML
    private TableColumn<PrintedEdition, AdditionalInfo.Quality> qualityColumn;
    @FXML
    private TableColumn<PrintedEdition, LocalDate> dateColumn;
    @FXML
    Label publDateLabel;

    @FXML
    Label ageLabel;

    @FXML
    Label typeLabel;

    @FXML
    Label colorLabel;

    @FXML
    Label authorLabel;

    @FXML
    Label pageNumLabel;

    @FXML
    Label rageLabel;

    @FXML
    Label genreLabel;

    @FXML
    Label sphereLabel;

    @FXML
    DatePicker publDatePicker;

    @FXML
    ComboBox<Magazine.AgeCategory> ageComboBox;

    @FXML
    ComboBox<Newspaper.NewspaperType> typeComboBox;

    @FXML
    ComboBox<FictionBook.Genre> genreComboBox;

    @FXML
    ComboBox<NonFictionBook.Sphere> sphereComboBox;

    @FXML
    TextField rageTextField;

    @FXML
    TextField pageNumTextField;

    @FXML
    CheckBox colorCheckBox;

    @FXML
    TextField authorTextField;

    @FXML
    ComboBox<String> classComboBox;

    @FXML
    ComboBox<AdditionalInfo.Quality> qualityComboBox;

    @FXML
    TextField nameTextField;

    @FXML
    TextField companyTextField;

    @FXML
    CheckBox takeawayCheckBox;

    @FXML
    private ObservableList<String> classes = FXCollections.observableArrayList("Newspaper", "Magazine", "Fiction book", "Non-fiction book");

    @FXML
    private ObservableList<AdditionalInfo.Quality> qualities = FXCollections.observableArrayList(Good, Normal, Bad);

    @FXML
    private ObservableList<Magazine.AgeCategory> ages = FXCollections.observableArrayList(forKids, forTeenagers, forAdults);

    @FXML
    private ObservableList<Newspaper.NewspaperType> types = FXCollections.observableArrayList(Local, Regional, State, International);

    @FXML
    private ObservableList<FictionBook.Genre> genres = FXCollections.observableArrayList(fairyTail, loveStory, novel, poetry);

    @FXML
    private ObservableList<NonFictionBook.Sphere> spheres = FXCollections.observableArrayList(programming, business, science);


    public void CheckStr(String oldValue, String newValue, TextField d) {
            String regDate = "[a-zA-Z]*";
            Pattern pattern = Pattern.compile(regDate);
            if (!pattern.matcher(newValue).matches())
                d.setText(oldValue);
            else
                d.setText(newValue);
    }

    public void CheckInt(String oldValue, String newValue, TextField d) {
        String regDate = "([1-9]+[0-9]*)|(^)";
        Pattern pattern = Pattern.compile(regDate);
        if (!pattern.matcher(newValue).matches())
            d.setText(oldValue);
        else
            d.setText(newValue);
    }

    @FXML
    private void initialize() {
        initData();
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        companyColumn.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getAdditionalInfo().getPublishingCompany()));
        takeawayColumn.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getAdditionalInfo().isTakeawayPermission()));
        qualityColumn.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getAdditionalInfo().getQuality()));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("publishingDate"));
        tableEditions.setItems(editionData);
        classComboBox.setItems(classes);
        qualityComboBox.setItems(qualities);
        genreComboBox.setItems(genres);
        sphereComboBox.setItems(spheres);
        typeComboBox.setItems(types);
        ageComboBox.setItems(ages);
        classComboBox.getSelectionModel().select("Newspaper");
        typeComboBox.setVisible(true);
        colorCheckBox.setVisible(true);
        {
            nameTextField.textProperty().addListener((observableValue, oldValue, newValue) -> {
                CheckStr(oldValue, newValue, nameTextField);
            });
            companyTextField.textProperty().addListener((observableValue, oldValue, newValue) -> {
                CheckStr(oldValue, newValue, companyTextField);
            });
            authorTextField.textProperty().addListener((observableValue, oldValue, newValue) -> {
                CheckStr(oldValue, newValue, authorTextField);
            });
            pageNumTextField.textProperty().addListener((observableValue, oldValue, newValue) -> {
                CheckInt(oldValue, newValue, pageNumTextField);
            });
            rageTextField.textProperty().addListener((observableValue, oldValue, newValue) -> {
                CheckInt(oldValue, newValue, rageTextField);
            });
        }
    }

    private void initData()
    {
        editionData.add(new Magazine("aaa", LocalDate.of(1945, 4, 15),"lololo", true,  Good, forTeenagers));
    }

    @FXML
    private void showRow()
    {
        PrintedEdition edition = tableEditions.getSelectionModel().getSelectedItem();
        if (edition.getClass() == Newspaper.class){
            classComboBox.setValue("Newspaper");
            nameTextField.setText(edition.getName());
            companyTextField.setText(edition.getAdditionalInfo().getPublishingCompany());
            takeawayCheckBox.setSelected(edition.getAdditionalInfo().isTakeawayPermission());
            qualityComboBox.setValue(edition.getAdditionalInfo().getQuality());
            publDatePicker.setValue(edition.getPublishingDate());
            ageLabel.setVisible(false);
            ageComboBox.setVisible(false);
            typeLabel.setVisible(true);
            typeComboBox.setVisible(true);
            colorLabel.setVisible(true);
            colorCheckBox.setVisible(true);
            authorLabel.setVisible(false);
            authorTextField.setVisible(false);
            pageNumLabel.setVisible(false);
            pageNumTextField.setVisible(false);
            rageLabel.setVisible(false);
            rageTextField.setVisible(false);
            genreLabel.setVisible(false);
            genreComboBox.setVisible(false);
            sphereLabel.setVisible(false);
            sphereComboBox.setVisible(false);
            colorCheckBox.setSelected(((Newspaper)edition).isColorful());
            typeComboBox.setValue(((Newspaper)edition).getType());
        }
        if (edition.getClass() == Magazine.class){
            classComboBox.setValue("Magazine");
            nameTextField.setText(edition.getName());
            companyTextField.setText(edition.getAdditionalInfo().getPublishingCompany());
            takeawayCheckBox.setSelected(edition.getAdditionalInfo().isTakeawayPermission());
            qualityComboBox.setValue(edition.getAdditionalInfo().getQuality());
            publDatePicker.setValue(edition.getPublishingDate());
            ageLabel.setVisible(true);
            ageComboBox.setVisible(true);
            typeLabel.setVisible(false);
            typeComboBox.setVisible(false);
            colorLabel.setVisible(false);
            colorCheckBox.setVisible(false);
            authorLabel.setVisible(false);
            authorTextField.setVisible(false);
            pageNumLabel.setVisible(false);
            pageNumTextField.setVisible(false);
            rageLabel.setVisible(false);
            rageTextField.setVisible(false);
            genreLabel.setVisible(false);
            genreComboBox.setVisible(false);
            sphereLabel.setVisible(false);
            sphereComboBox.setVisible(false);
            ageComboBox.setValue(((Magazine)edition).getAgeCategory());
        }
        if (edition.getClass() == FictionBook.class){
            classComboBox.setValue("Fiction Book");
            nameTextField.setText(edition.getName());
            companyTextField.setText(edition.getAdditionalInfo().getPublishingCompany());
            takeawayCheckBox.setSelected(edition.getAdditionalInfo().isTakeawayPermission());
            qualityComboBox.setValue(edition.getAdditionalInfo().getQuality());
            publDatePicker.setValue(edition.getPublishingDate());
            ageLabel.setVisible(false);
            ageComboBox.setVisible(false);
            typeLabel.setVisible(false);
            typeComboBox.setVisible(false);
            colorLabel.setVisible(false);
            colorCheckBox.setVisible(false);
            authorLabel.setVisible(true);
            authorTextField.setVisible(true);
            pageNumLabel.setVisible(true);
            pageNumTextField.setVisible(true);
            rageLabel.setVisible(true);
            rageTextField.setVisible(true);
            genreLabel.setVisible(true);
            genreComboBox.setVisible(true);
            sphereLabel.setVisible(false);
            sphereComboBox.setVisible(false);
            authorTextField.setText(((FictionBook)edition).getAuthorName());
            pageNumTextField.setText(Integer.toString(((FictionBook)edition).getPageNumber(), 10));
            rageTextField.setText(Integer.toString(((FictionBook)edition).getRage(), 10));
            genreComboBox.setValue(((FictionBook)edition).getGenre());
        }
        if (edition.getClass() == NonFictionBook.class){
            classComboBox.setValue("Non-fiction book");
            nameTextField.setText(edition.getName());
            companyTextField.setText(edition.getAdditionalInfo().getPublishingCompany());
            takeawayCheckBox.setSelected(edition.getAdditionalInfo().isTakeawayPermission());
            qualityComboBox.setValue(edition.getAdditionalInfo().getQuality());
            publDatePicker.setValue(edition.getPublishingDate());
            ageLabel.setVisible(false);
            ageComboBox.setVisible(false);
            typeLabel.setVisible(false);
            typeComboBox.setVisible(false);
            colorLabel.setVisible(false);
            colorCheckBox.setVisible(false);
            authorLabel.setVisible(true);
            authorTextField.setVisible(true);
            pageNumLabel.setVisible(true);
            pageNumTextField.setVisible(true);
            rageLabel.setVisible(true);
            rageTextField.setVisible(true);
            genreLabel.setVisible(true);
            genreComboBox.setVisible(true);
            sphereLabel.setVisible(false);
            sphereComboBox.setVisible(false);
            authorTextField.setText(((NonFictionBook)edition).getAuthorName());
            pageNumTextField.setText(Integer.toString(((NonFictionBook)edition).getPageNumber(), 10));
            rageTextField.setText(Integer.toString(((NonFictionBook)edition).getRage(), 10));
            sphereComboBox.setValue(((NonFictionBook)edition).getSphere());
        }
    }

    @FXML
    private void deleteRow()
    {
        PrintedEdition edition = tableEditions.getSelectionModel().getSelectedItem();
        if (edition == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Nothing selected!");
            alert.showAndWait();
        }
        else{
            int selectedIndex = tableEditions.getSelectionModel().getSelectedIndex();
            editionData.remove(selectedIndex);
            tableEditions.refresh();
        }
    }

    @FXML
    private void generateFields() {
        switch (classComboBox.getValue()) {
            case "Newspaper": {
                nameTextField.setText("");
                companyTextField.setText("");
                takeawayCheckBox.setSelected(false);
                qualityComboBox.setValue(null);
                publDatePicker.setValue(null);
                ageLabel.setVisible(false);
                ageComboBox.setVisible(false);
                typeLabel.setVisible(true);
                typeComboBox.setVisible(true);
                colorLabel.setVisible(true);
                colorCheckBox.setVisible(true);
                authorLabel.setVisible(false);
                authorTextField.setVisible(false);
                pageNumLabel.setVisible(false);
                pageNumTextField.setVisible(false);
                rageLabel.setVisible(false);
                rageTextField.setVisible(false);
                genreLabel.setVisible(false);
                genreComboBox.setVisible(false);
                sphereLabel.setVisible(false);
                sphereComboBox.setVisible(false);
                typeComboBox.setValue(null);
                break;
            }
            case "Magazine": {
                nameTextField.setText("");
                companyTextField.setText("");
                takeawayCheckBox.setSelected(false);
                qualityComboBox.setValue(null);
                publDatePicker.setValue(null);
                ageLabel.setVisible(true);
                ageComboBox.setVisible(true);
                typeLabel.setVisible(false);
                typeComboBox.setVisible(false);
                colorLabel.setVisible(false);
                colorCheckBox.setVisible(false);
                authorLabel.setVisible(false);
                authorTextField.setVisible(false);
                pageNumLabel.setVisible(false);
                pageNumTextField.setVisible(false);
                rageLabel.setVisible(false);
                rageTextField.setVisible(false);
                genreLabel.setVisible(false);
                genreComboBox.setVisible(false);
                sphereLabel.setVisible(false);
                sphereComboBox.setVisible(false);
                ageComboBox.setValue(null);
                break;
            }
            case "Fiction book": {
                nameTextField.setText("");
                companyTextField.setText("");
                takeawayCheckBox.setSelected(false);
                qualityComboBox.setValue(null);
                publDatePicker.setValue(null);
                ageLabel.setVisible(false);
                ageComboBox.setVisible(false);
                typeLabel.setVisible(false);
                typeComboBox.setVisible(false);
                colorLabel.setVisible(false);
                colorCheckBox.setVisible(false);
                authorLabel.setVisible(true);
                authorTextField.setVisible(true);
                pageNumLabel.setVisible(true);
                pageNumTextField.setVisible(true);
                rageLabel.setVisible(true);
                rageTextField.setVisible(true);
                genreLabel.setVisible(true);
                genreComboBox.setVisible(true);
                sphereLabel.setVisible(false);
                sphereComboBox.setVisible(false);
                authorTextField.setText("");
                pageNumTextField.setText("");
                rageTextField.setText("");
                genreComboBox.setValue(null);
                break;
            }
            case "Non-fiction book": {
                nameTextField.setText("");
                companyTextField.setText("");
                takeawayCheckBox.setSelected(false);
                qualityComboBox.setValue(null);
                publDatePicker.setValue(null);
                ageLabel.setVisible(false);
                ageComboBox.setVisible(false);
                typeLabel.setVisible(false);
                typeComboBox.setVisible(false);
                colorLabel.setVisible(false);
                colorCheckBox.setVisible(false);
                authorLabel.setVisible(true);
                authorTextField.setVisible(true);
                pageNumLabel.setVisible(true);
                pageNumTextField.setVisible(true);
                rageLabel.setVisible(true);
                rageTextField.setVisible(true);
                genreLabel.setVisible(false);
                genreComboBox.setVisible(false);
                sphereLabel.setVisible(true);
                sphereComboBox.setVisible(true);
                authorTextField.setText("");
                pageNumTextField.setText("");
                rageTextField.setText("");
                sphereComboBox.setValue(null);
                break;
            }
        }
    }

    @FXML
    public void saveNewRec()
    {
        PrintedEdition edition = tableEditions.getSelectionModel().getSelectedItem();
        if (edition == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Nothing selected!");
            alert.showAndWait();
        }
        else {
            String name = nameTextField.getText();
            String publishingCompany = companyTextField.getText();
            boolean takeawayPermission = takeawayCheckBox.isSelected();
            AdditionalInfo.Quality quality = qualityComboBox.getValue();
            LocalDate publishingDate = publDatePicker.getValue();
            switch (classComboBox.getValue()) {
                case "Newspaper": {
                    Newspaper.NewspaperType type = typeComboBox.getValue();
                    boolean isColorful = colorCheckBox.isSelected();
                    if ((name.equals("")) || (publishingCompany.equals("")) || (quality == null) || (publishingDate == null) || (type == null)){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setContentText("Please, enter all information!!!");
                        alert.showAndWait();
                    }
                    else {
                        edition.setName(name);
                        edition.getAdditionalInfo().setPublishingCompany(publishingCompany);
                        edition.getAdditionalInfo().setTakeawayPermission(takeawayPermission);
                        edition.getAdditionalInfo().setQuality(quality);
                        edition.setPublishingDate(publishingDate);
                        ((Newspaper) edition).setType(type);
                        ((Newspaper) edition).setColorful(isColorful);
                    }
                    break;
                }
                case "Magazine": {
                    Magazine.AgeCategory ageCategory = ageComboBox.getValue();
                    if ((name.equals("")) || (publishingCompany.equals("")) || (quality == null) || (publishingDate == null) || (ageCategory == null)){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setContentText("Please, enter all information!!!");
                        alert.showAndWait();
                    }
                    else {
                        edition.setName(name);
                        edition.getAdditionalInfo().setPublishingCompany(publishingCompany);
                        edition.getAdditionalInfo().setTakeawayPermission(takeawayPermission);
                        edition.getAdditionalInfo().setQuality(quality);
                        edition.setPublishingDate(publishingDate);
                        ((Magazine) edition).setAgeCategory(ageCategory);
                    }
                    break;
                }
                case "Fiction Book": {
                    String authorName = authorTextField.getText();
                    FictionBook.Genre genre = genreComboBox.getValue();
                    if ((name.equals("")) || (publishingCompany.equals("")) || (quality == null) || (publishingDate == null) || (authorName.equals("")) || (pageNumTextField.getText().equals("")) || (rageTextField.getText().equals("")) || (genre == null)){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setContentText("Please, enter all information!!!");
                        alert.showAndWait();
                    }
                    else {
                        int pageNumber = Integer.parseInt(pageNumTextField.getText());
                        int rage = Integer.parseInt(rageTextField.getText());
                        edition.setName(name);
                        edition.getAdditionalInfo().setPublishingCompany(publishingCompany);
                        edition.getAdditionalInfo().setTakeawayPermission(takeawayPermission);
                        edition.getAdditionalInfo().setQuality(quality);
                        edition.setPublishingDate(publishingDate);
                        ((FictionBook) edition).setAuthorName(authorName);
                        ((FictionBook) edition).setGenre(genre);
                        ((FictionBook) edition).setPageNumber(pageNumber);
                        ((FictionBook) edition).setRage(rage);
                    }
                    break;
                }
                case "Non-fiction Book": {
                    String authorName = authorTextField.getText();
                    NonFictionBook.Sphere sphere = sphereComboBox.getValue();
                    if ((name.equals("")) || (publishingCompany.equals("")) || (quality == null) || (publishingDate == null) || (authorName.equals("")) || (pageNumTextField.getText().equals("")) || (rageTextField.getText().equals("")) || (sphere == null)){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setContentText("Please, enter all information!!!");
                        alert.showAndWait();
                    }
                    else {
                        int pageNumber = Integer.parseInt(pageNumTextField.getText());
                        int rage = Integer.parseInt(rageTextField.getText());
                        edition.setName(name);
                        edition.getAdditionalInfo().setPublishingCompany(publishingCompany);
                        edition.getAdditionalInfo().setTakeawayPermission(takeawayPermission);
                        edition.getAdditionalInfo().setQuality(quality);
                        edition.setPublishingDate(publishingDate);
                        ((NonFictionBook) edition).setAuthorName(authorName);
                        ((NonFictionBook) edition).setSphere(sphere);
                        ((NonFictionBook) edition).setPageNumber(pageNumber);
                        ((NonFictionBook) edition).setRage(rage);
                    }
                    break;
                }
            }
        }
        tableEditions.refresh();
    }

    @FXML
    public void createNewRec()
    {
        String name = nameTextField.getText();
        String publishingCompany = companyTextField.getText();
        boolean takeawayPermission = takeawayCheckBox.isSelected();
        AdditionalInfo.Quality quality = qualityComboBox.getValue();
        LocalDate publishingDate = publDatePicker.getValue();
        switch (classComboBox.getValue()) {
            case "Newspaper": {
                Newspaper.NewspaperType type = typeComboBox.getValue();
                boolean isColorful = colorCheckBox.isSelected();
                if ((name.equals("")) || (publishingCompany.equals("")) || (quality == null) || (publishingDate == null) || (type == null)){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Please, enter all information!!!");
                    alert.showAndWait();
                }
                else {
                    Newspaper newspaper = new Newspaper(name, publishingDate, publishingCompany, takeawayPermission, quality, type, isColorful);
                    editionData.add(newspaper);
                }
                break;
            }
            case "Magazine":{
                Magazine.AgeCategory ageCategory = ageComboBox.getValue();
                if ((name.equals("")) || (publishingCompany.equals("")) || (quality == null) || (publishingDate == null) || (ageCategory == null)){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Please, enter all information!!!");
                    alert.showAndWait();
                }
                else {
                    Magazine magazine = new Magazine(name, publishingDate, publishingCompany, takeawayPermission, quality, ageCategory);
                    editionData.add(magazine);
                }
                break;
            }
            case "Fiction book":{
                String authorName = authorTextField.getText();
                FictionBook.Genre genre = genreComboBox.getValue();
                if ((name.equals("")) || (publishingCompany.equals("")) || (quality == null) || (publishingDate == null) || (authorName.equals("")) || (pageNumTextField.getText().equals("")) || (rageTextField.getText().equals("")) || (genre == null)){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Please, enter all information!!!");
                    alert.showAndWait();
                }
                else {
                    int pageNumber = Integer.parseInt(pageNumTextField.getText());
                    int rage = Integer.parseInt(rageTextField.getText());
                    FictionBook fictionBook = new FictionBook(name, publishingDate, publishingCompany, takeawayPermission, quality, authorName, pageNumber, rage, genre);
                    editionData.add(fictionBook);
                }
                break;
            }
            case "Non-fiction book":{
                String authorName = authorTextField.getText();
                NonFictionBook.Sphere sphere = sphereComboBox.getValue();
                if ((name.equals("")) || (publishingCompany.equals("")) || (quality == null) || (publishingDate == null) || (authorName.equals("")) || (pageNumTextField.getText().equals("")) || (rageTextField.getText().equals("")) || (sphere == null)){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Please, enter all information!!!");
                    alert.showAndWait();
                }
                else {
                    int pageNumber = Integer.parseInt(pageNumTextField.getText());
                    int rage = Integer.parseInt(rageTextField.getText());
                    NonFictionBook fictionBook = new NonFictionBook(name, publishingDate, publishingCompany, takeawayPermission, quality, authorName, pageNumber, rage, sphere);
                    editionData.add(fictionBook);
                }
                break;
            }
        }
        tableEditions.refresh();
    }
}
