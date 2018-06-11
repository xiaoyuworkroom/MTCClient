package xiaoyu.mtc.core.serialport;

import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import xiaoyu.mtc.util.NumberUtil;
import xiaoyu.mtc.util.SerialPort.SerialPortTool;
import xiaoyu.mtc.util.SerialPort.serialException.*;

import javax.swing.*;
import javax.swing.plaf.synth.Region;
import java.util.ArrayList;
import java.util.List;
import java.util.TooManyListenersException;

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

    public SerialPort getSerialPort() {
        return serialPort;
    }

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
    public boolean connection(String name, int baudrate) {
        try {
            serialPort = null;
            serialPort = SerialPortTool.openPort(name, baudrate);
            addListener(serialPort, new SerialListener());
            if (serialPort != null) return true;
        } catch (NotASerialPort notASerialPort) {
            notASerialPort.printStackTrace();
        } catch (PortInUse portInUse) {
            portInUse.printStackTrace();
        } catch (SerialPortParameterFailure serialPortParameterFailure) {
            serialPortParameterFailure.printStackTrace();
        } catch (NoSuchPort noSuchPort) {
            noSuchPort.printStackTrace();
        } catch (TooManyListeners tooManyListeners) {
            tooManyListeners.printStackTrace();
        }

        return false;
    }


    /**
     * 发送数据到端口
     *
     * @param order
     * @return
     * @throws SerialPortOutputStreamCloseFailure
     * @throws SendDataToSerialPortFailure
     */
    public boolean sendToPort(byte[] order) throws SerialPortOutputStreamCloseFailure, SendDataToSerialPortFailure {
        if (serialPort != null) {
            SerialPortTool.sendToPort(serialPort, order);
            return true;
        }
        return false;
    }

    /**
     * 关闭端口
     */
    public void close() {
        if (serialPort != null) serialPort.close();
    }

    //监听数据

    /**
     * 添加监听器
     *
     * @param port     串口对象
     * @param listener 串口监听器
     * @throws TooManyListeners 监听类对象过多
     */
    public static void addListener(SerialPort port, SerialPortEventListener listener) throws TooManyListeners {
        try {
            //给串口添加监听器
            port.addEventListener(listener);
            //设置当有数据到达时唤醒监听接收线程
            port.notifyOnDataAvailable(true);
            //设置当通信中断时唤醒中断线程
            port.notifyOnBreakInterrupt(true);
        } catch (TooManyListenersException e) {
            throw new TooManyListeners();
        }
    }

    //发送数据

    // </editor-fold>

    /**
     * 以内部类形式创建一个串口监听类
     *
     * @author zhong
     */
    private class SerialListener implements SerialPortEventListener {

        /**
         * 处理监控到的串口事件
         */
        public void serialEvent(SerialPortEvent serialPortEvent) {

            switch (serialPortEvent.getEventType()) {

                case SerialPortEvent.BI: // 10 通讯中断
                    JOptionPane.showMessageDialog(null, "与串口设备通讯中断", "错误", JOptionPane.INFORMATION_MESSAGE);
                    break;

                case SerialPortEvent.OE: // 7 溢位（溢出）错误

                case SerialPortEvent.FE: // 9 帧错误

                case SerialPortEvent.PE: // 8 奇偶校验错误

                case SerialPortEvent.CD: // 6 载波检测

                case SerialPortEvent.CTS: // 3 清除待发送数据

                case SerialPortEvent.DSR: // 4 待发送数据准备好了

                case SerialPortEvent.RI: // 5 振铃指示

                case SerialPortEvent.OUTPUT_BUFFER_EMPTY: // 2 输出缓冲区已清空
                    break;

                case SerialPortEvent.DATA_AVAILABLE: // 1 串口存在可用数据
                    byte[] data = new byte[1024];

                    try {
                        if (serialPort == null) {
                            JOptionPane.showMessageDialog(null, "串口对象为空！监听失败！", "错误", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            data = SerialPortTool.readFromPort(serialPort);    //读取数据，存入字节数组

                            // 自定义解析过程，你在实际使用过程中可以按照自己的需求在接收到数据后对数据进行解析
                            if (data == null || data.length < 1) {    //检查数据是否读取正确
                                JOptionPane.showMessageDialog(null, "读取数据过程中未获取到有效数据！请检查设备或程序！", "错误", JOptionPane.INFORMATION_MESSAGE);
                                System.exit(0);
                            } else {

                                int[] v = parseData(data);

                                for(int i=0; i< v.length; ++i){
                                    System.out.printf("=[%d]", v[i]);
                                }

//                                String dataOriginal = new String(data);    //将字节数组数据转换位为保存了原始数据的字符串
//                                String dataValid = "";    //有效数据（用来保存原始数据字符串去除最开头*号以后的字符串）
//                                String[] elements = null;    //用来保存按空格拆分原始字符串后得到的字符串数组
//                                //解析数据
//                                if (dataOriginal.charAt(0) == '*') {    //当数据的第一个字符是*号时表示数据接收完成，开始解析
//                                    dataValid = dataOriginal.substring(1);
//                                    elements = dataValid.split(" ");
//                                    if (elements == null || elements.length < 1) {    //检查数据是否解析正确
//                                        JOptionPane.showMessageDialog(null, "数据解析过程出错，请检查设备或程序！", "错误", JOptionPane.INFORMATION_MESSAGE);
//                                        System.exit(0);
//                                    } else {
//                                        try {
//                                            //更新界面Label值
//                                            /*for (int i=0; i<elements.length; i++) {
//                                                System.out.println(elements[i]);
//                                            }*/
//                                            //System.out.println("win_dir: " + elements[5]);
////                                            tem.setText(elements[0] + " ℃");
////                                            hum.setText(elements[1] + " %");
////                                            pa.setText(elements[2] + " hPa");
////                                            rain.setText(elements[3] + " mm");
////                                            win_sp.setText(elements[4] + " m/s");
////                                            win_dir.setText(elements[5] + " °");
//                                        } catch (ArrayIndexOutOfBoundsException e) {
//                                            JOptionPane.showMessageDialog(null, "数据解析过程出错，更新界面数据失败！请检查设备或程序！", "错误", JOptionPane.INFORMATION_MESSAGE);
//                                            System.exit(0);
//                                        }
//                                    }
//                                }
                            }

                        }

                    } catch (ReadDataFromSerialPortFailure | SerialPortInputStreamCloseFailure e) {
                        JOptionPane.showMessageDialog(null, e, "错误", JOptionPane.INFORMATION_MESSAGE);
                        System.exit(0);    //发生读取错误时显示错误信息后退出系统
                    }

                    break;

            }

        }


        private int[] parseData(byte[] data) {

            if (data != null && data.length > 0) {

                int length = data.length;
                int retArrayCount = length / 3;
                int[] retArray = new int[retArrayCount];
                for (int i = 0; i < retArrayCount; ++i) {
                    int index0 = i * 3;
                    if (data[index0] == 0x01) {
                        int v = NumberUtil.byte2ToUnsignedShort(data, index0 + 1);
                        retArray[i] = v;
                    }
                }
                return retArray;
            }
            return null;
        }


    }
}