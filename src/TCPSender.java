import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class TCPSender {
    static int packetSize = 1000;
    static int packetNum = 1024;
    static String ip = "pi.cs.oswego.edu";


    public static void main(String[]args) throws Exception{
        int portNum = 2525;
        Socket clientSocket = new Socket(ip, portNum);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        DataInputStream inFromServer = new DataInputStream(clientSocket.getInputStream());
        System.out.println("Sending to: " + ip);
        long start = System.nanoTime();
        byte response[] = new byte[packetSize];
        byte multiResponse;
        for(int i = 0; i < packetNum; i++){
            outToServer.write(new byte[packetSize]);
            System.out.println("Sent: " + i);
            if(packetNum == 1){
                inFromServer.readFully(response);
                System.out.println("Response: " + response);
            }else{
                multiResponse = inFromServer.readByte();
                System.out.println("Response: " + multiResponse);
            }
        }
        System.out.println("Successfully sent");
        System.out.println(System.nanoTime() - start);
        outToServer.close();
        inFromServer.close();
    }

}
