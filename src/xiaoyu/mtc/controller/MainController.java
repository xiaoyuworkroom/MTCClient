package xiaoyu.mtc.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {
    public void ConnectionDefault(ActionEvent actionEvent) {
try {
    Parent target = FXMLLoader.load(getClass().getResource("/fxml/ui/SerialportPeripheralConnection.fxml"));
    Scene scene = new Scene(target); //创建场景；
    Stage stg=new Stage();//创建舞台；
    stg.setScene(scene); //将场景载入舞台；
    stg.show(); //显示窗口；

}
catch (Exception e){
    e.printStackTrace();
}
    }
}
