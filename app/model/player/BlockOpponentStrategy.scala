package model.player
import model.Board

class BlockOpponentStrategy extends AutomatedPlayerStrategy {

  override def isBlockOpponentStrategy = {
    true
  }

  override def toString: String = {
    "BlockOpponentStrategy"
  }

  override def evaluate(board: Board) {
    if (this.playerId == 1) {
      this.evaluatePlayer1(board)
    } else {
      this.evaluatePlayer2(board)
    }
  }
  
  def evaluatePlayer1(board: Board) {
    val end = board.size * board.size
    for(i <- 0.until(end)) {
      if (board.isEmpty(i)) {
        val tempBoard = board.setPlayer2(i)
        val cells = tempBoard.player2CellsFormingLines
        if (cells.contains(i)) {
          this.targetCell = i
          this.isValid = true
          this.steps = 1
          return
        }
      }
    }
    this.steps = 0
    this.isValid = false
  }

  def evaluatePlayer2(board: Board) {
    val end = board.size * board.size
    for(i <- 0.until(end)) {
      if (board.isEmpty(i)) {
        val tempBoard = board.setPlayer1(i)
        val cells = tempBoard.player1CellsFormingLines
        if (cells.contains(i)) {
          this.targetCell = i
          this.isValid = true
          this.steps = 1
          return
        }
      }
    }
    this.steps = 0
    this.isValid = false
  }
  
}