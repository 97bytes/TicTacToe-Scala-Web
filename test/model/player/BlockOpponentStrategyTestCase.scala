package model.player

import org.junit.Assert._
import org.junit.Test
import model.Board

class BlockOpponentStrategyTestCase {

    @Test def testInit {
	  val strategy = AutomatedPlayerStrategy.blockOpponentStrategy(5)
	  assertNotNull(strategy)
	  assertEquals(5, strategy.playerId)
	  assertEquals(0, strategy.origin)
	  assertTrue(strategy.isBlockOpponentStrategy)
	  assertFalse(strategy.isRandomStrategy)
	  assertEquals("BlockOpponentStrategy", strategy.toString)
    }

    @Test def testEvaluate1 {
      val board = Board.initWith("0000000000000000")

      var strategy = AutomatedPlayerStrategy.blockOpponentStrategy(1)
	  strategy.evaluate(board)
	  assertEquals(0, strategy.steps)
	  assertTrue(!strategy.isValid)

	  strategy = AutomatedPlayerStrategy.blockOpponentStrategy(2)
	  strategy.evaluate(board)
	  assertEquals(0, strategy.steps)
	  assertTrue(!strategy.isValid)
    }
  
    @Test def testEvaluate2 {
      val board = Board.initWith("2200000000000000")
	  var strategy = AutomatedPlayerStrategy.blockOpponentStrategy(1)

	  // Player 1
	  strategy.evaluate(board)
	  assertEquals(1, strategy.steps)
	  assertTrue(strategy.isValid)
	  assertEquals(2, strategy.targetCell)

	  // Player 2
	  strategy = AutomatedPlayerStrategy.blockOpponentStrategy(2)
	  strategy.evaluate(board)
	  assertEquals(0, strategy.steps)
	  assertTrue(!strategy.isValid)
    }
  
    @Test def testEvaluate3 {
      val board = Board.initWith("1100000000000000")
	  var strategy = AutomatedPlayerStrategy.blockOpponentStrategy(1)

	  // Player 1
	  strategy.evaluate(board)
	  assertEquals(0, strategy.steps)
	  assertTrue(!strategy.isValid)

	  // Player 2
	  strategy = AutomatedPlayerStrategy.blockOpponentStrategy(2)
	  strategy.evaluate(board)
	  assertEquals(1, strategy.steps)
	  assertTrue(strategy.isValid)
	  assertEquals(2, strategy.targetCell)
    }
  
}