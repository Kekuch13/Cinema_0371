package Server;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import forms.AuthenticationForm;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Server {
    private final int port;
    private final Socket socket;
    private final ServerSocket server;
    private DataOutputStream dataOutputStream;
    private BufferedReader reader;
    private DatabaseManager databaseManager;
    private Gson gson;

    public Server(int port) {
        this.gson = new Gson();
        this.port = port;
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Server started");
        try {
            System.out.println("Waiting for a client ...");
            socket = server.accept();
            System.out.println("Client accepted");

            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        String line;
        while (true) {
            line = receiveFromClient();
            //получаем название формы
            JsonObject jsonObject = JsonParser.parseString(line).getAsJsonObject();
            String form = jsonObject.get("form").getAsString();
            switch (form) {
                case "authentication":
                    this.authentication(line);
                    break;
                case "kekek":
                    //to-do
                    break;
                case "addFilm":
                    //to-do
                    break;
                case "exit":
                    System.out.println("Closing connection");
                    try {
                        socket.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
            }
        }
    }

    public String receiveFromClient() {
        String line = "";
        try {
            line = reader.readLine();
            System.out.println("Receive from client: " + line);
        } catch (IOException i) {
            System.out.println(i);
        }
        return line;
    }

    public void sendToClient(String line) {
        try {
            dataOutputStream.writeBytes(line + "\n");
            dataOutputStream.flush();
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public void authentication(String line) {
        AuthenticationForm auth = gson.fromJson(line, AuthenticationForm.class);
        try {
            Connection conn = DatabaseManager.getInstance().getConnection();
            Statement st = conn.createStatement();

            String k = "select * from users where username = " + "'" + auth.getLogin() + "'" +
                    " and password = " + "'" + auth.getPassword() + "'";
            ResultSet r = st.executeQuery(k);
            if (r.next()) {
                auth.setValid(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String json = gson.toJson(auth);
        sendToClient(json);
    }

    public void setDatabaseManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }
}