package model;

import java.util.List;

@lombok.Data
public class Nap {
    private int day;
    private String Dátum;
    private int VendégekSzáma;
    private int FiatalokSzáma;
    private int HelyiekSzáma;
    private int MenekűltekSzáma;
    private int Szállásértfizetve;
    private List<String> Vendegek;


}
