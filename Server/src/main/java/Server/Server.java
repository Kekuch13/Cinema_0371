package Server;

import com.google.gson.Gson;
import forms.AuthenticationForm;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    /*public static void main(String[] args) {
        int port = 80;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server on port: " + port);
            try (Socket socket = serverSocket.accept()) {
                System.out.println("Accept");
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                Thread.sleep(10);

                String text = "";
                while (reader.ready()) {
                    text += reader.readLine();
                }
                System.out.println(text);
                String toClientText = text.toUpperCase();

                PrintWriter writer = new PrintWriter(outputStream,true);
                writer.println(toClientText);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/

    private Socket socket;
    private ServerSocket server;
    private DataInputStream in;


    // creates a server and connects it to the given port
    public Server(int port)
    {
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

        } catch(IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String args[]) {
        Server server = new Server(6666);
    }
}