package com.theironyard;

/**
 * Created by ericweidman on 3/3/16.
 */
public class Sighting {
    int id;
    String lat;
    String lon;
    String text;
    String timestamp;
    String url;


    public Sighting(int id, String lat, String lon, String text, String timestamp, String url) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
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

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void getLon(String lon){
        this.lon = lon;
    }

    public void setLon(String lon){
        this.lon = lon;

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
