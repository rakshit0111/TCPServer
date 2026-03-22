import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public void  run() throws IOException
    {
        int port = 8010;
        InetAddress address = InetAddress.getByName("localhost");
        Socket socket = new Socket(address,port);
        PrintWriter toServer = new PrintWriter(socket.getOutputStream(),true);
        BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        toServer.println("Hello from Client");
        String line = fromServer.readLine();
        System.out.println("Message from server:"+line);
        toServer.close();
        fromServer.close();
        socket.close();
    }

    public static void main(String[] args) {
        Client client = new Client();
        try {
            client.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
