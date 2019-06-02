
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BigObject {

	@SerializedName("resultsPage")
	@Expose
	private ResultsPage resultsPage;
	
	public ResultsPage getResultsPage() {
	return resultsPage;
	}
	
	public void setResultsPage(ResultsPage resultsPage) {
	this.resultsPage = resultsPage;
	}

}
