package software.carter;

import software.carter.gui.Graphics;
import software.carter.local.Auth;
import software.carter.server.Server;

public class App 
{
    public static void main( String[] args )
    {
        //-------------------------
        // Thread instantiation
        Server server = new Server();
        Thread serverThread = new Thread(server);
        serverThread.start();

        Graphics graphics = new Graphics();
        Thread graphicsThread = new Thread(graphics);
        graphicsThread.start();

        Auth.s().setUsername("Carter");
    }
}
