package Client;

import javax.swing.*;
import java.net.*;
import java.io.*;

public class Client {
    public static void main(String args[])
    {
        ClientConnection Conn = ClientConnection.getInstance();
        SignInFrame frame = new SignInFrame();
        AdminPanel adminframe = new AdminPanel();
    }
}