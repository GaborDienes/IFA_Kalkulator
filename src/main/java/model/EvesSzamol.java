package model;

import java.util.List;

public class EvesSzamol {

    public EvesEredmenyek countAll(){
        System.out.println("hello");
        EvesEredmenyek result=new EvesEredmenyek();

        BookingRepository repository = BookingRepository.getInstance();
        List<Nap> Napok = repository.getAll();
        System.out.println(Napok);

        for (int i = 0; i < 365; i++) {
            result.setOsszVendeg(result.getOsszVendeg() + Napok.get(i).getVendégekSzáma());
            result.setOsszDij(result.getOsszDij() + Napok.get(i).getSzállásértfizetve());
            int IFA=(Napok.get(i).getVendégekSzáma()-Napok.get(i).getFiatalokSzáma()-Napok.get(i).getHelyiekSzáma()-Napok.get(i).getMenekűltekSzáma())*500;
            result.setOsszIFA(result.getOsszIFA() + IFA);

        }
        result.setOsszTurisztika((int) (result.getOsszDij()*0.04));
        result.setOsszAltalanyAdo(7*38400);
        result.setOsszKiadas((int) (result.getOsszIFA() + result.getOsszTurisztika()) + result.getOsszAltalanyAdo());
        result.setOsszBevetel(result.getOsszIFA() + result.getOsszBevetel());
        result.setMerleg(result.getOsszBevetel() - result.getOsszKiadas());

        return result;
    }
}
