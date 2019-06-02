import com.google.gson.annotations.SerializedName;

public class EventList {
	@SerializedName("list")
    private Event[] cw;
	public Event[] getList() {
        return cw;
    }
	public Event getEvent(int i) {
        return cw[i];
    }

    public void setList(Event[] cw) {
        this.cw = cw;
    }
}
