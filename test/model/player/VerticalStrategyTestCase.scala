package model.player

import org.junit.Assert._
import org.junit.Test
import model.Board

class VerticalStrategyTestCase {

    @Test def testInit {
	  val strategy = AutomatedPlayerStrategy.verticalStrategy(5, 9)
	  assertNotNull(strategy)
	  assertEquals(5, strategy.playerId)
	  assertEquals(9, strategy.origin)
	  assertFalse(strategy.isBlockOpponentStrategy)
	  assertFalse(strategy.isRandomStrategy)
	  assertEquals("VerticalStrategy", strategy.toString)
    }

    @Test def testEvaluate1 {
      val board = Board.initWith("0000020000200002000000000")
	  val strategy = AutomatedPlayerStrategy.verticalStrategy(1, 6)
	  strategy.evaluate(board)
	  
	  assertEquals(3, strategy.steps)
	  assertTrue(strategy.isValid)
	  assertEquals(16, strategy.targetCell)
    }

    @Test def testEvaluate2 {
      val board = Board.initWith("0000001000000000000000000")
	  val strategy = AutomatedPlayerStrategy.verticalStrategy(1, 6)
	  strategy.evaluate(board)
	  
	  assertEquals(2, strategy.steps)
	  assertTrue(strategy.isValid)
	  assertEquals(16, strategy.targetCell)
    }

    @Test def testEvaluate3 {
      val board = Board.initWith("0000002000000000000000000")
	  val strategy = AutomatedPlayerStrategy.verticalStrategy(1, 6)
	  strategy.evaluate(board)
	  
	  assertEquals(0, strategy.steps)
	  assertTrue(!strategy.isValid)
	  assertEquals(0, strategy.targetCell)
    }

    @Test def testEvaluate5 {
      val board = Board.initWith("0000001000000000100000000")
	  val strategy = AutomatedPlayerStrategy.verticalStrategy(1, 6)
	  strategy.evaluate(board)
	  
	  assertEquals(1, strategy.steps)
	  assertTrue(strategy.isValid)
	  assertEquals(11, strategy.targetCell)
    }

    @Test def testEvaluate6 {
      val board = Board.initWith("0000000000010000100000000")
	  val strategy = AutomatedPlayerStrategy.verticalStrategy(1, 6)
	  strategy.evaluate(board)
	  
	  assertEquals(1, strategy.steps)
	  assertTrue(strategy.isValid)
	  assertEquals(6, strategy.targetCell)
    }

    @Test def testEvaluate7 {
      val board = Board.initWith("0000001000010000100000000")
	  val strategy = AutomatedPlayerStrategy.verticalStrategy(1, 6)
	  strategy.evaluate(board)
	  
	  assertEquals(0, strategy.steps)
	  assertTrue(!strategy.isValid)
    }

}