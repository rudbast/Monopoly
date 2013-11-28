package lastproj;
import java.util.Random;

public class Dice {
	private int value;
	
	public Dice() {
	}
    
	public int getVal() {
		return value;
	}
    
	public void throwD(Player p) {
		this.value = (new Random().nextInt(6) + 1);
		System.out.println(p.getName() + " melempar dadu, hasilnya = " + getVal());
	}
}