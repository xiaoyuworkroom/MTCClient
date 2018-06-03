package xiaoyu.mtc.util.SerialPort.serialException;

public class PortInUse extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public PortInUse() {}

    @Override
    public String toString() {
        return "端口已被占用!";
    }

}
