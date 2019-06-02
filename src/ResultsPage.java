

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultsPage {

@SerializedName("status")
@Expose
private String status;
@SerializedName("results")
@Expose
private Results results;
@SerializedName("perPage")
@Expose
private Integer perPage;
@SerializedName("page")
@Expose
private Integer page;
@SerializedName("totalEntries")
@Expose
private Integer totalEntries;

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public Results getResults() {
return results;
}

public void setResults(Results results) {
this.results = results;
}

public Integer getPerPage() {
return perPage;
}

public void setPerPage(Integer perPage) {
this.perPage = perPage;
}

public Integer getPage() {
return page;
}

public void setPage(Integer page) {
this.page = page;
}

public Integer getTotalEntries() {
return totalEntries;
}

public void setTotalEntries(Integer totalEntries) {
this.totalEntries = totalEntries;
}

}