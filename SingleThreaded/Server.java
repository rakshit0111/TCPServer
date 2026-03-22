import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
    public void run() throws IOException
    {
        int port = 8010;
        ServerSocket socket;
    
            socket = new ServerSocket(port);
            socket.setSoTimeout(10000);

            while (true) {
                System.out.println("Server listening on port"+port);
                Socket acceptedConnection = socket.accept();
                System.out.println("Connection requested from client"+acceptedConnection.getInetAddress());
                PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream(),true);
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));
                toClient.println("Hello from the server");
                String line = fromClient.readLine();
                System.out.println("Message from client"+line);
                toClient.close();
                fromClient.close();
                acceptedConnection.close();
            }
    }
    
    public static void main(String[]args)
    {
        Server server = new Server();
        try {
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}