package library;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SortTesting {
	@Test
    void test() {
        Mergsort ms = new Mergsort();
        Object[][] test = {{1,"a"}, {2,"x"}, {3,"b"}, {4,"d"}};
        ms.sort(test, 0, test.length-1, 1, "Ascending");
        
        Object[][] expected = {{1, "a"}, {3, "b"}, {4, "d"}, {2, "x"}};
        assertEquals(expected[3][0], test[3][0]);
    }

}




