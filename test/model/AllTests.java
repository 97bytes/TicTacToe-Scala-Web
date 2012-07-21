package model;

import org.junit.runner.RunWith;  
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Run all test cases
 *
 */
@RunWith(Suite.class)
@SuiteClasses({
	BoardTestCase.class,
	SessionDataTestCase.class, 
	model.player.AllTests.class})
public class AllTests {

}
