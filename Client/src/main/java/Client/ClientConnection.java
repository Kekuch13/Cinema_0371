package Client;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

public class ClientConnection {
    private Socket socket;
    private BufferedReader reader;
    public static ClientConnection instance;

    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;

    public static ClientConnection getInstance() {
        if(instance == null) {
            instance = new ClientConnection("localhost", 6666);
        }
        return instance;
    }

    public ClientConnection(String address, int port) {
        try
        {
            socket = new Socket("localhost",port);
            System.out.println("Connected");

            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataInputStream = new DataInputStream(socket.getInputStream());
            reader = new BufferedReader(new InputStreamReader(dataInputStream));
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
            dataOutputStream.writeBytes(line);
        } catch(IOException i) {
            System.out.println(i);
        }
    }

    public String receive () {
        System.out.println("++++++++++");
        String line = "";
        try {
            line = reader.readLine();
        } catch (IOException i) {
            System.out.println(i);
        }
        System.out.println("========");
        return line;
    }
}
