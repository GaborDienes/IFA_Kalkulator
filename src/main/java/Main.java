import model.BookingRepository;
import model.Nap;
import javafx.application.Application;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
       /* BookingRepository repository = BookingRepository.getInstance();
       // System.out.println(repository.getAll());
        List<Nap> Napok= repository.getAll();
        System.out.println(Napok);
        for (int i = 0; i < Napok.size(); i++) {
            System.out.println(Napok.get(i).getDay());
            System.out.println(i);
        }
*/
        Application.launch(IFA_Kalkulator_Application.class, args);

    }

}
