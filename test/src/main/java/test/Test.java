package test;


import java.net.*;
import java.util.Enumeration;

public class Test {

    public static void main(String[] args) throws UnknownHostException, SocketException {

//        InetAddress localHost = InetAddress.getLocalHost();
//        System.out.println(localHost);

//        Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
//        while (enumeration.hasMoreElements()) {
//            NetworkInterface networkInterface = enumeration.nextElement();
//
//            if (networkInterface.isUp()) {
//                System.out.println(networkInterface.getDisplayName());
//                Enumeration<InetAddress> addressEnumeration = networkInterface.getInetAddresses();
//
//                while (addressEnumeration.hasMoreElements()) {
//                    System.out.println("\t" + addressEnumeration.nextElement());
//                }
//            }
//        }

//        InetAddress address = getAddress();
//        System.out.println(address);


        String ip = getIp();
        System.out.println(ip);

    }


    /**
     * Get host IP address
     *
     * @return IP Address
     */
    private static InetAddress getAddress() {
        try {
            for (Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces(); interfaces.hasMoreElements();) {
                NetworkInterface networkInterface = interfaces.nextElement();
                if("en0".equalsIgnoreCase(networkInterface.getName())) {
                    Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        InetAddress inetAddress = addresses.nextElement();
                        if (inetAddress instanceof Inet4Address) {
                            System.out.println(inetAddress.getHostAddress());
                        }
                    }
                }
            }
        } catch (SocketException e) {
            System.out.println("Error when getting host ip address: <"+ e.getMessage() +">.");

        }
        return null;
    }

    /**
     * 多IP处理，可以得到最终ip
     */
    public static String getIp() {
        String localIp = null;// 本地IP，如果没有配置外网IP则返回它
        try {
            InetAddress ip;
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
            boolean find = false;// 是否找到外网IP
            while (netInterfaces.hasMoreElements() && !find) {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> address = ni.getInetAddresses();
                while (address.hasMoreElements()) {
                    ip = address.nextElement();
//                    if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
//                            && ip.getHostAddress().indexOf(":") == -1) {// 外网IP
//                        netip = ip.getHostAddress();
//                        finded = true;
//                        break;
//                    } else
                    if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && !ip.getHostAddress().contains(":")) {// 内网IP
                        localIp = ip.getHostAddress();
                        find = true;
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return localIp;
    }
}
