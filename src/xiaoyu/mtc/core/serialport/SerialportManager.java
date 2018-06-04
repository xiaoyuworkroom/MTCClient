package xiaoyu.mtc.core.serialport;

import gnu.io.SerialPort;

public class SerialportManager {
    //create an object of MainWindow
    private static SerialportManager instance = new SerialportManager();

    //make the constructor private so that this class cannot be
    //instantiated by other class
    private SerialportManager(){}

    //Get the only object available
    public static SerialportManager getInstance(){
        return instance;
    }

    public SerialPort serialPort = null;

    public boolean Connection(String name, int baudrate){
        return false;
    }

    public void Close(){

    }

    //监听数据

    //发送数据



}
