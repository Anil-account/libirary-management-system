package library;

import java.awt.Font;
import java.awt.Frame;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Login {
	public static void main(String[] args) {
		Home log = new Home();		
	}
}

class Home{
	public Home() {
JFrame form = new JFrame();
		
		JLabel lname,lcode,lpass1,lpass2,header;
		JTextField tname;
		JPasswordField psw1;
		JButton btnsave,btnreg;
		
		header = new JLabel("User Login");
		form.add(header);
		header.setBounds(230, 40, 200, 40);
		header.setFont(new Font("Serif",Font.BOLD,30));
		
		
		lname = new JLabel("Username");
		form.add(lname);
		lname.setBounds(130, 80, 300, 50);
		
		tname = new JTextField();
		form.add(tname);
		tname.setBounds(200, 90, 200, 30);
		
		lpass1 = new JLabel("Password");
		form.add(lpass1);
		lpass1.setBounds(130, 120, 300, 50);
		
		psw1 = new JPasswordField();
		form.add(psw1);
		psw1.setBounds(200, 130, 200, 30);		psw1.setBounds(200, 130, 200, 30);
		
		
		btnsave = new JButton("login");
		form.add(btnsave);
		btnsave.setBounds(200, 170, 200, 30);
		
		btnreg = new JButton("Register");
		form.add(btnreg);
		btnreg.setBounds(200, 220, 200, 30);
		
		
		
		btnsave.addActionListener(e -> {
            String username = tname.getText();
            String psw = psw1.getText();
            
            boolean res = userLogin(username, psw);
            
            if (res) {
                new Main();
                form.dispose();
                
            } else {
                JOptionPane.showMessageDialog(form, "Username/Password Invalid");
            }
        });
        
        
        btnreg.addActionListener(e -> {
            new Register();
            form.dispose();
            
        });
        
	 	form.setLayout(null);
		form.setVisible(true);
		form.setSize(600,600);
		form.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
    }
    
    public boolean userLogin(String user, String psw) {
    	DbConnect db = new DbConnect();
    	String query = "Select * from user";
        ArrayList<User> user_arr = new ArrayList<User>();
        try {
            ResultSet rs = (db.connection()).executeQuery(query);
            while(rs.next()) {
                String name = rs.getString("username");
                String password = rs.getString("password");
                
                User us = new User(name, password);
                user_arr.add(us);
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        Object user_data[][] = new Object[user_arr.size()][2];
        
        for (int i=0; i<user_data.length; i++) {
        	user_data[i][0] = user_arr.get(i).username;
        	user_data[i][1] = user_arr.get(i).password;
        }
        
        for(Object row[]: user_data) {
            if (user.equals(row[0]) && psw.equals(row[1])) {
                return true;
            }
        }
                        
        return false;
    }
		
		
		
		
	        

	        
	    

	    
	    

}
	


