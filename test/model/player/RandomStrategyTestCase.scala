package model.player

import org.junit.Assert._
import org.junit.Test
import model.Board

class RandomStrategyTestCase {

    @Test def testInit {
	  val strategy = AutomatedPlayerStrategy.randomStrategy(5)
	  assertNotNull(strategy)
	  assertEquals(5, strategy.playerId)
	  assertEquals(0, strategy.origin)
	  assertFalse(strategy.isBlockOpponentStrategy)
	  assertTrue(strategy.isRandomStrategy)
	  assertEquals("RandomStrategy", strategy.toString)
    }

    @Test def testEvaluate1 {
      val board = Board.initWith("1111222211112222")
	  val strategy = AutomatedPlayerStrategy.randomStrategy(1)
	  strategy.evaluate(board)
	  
	  assertEquals(0, strategy.steps)
	  assertTrue(!strategy.isValid)
    }
    
    @Test def testEvaluate2 {
      val board = Board.initWith("1111222211112220")
	  val strategy = AutomatedPlayerStrategy.randomStrategy(1)
	  strategy.evaluate(board)
	  
	  assertEquals(1, strategy.steps)
	  assertTrue(strategy.isValid)
	  assertEquals(15, strategy.targetCell)
    }
    
    @Test def testEvaluate3 {
      val board = Board.initWith("0011200011112220")
	  val strategy = AutomatedPlayerStrategy.randomStrategy(1)
	  strategy.evaluate(board)
	  
	  assertEquals(1, strategy.steps)
	  assertTrue(strategy.isValid)
	  assertTrue(List(0, 1, 5, 6, 7, 15).contains(strategy.targetCell))
    }
    
}