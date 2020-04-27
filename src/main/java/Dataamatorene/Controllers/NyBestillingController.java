package Dataamatorene.Controllers;

import Dataamatorene.App;
import Dataamatorene.Bestilling.Bestilling;
import Dataamatorene.Bestilling.BestillingsRegister;
import Dataamatorene.Brukere.BrukerRegister;
import Dataamatorene.Comparators.DatakomponentNavnComparator;
import Dataamatorene.Comparators.DatakomponentPrisHoyComparator;
import Dataamatorene.Comparators.DatakomponentPrisLavComparator;
import Dataamatorene.Comparators.DatakomponentVarekodeComparator;
import Dataamatorene.Datakomponenter.*;
import Dataamatorene.Dialogs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;


public class NyBestillingController {

    ObservableList<Harddisk> oHarddisk = FXCollections.observableArrayList(KomponentRegister.getHarddiskArrayList());
    ObservableList<Hovedkort> oHovedkort = FXCollections.observableArrayList(KomponentRegister.getHovedkortArrayList());
    ObservableList<Kabinett> oKabinett = FXCollections.observableArrayList(KomponentRegister.getKabinettArrayList());
    ObservableList<Lydkort> oLydkort = FXCollections.observableArrayList(KomponentRegister.getLydkortArrayList());
    ObservableList<Minne> oMinne = FXCollections.observableArrayList(KomponentRegister.getMinneArrayList());
    ObservableList<Mus> oMus = FXCollections.observableArrayList(KomponentRegister.getMusArrayList());
    ObservableList<Prosessor> oProsessor = FXCollections.observableArrayList(KomponentRegister.getProsessorArrayList());
    ObservableList<Skjerm> oSkjerm = FXCollections.observableArrayList(KomponentRegister.getSkjermArrayList());
    ObservableList<Skjermkort> oSkjermkort = FXCollections.observableArrayList(KomponentRegister.getSkjermkortArrayList());
    ObservableList<Tastatur> oTastatur = FXCollections.observableArrayList(KomponentRegister.getTastaturArrayList());

    ObservableList<String> oSort = FXCollections.observableArrayList("Navn", "Varenummer", "Pris lav-høy",
            "Pris høy-lav");

    double totPris; Double harddiskPris = 0.0; double hovedkortPris = 0; double lydkortPris; double skjermkortPris = 0;
    double prosessorPris = 0; double minnePris = 0; double kabinettPris = 0; double skjermPris = 0;
    double tastarurPris = 0; double musPris = 0;

