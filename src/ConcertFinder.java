import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;

import com.google.gson.Gson;

public class ConcertFinder {
	static JFrame f;
	static JProgressBar b;
	
	
	public static void main (String[] args) {
		f = new JFrame("Progress");
		JPanel p = new JPanel(); 
		b = new JProgressBar(); 
		b.setValue(0);   
        b.setStringPainted(true);  
        p.add(b); 
        JTextArea taskOutput= new JTextArea(20, 30);
        taskOutput.setEditable(false);
        p.add(new JScrollPane(taskOutput));
        f.add(p); 
        f.setSize(500, 500); 
        f.setVisible(true);
        
        
        
		int[] venueIDs = {712506, 74901, 945, 1299, 31275, 2645573, 150303, 4665, 1039, 2871953, 1040, 90, 3552799, 1300, 1352186, 36035, 31256, 8, 3200153, 48, 31251, 1290076, 299, 16268, 3034, 2940408, 1281801, 780, 523, 1606674, 1401, 187523, 598, 7211, 31260, 1484608, 2682593, 2588818, 1487, 3143214, 1489363, 3631474, 3454024, 3688984, 37123, 34, 2457439};
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dateFormat2 = new SimpleDateFormat("EEEE, MM/dd");
        DateFormat dateFormat3 = new SimpleDateFormat("MM_dd");
        Gson gson = new Gson();
        Calendar c = Calendar.getInstance();
        int nextmonday = (1 + 7 - c.get(Calendar.DAY_OF_WEEK)) % 7;
        nextmonday++;
        if(nextmonday==0) {
        	nextmonday = 7;
        }
        c.add(Calendar.DATE, nextmonday);
        Date currentDate = c.getTime();
        double progress = 0;
        
		try { 
	        //An output stream accepts output bytes and sends them to sink.
			String filename = "ConcertCalendar" + dateFormat3.format(c.getTime()) + ".xlsx";
			//filename = "hello2.xlsx";
			//OutputStream fo = new FileOutputStream(filename);
	        WorkbookDuplicator wbd = new WorkbookDuplicator("Blank.xlsx", filename);
	        WorkbookDuplicator.copy();
	        
			FileInputStream fsIP= new FileInputStream(new File(filename)); 
			Workbook workbook = WorkbookFactory.create(fsIP);
			CreationHelper createHelper = workbook.getCreationHelper();
			String mindate = dateFormat.format(currentDate);
			for(int i = 0; i < 7; i++) {
				
				
				int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
				if(dayOfWeek-2 < 0) {
					dayOfWeek = 7 + dayOfWeek;
				}
				Sheet sheet = workbook.getSheetAt(dayOfWeek - 2);
				String titledate = dateFormat2.format(c.getTime());
				Row row = sheet.getRow(0);
				Cell cell = row.getCell(0);
				
				System.out.println("Searching for concerts on " + titledate + "...");
				taskOutput.append(String.format("Searching for concerts on " + titledate +"\n"));
				cell.setCellValue(titledate);
				for(int j = 0; j < venueIDs.length; j++) {
					progress += 0.3;
					b.setValue((int)progress); 
					//int venueid = venueIDs[j];
					java.net.URL url = new java.net.URL("https://api.songkick.com/api/3.0/venues/" + venueIDs[j] + "/calendar.json?apikey=2oskPouChJoh1wpt&min_date=" + mindate + "&max_date=" + mindate);
					BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
					BigObject bo = gson.fromJson(br, BigObject.class);
					
					Event[] events = bo.getResultsPage().getResults().getEvents();
					
					if(events!= null) {
						//System.out.println("Venue: " + events[0].getVenue().getDisplayName());
						int boxrow = ((j/5) * 6) + 2;
						int currrow = boxrow;
						int boxcol = (j%5) * 3;
						String fullstring = "";
						for(int k = 0; k < events.length; k++) {
							
							Performance[] performances = events[k].getPerformance();
							
							if(performances != null) {
								String time = events[k].getStart().getTime();
								if(time != null) {
									time = convert12(time);
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
				c.setTime(currentDate);
		        c.add(Calendar.DATE, (i+1));
		        mindate = dateFormat.format(c.getTime());
			}
			taskOutput.append(String.format("Searching for concerts in art spaces\n"));
			System.out.println("Searching for concerts in art spaces\n");
			ArtSpaceFiller asf = new ArtSpaceFiller(workbook, currentDate, b, progress);
			asf.fill();
			taskOutput.append(String.format("Writing to excel file \n"));
			System.out.println("Writing to excel file.");
			try {
				FileOutputStream fileOut = new FileOutputStream(new File(filename));
				workbook.write(fileOut);
				fileOut.close();
			}catch(EOFException eofe) {
				System.out.println("eofe: " + eofe.getMessage());
				taskOutput.append(String.format("eofe: " + eofe.getMessage()));
			}catch(POIXMLException poie) {
				System.out.println("poi: " + poie.getMessage());
				taskOutput.append(String.format("poi: " + poie.getMessage()));
			}
		    

		    // Closing the workbook
		    workbook.close();
			
		    b.setValue(100);
		    b.setString("Finished."); 
		    taskOutput.append(String.format("Finished \n"));
			System.out.println("Finished.");
			
		}catch(IOException ioe) {
			System.out.println("ioe in main: " + ioe.getMessage());
			taskOutput.append(String.format("ioe in main: " + ioe.getMessage()));
		}catch(Exception e) {
			taskOutput.append(String.format("e in main: " + e.getMessage()));
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
	  
	    // After time is printed 
	    // cout Meridien 
	    return newTime + Meridien;
	}
}
