package software.carter.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import software.carter.database.Accounts;


public class ClientHandler implements Runnable
{
    private final BufferedReader input;
    private final PrintWriter output;
    private final Server serverRef;

    public ClientHandler(Socket clientSocket, Server serverReference) throws IOException
    {
        this.serverRef= serverReference;
        input  = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        output = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    // TODO: Setup actual packet intake system that doesn't require 18 bytes to communicate a single command.
    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                String request = input.readLine();
                if (request != null)
                    System.out.println(request);
                else
                    continue;

                if (request != null && request.contains("0x99"))
                {
                    String[] userPass = request.split(",");
                    Accounts.createAccount(userPass[1], userPass[2]);
                    output.println("Account " + userPass[1] + " created");
                }
           }
        }
        catch (IOException e)
        {
            e.getStackTrace();
        }
        finally
        {
            output.close();
            try
            {
                input.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}