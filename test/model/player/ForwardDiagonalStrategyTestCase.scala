package model.player

import org.junit.Assert._
import org.junit.Test
import model.Board

class ForwardDiagonalStrategyTestCase {

    @Test def testInit {
	  val strategy = AutomatedPlayerStrategy.forwardDiagonalStrategy(5, 9)
	  assertNotNull(strategy)
	  assertEquals(5, strategy.playerId)
	  assertEquals(9, strategy.origin)
	  assertFalse(strategy.isBlockOpponentStrategy)
	  assertFalse(strategy.isRandomStrategy)
	  assertEquals("ForwardDiagonalStrategy", strategy.toString)
    }

    @Test def testEvaluate1 {
      val board = Board.initWith("0000022000000000000000000")
	  val strategy = AutomatedPlayerStrategy.forwardDiagonalStrategy(1, 7)
	  strategy.evaluate(board)
	  
	  assertEquals(3, strategy.steps)
	  assertTrue(strategy.isValid)
	  assertEquals(15, strategy.targetCell)
    }

    @Test def testEvaluate2 {
      val board = Board.initWith("0000000100000000000000000")
	  val strategy = AutomatedPlayerStrategy.forwardDiagonalStrategy(1, 7)
	  strategy.evaluate(board)
	  
	  assertEquals(2, strategy.steps)
	  assertTrue(strategy.isValid)
	  assertEquals(15, strategy.targetCell)
    }

    @Test def testEvaluate3 {
      val board = Board.initWith("0000000200000000000000000")
	  val strategy = AutomatedPlayerStrategy.forwardDiagonalStrategy(1, 7)
	  strategy.evaluate(board)
	  
	  assertEquals(0, strategy.steps)
	  assertTrue(!strategy.isValid)
    }

    @Test def testEvaluate4 {
      val board = Board.initWith("0000000100000001000000000")
	  val strategy = AutomatedPlayerStrategy.forwardDiagonalStrategy(1, 7)
	  strategy.evaluate(board)
	  
	  assertEquals(1, strategy.steps)
	  assertTrue(strategy.isValid)
	  assertEquals(11, strategy.targetCell)
    }

    @Test def testEvaluate5 {
      val board = Board.initWith("0000000100010001000000000")
	  val strategy = AutomatedPlayerStrategy.forwardDiagonalStrategy(1, 7)
	  strategy.evaluate(board)
	  
	  assertEquals(0, strategy.steps)
	  assertTrue(!strategy.isValid)
    }


}