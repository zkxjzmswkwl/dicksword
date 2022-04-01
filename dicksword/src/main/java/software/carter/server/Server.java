package software.carter.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {
    private static final int PORT = 6969;

    private ArrayList<ClientHandler> connectedClients = new ArrayList<>();
    private final ExecutorService clientPool = Executors.newFixedThreadPool(16);
    private boolean shouldListen = true;

    @Override
    public void run()
    {
        try
        {
            ServerSocket listener = new ServerSocket(PORT);
            while (shouldListen)
            {
                Socket client = listener.accept();
                ClientHandler clientThread = new ClientHandler(client, this);
                connectedClients.add(clientThread);

                clientPool.execute(clientThread);
            }
            listener.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}