package Server;

import com.google.gson.Gson;
import forms.AuthenticationForm;
import org.jooq.DSLContext;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private Socket socket;
    private ServerSocket server;
    private DataInputStream in;
    private int port;
    private DatabaseManager databaseManager;

    public void run() {
        // starts server and waits for a connection
        try {
            // we start our server
            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            // we accept the client in the given port
            // and create a socket
            // we now have an established connection between our client and our server on the
            // given socket
            socket = server.accept();
            System.out.println("Client accepted");

            // takes input from the client socket
            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line = "";

            try {
                line = reader.readLine();
                System.out.println(line);
            } catch(IOException i) {
                System.out.println(i);
            }
            System.out.println("Closing connection");

            // close connection
            socket.close();

            Gson foo = new Gson();
            AuthenticationForm auth = foo.fromJson(line, AuthenticationForm.class);
            System.out.println(auth.getForm());
            System.out.println(auth.login);
            System.out.println(auth.password);

            DSLContext context = databaseManager.getContext();
        } catch(IOException i) {
            System.out.println(i);
        }
    }

    public Server(int port) {
        this.port = port;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public void setDatabaseManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }
}