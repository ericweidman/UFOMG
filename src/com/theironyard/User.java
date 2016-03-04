package com.theironyard;

/**
 * Created by ericweidman on 3/3/16.
 */
public class User {
    int id;
    String userName;
    String userPass;

    public User(int id, String userName, String userPass) {
        this.id = id;
        this.userName = userName;
        this.userPass = userPass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
}
