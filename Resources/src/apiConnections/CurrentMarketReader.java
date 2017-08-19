package apiConnections;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import com.google.gson.*;

import Classes.Market;
import Classes.Resource;
import database.MarketConnection;

public class CurrentMarketReader {

	private static final String url_string = "http://www.resources-game.ch/exchange/kurseliste_json.txt";
	
	
	/**
	 * Retrieves market state from API and inserts market data into database
	 */
	public static void updateMarketDB() {
		Market currentMarketState = retrieveMarketState();
		if(currentMarketState == null) {
			System.out.println("Unable to connect to market API");
			return;
		}
		updateDatabase(currentMarketState);
		
	}
	
	/**
	 * Reads JSON data from a URL using a char buffer. Returns the JSON string
	 * From https://stackoverflow.com/questions/7467568/parsing-json-from-url
	 * 
	 * @param urlString URL to read from
	 * @return String data
	 * @throws Exception
	 */
	private static String readUrl(String urlString) throws Exception {
	    BufferedReader reader = null;
	    try {
	        URL url = new URL(urlString);
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 

	        return buffer.toString();
	    } finally {
	        if (reader != null)
	            reader.close();
	    }
	}
	
	private static void updateDatabase(Market marketState) {
		try {
			//Attempt to open connection to database
			MarketConnection connection = new MarketConnection();
			
			//Pass data along to DB handler to attempt to insert into database
			connection.writeMarketData(marketState);
			connection.closeConnection();
			
		} catch(SQLException ex) {
			System.out.println("SQL Exception");
			ex.printStackTrace();
		}
	}
	
	/**
	 * Connect to Resources API market data and retrieve the current market state
	 * @return Market object holding black market and ki market state data
	 */
	public static Market retrieveMarketState() {
	    try {
	    	//Retrieve JSON string from URL
	    	String json = readUrl(url_string);
	    
			Gson gson = new Gson();
			
			//Map JSON data to an array of type Resource
			Resource[] resources = gson.fromJson(json, Resource[].class);

			//Create maps to hold data
			HashMap<String, Long> blackMarket = new HashMap<>();
			HashMap<String, Long> kiMarket = new HashMap<>();
			
			//Iterate Resource array, assigning values for the blackMarket and kiMarket maps
			for(int i = 0; i < resources.length; i++) {
				blackMarket.put(resources[i].getNAME_EN(), resources[i].getSMKURS());
				kiMarket.put(resources[i].getNAME_EN(), resources[i].getNORMKURS());	
			}
			
			//Create market object and assign maps to the market object
			Market marketState = new Market();
			marketState.setBlackMarket(blackMarket);
			marketState.setKiMarket(kiMarket);
			
			return marketState;
			
	   } catch(Exception ex) {
		   ex.printStackTrace();
		   return null;
	   }
   
	}
}
