package model;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javafx.scene.control.DatePicker;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import java.io.FileWriter;
import java.util.List;


public class IFA_Kalkulator {

    public String countDays(int a, int b){
        int Erkezesnapja = a;
        int Tavozasnapja = b;

        if(Erkezesnapja>=Tavozasnapja){return Integer.toString(0);}
        return Integer.toString(Tavozasnapja - Erkezesnapja);

        }

    public String Vacancy(DatePicker erkezes, DatePicker tavozas) {
        // File olvasasa
        BookingRepository repository = BookingRepository.getInstance();
        List<Nap> Napok = repository.getAll();

       // System.out.println(Napok1);
        int Erkezesnapja = erkezes.getValue().getDayOfYear();
        int Tavozasnapja = tavozas.getValue().getDayOfYear();
       // return Integer.toString(Tavozasnapja - Erkezesnapja);
        String result=new String();
        for (int i = Erkezesnapja; i < Tavozasnapja; i++) {
            String S= String.valueOf(16-Napok.get(i-1).getVendégekSzáma());
            result=result + " " + S;
        }
        if(Erkezesnapja>=Tavozasnapja){return Integer.toString(0);}
        return result;
    }

    public int szamolIFAosszeg(int erkezes,int tavozas, int ossz, int fiatal, int helyi, int menekult){
        return (tavozas-erkezes)*(ossz-fiatal-helyi-menekult)*500;
    }

    public int szamolIFA(DatePicker erkezes, DatePicker tavozas, String ossz,String fiatal,String helyi,String menekult){
        int Erkezesnapja = erkezes.getValue().getDayOfYear();
        int Tavozasnapja = tavozas.getValue().getDayOfYear();
        if(Erkezesnapja>=Tavozasnapja){return 0;}
        return szamolIFAosszeg(Erkezesnapja,Tavozasnapja,Integer.parseInt(ossz),Integer.parseInt(fiatal),Integer.parseInt(helyi),Integer.parseInt(menekult));
        //return (Integer.parseInt(ossz)-Integer.parseInt(fiatal)-Integer.parseInt(helyi)-Integer.parseInt(menekult))*(Tavozasnapja - Erkezesnapja)*500;

     }

    public int szamolSzallasDij(int erkezes,int tavozas,int ossz, int menekultek, int ar){
        return (tavozas - erkezes)*(ossz-menekultek)*ar;
    }

    public int szamolSzallas(DatePicker erkezes, DatePicker tavozas, String ossz, String menekultek, String ar){
        int Erkezesnapja = erkezes.getValue().getDayOfYear();
        int Tavozasnapja = tavozas.getValue().getDayOfYear();
        if(Erkezesnapja>=Tavozasnapja){return 0;}
        //return (Tavozasnapja - Erkezesnapja)*(Integer.parseInt(ossz)-Integer.parseInt(menekultek))*Integer.parseInt(ar);
        return szamolSzallasDij(Erkezesnapja, Tavozasnapja, Integer.parseInt(ossz), Integer.parseInt(menekultek), Integer.parseInt(ar));
    }

    public boolean BookingPosible(DatePicker erkezes,DatePicker tavozas,String ossz) {
        // File olvasasa
        BookingRepository repository = BookingRepository.getInstance();
        List<Nap> Napok = repository.getAll();

        int Erkezesnapja = erkezes.getValue().getDayOfYear();
        int Tavozasnapja = tavozas.getValue().getDayOfYear();
        if(Erkezesnapja>=Tavozasnapja){return false;}

        for (int i = Erkezesnapja; i < Tavozasnapja; i++) {
            //System.out.println(Napok.get(i - 1).getDay());
            if ((Napok.get(i - 1).getVendégekSzáma() + Integer.parseInt(ossz)) > 16) {
                return false;
            }
        }
        return true;
    }

