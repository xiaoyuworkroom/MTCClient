package xiaoyu.mtc.controller;

import gnu.io.SerialPort;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import xiaoyu.mtc.util.SerialPort.SerialPortTool;
import xiaoyu.mtc.util.SerialPort.serialException.NoSuchPort;
import xiaoyu.mtc.util.SerialPort.serialException.NotASerialPort;
import xiaoyu.mtc.util.SerialPort.serialException.PortInUse;
import xiaoyu.mtc.util.SerialPort.serialException.SerialPortParameterFailure;

import java.util.ArrayList;

public class SerialportPeripheralConnectionController {

    public ListView listviewSerialPort;
    public TextField textFieldBaudrate;

    @FXML
    public void initialize() {
        RefreshSerialPort();
    }

    private void RefreshSerialPort() {
        ArrayList<String> arrPorts = SerialPortTool.findPort();
        ObservableList<String> ob = FXCollections.observableArrayList(arrPorts);
        listviewSerialPort.setItems(ob);
    }

    public void Refresh(ActionEvent actionEvent) {
        RefreshSerialPort();
    }

    public void Connection(ActionEvent actionEvent) throws SerialPortParameterFailure, NoSuchPort, PortInUse, NotASerialPort {
        SerialPort port = SerialPortTool.openPort("COM1", Integer.parseInt(textFieldBaudrate.getText()));
        System.out.println(port.toString());
    }


}
