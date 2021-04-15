package library;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.Test;

public class SearchTesting {
	
	@Test
	void test() {
		search ls = new search();
		Object[][] test = {{101,"Apple"}, {3,"Ball"}, {7,"cat"}, {6,"Dog"}};
		ArrayList<Object[]> searched = new ArrayList<Object[]>();
		ls.searchString(test, searched, 1, "Dog");
		ArrayList<Object[]> expected = new ArrayList<Object[]>();
		Object[]arr = {6, "Dog"};
		expected.add(arr);
		
		assertEquals(expected.get(0)[0], searched.get(0)[0]);
		
	}

}












