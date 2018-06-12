package xiaoyu.mtc.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController {
    public void ConnectionDefault(ActionEvent actionEvent) {
        try {
            Parent target = FXMLLoader.load(getClass().getResource("/fxml/ui/SerialportPeripheralConnection.fxml"));
            Scene scene = new Scene(target);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setScene(scene);
            stg.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Sampling(ActionEvent actionEvent){
        try {
            Parent target = FXMLLoader.load(getClass().getResource("/fxml/ui/Sampling.fxml"));
            Scene scene = new Scene(target);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setScene(scene);
            stg.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
