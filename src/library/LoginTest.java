package library;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LoginTest {
	@Test
    void test() {
        Home ltest = new Home();
        boolean actualOutput = ltest.userLogin("anil", "12345678");
        assertEquals(true, actualOutput);
    }

}




