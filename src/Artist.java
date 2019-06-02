

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Artist {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("displayName")
    @Expose
    private String displayName;
    @SerializedName("identifier")
    @Expose
    private Identifier[] identifier;

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

    public Identifier[] getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier[] identifier) {
        this.identifier = identifier;
    }

}
