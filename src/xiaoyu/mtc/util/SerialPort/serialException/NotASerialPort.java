package xiaoyu.mtc.util.SerialPort.serialException;

public class NotASerialPort extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public NotASerialPort() {}

    @Override
    public String toString() {
        return "没有该端口对应的串口设备";
    }

}
