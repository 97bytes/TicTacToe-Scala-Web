package model.player
import model.Board

object AutomatedPlayerStrategy {
  
	/**
	 * -X-
	 * -X-
	 * -X- 
	 */
	def verticalStrategy(playerId: Int, origin: Int): VerticalStrategy = {
		val result = new VerticalStrategy
		result.playerId = playerId
		result.origin = origin
        result
	}
	
	/**
	 * ---
	 * XXX
	 * --- 
	 */
	def horizontalStrategy(playerId: Int, origin: Int): HorizontalStrategy = {
		val result = new HorizontalStrategy
		result.playerId = playerId
		result.origin = origin
        result
	}
	
	/**
	 * X--
	 * -X-
	 * --X 
	 */
	def backDiagonalStrategy(playerId: Int, origin: Int): BackDiagonalStrategy = {
		val result = new BackDiagonalStrategy
		result.playerId = playerId
		result.origin = origin
        result
	}
	
	/**
	 * --X
	 * -X-
	 * X-- 
	 */
	def forwardDiagonalStrategy(playerId: Int, origin: Int): ForwardDiagonalStrategy = {
		val result = new ForwardDiagonalStrategy
		result.playerId = playerId
		result.origin = origin
        result
	}
	
	def blockOpponentStrategy(playerId: Int): BlockOpponentStrategy = {
		val result = new BlockOpponentStrategy
		result.playerId = playerId
		result.origin = 0
        result
	}
	
	def randomStrategy(playerId: Int): RandomStrategy = {
		val result = new RandomStrategy
		result.playerId = playerId
		result.origin = 0
        result
	}	
}
	
class AutomatedPlayerStrategy extends Ordered[AutomatedPlayerStrategy] {
  var origin = 0
  var playerId = 0
  var isValid = false
  var targetCell = 0
  var steps = 0

  def evaluate(board: Board) {
  }
  
  def isBlockOpponentStrategy = {
    false
  }
  
  def isRandomStrategy = {
    false
  }
  
  /**
   * Ordena 2 estrategias segun la cantidad del pasos y otros criterios
   * 1) La estrategia con 1 step tiene prioridad porque significa que es una jugada ganadora
   * 2) Si no, tiene prioridad la estrategia que bloquee al oponente
   * 3) Si no, la estrategia con menor cantidad de pasos
   */
  def compare(that: AutomatedPlayerStrategy): Int = {
	if (!this.isValid) {
	  if (!that.isValid) {
	    return 0 //misma prioridad porque ambas son invalidas
	  } else {
	    return 1
	  }
	}
    if (!that.isValid) {
        return -1 //this < that
    }
    if (this.isRandomStrategy) {
        return 1 //that < this
    }
    if (that.isRandomStrategy) {
        return -1 //this < that
    }
    if (this.steps == 1 && !this.isBlockOpponentStrategy) {
        return -1 //this < that
    }
    if (that.steps == 1) {
        return 1 //that < this
    }
    if (this.isBlockOpponentStrategy) {
        return -1 //this < that
    }
    if (that.isBlockOpponentStrategy) {
        return 1 //that < this
    }
    this.steps - that.steps
  }
  
  override def toString: String = {
    "AutomatedPlayerStrategy"
  }
  
}

