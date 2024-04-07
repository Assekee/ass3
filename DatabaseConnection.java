import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DatabaseConnection {
    private static DatabaseConnection data;
    private static final Object lock = new Object();

    private String hostname;
    private String username;
    private String password;
    private String databaseName;

    private DatabaseConnection() {
        loadConfiguration();
    }

    public static DatabaseConnection getInstance() {
        if (data == null) {
            synchronized (lock) {
                if (data == null) {
                    data = new DatabaseConnection();
                }
            }
        }
        return data;
    }

    private void loadConfiguration() {
        try {
            String fp = "./src/database.config";
            Properties properties = new Properties();
            FileInputStream ip = new FileInputStream(fp);
            properties.load(ip);

            hostname = properties.getProperty("hostname");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            databaseName = properties.getProperty("databaseName");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getHostname() {
        return hostname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public static void main(String[] args) {
        DatabaseConnection connect = DatabaseConnection.getInstance();

        System.out.println("hostname: " + connect.getHostname());
        System.out.println("username: " + connect.getUsername());
        System.out.println("password: " + connect.getPassword());
        System.out.println("database name : " + connect.getDatabaseName());

    }
}
