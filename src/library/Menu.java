package library;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class Menu {
	public static void main(String[] args) {
		Main mn = new Main();		
	}
}

class Main{
	public Main() {
		JFrame f = new JFrame();
		JLabel ltext;
		JButton btnadd,btnview,btnsold,logout;
		
		ltext = new JLabel("Book Store");
		f.add(ltext);
		ltext.setBounds(270, 60, 200, 20);
		
		btnadd = new JButton("Add Book");
		f.add(btnadd);
		btnadd.setBounds(200, 90, 200, 40);
		
		btnview = new JButton("View Book");
		f.add(btnview);
		btnview.setBounds(200, 140, 200, 40);
		
		logout = new JButton("Logout");
		f.add(logout);
		logout.setBounds(200, 190, 200, 40);
		
		
		btnadd.addActionListener(e ->{
			new Book();
			f.dispose();
		});
		
		btnview.addActionListener(e ->{
			new  ViewBooks(null, false);
			f.dispose();
		});

		
		logout.addActionListener(e ->{
			new Home();
			f.dispose();
		});
		
		
		
		
		f.setLayout(null);
		f.setVisible(true);
		f.setSize(600,600);
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
		
	}
}
