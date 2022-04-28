import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class IFA_Kalkulator_Application extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/IFA_Kalkulator.fxml"));
        stage.setTitle("Linett Apartman foglalás");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }
}