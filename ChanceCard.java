package lastproj;

public class ChanceCard{
	public void card1(Player p) {
		System.out.println("Anda menuju ke start(terima $200).");
		p.setPos(0);
		p.altBal(1,200);
	}
	
	public void card2(Player p) {
		int temp = p.getPos();
		System.out.println("Anda menuju ke Aceh.");
		p.setPos(13);
		if(temp>13){
			System.out.println("Anda telah melewati start(terima $200).");
			p.altBal(1,200);
		}
	}
	
	public void card3(Player p) {
		int temp = p.getPos();
		System.out.println("Anda menuju ke Jakarta");
		p.setPos(7);
		if(temp>7){
			System.out.println("Anda telah melewati start(terima $200).");
			p.altBal(1,200);
		}
	}
	
	public void card4(Player p) {
		int temp = p.getPos();
		System.out.println("Anda menuju tempat pelayanan umum terdekat.");
		p.setPos(temp+1);
		//ada yang kurang
	}
	
	public void card5(Player p) {
		int temp = p.getPos();
		System.out.println("Anda menuju ke Stasiun Kota.");
		p.setPos(2);
		if(temp>2){
			System.out.println("Anda telah melewati start(terima $200).");
			p.altBal(1,200);
		}
		//kurang
	}
	
	public void card6(Player p) {
		System.out.println("Selamat! Anda mendapat dana dari bank($50).");
		p.altBal(1,50);
	}
	
	public void card7(Player p) {
		System.out.println("Anda mendapatkan Kartu Bebas Penjara.");
		p.altJCard(1);
	}
	
	public void card8(Player p) {
		int temp = p.getPos();
		System.out.println("Anda mundur 3 langkah.");
		p.setPos((temp-3)%24);
	}
	
	public void card9(Player p) {
		System.out.println("Anda dimasukkan ke penjara.");
		p.setStat(true);
		p.setPos(6);
	}
	
	public void card10(Player p) {
		int temp = p.getPos();
		System.out.println("Anda harus membayar biaya perawatan bangunan($50 per jumlah bangunan anda).");
		p.altBal(0,temp*50);
		// kurang kalo ga punya duit
	}
	
	public void card11(Player p) {
		System.out.println("Anda harus membayar pajak(bayar $15).");
		p.altBal(0,15);
	}
	
	public void card12(Player p) {
		int temp = p.getPos();
		System.out.println("Anda menuju ke Stasiun Kota.");
		p.setPos(2);
		if(temp>2){
			System.out.println("Anda telah melewati start(terima $200).");
			p.altBal(1,200);
		}
	}
	
	public void card13(Player p) {
		int temp = p.getPos();
		System.out.println("Anda menuju parkir bebas.");
		p.setPos(12);
		if(temp>12) {
			System.out.println("Anda telah melewati start(terima $200).");
			p.altBal(1,200);
		}
	}
	
	public void card14(Player p1, Player p2) {
		System.out.println("Selamat! Anda memenangkan kampanye(bayar 50$ ke pemain lain).");
		p1.altBal(0,50);
		p2.altBal(1,50);
	}
	
	public void card15(Player p) {
		System.out.println("Selamat! Anda mendapat keuntungan(terima $50).");
		p.altBal(1,50);
	}
	
	public void card16(Player p) {
		System.out.println("Selamat! Anda memenangkan lomba teka-teki silang(terima $100).");
		p.altBal(1,100);
	}
}