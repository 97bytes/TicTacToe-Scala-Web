package model.player

import org.junit.Assert._
import org.junit.Test
import model.Board

class BackDiagonalStrategyTestCase {

    @Test def testInit {
	  val strategy = AutomatedPlayerStrategy.backDiagonalStrategy(5, 9)
	  assertNotNull(strategy)
	  assertEquals(5, strategy.playerId)
	  assertEquals(9, strategy.origin)
	  assertFalse(strategy.isBlockOpponentStrategy)
	  assertFalse(strategy.isRandomStrategy)
	  assertEquals("BackDiagonalStrategy", strategy.toString)
    }

    @Test def testEvaluate1 {
      val board = Board.initWith("0000022000000000000000000")
	  val strategy = AutomatedPlayerStrategy.backDiagonalStrategy(1, 7)
	  strategy.evaluate(board)
	  
	  assertEquals(3, strategy.steps)
	  assertTrue(strategy.isValid)
	  assertEquals(19, strategy.targetCell)
    }
  
    @Test def testEvaluate2 {
      val board = Board.initWith("0000000100000000000000000")
	  val strategy = AutomatedPlayerStrategy.backDiagonalStrategy(1, 7)
	  strategy.evaluate(board)
	  
	  assertEquals(2, strategy.steps)
	  assertTrue(strategy.isValid)
	  assertEquals(19, strategy.targetCell)
    }
  
    @Test def testEvaluate3 {
      val board = Board.initWith("0000000200000000000000000")
	  val strategy = AutomatedPlayerStrategy.backDiagonalStrategy(1, 7)
	  strategy.evaluate(board)
	  
	  assertEquals(0, strategy.steps)
	  assertTrue(!strategy.isValid)
    }

    @Test def testEvaluate4 {
      val board = Board.initWith("0000000100000000000100000")
	  val strategy = AutomatedPlayerStrategy.backDiagonalStrategy(1, 7)
	  strategy.evaluate(board)
	  
	  assertEquals(1, strategy.steps)
	  assertTrue(strategy.isValid)
	  assertEquals(13, strategy.targetCell)
    }
  
    @Test def testEvaluate5 {
      val board = Board.initWith("0000000100000100000100000")
	  val strategy = AutomatedPlayerStrategy.backDiagonalStrategy(1, 7)
	  strategy.evaluate(board)
	  
	  assertEquals(0, strategy.steps)
	  assertTrue(!strategy.isValid)
    }
  
    
}