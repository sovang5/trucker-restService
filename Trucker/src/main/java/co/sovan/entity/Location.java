package co.sovan.entity;


import javax.persistence.Entity;
import java.util.Date;
// Location Class is used to return the object location of a specific vehicle
public class Location {
    private Date timestamp;
    private double longitude;
    private double latitude;

    public Location(double latitude,double longitude,Date timestamp) {
        this.timestamp = timestamp;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
