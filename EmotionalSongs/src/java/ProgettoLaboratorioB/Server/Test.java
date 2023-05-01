import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class Test {
    public static void main(String[] args) throws UnknownHostException {
        String address = getLocalHostIP();
        System.out.println(address); // Correct, now set this as the server address, set property;

    }

    public static String getLocalHostIP() throws UnknownHostException {

        try {
            InetAddress candidateAddress = null;

            for (Enumeration<NetworkInterface> ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements();) {
                NetworkInterface iface = (NetworkInterface) ifaces.nextElement();

                for (Enumeration<InetAddress> inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements(); ) {
                    InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
                    if (!inetAddr.isLoopbackAddress()) {

                        if (inetAddr.isSiteLocalAddress()) {
                            // Found non-loopback site-local address.
                            String result = inetAddr.toString();
                            if(result.startsWith("/"))
                                result = result.substring(1);
                            return result;
                        }
                        else if (candidateAddress == null) {
                            // Found a non-loopback address, but not necessarily site-local.
                            candidateAddress = inetAddr;
                        }
                    }
                }
            }
            if (candidateAddress != null) {
                /*
                 * Did not find a site localhost address, but found some other non-loopback address.
                 * Return this non-loopback candidate address.
                 */
                String result = candidateAddress.toString();
                if(result.startsWith("/"))
                    result = result.substring(1);
                return result;
            }
            /*
             * Didn't find a non-loopback address.
             * Return InetAddress.getLocalHost() result.
             */
            InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
            if (jdkSuppliedAddress == null) {
                throw new UnknownHostException("The JDK InetAddress.getLocalHost() method unexpectedly returned null.");
            }
            String result = jdkSuppliedAddress.toString();
            if(result.startsWith("/"))
                return result.substring(1);
            return result;
        }
        catch (Exception e) {
            UnknownHostException unknownHostException = new UnknownHostException("Failed to determine LAN address: " + e);
            unknownHostException.initCause(e);
            throw unknownHostException;
        }

    }
}
