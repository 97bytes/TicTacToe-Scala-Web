package model.player

import org.junit.Assert._
import org.junit.Test
import model.Board

class HorizontalStrategyTestCase {

    @Test def testInit {
	  val strategy = AutomatedPlayerStrategy.horizontalStrategy(5, 9)
	  assertNotNull(strategy)
	  assertEquals(5, strategy.playerId)
	  assertEquals(9, strategy.origin)
	  assertFalse(strategy.isBlockOpponentStrategy)
	  assertFalse(strategy.isRandomStrategy)
	  assertEquals("HorizontalStrategy", strategy.toString)
    }

    @Test def testEvaluate1 {
      val board = Board.initWith("0000000000000000000000000")
	  val strategy = AutomatedPlayerStrategy.horizontalStrategy(1, 6)
	  strategy.evaluate(board)
	  
	  assertEquals(3, strategy.steps)
	  assertTrue(strategy.isValid)
	  assertEquals(8, strategy.targetCell)
    }

    @Test def testEvaluate2 {
      val board = Board.initWith("0000001000000000000000000")
	  val strategy = AutomatedPlayerStrategy.horizontalStrategy(1, 6)
	  strategy.evaluate(board)
	  
	  assertEquals(2, strategy.steps)
	  assertTrue(strategy.isValid)
	  assertEquals(8, strategy.targetCell)
    }

    @Test def testEvaluate3 {
      val board = Board.initWith("0000002000000000000000000")
	  val strategy = AutomatedPlayerStrategy.horizontalStrategy(1, 6)
	  strategy.evaluate(board)
	  
	  assertEquals(0, strategy.steps)
	  assertTrue(!strategy.isValid)
    }

    @Test def testEvaluate4 {
      val board = Board.initWith("00000010100000000000000000")
	  val strategy = AutomatedPlayerStrategy.horizontalStrategy(1, 6)
	  strategy.evaluate(board)
	  
	  assertEquals(1, strategy.steps)
	  assertTrue(strategy.isValid)
	  assertEquals(7, strategy.targetCell)
    }

    @Test def testEvaluate5 {
      val board = Board.initWith("0000001110000000000000000")
	  val strategy = AutomatedPlayerStrategy.horizontalStrategy(1, 6)
	  strategy.evaluate(board)
	  
	  assertEquals(0, strategy.steps)
	  assertTrue(!strategy.isValid)
    }

    
  
}