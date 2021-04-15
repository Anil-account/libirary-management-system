package library;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Statement;

import org.junit.jupiter.api.Test;

class ConnectionTesting {

	@Test
	void test() {
		DbConnect db = new DbConnect();
		Statement actualOutput = db.connection();
		
		assertEquals(null, actualOutput);
	}
	

}