    public void initialize() throws FileNotFoundException {

        // Alle bestillingsbokser i en

        titledPanes = new ArrayList<>(Arrays.asList(tpHarddisk, tpHovedkort, tpLydkort, tpSkjermkort, tpProsessor,
                tpMinne, tpKabinett, tpSkjerm, tpTastatur, tpMus));

        scrollPanes = new ArrayList<>(Arrays.asList(spHarddisk, spHovedkort, spLydkort, spSkjermkort, spProsessor,
                spMinne, spKabinett, spSkjerm, spTastatur, spMus));

        observableLists = new ArrayList<>(Arrays.asList(oHarddisk,
                oHovedkort, oLydkort, oSkjermkort, oProsessor, oMinne, oKabinett, oSkjerm, oTastatur, oMus));

        labels = new ArrayList<>(Arrays.asList(lblUtHarddisk, lblUtHovedkort, lblUtLydkort,
                lblUtSkjermkort, lblUtProsessor, lblUtMinne,
                 lblUtKabinett, lblUtSkjerm, lblUtTastatur, lblUtMus));

        priser = new ArrayList<>(Arrays.asList(harddiskPris, hovedkortPris, lydkortPris, skjermkortPris,
                prosessorPris, minnePris
                 , kabinettPris, skjermPris, tastarurPris, musPris));

        for (Label l:labels) {
            l.toBack();
        }

        for (int i = 0; i < titledPanes.size(); i++) {
            double x = titledPanes.get(i).getLayoutX();
            double y = titledPanes.get(i).getLayoutY();

            VBox vBox = new VBox(10);
            ChoiceBox<String> valg = new ChoiceBox<>(oSort);
            TextField text = new TextField();
            text.setPromptText("Søk");
            valg.setValue("Varenummer");
            HBox hBoxUt = new HBox(10);
            hBoxUt.getChildren().addAll(new Label("Sorter"), valg, text);
            hBoxUt.setAlignment(Pos.CENTER_RIGHT);
            hBoxUt.setStyle("-fx-padding: 4 4 0 0;");
            vBox.getChildren().add(hBoxUt);

            /*
            for (int j = 0; j < observableLists.get(i).size(); j++) {
                HBox hBox = new HBox(10);
                Label label = new Label(observableLists.get(i).get(j).getBeskrivelse());
                label.setStyle("-fx-border-width: 0 0 1 0; -fx-border-color: white; -fx-border-style: solid;" +
                        " -fx-text-alignment: left!important; -fx-font-size: 20px");
                ImageView image;
                if (observableLists.get(i).get(j).getBilde() != null) {
                    image = new ImageView(observableLists.get(i).get(j).getBilde());
                } else image = new ImageView(new Image(
                        new FileInputStream("src/main/java/Dataamatorene/Pictures/nia.jpg")));
                image.setFitHeight(150);
                image.setPreserveRatio(true);
                label.setPrefWidth(1115);
                label.setPrefHeight(150);
                hBox.getChildren().addAll(image, label);

                int finalI = i;
                int finalJ = j;
                image.setOnMouseClicked(mouseEvent -> {
                    mouseClick(x, y, finalI, finalJ);
                    datakomponents[finalI] = observableLists.get(finalI).get(finalJ);
                });
                label.setOnMouseClicked(mouseEvent -> {
                    mouseClick(x, y, finalI, finalJ);
                    datakomponents[finalI] = observableLists.get(finalI).get(finalJ);
                });
                vBox.getChildren().add(hBox);
                vBox.setPrefWidth(1100);

                titledPanes.get(i).setExpanded(false);
                scrollPanes.get(i).setContent(vBox);

                titledPanes.get(i).setOnMouseClicked(mouseEvent -> {
                    if (titledPanes.get(finalI).isExpanded()) {
                        titledPanes.get(finalI).toFront();
                        titledPanes.get(finalI).setLayoutX(20);
                        titledPanes.get(finalI).setLayoutY(20);
                        titledPanes.get(finalI).setPrefWidth(1125);
                        titledPanes.get(finalI).setPrefHeight(600);
                    } else {
                        titledPanes.get(finalI).setPrefWidth(150);
                        titledPanes.get(finalI).setPrefHeight(28);
                        titledPanes.get(finalI).setLayoutX(x);
                        titledPanes.get(finalI).setLayoutY(y);
                        if (labels.get(finalI).getText().equals("")) {
                            titledPanes.get(finalI).setStyle("-fx-border-color: red;" +
                                    " -fx-border-width: 1px; -fx-border-style: solid");
                        }
                    }
                });
            }
             */

            int finalI1 = i;
            int finalI2 = i;

            scrollPanes.get(finalI1).setContent(null);
            vBox.getChildren().clear();
            setFiltered(x, y, vBox, hBoxUt, finalI1, finalI2, observableLists.get(i));
            titledPanes.get(i).setExpanded(false);

            text.setOnKeyReleased(keyEvent -> {
                scrollPanes.get(finalI1).setContent(null);
                vBox.getChildren().clear();
                ObservableList<? extends Datakomponent> aktiv = observableLists.get(finalI1)
                        .filtered(l -> l.getBeskrivelse().toLowerCase().contains(text.getText().toLowerCase()));
                setFiltered(x, y, vBox, hBoxUt, finalI1, finalI2, aktiv);
            });

            valg.setOnHiding(event -> {
                scrollPanes.get(finalI1).setContent(null);
                vBox.getChildren().clear();
                if (valg.getSelectionModel().getSelectedItem().equalsIgnoreCase("Navn")) {
                    observableLists.get(finalI1).sort(new DatakomponentNavnComparator());
                }
                if (valg.getSelectionModel().getSelectedItem().equalsIgnoreCase("Pris lav-høy")) {
                    observableLists.get(finalI1).sort(new DatakomponentPrisLavComparator());
                }
                if (valg.getSelectionModel().getSelectedItem().equalsIgnoreCase("Pris høy-lav")) {
                    observableLists.get(finalI1).sort(new DatakomponentPrisHoyComparator());
                }
                if (valg.getSelectionModel().getSelectedItem().equalsIgnoreCase("Varenummer")) {
                    observableLists.get(finalI1).sort(new DatakomponentVarekodeComparator());
                }
                ObservableList<? extends Datakomponent> aktiv = observableLists.get(finalI1)
                        .filtered(l -> l.getBeskrivelse().toLowerCase().contains(text.getText().toLowerCase()));
                setFiltered(x, y, vBox, hBoxUt, finalI1, finalI2, aktiv);
            });
        }

    }

