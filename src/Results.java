import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {
	@SerializedName("event")
	@Expose
	private Event[] event;
	
	
	public Event[] getEvents() {
        return event;
    }
	public Event getEvent(int i) {
        return event[i];
    }

    public void setEvents(Event[] event) {
        this.event = event;
    }
}
