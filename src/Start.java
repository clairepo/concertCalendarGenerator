

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Start {

    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("datetime")
    @Expose
    private String datetime;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

}
