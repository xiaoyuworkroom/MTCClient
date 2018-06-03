package xiaoyu.mtc.util.SerialPort.serialException;

/**
 * 端口指向设备不是串口类型
 */
public class NoSuchPort extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public NoSuchPort() {}

    @Override
    public String toString() {
        return "端口指向设备不是串口类型";
    }


}
