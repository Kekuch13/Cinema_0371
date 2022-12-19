package Server;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static DatabaseManager dm;
    private Connection conn;
    private DSLContext context;

    private DatabaseManager() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        DatabaseConfig dbConf = new DatabaseConfig();
        String host = dbConf.getHost();
        int port = dbConf.getPort();
        String base = dbConf.getBase();
        String login = dbConf.getLogin();
        String password = dbConf.getPassword();
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://"+ host + ":" + port  + "/" + base, login, password);
            context = DSL.using(conn, SQLDialect.POSTGRES);
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
    public DSLContext getContext() {
        return context;
    }
}
