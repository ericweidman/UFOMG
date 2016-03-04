package com.theironyard;

import jodd.json.JsonSerializer;
import spark.Session;
import spark.Spark;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./main");


        Spark.init();
        Spark.get(
                "/", //  This will be renamed to /index?
                (request, response) -> {
                    JsonSerializer serializer = new JsonSerializer();
                    //Insert some kind of code here.
                    return serializer.serialize("INSERT SOMETHING HERE!");
                }
        );

        Spark.post(
                "/", //  This will likely be where we create users. Will rename to something like "/create-user"
                (request, response) -> {
                    String userName = request.queryParams("rename"); //We need ot figure out these calls as a group.
                    String userPass = request.queryParams("renameThisToo");

                    //Insert code here, probably with a method.

                    return "";
                }
        );

        Spark.post(
                "/", //  Create sighting.
                (request, response) -> {
                    String location = request.queryParams("rename");
                    String text = request.queryParams("rename");
                    String timestamp = request.queryParams("rename"); //Date and time?
                    String url = request.queryParams("rename");
                    //We may need additional information.

                    //Insert code here, also probably with a method.

                    return "";
                }
        );
        Spark.post(
                "/", //  Logout. I'm not sure how much of this post we'll need to change.
                (request, response) -> {
                    Session session = request.session();
                    session.invalidate();
                    response.redirect("/");
                    return "";
                }
        );
        // We're probably going to need to write more posts, but I think this will be enough to get us started.


    }

    public static void createTables(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS user (id IDENTITY, user_name VARCHAR, user_password VARCHAR)");
        // Some of this may change.
        stmt.execute("CREATE TABLE IF NOT EXISTS sighting (id IDENTITY, location VARCHAR, text VARCHAR, timestamp VARCHAR," +
                "url VARCHAR, user_id INT)");
        // We may need too add additional information here, also I think we need to create a userId int
        // so that we can INNER JOIN them. I need some clarification on this.
    }

    public static void insertUser(Connection conn, String userName, String userPass) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO user VALUES (NULL, ?, ?)");
        stmt.setString(1, userName);
        stmt.setString(1, userPass);
        stmt.execute();
        // This should cover it. Again, maybe?
    }

    public static User selectUser(Connection conn, String userName) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE user_name = ?)");
        stmt.setString(1, userName);
        ResultSet results = stmt.executeQuery();
        if (results.next()) {
            int id = results.getInt("id");
            String password = results.getString("user_password");
            return new User(id, userName, password);
        }
        return null;

        // I think this method will work find for what we need.
    }

    public static void insertSighting(Connection conn, int userId, String location, String text, String timestamp, String url) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO sighting VALUES (NULL, ?, ?, ?, ?, ?)");
        stmt.setInt(1, userId);
        stmt.setString(2, location);
        stmt.setString(3, text);
        stmt.setString(4, timestamp);
        stmt.setString(5, url);
        stmt.execute();


    }
}

