package Client;

import com.thoughtworks.xstream.io.json.JsonWriter;

import javax.swing.*;
import java.net.*;
import java.io.*;

public class Client {
    /*public static void main(String[] args) {
        System.out.println("Hello world!");
    }*/

    public static void main(String args[])
    {
        ClientConnection Conn = ClientConnection.getInstance("127.0.0.1", 6666);
        SignInFrame frame = new SignInFrame();
    }
}