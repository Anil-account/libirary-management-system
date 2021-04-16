package library;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.TableModel;
public class ViewBooks {
    ViewBooks(Object filtered_data[][], boolean filtered) {
        JFrame f = new JFrame();
        String column[] = {"Book ID", "Book Name", "Publisher Name", "Publish Date", "Price", "Stock"};
        
        DbConnect db = new DbConnect();
        
        Object [][] data;
        
        if(filtered) {
            data = filtered_data;
        } else {
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
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            data = new Object[book_arr.size()][column.length];
            
            for (int i=0; i<data.length; i++) {
            	data[i][0] = book_arr.get(i).book_id;
            	data[i][1] = book_arr.get(i).book_name;
            	data[i][2] = book_arr.get(i).pub_name;
            	data[i][3] = book_arr.get(i).pub_date;
            	data[i][4] = book_arr.get(i).price;
            	data[i][5] = book_arr.get(i).stock;
            }
            
        }
                
        JButton btnBack = new JButton("Back");
        f.add(btnBack);
        btnBack.setBounds(10, 10, 150, 40);
        
        
        JLabel lSearchBy = new JLabel("Search by");
        f.add(lSearchBy);
        lSearchBy.setBounds(220, 10, 150, 40);
        
        String category[] = {"Book Name", "Publisher Name", "Published Date"};
        JComboBox jcSearchBy = new JComboBox(category);
        f.add(jcSearchBy);
        jcSearchBy.setBounds(280, 15, 100, 30);
        
        JLabel lsearchVal = new JLabel("Value");
        f.add(lsearchVal);
        lsearchVal.setBounds(400, 15, 150, 30);
        
        JTextField tfsearchVal = new JTextField();
        f.add(tfsearchVal);
        tfsearchVal.setBounds(440, 15, 150, 30);
        
        JButton btnSearch = new JButton("Search");
        f.add(btnSearch);
        btnSearch.setBounds(600, 15, 100, 30);
        
        JLabel lsortby = new JLabel("Sort by");
        f.add(lsortby);
        lsortby.setBounds(220, 50, 100, 30);
        
//        String category1[] = {"Book Name", "Publisher Name", "Stock"};
        JComboBox jcSortBy = new JComboBox(category);
        f.add(jcSortBy);
        jcSortBy.setBounds(280, 55, 100, 30);
        
        JLabel lSort = new JLabel("Order By");
        f.add(lSort);
        lSort.setBounds(400, 55, 100, 30);
        
        String sort_by[] = {"Ascending", "Descending"};
        JComboBox jcSort = new JComboBox(sort_by);
        f.add(jcSort);
        jcSort.setBounds(470, 55, 100, 30);
        
        JButton btnSort = new JButton("Sort");
        f.add(btnSort);
        btnSort.setBounds(585, 55, 100, 30);
        
        JButton btnUpdate = new JButton("Update");
        f.add(btnUpdate);
        btnUpdate.setBounds(755, 105, 90, 30);
        
        JButton btnDelete = new JButton("Delete");
        f.add(btnDelete);
        btnDelete.setBounds(755, 145, 90, 30);
        
        JButton btnSell = new JButton("Sell");
        f.add(btnSell);
        btnSell.setBounds(755, 185, 90, 30);
        
        JButton btnAll = new JButton("All Books");
        f.add(btnAll);
        btnAll.setBounds(755, 225, 90, 30);
        
        JButton btnSold = new JButton("Sold Books");
        f.add(btnSold);
        btnSold.setBounds(755, 265, 120, 30);
        
        JButton btnAvailable = new JButton("Available Books");
        f.add(btnAvailable);
        btnAvailable.setBounds(755, 305, 130, 30);
        
        JTable jtEmp = new JTable(data, column);
        JScrollPane sp = new JScrollPane(jtEmp);
        f.add(sp);
        sp.setBounds(50, 100, 700, 400);
        
        btnBack.addActionListener(e-> {
            new Main();
            f.dispose();
        });   
        
        btnAll.addActionListener(e ->{
        	new ViewBooks(null, false);
        	f.dispose();
        });
        
        
        btnSearch.addActionListener(e-> {
            String search_by = jcSearchBy.getSelectedItem().toString();
            int index = 1;
            switch(search_by) {
            case "Book Name":
                index = 1;
                break;
            case "Publisher Name":
                index = 2;
                break;
            case "Published Date":
                index = 3;
                break;
            }
            String search_value = tfsearchVal.getText();
            
            if (search_value.length()==0) {
                JOptionPane.showMessageDialog(sp, "Please enter the search value");
            } else {
            	
            	
            	
            	
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
                
                Object book_data[][] = new Object[book_arr.size()][column.length];
                
                for (int i=0; i<book_data.length; i++) {
                	book_data[i][0] = book_arr.get(i).book_id;
                	book_data[i][1] = book_arr.get(i).book_name;
                	book_data[i][2] = book_arr.get(i).pub_name;
                	book_data[i][3] = book_arr.get(i).pub_date;
                	book_data[i][4] = book_arr.get(i).price;
                	book_data[i][5] = book_arr.get(i).stock;
                }
                
                
                ArrayList<Object[]> searched = new ArrayList<Object[]>();
                
                search ls = new search();
                ls.searchString(book_data, searched, index, search_value);
                
                Object[][] searched_list = new Object[searched.size()][column.length];
                for(int i=0; i<searched.size(); i++) {
                    searched_list[i] = searched.get(i);
                }
                if (searched_list.length<=0) {
                    JOptionPane.showMessageDialog(sp, "No data found");
                } else {
            
                    new ViewBooks(searched_list, true);
                    f.dispose();
                    
                }             
            }      
        });
        
        
        
        btnSort.addActionListener(e -> {
            String order_by = jcSortBy.getSelectedItem().toString();
            int index=1;
            
            if (order_by.equals("Book Name")) {
                index = 1;
            } else if(order_by.equals("Publisher Name")) {
                index = 2;
            } else if (order_by.equals("Published Date")) {
                index = 3;
            }
            String order = jcSort.getSelectedItem().toString();
            Mergsort ms = new Mergsort();
            int n = data.length;
            ms.sort(data, 0, n-1, index, order);
            new ViewBooks(data, true);
            f.dispose();
        });
        
        
        
        
        btnUpdate.addActionListener(e -> {
            int row = jtEmp.getSelectedRow();
            if(row>=0) {          
                JLabel lbname, lpname, lpdate, lprice, lstock;
                JTextField tfbname, tfpname, tfpdate, tfprice, tfstock;
                                        
                lbname = new JLabel("Book Name");
                f.add(lbname);
                lbname.setBounds(820, 350, 300, 50);
                
                tfbname = new JTextField();
                f.add(tfbname);
                tfbname.setBounds(950, 360, 300, 30);
                
                lpname = new JLabel("Publisher Name");
                f.add(lpname);
                lpname.setBounds(820, 390, 300, 50);    
                
                tfpname = new JTextField();
                f.add(tfpname);
                tfpname.setBounds(950, 400, 300, 30);
                
                lpdate = new JLabel("Publish Date");
                f.add(lpdate);
                lpdate.setBounds(820, 430, 300, 50);    
                
                tfpdate = new JTextField();
                f.add(tfpdate);
                tfpdate.setBounds(950, 440, 300, 30);
                
                lprice = new JLabel("Price");
                f.add(lprice);
                lprice.setBounds(820, 480, 300, 50);    
                
                tfprice = new JTextField();
                f.add(tfprice);
                tfprice.setBounds(950, 490, 300, 30);
                
                lstock = new JLabel("Stock");
                f.add(lstock);
                lstock.setBounds(820, 520, 300, 50);    
                
                tfstock = new JTextField();
                f.add(tfstock);
                tfstock.setBounds(950, 530, 300, 30);
    
                JButton btnChange = new JButton("Make Change");
                f.add(btnChange);
                btnChange.setBounds(950, 570, 300, 30);
    
                JButton btnCancel = new JButton("Cancel");
                f.add(btnCancel);
                btnCancel.setBounds(950, 610, 300, 30);
                
                TableModel model = jtEmp.getModel();
                    int id = (int) model.getValueAt(row, 0);
                    tfbname.setText((String) model.getValueAt(row, 1));
                    tfpname.setText((String) model.getValueAt(row, 2));
                    tfpdate.setText((String) model.getValueAt(row, 3));
                    int price = (int) model.getValueAt(row, 4);
                    int stock = (int) model.getValueAt(row, 5);
                    
                    tfprice.setText(price+"");
                    tfstock.setText(stock+"");
                    // update action
                    btnChange.addActionListener(e3->{
                        String book_name = tfbname.getText();
                        String pub_name = tfpname.getText();
                        String pub_date = tfpdate.getText();
                        String book_price = tfprice.getText();
                        String book_stock = tfstock.getText();
                        
                        String uquery = "update books set name='"+book_name+"', publisher_name='"+pub_name+"', publish_date='"+ pub_date + "', price="+book_price+", stock=" + book_stock +"  where id="+id;
                        
                        try {
                            int result = db.connection().executeUpdate(uquery);
                            if(result > 0) {
                                JOptionPane.showMessageDialog(sp, "Book Updated");
                                new ViewBooks(null, false);
                                f.dispose();
                            }
                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    });
                    //cancel action
                    btnCancel.addActionListener(e2->{
                        new ViewBooks(null, false);
                        f.dispose();
                    });
                }else {
                    JOptionPane.showMessageDialog(sp, "Select Row for Update");
                }
            
        	});
        
        
			btnDelete.addActionListener(e -> {
            
            int row = jtEmp.getSelectedRow();
            
            if (row>=0) {
                
                TableModel model = jtEmp.getModel();
                int book_id = (int) model.getValueAt(row, 0);
                String dquery = "delete from books where id="+book_id;
                
                try {
                    int result = db.connection().executeUpdate(dquery);
                    if (result>0) {
                        JOptionPane.showMessageDialog(sp, "Book Deleted");
                        new ViewBooks(null, false);
                        f.dispose();
                    }
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
            } else {
                JOptionPane.showMessageDialog(sp, "Select Row");
            }
        });
			
			btnSell.addActionListener(e -> {
	            int row = jtEmp.getSelectedRow();
	            if(row>=0) {          
	                JLabel lquantity, ldate;
	                JTextField tfquantity, tfdate;
	                                        
	                lquantity = new JLabel("Quantity");
	                f.add(lquantity);
	                lquantity.setBounds(50, 510, 150, 50);
	                
	                tfquantity = new JTextField();
	                f.add(tfquantity);
	                tfquantity.setBounds(120, 520, 200, 30);
	                
	                ldate = new JLabel("Date");
	                f.add(ldate);
	                ldate.setBounds(50, 540, 150, 50); 
	                
	                tfdate = new JTextField();
	                f.add(tfdate);
	                tfdate.setBounds(120, 560, 200, 30);
	                
	                JButton btnSubmit = new JButton("Submit");
	                f.add(btnSubmit);
	                btnSubmit.setBounds(120, 595, 200, 30);
	                
	                JButton btnCancel = new JButton("Cancel");
	                f.add(btnCancel);
	                btnCancel.setBounds(120, 630, 200, 30);
	                
	                TableModel model = jtEmp.getModel();
	                int book_id = (int) model.getValueAt(row, 0);
	                int stock = (int) model.getValueAt(row, 5);
	                
	                btnSubmit.addActionListener(e1 -> {
	                    String qty = tfquantity.getText();
	                    String date = tfdate.getText();
	                    
	                    if(qty.length()==0 || date.length()==0) {
	                        JOptionPane.showMessageDialog(sp, "All fields are required");
	                    } else if(Integer.parseInt(qty)<=0) {
	                        JOptionPane.showMessageDialog(sp, "Quantity must be greater than 0");
	                    }
	                    else if(Integer.parseInt(qty)>stock) {
	                        JOptionPane.showMessageDialog(sp, "Quantity not available");
	        
	                    } else {
	                        int update_stock = stock - Integer.parseInt(qty);
	                        
	                        String sold_query = "insert into sold(quantity, sold_date, book_id) values("+qty+",'"+date+"',"+book_id+")";                
	                        String update_query = "update books set stock="+update_stock+" where id="+book_id;
	                        
	                        try {
	                            int res_insert = db.connection().executeUpdate(sold_query);
	                            int res_upd = db.connection().executeUpdate(update_query);
	                            if(res_insert>0) {
	                                JOptionPane.showMessageDialog(sp, "Book Sold");
	                                new ViewBooks(null, false);
	                                f.dispose();
	                            }
	                        } catch (SQLException e2) {
	                            // TODO Auto-generated catch block
	                            e2.printStackTrace();
	                        }
	                        
	                    }
	                                    
	                    
	                    
	                });
	                
	                btnCancel.addActionListener(e1-> {
	                    new ViewBooks(null, false);
	                    f.dispose();
	                });
	        
	            }else {
	                JOptionPane.showMessageDialog(sp, "Select a row to sell");
	            }
	            
	            
	        });
			btnAvailable.addActionListener(e-> {
	            String query = "select * from books where stock>0";
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
	            
	            Object available_books[][] = new Object[book_arr.size()][column.length];
	            
	            for (int i=0; i<available_books.length; i++) {
	                available_books[i][0] = book_arr.get(i).book_id;
	                available_books[i][1] = book_arr.get(i).book_name;
	                available_books[i][2] = book_arr.get(i).pub_name;
	                available_books[i][3] = book_arr.get(i).pub_date;
	                available_books[i][4] = book_arr.get(i).price;
	                available_books[i][5] = book_arr.get(i).stock;
	            }
	            new ViewBooks(available_books, true);
	            f.dispose();
	        });
        
			
	        btnSold.addActionListener(e-> {
	            new Soldbook();
	            f.dispose();
	        });
        
        
        
        f.setLayout(null);
        f.setVisible(true); 
        f.setSize(1300, 800);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        
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
    
  public static void main(String[] args) {
      ViewBooks vb = new ViewBooks(null, false);
  }
}