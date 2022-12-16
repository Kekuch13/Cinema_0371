package Client;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

public class ClientConnection {
    // We initialize our socket( tunnel )
    // and our input reader and output stream
    // we will take the input from the user
    // and send it to the socket using output stream
    private Socket socket;
    private BufferedReader input;
    private DataOutputStream out;
    public static ClientConnection instance;

    public static ClientConnection getInstance(String address, int port) {
        if(instance == null) {
            instance = new ClientConnection(address, port);
        }
        return instance;
    }

    // constructor that takes the IP Address and the Port
    public ClientConnection(String address, int port) {
        // we try to establish a connection
        try
        {
            // creates a socket with the given information
            socket = new Socket(address, port);
            System.out.println("Connected");

            // we 'ready' the input reader
            input = new BufferedReader(new InputStreamReader(System.in));

            // and the output that is connected to the Socket
            out = new DataOutputStream(socket.getOutputStream());
        }
        catch(UnknownHostException u)
        {
            System.out.println(u);
        }
        catch(IOException i)
        {
            System.out.println(i);
        }

        // string to read message from input
        /*String line = "";


        // keep reading until "Stop" is input
        while (!line.equals("Stop")) {
            try {
                line = input.readLine(); // reads the line from the keyboard
                out.writeUTF(line); // writes it to the output stream
                // now we just need to collect the data  from the socket on our server
            } catch(IOException i) {
                System.out.println(i);
            }
        }

        // close the connection
        try
        {
            input.close();
            out.close();
            socket.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }*/
    }

    public void send (String line) {
        try {
            out.writeBytes(line);
        } catch(IOException i) {
            System.out.println(i);
        }
    }
}
