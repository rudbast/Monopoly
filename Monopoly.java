import lastproj.*;
import java.util.Scanner;
import java.util.Random;

public class Monopoly {
	static Scanner in = new Scanner(System.in);
	
	static Gui G = new Gui();
	static Dice d = new Dice();
	static Map[] map = new Map[24];
	
	static ChanceCard chan = new ChanceCard();
	static CommunityCard comm = new CommunityCard();
	
// shuffle card
	static int[] cardCh = new int[16];
	static int[] cardCo = new int[17];

// card's index (% 16 / 17)
	static int ChIdx = 0, CoIdx = 0;

// game end signal
	static boolean gameOver = false;
	
// main program
	public static void main(String[] args) {
	// deklarasi
		Player[] p = new Player[2];
    	int ch;	// pilihan
    	int t; // giliran
    	
    	for(int i=0 ; i<16 ; i++){
    		cardCh[i] = i+1;
    		cardCo[i] = i+1;
    	}
    	cardCo[16] = 17;
    	
    	setMap();
    	shuffle(cardCh);
    	shuffle(cardCo);
    	
	// mulai
		do{
			do{
				cls();
				header();
				System.out.println("1. Play");
				System.out.println("2. Exit");
				System.out.print("? ");
				ch = in.nextInt();
			}while(ch < 1 && ch > 2);
			if(ch==2) System.exit(0);
			in.nextLine();
			
		// inisialisasi pemain
			playerInit(p);
			pause();
			cls();
		
		// random giliran pemain
			t = checkStart(p);
			pause();
		
		// loop giliran
			do{
				cls();
				turn(p, t);
				t++;
				t %= 2;
			}while(!gameOver);
			
		}while(ch==1);
	// END
	}

// player initiation (nicks)
	public static void playerInit(Player[] p) {
		String temp, temp2;
		do{
			cls();
			header();
			System.out.println("[P1]");
			System.out.print("# Nick : ");
			temp = in.nextLine();
			
			
			System.out.println("\n[P2]");
			System.out.print("# Nick : ");
			temp2 = in.nextLine();
		}while(temp.equals(temp2));
		p[0] = new Player(temp);
		p[1] = new Player(temp2);
	}

// random who goes first
	public static int checkStart(Player[] p) {
		Random rnd = new Random();
		int temp = rnd.nextInt(2);
		header();
		System.out.println(p[temp].getName()+ " maju terlebih dahulu.");
		return temp;
	}

// turn loop
	public static void turn(Player[] p, int t) {
		boolean out = jailCheck(p, t);
		if(!out){
			boolean pay = true;
			boolean end = false;
			
			cls();
			playerStat(p, t);
			d.throwD(p[t]);
		
		// cek apakah melewati GO
			int pos = p[t].getPos();
			
			if((pos + d.getVal())>=24){
				System.out.println("Anda melewati GO(terima $200).");
				p[t].altBal(1, 200);
			}
			
			p[t].setPos((pos + d.getVal()) % 24);
			pos = p[t].getPos();
			pause();
			
			do{
				cls();
				playerStat(p, t);
				mapStat(p, t);
			
			// property
				if(map[pos].getType()==0){
					if(map[pos].getOwner()==p[(t+1)%2].getName() && pay)
						payRent(p, t, pos, pay);
					end = transaction(p, t, pos);
				}
			
			// pajak
				else if(map[pos].getType()==1){
					System.out.println("Anda dikenai pajak umum(bayar $" + map[pos].getVal() + ").");
					p[t].altBal(0, map[pos].getVal());
					end = true;
				}
			
			// kartu
				else if(map[pos].getType()==2){
					drawCard(p, t);
					end = true;
				}
			
			// penjara
				else if(map[pos].getType()==4){
					p[t].setStat(true);
					System.out.println("Anda masuk penjara.");
					System.out.println("Untuk keluar, giliran selanjutnya dapat membayar $50," +
										"\natau menggunakan kartu bebas penjara," +
										"\natau melempar dadu dan mendapatkan nilai yang ditentukan (random), 2 kali kesempatan.");
					p[t].setPos(6);
					end = true;
				}
			
			// 3 pojok
				else if(map[pos].getType()==5){
				// parkir bebas
					if(pos==12)
						freePark(p[t], pos);
						
				// hanya lewat (penjara)
					else {
						System.out.println("Hanya melewati penjara.");
						end = true;
					}
				}
				
				pause();
			}while(!end);
		}
	}
	
// transaction
	public static boolean transaction(Player[] p, int t, int pos) {
		int ch, price, temp;
		
		do{
			System.out.println("1. Buy");
			System.out.println("2. Sell");
			System.out.println("3. End Turn");
			System.out.print("? ");
			ch = in.nextInt();
		}while(ch < 1 && ch > 3);
		in.nextLine();
		
	// BELI
		if(ch==1){
		// (y/n)
			String temp2;
		
		// belum dibeli pemain
			if(map[pos].getOwner()=="-"){
				p[t].buyProp(map[pos], pos);
				G.warnai(pos, t);
			}
			
		// property sendiri
			else if(map[pos].getOwner()==p[t].getName())
				System.out.println("Maaf, property ini sudah milik anda.");
				
		// property milik pemain lain
			else {
				System.out.println("Maaf, property ini dimiliki pemain lain.");
				System.out.println("Tetapi anda dapat membelinya jika pemilik bersedia menjualnya.");
				in.nextLine();
				
				do{
					System.out.print("Apakah " + map[pos].getOwner() + " bersedia menjual property nya ? (y/n) ");
					temp2 = in.nextLine();
				}while(!temp2.equalsIgnoreCase("y") && !temp2.equalsIgnoreCase("n"));
				
			// setuju dijual oleh pemilik
				if(temp2.equalsIgnoreCase("y")){
					do{
						System.out.print("Harga jual oleh " + map[pos].getOwner() + " : $");
						price = in.nextInt();
					}while(price < 0);
					in.nextLine();
					
					do{
						System.out.print("Apakah " + p[t].getName() + " setuju membeli? (y/n) ");
						temp2 = in.nextLine();
					}while(!temp2.equalsIgnoreCase("y") && !temp2.equalsIgnoreCase("n"));
				
				// tawaran diterima
					if(temp2.equalsIgnoreCase("y")){
						p[(t+1)%2].transfer(p[t], map[pos], pos, price);
						G.warnai(pos, (t+1)%2);
						System.out.println("Transaksi berhasil(bayar $" + price + ").");
						return true;
					}
					
				// tawaran dibatal
					else
						System.out.println("Transaksi batal.");
				}
				else
					System.out.println("Transaksi batal.");
			}
		}
		
	// JUAL
		else if(ch==2){
			if(map[pos].getOwner()=="-" || map[pos].getOwner()==p[(t+1)%2].getName())
				System.out.println("Maaf property ini bukan milik anda.");
			else {
				do{
					System.out.println("1. Bank");
					System.out.println("2. Pemain lain");
					System.out.print("? ");
					temp = in.nextInt();
				}while(temp < 1 || temp > 2);
				in.nextLine();
				
			// jual ke bank
				if(temp==1){
					p[t].sellProp(map[pos], pos);
					G.warnai(pos, 2);
				}
				
			// jual ke pemain
				else {
					String temp2;
					do{
						System.out.print("Harga jual oleh " + map[pos].getOwner() + " : $");
						price = in.nextInt();
					}while(price < 0);
					in.nextLine();
					
					do{
						System.out.print("Apakah " + p[(t+1)%2].getName() + " setuju membeli? (y/n) ");
						temp2 = in.nextLine();
					}while(!temp2.equalsIgnoreCase("y") && !temp2.equalsIgnoreCase("n"));
				
				// tawaran diterima
					if(temp2.equalsIgnoreCase("y")){
						p[t].transfer(p[(t+1)%2], map[pos], pos, price);
						System.out.println("Transaksi berhasil(terima $" + price + ").");
						G.warnai(pos, (t+1)%2);
						return true;
					}
					
				// tawaran dibatal
					else
						System.out.println("Transaksi batal.");
				}
			}
		}
		
	// pilihan 3 (end turn)
		else
			return true;

		return false;
	}
	
// pay rent (if visited)
	public static void payRent(Player[] p, int t, int pos, boolean pay) {
		System.out.println("Anda mengunjungi kawasan milik " + p[(t+1)%2].getName() + "(bayar $"
							+ map[pos].getRent() + ").");
		p[t].altBal(0, map[pos].getRent());
		p[(t+1)%2].altBal(1, map[pos].getRent());
		pay = false;
	}
	
// jail status handler
	public static boolean jailCheck(Player[] p, int t) {
		int ch;
		boolean loop = true;
		if(p[t].isJail()){
			do{
				cls();
				playerStat(p, t);
				mapStat(p, t);
				do{
					System.out.println("1. Bayar $50");
					System.out.println("2. Kartu Bebas Penjara");
					System.out.println("3. Lempar Dadu");
					System.out.print("? ");
					ch = in.nextInt();
				}while(ch < 1 || ch > 3);
				in.nextLine();
				
			// bayar $50
				if(ch==1){
					System.out.println("Anda bebas dari penjara.");
					p[t].setStat(false);
					p[t].altBal(0, 50);
					pause();
					break;
				}
			// menggunakan jail free card
				else if(ch==2){
					if(p[t].getJCard() > 0){
						System.out.println("Anda bebas dari penjara.");
						p[t].setStat(false);
						p[t].altJCard(0);
						pause();
						break;
					}
					else{
						System.out.println("Maaf, anda tidak punya kartu bebas penjara.");
						pause();
					}
				}
			// lempar dadu
				else {
				// 2 kali kesempatan
					if(p[t].getTry() < 2){
						Random rnd = new Random();
						int target = (rnd.nextInt(6) + 1);
						System.out.println("# Nilai target : " + target);// in.nextLine();
						System.out.println("Silahkan lempar dadu.");
						pause();
						int result = (rnd.nextInt(6) + 1);
						System.out.println("# Hasil        : " + result);
						if(target==result){
							System.out.println("Anda bebas dari penjara.");
							p[t].altTry(1);
							p[t].setStat(false);
							pause();
							break;
						}
						else {
							System.out.println("Hasil tidak sesuai, gagal keluar dari penjara.");
							p[t].altTry(0);
							System.out.println("Sisa kesempatan mencoba : " + p[t].getTry());
							pause();
							return true;
						}
					}
					
				// kesempatan habis
					else {
						System.out.println("Maaf, kesempatan anda untuk menggunakan option ini sudah habis.");
					// cek apakah punya jail free card
						if(p[t].getJCard()==0){
							System.out.println("Bayar $50");
							p[t].altBal(0, 50);
							p[t].setStat(false);
							p[t].altTry(1);
							System.out.println("Anda bebas dari penjara.");
							pause();
							break;
						}
					// jika punya
						else {
							int tempChoicev2;
							System.out.println("\n1. Bayar $50");
							System.out.println("2. Kartu Bebas Penjara");
							
							do{
								System.out.println("? ");
								tempChoicev2 = in.nextInt();
							}while(tempChoicev2 < 1 || tempChoicev2 > 2);
							in.nextLine();
							
						// bayar
							if(tempChoicev2==1){
								p[t].altBal(0, 50);
								p[t].setStat(false);
								p[t].altTry(1);
								System.out.println("Anda bebas dari penjara.");
								pause();
								break;
							}
						// gunakan kartu
							else {
								System.out.println("Anda bebas dari penjara.");
								p[t].setStat(false);
								p[t].altJCard(0);
								pause();
								break;
							}
						}
					}
				}
			}while(loop);
		}
		return false;
	}
	
// free parking
	public static void freePark(Player p, int pos) {
		System.out.println("Anda berada di posisi parkir bebas.");
		int target;
		do{
			System.out.print("Silahkan pilih tujuan anda (index peta[0-23]) : ");
			target = in.nextInt();
		}while(target < 0 || target > 23 || target == pos);
		in.nextLine();
		
		System.out.println("Berhasil pindah ke tujuan.");
		if(target < pos){
			System.out.println("Anda melewati GO(terima $200).");
			p.altBal(1, 200);
		}
		p.setPos(target);
		pos = target;
	}

// monopoly header
	public static void header() {
		System.out.println("===================[ M O N O P O L Y ]===================");
	}

// show player status
	public static void playerStat(Player[] p, int t) {
		header();
		System.out.println("[P" + (t+1%2) + "]");
		System.out.println("# Nick           : " + p[t].getName());
		System.out.println("# Balance        : " + "$" + p[t].getBal());
		System.out.println("# Position       : " + p[t].getPos());
		System.out.println("# Jail Free Card : " + p[t].getJCard());
		System.out.println("# Property Owned : " + p[t].getOwn());
		System.out.print("# List Property  : "); p[t].getProp();
		System.out.println("=========================================================");
	}

// show map status
	public static void mapStat(Player[] p, int t) {
		int pos = p[t].getPos();
		System.out.println("# Map Index      : " + pos);
		System.out.println("# Title          : " + map[pos].getTitle());
		if(map[pos].getType()==0){
			System.out.println("# Original value : " + map[pos].getVal());
			System.out.println("# Rent value     : " + map[pos].getRent());
			System.out.println("# Sell value     : " + map[pos].getSell());
			System.out.println("# Owner          : " + map[pos].getOwner());
		}
		System.out.println("=========================================================");
	}
	
// Map initialization
	public static void setMap() {
		map[0] = new Map("GO", 200, 3);
		map[1] = new Property("Malang", 100, 0);
		map[2] = new Property("Stasiun Kota", 200, 0);
		map[3] = new Map("Dana Umum", 2);
		map[4] = new Property("Yogyakarta", 100, 0);
		map[5] = new Tax("Pajak Umum", 150, 1);
		map[6] = new Map("Penjara / Cuma Lewat", 5);
		map[7] = new Property("Jakarta", 220, 0);
		map[8] = new Property("Medan", 120, 0);
		map[9] = new Map("Kesempatan", 2);
		map[10] = new Property("PLN", 200, 0);
		map[11] = new Property("Padang", 120, 0);
		map[12] = new Map("Parkir Bebas", 5);
		map[13] = new Property("Aceh", 240, 0);
		map[14] = new Property("PAM", 200, 0);
		map[15] = new Map("Dana Umum", 2);
		map[16] = new Property("Manado", 180, 0);
		map[17] = new Property("Bandung", 160, 0);
		map[18] = new Map("Masuk Penjara", 4);
		map[19] = new Property("Bali", 300, 0);
		map[20] = new Tax("Pajak Umum", 150, 1);
		map[21] = new Map("Kesempatan", 2);
		map[22] = new Property("Telkom", 200, 0);
		map[23] = new Property("Jayapura", 400, 0);
	}
	
// draw card
	public static void drawCard(Player[] p, int t) {
		int pos = p[t].getPos();
		Player p1 = p[t];
		Player p2 = p[(t+1)%2];
		
		if(pos==3 || pos==15){
			switch(cardCo[CoIdx]){
				case 1: comm.card1(p1); break;
				case 2: comm.card2(p1); break;
				case 3: comm.card3(p1); break;
				case 4: comm.card4(p1); break;
				case 5: comm.card5(p1); break;
				case 6: comm.card6(p1); break;
				case 7: comm.card7(p1, p2); break;
				case 8: comm.card8(p1); break;
				case 9: comm.card9(p1); break;
				case 10: comm.card10(p1, p2); break;
				case 11: comm.card11(p1); break;
				case 12: comm.card12(p1); break;
				case 13: comm.card13(p1); break;
				case 14: comm.card14(p1); break;
				case 15: comm.card15(p1); break;
				case 16: comm.card16(p1, p2); break;
				case 17: comm.card17(p1); break;
			}
			CoIdx++;
			CoIdx %= 17;
			if(CoIdx==0) shuffle(cardCo);
		}
		else {
			switch(cardCh[ChIdx]){
				case 1: chan.card1(p1); break;
				case 2: chan.card2(p1); break;
				case 3: chan.card3(p1); break;
				case 4: chan.card4(p1); break;
				case 5: chan.card5(p1); break;
				case 6: chan.card6(p1); break;
				case 7: chan.card7(p1); break;
				case 8: chan.card8(p1); break;
				case 9: chan.card9(p1); break;
				case 10: chan.card10(p1); break;
				case 11: chan.card11(p1); break;
				case 12: chan.card12(p1); break;
				case 13: chan.card13(p1); break;
				case 14: chan.card14(p1, p2); break;
				case 15: chan.card15(p1); break;
				case 16: chan.card16(p1); break;
			}
			ChIdx++;
			ChIdx %= 16;
			if(ChIdx==0) shuffle(cardCh);
		}
	}
	
	// ref : http://stackoverflow.com/questions/1519736/random-shuffling-of-an-array
	public static void shuffle(int[] ar) {
		Random rnd = new Random();
		for (int i = ar.length - 1; i > 0; i--){
			int index = rnd.nextInt(i + 1);
			int a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}
	
// clear screen
	public static void cls() {
		for (int i=1 ; i<=30 ; i++) System.out.println("\n");
	}

// pause
	public static void pause() {
		System.out.println("Press Enter To Continue...");
		new java.util.Scanner(System.in).nextLine();
	}
}