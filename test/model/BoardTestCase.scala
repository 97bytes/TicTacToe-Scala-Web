package model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class BoardTestCase {
  
  @Test def testConstructor() {
    val board = new Board()
    assertNotNull(board)
    assertEquals(List(0, 0, 0, 0, 0, 0, 0, 0, 0), board.configuration)
  }

  @Test def testInitWithSize3x3() {
    val board = Board.initWith(3)
    assertNotNull(board)
    assertEquals(List(0, 0, 0, 0, 0, 0, 0, 0, 0), board.configuration)
    assertEquals(3, board.size)
  }

  @Test def testInitWithSize4x4() {
    val board = Board.initWith(4)
    assertNotNull(board)
    val expected = List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    assertEquals(expected, board.configuration)
    assertEquals(4, board.size)
  }

  @Test def testInitWithSize5x5() {
    val board = Board.initWith(5)
    assertNotNull(board)
    assertEquals(List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), board.configuration)
    assertEquals(5, board.size)
  }

  @Test def testInitWithString() {
    val board = Board.initWith("012345678")
    assertNotNull(board)
    assertEquals(List(0, 1, 2, 3, 4, 5, 6, 7, 8), board.configuration)
    assertEquals(3, board.size)
  }

  @Test def testSetValue1() {
    var board = Board.initWith(List(0, 1, 2, 3, 4, 5, 6, 7, 8))
    board = board.setValue(2, 11)
    assertNotNull(board)
    val expected = List(0, 1, 11, 3, 4, 5, 6, 7, 8)
    assertEquals(expected, board.configuration)
  }

  @Test def testSetValue2() {
    var board = Board.initWith(List(0, 1, 2, 3, 4, 5, 6, 7, 8))
    board = board.setValue(0, 11)
    assertNotNull(board)
    val expected = List(11, 1, 2, 3, 4, 5, 6, 7, 8)
    assertEquals(expected, board.configuration)
  }

  @Test def testSetValue3() {
    var board = Board.initWith(List(0, 1, 2, 3, 4, 5, 6, 7, 8))
    board = board.setValue(8, 11)
    assertNotNull(board)
    val expected = List(0, 1, 2, 3, 4, 5, 6, 7, 11)
    assertEquals(expected, board.configuration)
  }

  @Test def testIsEmptyFalse() {
    var board = Board.initWith(List(0, 1, 2, 3, 4, 5, 6, 7, 8))
    assertTrue(!board.isEmpty)
  }

  @Test def testIsEmptyTrue() {
    var board = Board.initWith(List(0, 0, 0, 0, 0))
    assertTrue(board.isEmpty)
  }

  @Test def testIsFullFalse() {
    var board = Board.initWith(List(1, 1, 2, 2, 2, 0, 1, 2, 2))
    assertTrue(!board.isFull)
  }

  @Test def testIsFullTrue() {
    var board = Board.initWith(List(1, 1, 2, 2, 2, 1, 1, 2, 2))
    assertTrue(board.isFull)
  }

  @Test def testSetPlayer1Size3x3() {
    var board = Board.initWith(3)
    var newBoard = board.setPlayer1(0)
    
    // Verificamos que el tablero original es inmutable
    assertEquals(List(0, 0, 0, 0, 0, 0, 0, 0, 0), board.configuration)
    
    // Comprobamos los cambios en el tablero nuevo
    assertEquals(List(1, 0, 0, 0, 0, 0, 0, 0, 0), newBoard.configuration)

    newBoard = newBoard.setPlayer1(3)
    assertEquals(List(1, 0, 0, 1, 0, 0, 0, 0, 0), newBoard.configuration)

    newBoard = newBoard.setPlayer1(8)
    assertEquals(List(1, 0, 0, 1, 0, 0, 0, 0, 1), newBoard.configuration)
  }

  @Test def testSetPlayer1Size5x5() {
    var board = Board.initWith(5)
    var newBoard = board.setPlayer1(0)
    assertEquals(List(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), newBoard.configuration)

    newBoard = newBoard.setPlayer1(9)
    assertEquals(List(1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), newBoard.configuration)

    newBoard = newBoard.setPlayer1(24)
    assertEquals(List(1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1), newBoard.configuration)
  }

  @Test def testIsPlayer1Size3x3() {
    var board = Board.initWith(List(1, 0, 2, 0, 0, 1, 0, 2, 1))
    assertTrue(board.isPlayer1(0))
    assertTrue(!board.isPlayer1(1))
    assertTrue(!board.isPlayer1(2))
    assertTrue(board.isPlayer1(5))
    assertTrue(board.isPlayer1(8))
  }

  @Test def testIsPlayer1Size5x5() {
    var board = Board.initWith(List(1, 2, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1))
    assertTrue(board.isPlayer1(0))
    assertTrue(!board.isPlayer1(1))
    assertTrue(!board.isPlayer1(2))
    assertTrue(board.isPlayer1(3))
    assertTrue(board.isPlayer1(24))
  }

  @Test def testSetPlayer2Size3x3() {
    var board = Board.initWith(3)
    var newBoard = board.setPlayer2(0)

    // Verificamos que el tablero original es inmutable
    assertEquals(List(0, 0, 0, 0, 0, 0, 0, 0, 0), board.configuration)
    
    // Comprobamos los cambios en el tablero nuevo
    assertEquals(List(2, 0, 0, 0, 0, 0, 0, 0, 0), newBoard.configuration)

    newBoard = newBoard.setPlayer2(3)
    assertEquals(List(2, 0, 0, 2, 0, 0, 0, 0, 0), newBoard.configuration)

    newBoard = newBoard.setPlayer2(8)
    assertEquals(List(2, 0, 0, 2, 0, 0, 0, 0, 2), newBoard.configuration)
  }

  @Test def testSetPlayer2Size5x5() {
    var board = Board.initWith(5)
    var newBoard = board.setPlayer2(0)
    assertEquals(List(2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), newBoard.configuration)

    newBoard = newBoard.setPlayer2(9)
    assertEquals(List(2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), newBoard.configuration)

    newBoard = newBoard.setPlayer2(24)
    assertEquals(List(2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2), newBoard.configuration)
  }

  @Test def testIsPlayer2Size3x3() {
    var board = Board.initWith(List(2, 0, 1, 0, 0, 2, 0, 1, 2))
    assertTrue(board.isPlayer2(0))
    assertTrue(!board.isPlayer2(1))
    assertTrue(!board.isPlayer2(2))
    assertTrue(board.isPlayer2(5))
    assertTrue(board.isPlayer2(8))
  }

  @Test def testIsPlayer2Size5x5() {
    var board = Board.initWith(List(2, 1, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2))
    assertTrue(board.isPlayer2(0))
    assertTrue(!board.isPlayer2(1))
    assertTrue(!board.isPlayer2(2))
    assertTrue(board.isPlayer2(3))
    assertTrue(board.isPlayer2(24))
  }
  
  @Test def testCheckVerticalLines() {
    var board = Board.initWith(3)
    var cells = board.checkVerticalLines(1)
    assertTrue(cells.isEmpty)
    
    board = Board.initWith("100100100")
    cells = board.checkVerticalLines(2)
    assertEquals(0, cells.size)
    
    cells = board.checkVerticalLines(1)
    assertEquals(3, cells.size)
    var expected = Set(0, 3, 6)
    assertEquals(expected, cells.intersect(expected))
        
    board = Board.initWith("0200022002200020")
    cells = board.checkVerticalLines(1)
    assertEquals(0, cells.size)
    
    cells = board.checkVerticalLines(2)
    assertEquals(6, cells.size)
    expected = Set(1, 5, 9, 6, 10, 14)
    assertEquals(expected, cells.intersect(expected))
  }

  @Test def testCheckHorizontalLines() {
    var board = Board.initWith(3)
    var cells = board.checkHorizontalLines(1)
    assertTrue(cells.isEmpty)
    
    board = Board.initWith("000000111")
    cells = board.checkVerticalLines(2)
    assertEquals(0, cells.size)
    
    cells = board.checkHorizontalLines(1)
    assertEquals(3, cells.size)
    var expected = Set(6, 7, 8)
    assertEquals(expected, cells.intersect(expected))
        
    board = Board.initWith("0222000022200000")
    cells = board.checkHorizontalLines(1)
    assertEquals(0, cells.size)
    
    cells = board.checkHorizontalLines(2)
    assertEquals(6, cells.size)
    expected = Set(1, 2, 3, 8, 9, 10)
    assertEquals(expected, cells.intersect(expected))
  }

  /**
   * X--
   * -X-
   * --X 
   */
  @Test def testCheckBackDiagonalLines() {
    var board = Board.initWith(3)
    var cells = board.checkBackDiagonalLines(1)
    assertTrue(cells.isEmpty)
    
    board = Board.initWith("100010001")
    cells = board.checkBackDiagonalLines(2)
    assertEquals(0, cells.size)
    
    cells = board.checkBackDiagonalLines(1)
    assertEquals(3, cells.size)
    var expected = Set(0, 4, 8)
    assertEquals(expected, cells.intersect(expected))
        
    board = Board.initWith("0000002020002020002000002")
    cells = board.checkBackDiagonalLines(1)
    assertEquals(0, cells.size)
    
    cells = board.checkBackDiagonalLines(2)
    assertEquals(4, cells.size)
    expected = Set(6, 12, 18, 24)
    assertEquals(expected, cells.intersect(expected))
  }

  /**
   * --X
   * -X-
   * X-- 
   */
  @Test def testCheckForwardDiagonalLines() {
    var board = Board.initWith(3)
    var cells = board.checkForwardDiagonalLines(1)
    assertTrue(cells.isEmpty)
    
    board = Board.initWith("001010100")
    cells = board.checkForwardDiagonalLines(2)
    assertEquals(0, cells.size)
    
    cells = board.checkForwardDiagonalLines(1)
    assertEquals(3, cells.size)
    var expected = Set(2, 4, 6)
    assertEquals(expected, cells.intersect(expected))
        
    board = Board.initWith("0002000200022000200020000")
    cells = board.checkForwardDiagonalLines(1)
    assertEquals(0, cells.size)
    
    cells = board.checkForwardDiagonalLines(2)
    assertEquals(6, cells.size)
    expected = Set(3, 7, 11, 12, 16, 20)
    assertEquals(expected, cells.intersect(expected))
  }

    @Test def testPlayer1CellsFormingLines() {
    var board = Board.initWith(3)
    var cells = board.player1CellsFormingLines
    assertTrue(cells.isEmpty)
    
    board = Board.initWith("111000111")
    cells = board.player1CellsFormingLines
    assertEquals(6, cells.size)
    var expected = Set(0, 1, 2, 6, 7, 8)
    assertEquals(expected, cells.intersect(expected))
        
    board = Board.initWith("1000111010101010011100000")
    cells = board.player1CellsFormingLines
    assertEquals(10, cells.size)
    expected = Set(0, 4, 5, 6, 8, 10, 12, 17, 18, 19)
    assertEquals(expected, cells.intersect(expected))
  }

    @Test def testPlayer2CellsFormingLines() {
    var board = Board.initWith(3)
    var cells = board.player2CellsFormingLines
    assertTrue(cells.isEmpty)
    
    board = Board.initWith("222000222")
    cells = board.player2CellsFormingLines
    assertEquals(6, cells.size)
    var expected = Set(0, 1, 2, 6, 7, 8)
    assertEquals(expected, cells.intersect(expected))
        
    board = Board.initWith("2000222020202020022200000")
    cells = board.player2CellsFormingLines
    assertEquals(10, cells.size)
    expected = Set(0, 4, 5, 6, 8, 10, 12, 17, 18, 19)
    assertEquals(expected, cells.intersect(expected))
  }
    
}