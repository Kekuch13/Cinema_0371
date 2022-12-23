package Server;

import Entities.Film;
import Entities.Session;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import forms.AuthenticationForm;
import forms.CreateSessionForm;
import forms.FilmsForm;
import forms.TableChangeForm;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Server {
    private final int port;
    private final Socket socket;
    private final ServerSocket server;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
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
            dataInputStream = new DataInputStream(socket.getInputStream());
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
                    authentication(line);
                    break;
                case "FilmsList":
                    filmsList(line);
                    break;
                case "ChangeTable":
                    changeTable(line);
                    break;
                case "CreateSession":
                    createSession(line);
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
            line = dataInputStream.readUTF();
            System.out.println("Receive from client: " + line);
        } catch (IOException i) {
            System.out.println(i);
        }
        return line;
    }

    public void sendToClient(String line) {
        try {
            dataOutputStream.writeUTF(line);
            dataOutputStream.flush();
            System.out.println("Send to client: " + line);
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

    public void filmsList(String line) {
        FilmsForm films = gson.fromJson(line, FilmsForm.class);
        ArrayList<Film> filmsList = new ArrayList<Film>();
        try {
            Connection conn = DatabaseManager.getInstance().getConnection();
            Statement st = conn.createStatement();

            String query = "select * from films";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("film_id");
                String title = rs.getString("title");
                int year = rs.getInt("year");
                String genre = rs.getString("genre");
                int duration = rs.getInt("duration");
                String country = rs.getString("country");
                Film film = new Film(id, title, year, genre, duration, country);
                filmsList.add(film);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        films.setFilms(filmsList);
        String json = gson.toJson(films);
        sendToClient(json);
    }

    private void changeTable(String line) {
        TableChangeForm tableChangeForm = gson.fromJson(line, TableChangeForm.class);
        if (Objects.equals(tableChangeForm.action, "delete")) {
            try {
                Connection conn = DatabaseManager.getInstance().getConnection();
                Statement st = conn.createStatement();

                char[] arrayOfId = Arrays.toString(tableChangeForm.rowIndex).toCharArray();
                arrayOfId[0] = '(';
                arrayOfId[arrayOfId.length - 1] = ')';
                String query = "DELETE FROM films WHERE film_id IN " + String.valueOf(arrayOfId);
                System.out.println(query);

                int rs = st.executeUpdate(query);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (Objects.equals(tableChangeForm.action, "add")) {
            try {
                Connection conn = DatabaseManager.getInstance().getConnection();
                Statement st = conn.createStatement();
                Film film = tableChangeForm.film;

                String query = "INSERT INTO films VALUES (default, '" + film.getTitle() + "', '" + film.getYear() + "', '" + film.getGenre() + "', '" + film.getDuration() + "', '" + film.getCountry() + "')";
                System.out.println(query);

                int rs = st.executeUpdate(query);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void createSession(String line) {
        CreateSessionForm createSessionForm = gson.fromJson(line, CreateSessionForm.class);

        try {
            Connection conn = DatabaseManager.getInstance().getConnection();
            Statement st = conn.createStatement();

            Session addedSession = createSessionForm.getSession();
            String query = "INSERT INTO sessions VALUES ('" + addedSession.getDate() + "', '" + addedSession.getTime() + "', '" + addedSession.getHall_id() + "', '" + addedSession.getFilm_id() + "')";
            System.out.println(query);

            int rs = st.executeUpdate(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setDatabaseManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }
}