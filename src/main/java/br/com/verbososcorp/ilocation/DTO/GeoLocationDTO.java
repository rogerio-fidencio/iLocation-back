package br.com.verbososcorp.ilocation.DTO;

import java.util.Date;

public class GeoLocationDTO {
    Date timestamp;
    String longitude;
    String latitude;

    public GeoLocationDTO(Date timestamp, String longitude, String latitude) {
        this.timestamp = timestamp;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public GeoLocationDTO() {
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
