package Classes;
import java.util.HashMap;

public class Market {

	private HashMap<String, Long> blackMarket;
	private HashMap<String, Long> kiMarket;
	
	
	public HashMap<String, Long> getBlackMarket() {
		return blackMarket;
	}
	
	public void setBlackMarket(HashMap<String, Long> blackMarket) {
		this.blackMarket = blackMarket;
	}
	
	public HashMap<String, Long> getKiMarket() {
		return kiMarket;
	}
	
	public void setKiMarket(HashMap<String, Long> kiMarket) {
		this.kiMarket = kiMarket;
	}
	
	
	
	
	
}
