

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Venue {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("displayName")
    @Expose
    private String displayName;
    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("lng")
    @Expose
    private Object lng;
    @SerializedName("lat")
    @Expose
    private Object lat;
    @SerializedName("metroArea")
    @Expose
    private MetroArea metroArea;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
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

    public MetroArea getMetroArea() {
        return metroArea;
    }

    public void setMetroArea(MetroArea metroArea) {
        this.metroArea = metroArea;
    }

}
