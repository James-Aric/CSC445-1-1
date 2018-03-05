import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPReceiver {

    //Variables to alter packet size/number
    static int packetSize = 1000;
    static int packetNum = 1024;

    public static void main(String[]args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(2525);
        System.out.println("Listening on TCP: " + InetAddress.getLocalHost().getHostAddress());
        Socket connection = serverSocket.accept();
        DataInputStream inputFromSender = new DataInputStream(connection.getInputStream());
        DataOutputStream backToSender = new DataOutputStream(connection.getOutputStream());

        //int inputs[] = new int[packetNum];
        byte input[] = new byte[packetSize];

        for(int i = 0; i < packetNum; i++) {
            inputFromSender.readFully(input);
            System.out.println("Input from sender: " +  new String(input, 0, input.length) + i);
            if(packetNum == 1) {
                backToSender.write(input);
            }
            else{
                System.out.println("Acknowledging");
                backToSender.write(new byte[1]);
                System.out.println("Sent acknowledgment");
            }
        }
        inputFromSender.close();
        backToSender.close();
    }
}

