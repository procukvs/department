package department;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestDataBase {
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		DataBase db = new DataBase();
        assertNotNull("Not set Driver!", db);
        assertEquals("Not set Driver!", "",db.setDriver);
        //assertTrue("Not correct Database Name!",db.connectionDb("Department"));
		//fail("Not yet implemented");
	}
	@Test
	public void test1() {
		DataBase db = new DataBase();
       // assertNotNull("Not set Driver!", db);
       // assertEquals("Not set Driver!", "",db.setDriver);
        assertTrue("Not correct Database Name!",db.connectionDb("Department"));
		//fail("Not yet implemented");
	}
}
