

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MetroArea {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("displayName")
    @Expose
    private String displayName;
    @SerializedName("country")
    @Expose
    private Country country;
    @SerializedName("state")
    @Expose
    private State state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

}
