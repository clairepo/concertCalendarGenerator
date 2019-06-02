

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Event {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("displayName")
    @Expose
    private String displayName;
    @SerializedName("start")
    @Expose
    private Start start;
    @SerializedName("performance")
    @Expose
    private Performance[] performances;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("venue")
    @Expose
    private Venue venue;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("popularity")
    @Expose
    private double popularity;
    @SerializedName("ageRestriction")
    @Expose
    private Object ageRestriction;
    @SerializedName("flaggedAsEnded")
    @Expose
    private boolean flaggedAsEnded;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Start getStart() {
        return start;
    }

    public void setStart(Start start) {
        this.start = start;
    }

    public Performance[] getPerformance() {
        return performances;
    }

    public void setPerformance(Performance[] performances) {
        this.performances = performances;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

}
