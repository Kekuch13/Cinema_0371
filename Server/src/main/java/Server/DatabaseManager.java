package Server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static DatabaseManager dm;
    private Connection conn;

    private DatabaseManager() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        /*String userHome = System.getProperty("user.home");
        String configPath = userHome + "/KSK/database.xml";
        File file = new File(configPath);

        DatabaseConfig dbConf;
        XStream xStream = new XStream();
        xStream.addPermission(AnyTypePermission.ANY);
        xStream.alias("DatabaseConfiguration", DatabaseConfig.class);
        if (file.exists()) {
            dbConf = (DatabaseConfig) xStream.fromXML(file);
        } else {
            file.getParentFile().mkdirs();
            dbConf = new DatabaseConfig();
            try {
                xStream.toXML(dbConf, new FileOutputStream(file));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }*/

        DatabaseConfig dbConf = new DatabaseConfig();
        String host = dbConf.getHost();
        int port = dbConf.getPort();
        String base = dbConf.getBase();
        String login = dbConf.getLogin();
        String password = dbConf.getPassword();
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cinema", login, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static synchronized DatabaseManager getInstance() {
        if (dm == null) {
            dm = new DatabaseManager();
        }
        return dm;
    }

    public Connection getConnection(){
        return conn;
    }

    public static void main(String[] args) {
        new DatabaseManager();
    }

}
