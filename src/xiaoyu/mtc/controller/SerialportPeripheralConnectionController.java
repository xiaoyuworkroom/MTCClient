package xiaoyu.mtc.controller;

import gnu.io.SerialPort;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import xiaoyu.mtc.core.serialport.SerialportManager;
import xiaoyu.mtc.util.SerialPort.SerialPortTool;
import xiaoyu.mtc.util.SerialPort.serialException.NoSuchPort;
import xiaoyu.mtc.util.SerialPort.serialException.NotASerialPort;
import xiaoyu.mtc.util.SerialPort.serialException.PortInUse;
import xiaoyu.mtc.util.SerialPort.serialException.SerialPortParameterFailure;

import java.util.ArrayList;

public class SerialportPeripheralConnectionController {

    public TextField textFieldBaudrate;
    public ComboBox comboBoxSerialPort;

    @FXML
    public void initialize() {
        RefreshSerialPort();
    }

    private void RefreshSerialPort() {
        ObservableList<String> observableList = FXCollections.observableArrayList(SerialportManager.getInstance().getSerialPortList());
        comboBoxSerialPort.setItems(observableList);
    }

    public void Refresh(ActionEvent actionEvent) {
        RefreshSerialPort();
    }

    public void Connection(ActionEvent actionEvent) throws SerialPortParameterFailure, NoSuchPort, PortInUse, NotASerialPort {

        String selectedV = (String) comboBoxSerialPort.getSelectionModel().getSelectedItem();

        if(selectedV == null || selectedV.isEmpty())
        {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("警告");
            alert.setHeaderText("端口!");
            alert.setContentText("请选择端口信息!");

            alert.showAndWait();
            return;
        }

        String baudrate = textFieldBaudrate.getText();
        if(baudrate == null || baudrate.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("警告");
            alert.setHeaderText("波特率!");
            alert.setContentText("请指定波特率!");

            alert.showAndWait();
            return;
        }

        SerialportManager.getInstance().Connection(selectedV, Integer.parseInt(baudrate));
        SerialPort port = SerialportManager.getInstance().getSerialPort();
        System.out.println(port.toString());
    }
}
