package xiaoyu.mtc.core.serialport;

import gnu.io.SerialPort;
import xiaoyu.mtc.util.SerialPort.SerialPortTool;
import xiaoyu.mtc.util.SerialPort.serialException.NoSuchPort;
import xiaoyu.mtc.util.SerialPort.serialException.NotASerialPort;
import xiaoyu.mtc.util.SerialPort.serialException.PortInUse;
import xiaoyu.mtc.util.SerialPort.serialException.SerialPortParameterFailure;

import javax.swing.plaf.synth.Region;
import java.util.ArrayList;
import java.util.List;

public class SerialportManager {


    // <editor-fold desc="单例">

    private static SerialportManager instance = new SerialportManager();

    private SerialportManager() {
    }

    public static SerialportManager getInstance() {
        return instance;
    }

    // </editor-fold>

    // <editor-fold desc="字段与属性">

    public List<String> getSerialPortList() {
        if (this.serialPortList == null || this.serialPortList.isEmpty()) {
            this.serialPortList = SerialPortTool.findPort();
        }
        return serialPortList;
    }

    private List<String> serialPortList = null;

    private SerialPort serialPort = null;

    // </editor-fold>

    // <editor-fold desc="方法">


    /**
     * 连接端口
     *
     * @param name     端口名称
     * @param baudrate 波特率
     * @return 是否连接成功
     */
    public boolean Connection(String name, int baudrate) {
        try {
            serialPort = null;
            serialPort = SerialPortTool.openPort(name, baudrate);
            if (serialPort != null) return true;
        } catch (NotASerialPort notASerialPort) {
            notASerialPort.printStackTrace();
        } catch (PortInUse portInUse) {
            portInUse.printStackTrace();
        } catch (SerialPortParameterFailure serialPortParameterFailure) {
            serialPortParameterFailure.printStackTrace();
        } catch (NoSuchPort noSuchPort) {
            noSuchPort.printStackTrace();
        }

        return false;
    }


    /**
     * 关闭端口
     */
    public void Close() {
        if (serialPort != null)
            serialPort.close();
    }

    //监听数据


    //发送数据

    // </editor-fold>
}