    public void Foglal(DatePicker erkezes,DatePicker tavozas,String ossz,String fiatalok,String helyiek,String menekultek,String foPerEj){
        // File olvasasa
        BookingRepository repository = BookingRepository.getInstance();
        List<Nap> Napok = repository.getAll();

        int Erkezesnapja = erkezes.getValue().getDayOfYear();
        int Tavozasnapja = tavozas.getValue().getDayOfYear();

        Napok.get(Erkezesnapja - 1).setDátum(erkezes.getValue().toString());
        Napok.get(Tavozasnapja - 1).setDátum(tavozas.getValue().toString());


        for (int i = Erkezesnapja; i < Tavozasnapja; i++) {

            Napok.get(i - 1).setVendégekSzáma(Napok.get(i - 1).getVendégekSzáma() + Integer.parseInt(ossz));
            Napok.get(i - 1).setFiatalokSzáma(Napok.get(i - 1).getFiatalokSzáma() + Integer.parseInt(fiatalok));
            Napok.get(i - 1).setHelyiekSzáma(Napok.get(i - 1).getHelyiekSzáma() + Integer.parseInt(helyiek));
            Napok.get(i - 1).setMenekűltekSzáma(Napok.get(i - 1).getMenekűltekSzáma() + Integer.parseInt(menekultek));
            Napok.get(i - 1).setSzállásértfizetve(Napok.get(i - 1).getSzállásértfizetve() + ((Integer.parseInt(ossz)-Integer.parseInt(menekultek))*Integer.parseInt(foPerEj)));
            //System.out.println( Napok.get(i - 1));
             }

        var objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        JSONArray JSONList = new JSONArray();
       /* for (int i = 0; i < 365; i++) {
            var temp= new JSONObject();
            temp.;
            objectMapper.writeValue(JsonGenerator g);
            //objectMapper.writeValue(temp, Napok.get(i));
            JSONList.add(temp);
            }*/
        for (int i = 0; i < 365; i++) {
            JSONObject JObj = new JSONObject();
            JObj.put("day",Napok.get(i).getDay());
            JObj.put("helyiekSzáma",Napok.get(i).getHelyiekSzáma());
            JObj.put("vendegek",Napok.get(i).getVendegek());
            JObj.put("dátum",Napok.get(i).getDátum());
            JObj.put("vendégekSzáma",Napok.get(i).getVendégekSzáma());
            JObj.put("fiatalokSzáma",Napok.get(i).getFiatalokSzáma());
            JObj.put("menekűltekSzáma",Napok.get(i).getMenekűltekSzáma());
            JObj.put("szállásértfizetve",Napok.get(i).getSzállásértfizetve());
            JSONList.add(JObj);
        }


        //JSONList.add(Napok);
        //System.out.println(JSONList);
        try(FileWriter file = new FileWriter("Foglalasok.json")){
            file.write(JSONList.toString());
            file.flush();
        }
        catch(IOException e) {e.printStackTrace();}

    }

    public EvesEredmenyek countAll(){
        EvesEredmenyek result=new EvesEredmenyek();

        BookingRepository repository = BookingRepository.getInstance();
        List<Nap> Napok = repository.getAll();

        for (int i = 0; i < 365; i++) {
            result.setOsszVendeg(result.getOsszVendeg() + Napok.get(i).getVendégekSzáma());
            result.setOsszDij(result.getOsszDij() + Napok.get(i).getSzállásértfizetve());
            int IFA=(Napok.get(i).getVendégekSzáma()-Napok.get(i).getFiatalokSzáma()-Napok.get(i).getHelyiekSzáma()-Napok.get(i).getMenekűltekSzáma())*500;
            result.setOsszIFA(result.getOsszIFA() + IFA);

        }
        result.setOsszTurisztika((int) (result.getOsszDij()*0.04));
        result.setOsszAltalanyAdo(7*38400);
        result.setOsszKiadas((int) (result.getOsszIFA() + result.getOsszTurisztika()) + result.getOsszAltalanyAdo());
        result.setOsszBevetel(result.getOsszIFA() + result.getOsszDij());
        result.setMerleg(result.getOsszBevetel() - result.getOsszKiadas());

        return result;
    }

}


