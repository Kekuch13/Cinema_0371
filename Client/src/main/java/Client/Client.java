package Client;

public class Client {
    public static void main(String args[])
    {
        ClientConnection Conn = ClientConnection.getInstance();
        UserFrame h = new UserFrame();
        //SignInFrame frame = new SignInFrame();
        AdminPanel adminframe = new AdminPanel();
        //AddingFilmDialog addingFilmDialog = new AddingFilmDialog();
        //AddingSessionDialog addingSessionDialog = new AddingSessionDialog("такой-то");
    }
}