    private void setFiltered(double x, double y, VBox vBox, HBox hBoxUt, int finalI1, int finalI2,
                             ObservableList<? extends Datakomponent> aktiv) {
        vBox.getChildren().add(hBoxUt);
        for (int j = 0; j < aktiv.size(); j++) {
            HBox hBox = new HBox(10);
            Label label = new Label(aktiv.get(j).getBeskrivelse());
            label.setStyle("-fx-border-width: 0 0 1 0; -fx-border-color: white; -fx-border-style: solid;" +
                    " -fx-text-alignment: left!important; -fx-font-size: 20px");
            ImageView image = null;
            if (aktiv.get(j).getBilde() != null) {
                image = new ImageView(aktiv.get(j).getBilde());
            } else {
                try {
                    image = new ImageView(new Image(
                            new FileInputStream("src/main/java/Dataamatorene/Pictures/nia.jpg")));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            image.setFitHeight(150);
            image.setPreserveRatio(true);
            label.setPrefWidth(1115);
            label.setPrefHeight(150);
            hBox.getChildren().addAll(image, label);

            int finalI = finalI2;
            int finalJ = j;
            image.setOnMouseClicked(mouseEvent -> {
                mouseClick(x, y, finalI, finalJ);
                datakomponents[finalI] = aktiv.get(finalJ);
            });
            label.setOnMouseClicked(mouseEvent -> {
                mouseClick(x, y, finalI, finalJ);
                datakomponents[finalI] = aktiv.get(finalJ);
            });
            vBox.getChildren().add(hBox);
            vBox.setPrefWidth(1100);


            titledPanes.get(finalI2).setOnMouseClicked(mouseEvent -> {
                if (titledPanes.get(finalI).isExpanded()) {
                    titledPanes.get(finalI).toFront();
                    titledPanes.get(finalI).setLayoutX(20);
                    titledPanes.get(finalI).setLayoutY(20);
                    titledPanes.get(finalI).setPrefWidth(1125);
                    titledPanes.get(finalI).setPrefHeight(600);
                } else {
                    titledPanes.get(finalI).setPrefWidth(150);
                    titledPanes.get(finalI).setPrefHeight(28);
                    titledPanes.get(finalI).setLayoutX(x);
                    titledPanes.get(finalI).setLayoutY(y);
                    if (labels.get(finalI).getText().equals("")) {
                        titledPanes.get(finalI).setStyle("-fx-border-color: red;" +
                                " -fx-border-width: 1px; -fx-border-style: solid");
                    }
                }
            });
        }
        titledPanes.get(finalI2).setExpanded(false);
        scrollPanes.get(finalI2).setContent(vBox);

        titledPanes.get(finalI2).setExpanded(true);
    }


    private void mouseClick(double x, double y, int finalI, int finalJ) {
        titledPanes.get(finalI).setLayoutX(x);
        titledPanes.get(finalI).setLayoutY(y);
        priser.set(finalI, observableLists.get(finalI).get(finalJ).getPris());
        totPris = 0;
        for (double d:priser) {
            totPris += d;
        }
        DecimalFormat df = new DecimalFormat("###,###.00");
        lblTotPris.setText(df.format(totPris) + "kr");
        labels.get(finalI).setText(observableLists.get(finalI)
                .get(observableLists.get(finalI).indexOf(observableLists.get(finalI).get(finalJ))).toString());
        datakomponents[finalI] = observableLists.get(finalI).get(finalJ);
        titledPanes.get(finalI).setStyle("-fx-border-color: none");
        titledPanes.get(finalI).setExpanded(false);
        titledPanes.get(finalI).setPrefWidth(150);
        titledPanes.get(finalI).setPrefHeight(28);

    }

    @FXML
    private TitledPane tpHarddisk;

    @FXML
    private ScrollPane spHarddisk;

    @FXML
    private Label lblUtHarddisk;

    @FXML
    private TitledPane tpHovedkort;

    @FXML
    private ScrollPane spHovedkort;

    @FXML
    private Label lblUtHovedkort;

    @FXML
    private TitledPane tpLydkort;

    @FXML
    private ScrollPane spLydkort;

    @FXML
    private Label lblUtLydkort;

    @FXML
    private TitledPane tpSkjermkort;

    @FXML
    private ScrollPane spSkjermkort;

    @FXML
    private Label lblUtSkjermkort;

    @FXML
    private TitledPane tpProsessor;

    @FXML
    private ScrollPane spProsessor;

    @FXML
    private Label lblUtProsessor;

    @FXML
    private TitledPane tpMinne;

    @FXML
    private ScrollPane spMinne;

    @FXML
    private Label lblUtMinne;

    @FXML
    private TitledPane tpKabinett;

    @FXML
    private ScrollPane spKabinett;

    @FXML
    private Label lblUtKabinett;

    @FXML
    private TitledPane tpSkjerm;

    @FXML
    private ScrollPane spSkjerm;

    @FXML
    private Label lblUtSkjerm;

    @FXML
    private TitledPane tpTastatur;

    @FXML
    private ScrollPane spTastatur;

    @FXML
    private Label lblUtTastatur;

    @FXML
    private TitledPane tpMus;

    @FXML
    private ScrollPane spMus;

    @FXML
    private Label lblUtMus;

    @FXML
    private Label lblTotPris;

    @FXML
    void bestill(ActionEvent event) {
        boolean valgt = true;
        for (int i = 0; i < observableLists.size(); i++) {
            if (datakomponents[i] == null) {
                titledPanes.get(i).setStyle("-fx-border-color: red; -fx-border-width: 1px; -fx-border-style: solid");
                valgt = false;
            }
        }

        if (valgt) {
            BestillingsRegister.addBestilling(new Bestilling(BrukerRegister.getAktivBruker(), (Harddisk) datakomponents[0],
                    (Hovedkort) datakomponents[1], (Lydkort) datakomponents[2], (Skjermkort) datakomponents[3],
                    (Prosessor) datakomponents[4], (Minne) datakomponents[5], (Kabinett) datakomponents[6],
                    (Skjerm) datakomponents[7], (Tastatur) datakomponents[8], (Mus) datakomponents[9]));
            Dialogs.showSuccessDialog("Bestillingen din er registrert");
            try {
                App.setRoot("menybruker");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else Dialogs.showErrorDialog("Du må velge de markerte feltene");
    }

    @FXML
    void tilbake(ActionEvent event) {
        try {
            App.setRoot("menybruker");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<TitledPane> titledPanes;

    private ArrayList<ScrollPane> scrollPanes;

    private ArrayList<ObservableList<? extends Datakomponent>> observableLists;

    private ArrayList<Label> labels;

    private ArrayList<Double> priser;

    private final Datakomponent[] datakomponents = new Datakomponent[10];
}
