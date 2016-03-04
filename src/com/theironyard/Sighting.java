package com.theironyard;

/**
 * Created by ericweidman on 3/3/16.
 */
public class Sighting {
    int id;
    String location;
    String text;
    String timestamp;
    String url;

    public Sighting(int id, String location, String text, String timestamp, String url) {
        this.id = id;
        this.location = location;
        this.text = text;
        this.timestamp = timestamp;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
