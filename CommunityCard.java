package lastproj;

public class CommunityCard{
	public void card1(Player p) {
		System.out.println("Anda menuju ke start (mendapatkan $200)");
		p.setPos(0);
		p.altBal(1, 200);
	}
	
	public void card2(Player p){
		System.out.println("Bank mengalami kerusakan, anda mendapatkan $200");
		p.altBal(1,200);
	}
	
	public void card3(Player p){
		System.out.println("Anda masuk ke dalam rumah sakit , harus membayar $50");
		p.altBal(0,50);
	}
	
	public void card4(Player p){
		System.out.println("Anda mendapatkan keuntungan saham sebesar $50");
		p.altBal(1,50);
	}
	
	public void card5(Player p){
		p.altJCard(1);
	}
	
	public void card6(Player p){
		System.out.println("Anda masuk penjara");
		p.setPos(6);
		p.setStat(true);
	}
	
	public void card7(Player p1, Player p2){
		System.out.println("Anda berhasil membuka pertunjukkan (Mendapatkan $50 dari setiap pemain");
		p1.altBal(1,50);
		p2.altBal(0,50);
	}
	
	public void card8(Player p){
		System.out.println("Selamat hari Natal ! (Anda mendapatkan $50)");
		p.altBal(1,50);
	}
	
	public void card9(Player p){
		System.out.println("Anda mendapatkan kembalian dari pajak ($20)");
		p.altBal(1,20);
	}
	
	public void card10(Player p1, Player p2){
		System.out.println("Selamat ulang tahun ! Anda mendapatkan $10 dari setiap pemain");
		p1.altBal(1,10);
		p2.altBal(0,10);
	}
	
	public void card11(Player p){
		System.out.println("Anda mendapatkan $100 dari asuransi ");
		p.altBal(1,100);
	}
	
	public void card12(Player p){
		System.out.println("Anda masuk rumah sakit (membayar $100)");
		p.altBal(0,100);
	}
	
	public void card13(Player p){
		System.out.println("Waktunya membayar uang sekolah ($150)");
		p.altBal(0,150);
	}
	
	public void card14(Player p){
		System.out.println("Anda mendapatkan $25 dari biaya konsultasi");
		p.altBal(1,25);
	}
	
	public void card15(Player p){
		int temp, pay;
		temp = p.getOwn();
		pay = temp*50;
		System.out.println("Anda membayar pajak $50 untuk setiap bangunan yang anda miliki(bayar $"+ pay +").");
		p.altBal(0, pay);
	}
	
	public void card16(Player p1, Player p2){
		System.out.println("Anda memenangkan kontes kecantikan (mendapatkan $10 dari setiap pemain)");
		p1.altBal(1,10);
		p2.altBal(0,10);
	}
	
	public void card17(Player p){
		System.out.println("Selamat anda mendapatkan warisan sebanyak $100");
		p.altBal(1,100);
	}
}