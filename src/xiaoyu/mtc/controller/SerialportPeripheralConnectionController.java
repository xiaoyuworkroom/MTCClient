package xiaoyu.mtc.controller;

import gnu.io.SerialPort;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import xiaoyu.mtc.core.serialport.SerialportManager;
import xiaoyu.mtc.util.NumberUtil;
import xiaoyu.mtc.util.SerialPort.serialException.*;

public class SerialportPeripheralConnectionController {

    public TextField textFieldBaudrate;
    public ComboBox comboBoxSerialPort;

    public SerialportPeripheralConnectionController(){
    }

    @FXML
    public void initialize() {
    }


    private void RefreshSerialPort() {
        ObservableList<String> observableList = FXCollections.observableArrayList(SerialportManager.getInstance().getSerialPortList());
        comboBoxSerialPort.setItems(observableList);
    }

    public void Refresh(ActionEvent actionEvent) {
        RefreshSerialPort();
    }


    /**
     * @param actionEvent
     * @throws SerialPortParameterFailure
     * @throws NoSuchPort
     * @throws PortInUse
     * @throws NotASerialPort
     */
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

//        String baudrate = textFieldBaudrate.getText();
//        if(baudrate == null || baudrate.isEmpty())
//        {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("警告");
//            alert.setHeaderText("波特率!");
//            alert.setContentText("请指定波特率!");
//
//            alert.showAndWait();
//            return;
//        }

        //连接
        SerialportManager.getInstance().connection(selectedV,3000000);
        SerialPort port = SerialportManager.getInstance().getSerialPort();
        System.out.println(port.toString());

        //发送数据到设备
        try {
            byte[] order  = NumberUtil.intToByte4(100);
            SerialportManager.getInstance().sendToPort(order);
        } catch (SerialPortOutputStreamCloseFailure serialPortOutputStreamCloseFailure) {
            serialPortOutputStreamCloseFailure.printStackTrace();
        } catch (SendDataToSerialPortFailure sendDataToSerialPortFailure) {
            sendDataToSerialPortFailure.printStackTrace();
        }

    }
}
