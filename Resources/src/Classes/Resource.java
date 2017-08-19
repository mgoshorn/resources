package Classes;

public class Resource {
	
	public int getITEM_ID() {
		return ITEM_ID;
	}

	public void setITEM_ID(int iTEM_ID) {
		ITEM_ID = iTEM_ID;
	}

	public String getNAME_DE() {
		return NAME_DE;
	}

	public void setNAME_DE(String nAME_DE) {
		NAME_DE = nAME_DE;
	}

	public long getTS() {
		return TS;
	}

	public void setTS(long tS) {
		TS = tS;
	}

	private int ITEM_ID;
	private String NAME_DE;
	private String NAME_EN;
	private long NORMKURS; //normal/ market (ki-market)
	private long SMKURS;  //Player market (black market) 
	private long TS; //time stamp
	
	public Resource(int id, String name_de, String name_en, long n_market, long b_market, long ts) {
		this.ITEM_ID = id;
		this.NAME_DE = name_de;
		this.setNAME_EN(name_en);
		this.setNORMKURS(n_market);
		this.setSMKURS(b_market);
		this.TS = ts;
	}

	public String getNAME_EN() {
		return NAME_EN;
	}

	public void setNAME_EN(String nAME_EN) {
		NAME_EN = nAME_EN;
	}

	public long getNORMKURS() {
		return NORMKURS;
	}

	public void setNORMKURS(long nORMKURS) {
		NORMKURS = nORMKURS;
	}

	public long getSMKURS() {
		return SMKURS;
	}

	public void setSMKURS(long sMKURS) {
		SMKURS = sMKURS;
	}
	
	
	
}
