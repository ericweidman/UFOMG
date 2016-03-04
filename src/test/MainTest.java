import com.theironyard.Main;
import com.theironyard.User;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by ericweidman on 3/4/16.
 */
public class MainTest {


    public Connection startConnection() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./test");
        Main.createTables(conn);
        return conn;
    }

    public void endConnection(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.execute("DROP TABLE users");
        stmt.execute("DROP TABLE sightings");
        conn.close();
    }

    @Test
    public void testInsertUser() throws SQLException {
        Connection conn = startConnection();
        Main.insertUser(conn, "Eric", "12345");
        User user = Main.selectUser(conn, "Eric");
        endConnection(conn);
        Assert.assertTrue(user != null);


    }
}
