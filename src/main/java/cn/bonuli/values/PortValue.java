package cn.bonuli.values;

/**
 * PortValue
 *
 * @author D.jin
 * @date 2018/6/26
 */
public class PortValue {
    final String ip;
    final int port;

    public PortValue(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }
}
