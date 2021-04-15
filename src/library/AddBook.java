package library;

import javax.swing.JButton;

import java.awt.Frame;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class AddBook {
	public static void main(String[] args) {
		Book bk = new Book();
	}

}

class Book{
	Book(){
		JFrame f = new JFrame();
		
		JLabel book,authorl,pubdate,pricel,stockl,datetl, booksec;
		JTextField booknamet,tcode, authornamet,datet,stockt, pricet;
		JButton btnsave,btnexit;
		
		booksec = new JLabel("Book Library");
		f.add(booksec);
		booksec.setBounds(250, 55, 200, 20);
		
		
		book = new JLabel("Book Name");
		f.add(book);
		book.setBounds(100, 80, 300, 50);
		
		booknamet = new JTextField();
		f.add(booknamet);
		booknamet.setBounds(200, 90, 200, 30);
		
		authorl = new JLabel("Author Name");
		f.add(authorl);
		authorl.setBounds(100, 120, 300, 50);
		
		authornamet = new JTextField();
		f.add(authornamet);
		authornamet.setBounds(200, 130, 200, 30);
		
		
		pubdate = new JLabel("published date");
		f.add(pubdate);
		pubdate.setBounds(100, 160, 300, 50);
		
		datet = new JTextField();
		f.add(datet);
		datet.setBounds(200, 170, 200, 30);
		
		
		pricel = new JLabel("Price");
		f.add(pricel);
		pricel.setBounds(100, 200, 300, 50);
		
		pricet = new JTextField();
		f.add(pricet);
		pricet.setBounds(200, 210, 200, 30);
//		
		
		stockl = new JLabel("Stock");
		f.add(stockl);
		stockl.setBounds(100, 240, 300, 50);
		
		stockt = new JTextField();
		f.add(stockt);
		stockt.setBounds(200, 250, 200, 30);
		
		btnsave = new JButton("Add Book");
		f.add(btnsave);
		btnsave.setBounds(200,310, 200, 30);
		
		btnexit = new JButton("Back");
		f.add(btnexit);
		btnexit.setBounds(10, 10, 100, 30);
		
		
		btnexit.addActionListener(e ->{
			new Main();
			f.dispose();
		});
		
		
		btnsave.addActionListener(e -> {
//              
			String name = booknamet.getText();
			String aut = authornamet.getText();
			String pub = datet.getText();
			String pric = pricet.getText();
			String stk = stockt.getText();
            		
			
			DbConnect db = new DbConnect();
	        String query = "Select * from books";
	        ArrayList<BookData> book_arr = new ArrayList<BookData>();
	        try {
	            ResultSet rs = db.connection().executeQuery(query);
	            
	            while(rs.next()) {
	                int book_id = rs.getInt("id");
	                String book_name = rs.getString("name");
	                String pub_name = rs.getString("publisher_name");
	                String pub_date = rs.getString("publish_date");
	                int price = rs.getInt("price");
	                int stock = rs.getInt("stock");
	                
	                BookData bk = new BookData(book_id, book_name, pub_name, pub_date, price, stock);
	                book_arr.add(bk);
	            }
	        } catch (SQLException e1) {
	            // TODO Auto-generated catch block
	            e1.printStackTrace();
	        }
	        
	        Object book_data[][] = new Object[book_arr.size()][6];
	        
	        for (int i=0; i<book_data.length; i++) {
	        	book_data[i][0] = book_arr.get(i).book_id;
	        	book_data[i][1] = book_arr.get(i).book_name;
	        	book_data[i][2] = book_arr.get(i).pub_name;
	        	book_data[i][3] = book_arr.get(i).pub_date;
	        	book_data[i][4] = book_arr.get(i).price;
	        	book_data[i][5] = book_arr.get(i).stock;
	        }

            ArrayList<String> bnames = new ArrayList<String>();
            
            for (Object[] row: book_data) {
                bnames.add((String)row[1]);
            }
            
            boolean bname_exists = linearSearch(name, bnames);

            
            String validation = validate(name, aut, pub, pric, stk);
            if (validation != "true") {
                JOptionPane.showMessageDialog(btnsave, validation);
            } 
			  else if (bname_exists) {
			                JOptionPane.showMessageDialog(btnsave, "Book Name already exists.");
			            } 
			  else {
			                
			                try {
			                    String insert_query = "insert into books(name, publisher_name, publish_date, price, stock) values('" + name+ "','" + aut + "','" + pub + "'," + pric + "," + stk + ")";
			                    int result = db.connection().executeUpdate(insert_query);
			                    if (result>0) {
			                        JOptionPane.showMessageDialog(btnsave, "Data successfully saved");
			                        new Book();
			                        f.dispose();
			                    }
			                } catch (SQLException e1) {
			                    // TODO Auto-generated catch block
			                    e1.printStackTrace();
			                }
			            }
             
        });
		
		
		
		
		
		
	 	f.setLayout(null);
		f.setVisible(true);
		f.setSize(600,600);
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		
		
		
	}
	
	public String validate(String book, String author, String date, String price, String stock) {
        String validation = "true";
        int name = book.length();
        int auth = author.length();
        int date1 = date.length();
        int pric = price.length();
        int stck = stock.length();
        
        if (name == 0 || auth== 0 || date1==0 || pric==0 || stck==0) {
            validation = "All Fields are required";
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
