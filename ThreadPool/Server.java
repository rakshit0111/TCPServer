import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final ExecutorService threadPool;
    
    public Server(int threadPoolSize)
    {
        this.threadPool = Executors.newFixedThreadPool(threadPoolSize);
    }
    public void handleClient(Socket socket)
    {
        try {
            PrintWriter toClient = new PrintWriter(socket.getOutputStream());
            toClient.println("Hello from Server");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        int port = 8010;
        int threadPoolSize = 10;
        Server server = new Server(threadPoolSize);

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(10000);
            System.out.println("Server listening on port"+port);
            
            while (true) {
                Socket socket = serverSocket.accept();
                server.threadPool.execute(() -> server.handleClient(socket));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
}
