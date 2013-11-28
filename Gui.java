package lastproj;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Gui extends JFrame{
	JButton[] btn = new JButton[24];
	
	public Gui(){
		
		btn[0] = new JButton("0) Go");
		btn[1] = new JButton("1) Malang");
		btn[2] = new JButton("2) Stasiun Kota");
		btn[3] = new JButton("3) Dana Umum");
		btn[4] = new JButton("4) Yogyakarta");
		btn[5] = new JButton("5) Pajak Umum");
		btn[6] = new JButton("6) Penjara /Cuma Lewat");
		btn[7] = new JButton("7) Jakarta");
		btn[8] = new JButton("8) Medan");
		btn[9] = new JButton("9) Kesempatan");
		btn[10] = new JButton("10) PLN");
		btn[11] = new JButton("11) Padang");
		btn[12] = new JButton("12) Parkir Bebas");
		btn[13] = new JButton("13) Aceh");
		btn[14] = new JButton("14) PAM");
		btn[15] = new JButton("15) Dana Umum");
		btn[16] = new JButton("16) Manado");
		btn[17] = new JButton("17) Bandung");
		btn[18] = new JButton("18) Masuk Penjara");
		btn[19] = new JButton("19) Bali");
		btn[20] = new JButton("20) Pajak Umum");
		btn[21] = new JButton("21) Kesempatan");
		btn[22] = new JButton("22) Telkom");
		btn[23] = new JButton("23) Jayapura");
		final JLabel text = new JLabel("");
		final JLabel text1 = new JLabel("");
		final JLabel text2 = new JLabel("");
		final JLabel text3 = new JLabel("");
		final JLabel mono = new JLabel("M O N O P O L Y");
		setTitle("Monopoly");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	final JFrame tes = new JFrame();
		tes.setTitle("INFO");
		tes.setLayout(new GridLayout(2,2));
		tes.setSize(500,125);
		tes.add(text);
		tes.add(text1);
		tes.add(text2);
		tes.add(text3);
		
		JPanel ats = new JPanel();
		ats.setLayout(new GridLayout(1,7));
		ats.setMaximumSize(new Dimension(1225, 100)); //Dimension : http://stackoverflow.com/questions/2536873/how-can-i-set-size-of-a-button
		ats.add(btn[0]);
		ats.add(btn[1]);
		ats.add(btn[2]);
		ats.add(btn[3]);
		ats.add(btn[4]);
		ats.add(btn[5]);
		ats.add(btn[6]);
		
		JPanel kan = new JPanel();
		kan.setLayout(new GridLayout(5,1));
		kan.setMaximumSize(new Dimension(875 ,100));
		kan.add(btn[7]);
		kan.add(btn[8]);
		kan.add(btn[9]);
		kan.add(btn[10]);
		kan.add(btn[11]);
		
		JPanel bwh = new JPanel();
		bwh.setLayout(new GridLayout(1,7));
		bwh.setMaximumSize(new Dimension(1225,100));
		bwh.add(btn[18]);
		bwh.add(btn[17]);
		bwh.add(btn[16]);
		bwh.add(btn[15]);
		bwh.add(btn[14]);
		bwh.add(btn[13]);
		bwh.add(btn[12]);
		
		JPanel kiri = new JPanel();
		kiri.setLayout(new GridLayout(5,1));
		kiri.setMaximumSize(new Dimension(875,100));
		kiri.add(btn[23]);
		kiri.add(btn[22]);
		kiri.add(btn[21]);
		kiri.add(btn[20]);
		kiri.add(btn[19]);
		
		for(int i=0;i<24;i++)
		{
			btn[i].setPreferredSize(new Dimension (175,100));
		}
		
		JPanel tgh = new JPanel();
		tgh.setLayout(new FlowLayout(FlowLayout.CENTER));
		tgh.add(mono, BorderLayout.CENTER);
		
		add(ats,BorderLayout.NORTH);
		add(kan, BorderLayout.EAST);
		add(bwh, BorderLayout.SOUTH);
		add(kiri, BorderLayout.WEST);
		add(tgh, BorderLayout.CENTER);
		
		ActionListener buttonClick = new ActionListener() {    
      		public void actionPerformed(ActionEvent e) {
        		Object source = e.getSource();
        		
      
        		if (source == btn[0])
        		{
        			text.setText("Go");
        			text1.setText("Anda mendapatkan $200 jika lewat");
        			text2.setText("");
        			text3.setText("");
        			tes.setLocationRelativeTo(null);
        			tes.setVisible(true);
        		}
        		if (source == btn[1])
        		{
        			text.setText("Harga Beli");
        			text1.setText("$100");
        			text2.setText("Harga sewa");
        			text3.setText("$40");
        			tes.setLocationRelativeTo(null);
        			tes.setVisible(true);
        		}
        		if (source == btn[2])
        		{
        			text.setText("Harga Beli");
        			text1.setText("$200");
        			text2.setText("Harga sewa");
        			text3.setText("$80");
        			tes.setLocationRelativeTo(null);
        			tes.setVisible(true);
        		}
        		if (source == btn[3])
        		{
        			text.setText("Dana Umum");
        			text1.setText("Anda mendapatkan kartu Dana umum ");
        			text2.setText("");
        			text3.setText("");
        			tes.setLocationRelativeTo(null);
        			tes.setVisible(true);
        		}
        		if (source == btn[4])
        		{
        			text.setText("Harga Beli");
        			text1.setText("$100");
        			text2.setText("Harga sewa");
        			text3.setText("$40");
        			tes.setLocationRelativeTo(null);
        			tes.setVisible(true);
        		}
        		if (source == btn[5])
        		{
        			text.setText("Pajak Umum");
        			text1.setText("Anda harus membayar $150");
        			text2.setText("");
        			text3.setText("");
        			tes.setLocationRelativeTo(null);
        			tes.setVisible(true);
        		}
        		if (source == btn[6])
        		{
        			text.setText("Penjara / Cuma Lewat");
        			text1.setText("Anda hanya melewati penjara");
        			text2.setText("");
        			text3.setText("");
        			tes.setLocationRelativeTo(null);
        			tes.setVisible(true);
        		}
        		if (source == btn[7])
        		{
        			text.setText("Harga Beli");
        			text1.setText("$220");
        			text2.setText("Harga sewa");
        			text3.setText("$88");
        			tes.setLocationRelativeTo(null);
        			tes.setVisible(true);
        		}
        		if (source == btn[8])
        		{
        			text.setText("Harga Beli");
        			text1.setText("$120");
        			text2.setText("Harga sewa");
        			text3.setText("$48");
        			tes.setLocationRelativeTo(null);
        			tes.setVisible(true);
        		}
        		if (source == btn[9])
        		{
        			text.setText("Kesempatan");
        			text1.setText("Anda mendapatkan kartu kesempatan");
        			text2.setText("");
        			text3.setText("");
        			tes.setLocationRelativeTo(null);
        			tes.setVisible(true);
        		}
        		if (source == btn[10])
        		{
        			text.setText("Harga Beli");
        			text1.setText("$200");
        			text2.setText("Harga sewa");
        			text3.setText("$80");
        			tes.setLocationRelativeTo(null);
        			tes.setVisible(true);
        		}
        		if (source == btn[11])
        		{
        			text.setText("Harga Beli");
        			text1.setText("$120");
        			text2.setText("Harga sewa");
        			text3.setText("$48");
        			tes.setLocationRelativeTo(null);
        			tes.setVisible(true);
        		}
        		if (source == btn[12])
        		{
        			text.setText("Parkir Bebas");
        			text1.setText("Anda bebas menuju ke mana saja");
        			text2.setText("");
        			text3.setText("");
        			tes.setLocationRelativeTo(null);
        			tes.setVisible(true);
        		}
        		if (source == btn[13])
        		{
        			text.setText("Harga Beli");
        			text1.setText("$240");
        			text2.setText("Harga sewa");
        			text3.setText("$96");
        			tes.setLocationRelativeTo(null);
        			tes.setVisible(true);
        		}
        		if (source == btn[14])
        		{
        			text.setText("Harga Beli");
        			text1.setText("$200");
        			text2.setText("Harga sewa");
        			text3.setText("$80");
        			tes.setLocationRelativeTo(null);
        			tes.setVisible(true);
        		}
        		if (source == btn[15])
        		{
        			text.setText("Dana Umum");
        			text1.setText("Anda mendapatkan kartu Dana Umum");
        			text2.setText("");
        			text3.setText("");
        			tes.setLocationRelativeTo(null);
        			tes.setVisible(true);
        		}
        		if (source == btn[16])
        		{
        			text.setText("Harga Beli");
        			text1.setText("$180");
        			text2.setText("Harga sewa");
        			text3.setText("$72");
        			tes.setLocationRelativeTo(null);
        			tes.setVisible(true);
        		}
        		if (source == btn[17])
        		{
        			text.setText("Harga Beli");
        			text1.setText("$160");
        			text2.setText("Harga sewa");
        			text3.setText("$64");
        			tes.setLocationRelativeTo(null);
        			tes.setVisible(true);
        		}
        		if (source == btn[18])
        		{
        			text.setText("Masuk Penjara");
        			text1.setText("Anda masuk ke dalam penjara");
        			text2.setText("");
        			text3.setText("");
        			tes.setLocationRelativeTo(null);
        			tes.setVisible(true);
        		} 
        		if (source == btn[19])
        		{
        			text.setText("Harga Beli");
        			text1.setText("$300");
        			text2.setText("Harga sewa");
        			text3.setText("$120");
        			tes.setLocationRelativeTo(null);
        			tes.setVisible(true);
        		}
        		if (source == btn[20])
        		{
        			text.setText("Pajak Umum");
        			text1.setText("Anda harus membayar $150");
        			text2.setText("");
        			text3.setText("");
        			tes.setLocationRelativeTo(null);
        			tes.setVisible(true);
        		}
        		if (source == btn[21])
        		{
        			text.setText("Kesempatan");
        			text1.setText("Anda mendapatkan kartu Kesempatan");
        			text2.setText("");
        			text3.setText("");
        			tes.setLocationRelativeTo(null);
        			tes.setVisible(true);
        		}
        		if (source == btn[22])
        		{
        			text.setText("Harga Beli");
        			text1.setText("$200");
        			text2.setText("Harga sewa");
        			text3.setText("$80");
        			tes.setLocationRelativeTo(null);
        			tes.setVisible(true);
        		}
        		if (source == btn[23])
        		{
        			text.setText("Harga Beli");
        			text1.setText("$400");
        			text2.setText("Harga sewa");
        			text3.setText("$160");
        			tes.setLocationRelativeTo(null);
        			tes.setVisible(true);
        		}      
        		}
      		};
		btn[0].addActionListener(buttonClick);
		btn[1].addActionListener(buttonClick);
		btn[2].addActionListener(buttonClick);
		btn[3].addActionListener(buttonClick);
		btn[4].addActionListener(buttonClick);
		btn[5].addActionListener(buttonClick);
		btn[6].addActionListener(buttonClick);
		btn[7].addActionListener(buttonClick);
		btn[8].addActionListener(buttonClick);
		btn[9].addActionListener(buttonClick);
		btn[10].addActionListener(buttonClick);
		btn[11].addActionListener(buttonClick);
		btn[12].addActionListener(buttonClick);
		btn[13].addActionListener(buttonClick);
		btn[14].addActionListener(buttonClick);
		btn[15].addActionListener(buttonClick);
		btn[16].addActionListener(buttonClick);
		btn[17].addActionListener(buttonClick);
		btn[18].addActionListener(buttonClick);
		btn[19].addActionListener(buttonClick);
		btn[20].addActionListener(buttonClick);
		btn[21].addActionListener(buttonClick);
		btn[22].addActionListener(buttonClick);
		btn[23].addActionListener(buttonClick);
		
		
		pack();
    	setVisible(true);
	}
	
	public void warnai(int index , int p)
	{
		if(p==0)
			btn[index].setBackground(Color.CYAN);
		else if(p==1)
			btn[index].setBackground(Color.GRAY);
		else if(p==2)
			btn[index].setBackground(null);
	}
	

}