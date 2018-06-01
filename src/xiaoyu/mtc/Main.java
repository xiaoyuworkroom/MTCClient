package xiaoyu.mtc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ui/MainScene.fxml"));
        primaryStage.setTitle("MTC");
        primaryStage.setScene(new Scene(root, 1024, 768));
        primaryStage.setMinWidth(1024);
        primaryStage.setMinHeight(768);
        primaryStage.setMaxWidth(1920);
        primaryStage.setMaxHeight(1080);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
