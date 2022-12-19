package Client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientConnection {
    public static ClientConnection instance;
    private static DataOutputStream dataOutputStream = null;
    private static final DataInputStream dataInputStream = null;
    private Socket socket;
    private BufferedReader reader;

    public ClientConnection(String address, int port) {
        try {
            socket = new Socket("localhost", port);
            System.out.println("Connected");

            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            //dataInputStream = new DataInputStream(socket.getInputStream());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException u) {
            System.out.println(u);
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public static ClientConnection getInstance() {
        if (instance == null) {
            instance = new ClientConnection("localhost", 6666);
        }
        return instance;
    }

    public void sendToServer(String line) {
        try {
            dataOutputStream.writeBytes(line + "\n");
            dataOutputStream.flush();
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public String receiveFromServer() {
        String line = "";
        try {
            line = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return line;
    }
}
