package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DbConnect {
	public Statement connection() {
	Connection con;
	Statement stmt = null;
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","sanjaya");
			stmt =   (Statement) con.createStatement();
			
			
		} catch (SQLException  e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		return stmt;
	}


}
