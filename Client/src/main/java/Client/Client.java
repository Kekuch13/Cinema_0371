package Client;

public class Client {
    public static void main(String[] args) {
        ClientConnection Conn = ClientConnection.getInstance();
        SignInFrame frame = new SignInFrame();
    }
}