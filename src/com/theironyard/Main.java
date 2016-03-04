package com.theironyard;

import jodd.json.JsonSerializer;
import spark.Session;
import spark.Spark;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./main");
        createTables(conn);


        Spark.init();
        Spark.get(
                "/index",
                (request, response) -> {
                    JsonSerializer serializer = new JsonSerializer();
                    //Insert some kind of code here.
                    return serializer.serialize("INSERT SOMETHING HERE!");
                }
        );

        Spark.post(
                "/create-user",
                (request, response) -> {
                    String userName = request.queryParams("userName"); //We need to figure out these call names as a group.
                    String userPass = request.queryParams("userPass");
                    insertUser(conn, userName, userPass);
                    response.redirect("/");
                    return userName;
                }
        );
        Spark.post(
                "/delete-sighting",
                (request, response) -> {
                    int deleteById = Integer.valueOf(request.queryParams("deleteSighting"));
                    deleteSighting(conn, deleteById);
                    response.redirect("/");
                    return "";
                }
        );
        Spark.post(
                "/create-sighting",
                (request, response) -> {
                    String lat = request.queryParams("lat");
                    String lon = request.queryParams("lon");
                    String text = request.queryParams("text");
                    String timestamp = request.queryParams("timestamp");
                    String url = request.queryParams("url");
                    insertSighting(conn, lat, lon, text, timestamp, url);
                    response.redirect("/");
                    return "Success!";
                }
        );
        Spark.post(
                "/update-sighting",
                (request, response) -> {

                    response.redirect("/");
                    return "";
                }
        );
        Spark.post(
                "/logout", //  Logout. I'm not sure how much of this post route we'll need to change.
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
        stmt.execute("CREATE TABLE IF NOT EXISTS users (id IDENTITY, user_name VARCHAR, user_password VARCHAR)");
        // Some of this may change.
        stmt.execute("CREATE TABLE IF NOT EXISTS sightings (id IDENTITY, lat VARCHAR, lon VARCHAR, text VARCHAR, timestamp VARCHAR," + //WHY IS THERE A + HERE?
                "url VARCHAR, user_id INT)");
        // We may need to add additional information here
        // so that we can INNER JOIN them. I need some clarification on this. NOTHING ELSE NEEDED HERE
    }

    public static void insertUser(Connection conn, String userName, String userPass) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO user VALUES (NULL, ?, ?)");
        stmt.setString(1, userName);
        stmt.setString(2, userPass);
        stmt.execute();
        // This should cover it. Maybe? YEP
    }

    public static User selectUser(Connection conn, String userName) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE user_name = ?)");
        stmt.setString(1, userName);
        ResultSet results = stmt.executeQuery();
        if (results.next()) {
            int id = results.getInt("id");
            String password = results.getString("user_password");
            return new User(id, userName, password);
        }
        return null;

        // I think this method will work for what we need. AGREED
    }

    public static void insertSighting(Connection conn, String lat, String lon, String text, String timestamp, String url) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO sightings VALUES (NULL, ?, ?, ?, ?, ?)");
        stmt.setString(1, lat);
        stmt.setString(2, lon);
        stmt.setString(3, text);
        stmt.setString(4, timestamp);
        stmt.setString(5, url);
        stmt.execute();

        //I think this is good too.
    }

    public static Sighting selectSighting(Connection conn, int id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM sighting INNER JOIN users ON" +
                "sightings.user_id = users.id WHERE sightings.id = ?)");
        stmt.setInt(1, id);
        ResultSet results = stmt.executeQuery();
        if (results.next()) {
            String lat = results.getString("sighting.lat");
            String lon = results.getString("sighting.lon");
            String text = results.getString("text");
            String timestamp = results.getString("timestamp");
            String url = results.getString("url");
            //int userId = results.getInt("user_id"); I THINK USERS.USERNAME
            return new Sighting(id, lat, lon, text, timestamp, url);

            // I think? This whole thing could be entirely broken. Let me know what you think.
        }
        return null;
    }

    static void deleteSighting(Connection conn, int id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM WHERE id = ?)");
        stmt.setInt(1, id);
        stmt.execute();
    }

    static void editSighting(Connection conn, int id, String lat, String lon, String text, String timestamp, String url) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("UPDATE sighting SET lat = ?, lon = ?, text = ?, timestamp = ?, url = ? WHERE id = ?)");
        stmt.setString(1, lat);
        stmt.setString(2, lon);
        stmt.setString(3, text);
        stmt.setString(4, timestamp);
        stmt.setString(5, url);
        stmt.setInt(6, id);

    }
    //  Our naming conventions for posts.
    //  "/create-"
    //  "/read-"
    //  "/update-"
    //  "/delete-"
    //
}

