package model.player
import model.Board

/**
 * X--
 * -X-
 * --X 
 */
class BackDiagonalStrategy extends AutomatedPlayerStrategy {

  override def toString: String = {
    "BackDiagonalStrategy"
  }

  override def evaluate(board: Board) {
    this.isValid = true
    this.steps = 3
    val end = this.origin + 2 * board.size + 2
    val skip = board.size + 1
    for (i <- this.origin.until(end + 1, skip)) {
      if (board.isEmpty(i)) {
        this.targetCell = i
      }
      if (board.isPlayer1(i)) {
        if (this.playerId == 2) {
          this.isValid = false
          this.steps = 0
          return
        } else {
          this.steps = this.steps - 1
        }
      }
      if (board.isPlayer2(i)) {
        if (this.playerId == 1) {
          this.isValid = false
          this.steps = 0
          return
        } else {
          this.steps = this.steps - 1
        }
      }
      // Si el tablero estuviera lleno, la estrategia no vale.
      // Notese que no deberiamos llegar a este caso porque suponemos
      // que en la fila existe al menos 1 celda libre
      if (this.steps == 0) {
    	  this.isValid = false
      }
    }
  }
  
}