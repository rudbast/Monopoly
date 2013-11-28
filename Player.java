package lastproj;
import java.util.Arrays;

public class Player {
	private String name;
	private int bal;
	private int prOwn;
	private int[] prPos = new int[14];
	private int pos;
	private boolean status;
	private int JCard;
	private int Try;
	
	public Player(String name) {
		this.name = name;
		bal = 1000;
		prOwn = 0;
		pos = 0;
		status = false;
		JCard = 0;
		Try = 0;
	}
    
	public String getName() {	// get name
		return this.name;
	}
    
	public int getBal() {	// get balance
		return this.bal;
	}
    
	public int getPos() {	// get position
		return this.pos;
	}
	
	public int getOwn() {	// get owned properties
		return this.prOwn;
	}
	
	public int getJCard() {	// get free jail card amount
		return this.JCard;
	}
	
	public int getTry() {	// get try count (jail free)
		return this.Try;
	}
	
	public void getProp() {	// get list owned properties
		if(prOwn > 0){
			for(int i=0 ; i<prOwn ; i++){
				if(i>0) System.out.print(",");
				System.out.print(prPos[i]);
			}
			System.out.print("\n");
		}
		else
			System.out.print("-\n");
	}

	public boolean isJail() {	// jail status
		return this.status;
	}
    
	public void altBal(int type, int bal) {	// 0 = reduce, 1 = add
		if(type==0) this.bal -= bal;
		else this.bal += bal;
	}
    
	public void altPr(int type) {	// 0 = reduce, 1 = add
		if(type==0) prOwn--;
		else prOwn++;
	}
	
	public void altJCard(int type) {	// 0 = reduce, 1 = add
		if(type==0) JCard--;
		else JCard++;
	}
	
	public void altTry(int type) {	// 0 = add try, 1 = reset
		if(type==0) Try++;
		else if(type==1) Try = 0;
	}
    
	public void setPos(int pos) {	// set position
		this.pos = pos;
	}
    
	public void setStat(boolean status) {	// set status
		this.status = status;
	}
	
	public void addProp(int index) {	// add properties list
		prPos[prOwn] = index;
		altPr(1);
		if(prOwn > 1) Arrays.sort(prPos, 0, prOwn);
	}
	
	public void remProp(int index) {	// remove property
		for(int i=0 ; i<prOwn ; i++){
			if(prPos[i]==index){
				prPos[i] = prPos[i+1];
				for(int j=i+1 ; j<prOwn-1 ; j++)
					prPos[j] = prPos[j+1];
				altPr(0);
				if(getOwn() > 0) Arrays.sort(prPos, 0, prOwn);
				break;
			}
		}
	}
	
// buy from bank
	public void buyProp(Map map, int pos) {
		altBal(0, map.getVal());
		map.setOwner(getName());
		addProp(pos);
		System.out.println("Transaksi berhasil(bayar $" + map.getVal() + ").");
	}

// sell to bank
	public void sellProp(Map map, int pos) {
		map.setOwner("-");
		altBal(1, map.getSell());
		remProp(pos);
		System.out.println("Transaksi berhasil(terima $" + map.getSell() + ").");
	}
	
// transfer property to other player
	public void transfer(Player p, Map map, int pos, int price) {
		altBal(1, price);
		remProp(pos);
		p.altBal(0, price);
		p.addProp(pos);
		map.setOwner(p.getName());
	}
}