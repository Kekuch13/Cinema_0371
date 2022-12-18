package Server;

public class ServerStart {
    public static void main(String args[]) {
        Server server = new Server(6666);
        server.setDatabaseManager(DatabaseManager.getInstance());
        server.run();
    }
}
