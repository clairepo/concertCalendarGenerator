import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JProgressBar;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;

import com.google.gson.Gson;

public class ArtSpaceFiller {
	Workbook workbook;
	String mindate;
	String maxdate;
	JProgressBar b;
	double progress;
	
	public ArtSpaceFiller(Workbook workbook, Date currdate, JProgressBar b, double progress) {
		this.progress = progress;
		this.b = b;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		mindate = dateFormat.format(currdate);
		Calendar c = Calendar.getInstance();
        c.setTime(currdate);
        c.add(Calendar.DATE, 7);
        Date currentDatePlusOne = c.getTime();
        maxdate = dateFormat.format(currentDatePlusOne);
		this.workbook = workbook;
	}
	
	public void fill() {
		int[] venueIDs = {1659833, 2174554, 34, 490866, 1210361, 2732848, 3630294, 1528213, 11537, 30102, 31261, 57614, 1257296, 2094129, 50675, 31219};
		Sheet sheet = workbook.getSheetAt(7);
		Gson gson = new Gson();
		Row row = sheet.getRow(0);
		Cell cell = row.getCell(0);
		CreationHelper createHelper = workbook.getCreationHelper();
		try {
			for(int j = 0; j < venueIDs.length; j++) {
				progress += 0.3;
				b.setValue((int)progress); 
				java.net.URL url = new java.net.URL("https://api.songkick.com/api/3.0/venues/" + venueIDs[j] + "/calendar.json?apikey=2oskPouChJoh1wpt&min_date=" + mindate + "&max_date=" + maxdate);
				BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
				BigObject bo = gson.fromJson(br, BigObject.class);
				
				Event[] events = bo.getResultsPage().getResults().getEvents();
				
				if(events!= null) {
					//System.out.println("Venue: " + events[0].getVenue().getDisplayName());
					int boxrow = ((j/4) * 6) + 2;
					int currrow = boxrow;
					int boxcol = (j%4) * 3;
					String fullstring = "";
					for(int k = 0; k < events.length; k++) {
						
						Performance[] performances = events[k].getPerformance();
						
						if(performances != null) {
							String time = events[k].getStart().getTime();
							
							if(time != null) {
								time = convert12(time);
								time += ", " + events[k].getStart().getDate();
								//System.out.println("time: " + time + " date: " + events[k].getStart().getDate());
								row = sheet.getRow(currrow);
								cell = row.getCell(boxcol + 1);
								cell.setCellValue(time);
							}
							for(int l = 0; l < performances.length; l++) {
								row = sheet.getRow(currrow);
								cell = row.getCell(boxcol);
								SearchArtist sa = new SearchArtist(performances[l].getArtist().getDisplayName());
								String spotifyurl = sa.getURL();
								if(currrow - boxrow < 3) {
									if(spotifyurl!= "") {
										cell.setCellValue(performances[l].getArtist().getDisplayName());
									    XSSFHyperlink link = (XSSFHyperlink)createHelper.createHyperlink(HyperlinkType.URL);
									    link.setAddress(spotifyurl);
									    cell.setHyperlink((XSSFHyperlink) link);
									}
									else {
										cell.setCellValue(performances[l].getArtist().getDisplayName());
									}
									currrow++;
								}
								else {
									fullstring += performances[l].getArtist().getDisplayName();
									if(l + 1 < performances.length) {
										fullstring += ", ";
									}
								}
								
							}
						}
					}
					if(fullstring != "") {
						cell.setCellValue(fullstring);
					}
				}
				
			}
		}catch(IOException ioe) {
			
		}
		
	}
	
	static String convert12(String str) 
	{ 
	// Get Hours 
		String newTime = "";
	    int h1 = (int)str.charAt(0) - '0'; 
	    int h2 = (int)str.charAt(1)- '0'; 
	  
	    int hh = h1 * 10 + h2; 
	  
	    // Finding out the Meridien of time 
	    // ie. AM or PM 
	    String Meridien; 
	    if (hh < 12) { 
	        Meridien = "AM"; 
	    } 
	    else
	        Meridien = "PM"; 
	  
	    hh %= 12; 
	  
	    // Handle 00 and 12 case separately 
	    if (hh == 0) { 
	        newTime += "12"; 
	  
	        // Printing minutes and seconds 
	        for (int i = 2; i < 5; ++i) { 
	        	newTime += str.charAt(i); 
	        } 
	    } 
	    else { 
	        newTime += hh; 
	        // Printing minutes and seconds 
	        for (int i = 2; i < 5; ++i) { 
	        	newTime += str.charAt(i); 
	        } 
	    } 
	   
	    return newTime + Meridien;
	}
}
