
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchArtistsRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchArtistsRequest.Builder;

public class SearchArtist {
	
	public SearchArtist(String artist) {
		this.q = artist;
		String accessToken = "";
		final ClientCredentials clientCredentials;
		try {
			 clientCredentials = clientCredentialsRequest.execute();
			 accessToken = clientCredentials.getAccessToken();
		}catch(SpotifyWebApiException e) {
			System.out.println("e: " + e.getMessage());
		}catch(IOException ioe) {
			System.out.println("ioe: " + ioe.getMessage());
		}
		spotifyApi.setAccessToken(accessToken);
		Builder searchArtistsRequestBuilder = new SearchArtistsRequest.Builder(accessToken);
		searchArtistsRequestBuilder = spotifyApi.searchArtists(q);
		sar = searchArtistsRequestBuilder.build();
		this.searchArtists_Async();
	}
	
	public String getURL() {
		return url;
	}
	private SearchArtistsRequest sar;
	//private static final String accessToken = "BQCoJD-inRdmaHMNFXTn7KPuC79dqDTjwjr-foF4w3u_O1giPVnHjRbrz8O41kVko__CHX6taZ0SNODRf3hfwiXSfmfIWHtRc4y_YlcbrL52JWte4tU5TEvlbJypHP4Nv36Ywc7ca6PQrDvDhw";
	private String q;
	private String url;
	
	

	private static SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setClientId("7fee83dea907488094b5a1db3decea2a")
          .setClientSecret("4ff7dda8a4cc43c4abf74c492cf3e983")
          .build();
	
	private static final ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
	          .build();
	
	//private SearchArtistsRequest searchArtistsRequest = spotifyApi.searchArtists(q)
          //.build();

	public void searchArtists_Sync() {
	    try {
	      final Paging<Artist> artistPaging = sar.execute();

	      System.out.println("Total: " + artistPaging.getTotal());
	    } catch (IOException | SpotifyWebApiException e) {
	      System.out.println("Error: " + e.getMessage());
	    }
	  }
  
	public void searchArtists_Async() {
		try {
			final Future<Paging<Artist>> pagingFuture = sar.executeAsync();

	      // ...
	
	      final Paging<Artist> artistPaging = pagingFuture.get();
	     
	      Artist [] a = artistPaging.getItems();
	      if(a!= null && a.length!=0) {
	    	  Artist a1 = a[0];
	          this.url = a1.getExternalUrls().get("spotify");
	      }
	      else {
	    	  this.url = "";
	      }
	      
	    } catch (InterruptedException | ExecutionException e) {
	      System.out.println("Error: " + e.getCause().getMessage());
	    }
	  }
}
