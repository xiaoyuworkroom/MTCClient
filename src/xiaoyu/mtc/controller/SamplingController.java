package xiaoyu.mtc.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import xiaoyu.mtc.core.SamplingManager;

public class SamplingController {
    @FXML
    public void initialize(){
        SamplingManager.getInstance().Initialize();
    }

    public void OnStart(ActionEvent actionEvent) {
        SamplingManager.getInstance().Start();
    }
}
