package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientConnection {
    private static ClientConnection instance;
    private static DataOutputStream dataOutputStream;
    private static DataInputStream dataInputStream;
    private Socket socket;

    public ClientConnection(String address, int port) {
        try {
            socket = new Socket("localhost", port);
            System.out.println("Connected");

            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataInputStream = new DataInputStream(socket.getInputStream());
        } catch (UnknownHostException u) {
            System.out.println(u);
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public static synchronized ClientConnection getInstance() {
        if (instance == null) {
            instance = new ClientConnection("localhost", 6666);
        }
        return instance;
    }

    public void sendToServer(String line) {
        try {
            dataOutputStream.writeUTF(line);
            dataOutputStream.flush();
            System.out.println("Send to server: " + line);
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public String receiveFromServer() {
        String line = "";
        try {
            line = dataInputStream.readUTF();
            System.out.println("Receive from Server: " + line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return line;
    }
}
