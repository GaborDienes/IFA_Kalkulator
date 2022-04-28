package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.EvesEredmenyek;
import model.EvesSzamol;
import model.IFA_Kalkulator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.Set;

public class IFA_KalkulatorController {


    @FXML
    public DatePicker ErkezesDatum;

    @FXML
    public DatePicker TavozasDatum;


    @FXML
    public TextField VendegekSzama;

    @FXML
    private TextField FiatalokSzama;

    @FXML
    private TextField HelyiekSzama;

    @FXML
    private TextField MenekultekSzama;

    @FXML
    private TextField FoPerEj;

    @FXML
    private Label EjszakakSzama;

    @FXML
    private Label ifa;

    @FXML
    private Label szallasOsszeg;

    @FXML
    private Label TeljesOsszeg;

    @FXML
    private Label szabadHelyek;

    @FXML
    private Label osszVendeg;

    @FXML
    private Label osszDij;
    @FXML
    private Label osszIFA;
    @FXML
    private Label OsszTurisztika;
    @FXML
    private Label osszAltalanyAdo;
    @FXML
    private Label osszKiadas;
    @FXML
    private Label osszBevetel;
    @FXML
    private Label merleg;


    private IFA_Kalkulator ifa_kalkulator;

    public void initialize() {ifa_kalkulator = new IFA_Kalkulator();}

    @FXML
    public void ShowDays(){


        EjszakakSzama.setText( ifa_kalkulator.countDays(ErkezesDatum.getValue().getDayOfYear(),TavozasDatum.getValue().getDayOfYear()));
        szabadHelyek.setText(ifa_kalkulator.Vacancy(ErkezesDatum,TavozasDatum));
        //szabadHelyek.setText("hello");
        //ifa_kalkulator.Vacancy(ErkezesDatum,TavozasDatum);
    }

    @FXML
    public void CountIFA(){
        int ifaOsszeg = ifa_kalkulator.szamolIFA(ErkezesDatum,TavozasDatum,VendegekSzama.getText(),FiatalokSzama.getText(),HelyiekSzama.getText(),MenekultekSzama.getText());
        int szallasOssz = ifa_kalkulator.szamolSzallas(ErkezesDatum,TavozasDatum,VendegekSzama.getText(),MenekultekSzama.getText(),FoPerEj.getText());
        ifa.setText(String.valueOf(ifaOsszeg) + " Ft");
        szallasOsszeg.setText(String.valueOf(szallasOssz) + " Ft");
        TeljesOsszeg.setText(String.valueOf(szallasOssz + ifaOsszeg) + " Ft");

        //ifa.setText(ifa_kalkulator.szamolIFA(ErkezesDatum,TavozasDatum,VendegekSzama.getText(),FiatalokSzama.getText(),HelyiekSzama.getText(),MenekultekSzama.getText()));

    }


    public void Book(ActionEvent actionEvent) throws IOException {

        if(ifa_kalkulator.BookingPosible(ErkezesDatum,TavozasDatum,VendegekSzama.getText())){
            System.out.println("Foglalhato :)");
            ifa_kalkulator.Foglal(ErkezesDatum,TavozasDatum,VendegekSzama.getText(),FiatalokSzama.getText(),HelyiekSzama.getText(),MenekultekSzama.getText(),FoPerEj.getText());
            szabadHelyek.setText(ifa_kalkulator.Vacancy(ErkezesDatum,TavozasDatum));
        }
        else{System.out.println("Nem Foglalhato :(");}
        }


    public void countEves(ActionEvent actionEvent)throws IOException{
        EvesEredmenyek eredmeny=new EvesEredmenyek();

        eredmeny=ifa_kalkulator.countAll();

        osszVendeg.setText(String.valueOf(eredmeny.getOsszVendeg()));
        osszDij.setText(String.valueOf(eredmeny.getOsszDij()) + " Ft");
        osszIFA.setText(String.valueOf(eredmeny.getOsszIFA()) + " Ft");
        OsszTurisztika.setText(String.valueOf(eredmeny.getOsszTurisztika()) + " Ft");
        osszAltalanyAdo.setText(String.valueOf(eredmeny.getOsszAltalanyAdo())+ " Ft");
        osszKiadas.setText(String.valueOf(eredmeny.getOsszKiadas())+ " Ft");
        osszBevetel.setText(String.valueOf(eredmeny.getOsszBevetel())+ " Ft");
        merleg.setText(String.valueOf(eredmeny.getMerleg()) + " Ft");

    }

}
