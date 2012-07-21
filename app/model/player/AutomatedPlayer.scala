package model.player

import java.util.Random
import model.Board

class AutomatedPlayer extends Player {

  override def isAutomated: Boolean = {
    true
  }
  
  override def play(board: Board) {
    val cell = this.nextMove(board)
    this.gameDirector.pickCell(cell)
  }
  
  def randomOpening(board: Board): Int = {
    val end = board.size * board.size
    val rand = new Random(System.currentTimeMillis());
    rand.nextInt(end);
  }
    
  def strategies(board: Board, cellIndex: Int):List[AutomatedPlayerStrategy] = {
    val s = board.size
    val y0 = cellIndex / s  // Convertimos el index a coordenadas cartesianas (x0, y0)
    val x0 = cellIndex - y0 * s
    var result = List.empty[AutomatedPlayerStrategy]
    
    // Estrategias verticales que incluyen a la celda 
    // -X-
    // -X-
    // -X-
    for(i <- 0.until(3)) {
      val x = x0
      val y = y0 - i
      if (y >= 0 && y <= s - 3) {
        val origin = x + y * s
        val strategy = AutomatedPlayerStrategy.verticalStrategy(this.playerId, origin)
        result = result ::: List(strategy)
      }
    }

    // Estrategias horizontales que incluyen a la celda 
    // ---
    // XXX
    // ---
    for(i <- 0.until(3)) {
      val x = x0 - i
      val y = y0
      if (x >= 0 && x <= s - 3) {
        val origin = x + y * s
        val strategy = AutomatedPlayerStrategy.horizontalStrategy(this.playerId, origin)
        result = result ::: List(strategy)
      }
    }

    // Estrategias Diagonales (Forward) que incluyen a la celda 
    // --X
    // -X-
    // X--
    for(i <- 0.until(3)) {
      val x = x0 + i
      val y = y0 - i
      if (x >= 2 && x <= s - 1 && y >= 0 && y <= s - 3) {
        val origin = x + y * s
        val strategy = AutomatedPlayerStrategy.forwardDiagonalStrategy(this.playerId, origin)
        result = result ::: List(strategy)
      }
    }

    // Estrategias Diagonales (Back) que incluyen a la celda 
    // X--
    // -X-
    // --X
    for(i <- 0.until(3)) {
      val x = x0 - i
      val y = y0 - i
      if (x >= 0 && x <= s - 3 && y >= 0 && y <= s - 3) {
        val origin = x + y * s
        val strategy = AutomatedPlayerStrategy.backDiagonalStrategy(this.playerId, origin)
        result = result ::: List(strategy)
      }
    }
    result
  }
 
  def nextMove(board: Board): Int = {
    if (board.isEmpty) {
      return this.randomOpening(board)
    }
    var strategies = List[AutomatedPlayerStrategy](AutomatedPlayerStrategy.blockOpponentStrategy(this.playerId), AutomatedPlayerStrategy.randomStrategy(this.playerId))
    val end = board.size * board.size
    for(i <- 0.until(end)) {
      if (board.isEmpty(i)) {
        strategies = strategies ::: this.strategies(board, i)
      }
    }
    strategies.map(st => st.evaluate(board))
    val sortedStrategies = strategies.sorted
    val best = sortedStrategies.first
    best.targetCell
  }
  
}