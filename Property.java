package lastproj;

public class Property extends Map {
	private int rentVal;	// rent value (if bought)
	private int sellVal;	// bank sell value
	private String owner;	// owner's name
	
	public Property(String title, int value, int type) {
		super(title, value, type);
		rentVal = getVal()*40/100;
		sellVal = getVal()*80/100;
		owner = "-";
	}
	
	public int getRent() {
		return this.rentVal;
	}
	
	public int getSell() {
		return this.sellVal;
	}
	
	public String getOwner() {
		return owner;
	}
	
	public void setOwner(String name) {
		owner = name;
	}
}