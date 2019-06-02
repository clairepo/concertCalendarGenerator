

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Performance {

    @SerializedName("artist")
    @Expose
    private Artist artist;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("displayName")
    @Expose
    private String displayName;
    @SerializedName("billingIndex")
    @Expose
    private Integer billingIndex;
    @SerializedName("billing")
    @Expose
    private String billing;

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

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

    public Integer getBillingIndex() {
        return billingIndex;
    }

    public void setBillingIndex(Integer billingIndex) {
        this.billingIndex = billingIndex;
    }

    public String getBilling() {
        return billing;
    }

    public void setBilling(String billing) {
        this.billing = billing;
    }

}
