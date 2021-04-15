package library;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


public class Signup {
	
	public static void main(String[] args) {		
		Register reg = new Register();
	}
}

class Register{
	Register(){
		
		
JFrame empFrm = new JFrame();
		
		JLabel lname,lpass1,lpass2;
		JTextField tname,tcode;
		JPasswordField psw1,psw2;
		JButton btnsave,btnexit;
		
		lname = new JLabel("Username");
		empFrm.add(lname);
		lname.setBounds(130, 80, 300, 50);
		
		tname = new JTextField();
		empFrm.add(tname);
		tname.setBounds(200, 90, 200, 30);
		
		
		lpass1 = new JLabel("New Password");
		empFrm.add(lpass1);
		lpass1.setBounds(100, 120, 300, 50);
		
		psw1 = new JPasswordField();
		empFrm.add(psw1);
		psw1.setBounds(200, 130, 200, 30);
		
		
		lpass2 = new JLabel("Confirm Password");
		empFrm.add(lpass2);
		lpass2.setBounds(90, 160, 300, 50);
		
		psw2 = new JPasswordField();
		empFrm.add(psw2);
		psw2.setBounds(200, 170, 200, 30);
		
		btnsave = new JButton("Save");
		empFrm.add(btnsave);
		btnsave.setBounds(200,220, 200, 30);
		
		btnexit = new JButton("Back");
		empFrm.add(btnexit);
		btnexit.setBounds(10, 10, 100, 30);

		btnexit.addActionListener(e ->{
			new Home();
			empFrm.dispose();
		
		});
		
		btnsave.addActionListener(e -> {
            DbConnect db = new DbConnect();
            
            String username = tname.getText();
            String password1 = psw1.getText();
            String password2 = psw2.getText();
            

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
            
            ArrayList<String> unames = new ArrayList<String>();
            
            for (Object[] row: user_data) {
                unames.add((String)row[0]);
            }
                                                
            boolean uname_exists = linearSearch(username, unames);      
            
            String validation = validate(username, password1, password2);
            
            if (validation != "true") {
                JOptionPane.showMessageDialog(btnsave, validation);
            } else if(uname_exists) {
                JOptionPane.showMessageDialog(btnsave, "Username already exists. Try other username");
            } else {
                try {
                    String insert_query = "insert into user(username, password) values('" + username + "','" + password1 + "')";                        
                    
                    int result;
                    result = db.connection().executeUpdate(insert_query);
                    if (result > 0) {
                        JOptionPane.showMessageDialog(btnsave, "Data successfully saved");
                        new Home();
                        empFrm.dispose();
                    }
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
            }
            
        });
		
		
		
	 	empFrm.setLayout(null);
		empFrm.setVisible(true);
		empFrm.setSize(600,600);
		empFrm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		
	}
	
	public String validate(String username, String password1, String password2) {
        String validation = "true";
        int un_len = username.length();
        int psw_len = password1.length();
        if (un_len == 0 || psw_len == 0) {
            validation = "All Fields are required";
        } else if(un_len<=3) {
            validation = "Username should be atleast contain 4 characters";
        } else if (psw_len<=7) {
            validation = "Password too short. Must contain atleast 8 character";
        } else if (!password1.equals(password2)) {
            validation = "Password does not match";
        }
        return validation;
    }
    
    public boolean linearSearch(String data, ArrayList<String> arr) {
        boolean exists = false;
        for(int i=0; i<arr.size(); i++) {
            if(data.equals(arr.get(i))) {
                exists = true;
                break;
            }
        }
        return exists;
       
    }
}
