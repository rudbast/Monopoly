package lastproj;

public class Map {
	private String title;
	private int value;	// price to buy for property || tax payment
	
// 0 = property || 1 = tax || 2 = card || 3 = go || 4 = jail || 5 = none
	private int type;
	
	public Map(String title, int type) {
		this.title = title;
		this.type = type;
	}
	
	public Map(String title, int value, int type) {
		this.title = title;
		this.value = value;
		this.type = type;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public int getVal() {
		return this.value;
	}
	
	public int getType() {
		return this.type;
	}
	
// to be overridden by class property only
	public int getRent() { return 0; }
	public int getSell() { return 0; }
	public String getOwner() { return ""; }
	public void setOwner(String name) {}
}