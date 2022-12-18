package Server;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import forms.AuthenticationForm;
import org.jooq.DSLContext;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Server {
    private Socket socket;
    private ServerSocket server;
    private DataOutputStream dataOutputStream = null;
    private DataInputStream dataInputStream = null;
    private final int port;
    private DatabaseManager databaseManager;

    public void run() {
        try {
            System.out.println("Waiting for a client ...");
            socket = server.accept();
            System.out.println("Client accepted");

            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(dataInputStream));

            String line = "";
            while (true) {
                Thread.sleep(1000);
                try {
                    line = reader.readLine();
                    System.out.println(line);
                } catch (IOException i) {
                    System.out.println(i);
                }
                System.out.println("Closing connection");

                //socket.close();

                //получаем название формы
                JsonObject jsonObject = JsonParser.parseString(line).getAsJsonObject();
                String form = jsonObject.get("form").getAsString();
                System.out.println(form);
                switch (form) {
                    case "authentication":
                        this.authentication(line);
                        break;
                }
                //делаем из json объект
                /*Gson foo = new Gson();
                AuthenticationForm auth = foo.fromJson(line, AuthenticationForm.class);
                System.out.println(auth.getForm());
                System.out.println(auth.login);
                System.out.println(auth.password);*/
            }
        } catch(IOException i) {
            System.out.println(i);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void authentication(String line) throws SQLException {
        Gson foo = new Gson();
        AuthenticationForm auth = foo.fromJson(line, AuthenticationForm.class);

        Connection conn = DatabaseManager.getInstance().getConnection();
        Statement st = conn.createStatement();

        String k = "select * from users where username = " + "\'" + auth.getLogin() + "\'" + " and password = " + "\'" + auth.getPassword() + "\'";
        System.out.println(k);
        ResultSet r = st.executeQuery(k);
        if(r.next()) {
            auth.setValid(true);
        }
        String json = foo.toJson(auth);
        System.out.println(auth);
        this.send(json);
        System.out.println("++++++++++");
    }

    public void send (String line) {
        System.out.println(line);
        try {
            dataOutputStream.writeBytes(line);
        } catch(IOException i) {
            System.out.println(i);
        }
        System.out.println("========");
    }

    public Server(int port) {
        this.port = port;
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Server started");
    }

    public void setDatabaseManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }
}