package model.player;

import org.junit.runner.RunWith;  
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;  

@RunWith(Suite.class)
@SuiteClasses({
	AutomatedPlayerStrategyTestCase.class,
	AutomatedPlayerTestCase.class, 
	BackDiagonalStrategyTestCase.class,
	BlockOpponentStrategyTestCase.class,
	ForwardDiagonalStrategyTestCase.class,
	HorizontalStrategyTestCase.class,
	HumanPlayerTestCase.class,
	RandomStrategyTestCase.class,
	VerticalStrategyTestCase.class })
public class AllTests {

}
