package model.player

import org.junit.Assert._
import org.junit.Test
import model.Board

class AutomatedPlayerTestCase {

    @Test def testInit() {
	  val player = Player.automatedPlayerWith(2, null)
	  assertNotNull(player)
	  assertEquals(2, player.playerId)
	  assertNull(player.gameDirector)
	  assertTrue(player.isAutomated)
	  assertFalse(player.isHuman)
    }

    @Test def testStrategies1() {
      val player = Player.automatedPlayerWith(2, null)
      val board = Board.initWith("000000000")
      
      var result = player.strategies(board, 0)
	  assertEquals(3, result.size)
	  
	  result = player.strategies(board, 1)
	  assertEquals(2, result.size)
	  
	  result = player.strategies(board, 4)
	  assertEquals(4, result.size)
    }
    
    @Test def testStrategies2() {
      val player = Player.automatedPlayerWith(2, null)
      val board = Board.initWith("0000000000000000000000000")
      
      var result = player.strategies(board, 12)
	  assertEquals(12, result.size)
    }
    
    @Test def testNextMove1() {
      val player = Player.automatedPlayerWith(1, null)
      
      var board = Board.initWith("222222220")
      var result = player.nextMove(board)
	  assertEquals(8, result)
	  
	  board = Board.initWith("220100100")
      result = player.nextMove(board)
	  assertEquals(2, result)
	  
	  board = Board.initWith("220110000")
      result = player.nextMove(board)
	  assertEquals(5, result)

	  board = Board.initWith("220110100")
      result = player.nextMove(board)
	  assertEquals(2, result)
    }
    
    @Test def testNextMove2() {
      val player = Player.automatedPlayerWith(1, null)
      
      var board = Board.initWith("1122001000000000000000000")
      var result = player.nextMove(board)
	  assertEquals(11, result)
	  
	  board = Board.initWith("1022001000000000000000000")
      result = player.nextMove(board)
	  assertEquals(12, result)
	  
	  board = Board.initWith("1122000000000000001000010")
      result = player.nextMove(board)
	  assertEquals(13, result)
	  
	  board = Board.initWith("1122000000000000002000020")
      result = player.nextMove(board)
	  assertEquals(4, result)

	  board = Board.initWith("1121000000000000002000020")
      result = player.nextMove(board)
	  assertEquals(13, result)
    }
    
    @Test def testRandomOpening1() {
      val player = Player.automatedPlayerWith(1, null)
      var board = Board.initWith(5)
      val cell = player.randomOpening(board)
      assertTrue(cell >= 0 && cell < 5 * 5)
    }

    @Test def testRandomOpening2() {
      val player = Player.automatedPlayerWith(1, null)
      var board = Board.initWith(3)
      val cell = player.randomOpening(board)
      assertTrue(cell >= 0 && cell < 3 * 3)
    }
    
}