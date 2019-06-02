

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("lng")
    @Expose
    private Object lng;
    @SerializedName("lat")
    @Expose
    private Object lat;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Object getLng() {
        return lng;
    }

    public void setLng(Object lng) {
        this.lng = lng;
    }

    public Object getLat() {
        return lat;
    }

    public void setLat(Object lat) {
        this.lat = lat;
    }

}
