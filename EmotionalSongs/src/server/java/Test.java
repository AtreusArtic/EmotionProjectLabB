import java.net.DatagramSocket;
import java.net.InetAddress;

public class Test {
    public static void main(String[] args) {
        // Get the IP address of the machine where the server is running:
        try (final DatagramSocket datagramSocket = new DatagramSocket()) {
            datagramSocket.connect(InetAddress.getByName("8.8.8.8"), 1099);
            System.out.println(datagramSocket.getLocalAddress().getHostAddress());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
