package model

import scala.collection.mutable.Set

/**
 * Este es el companion object de la clase Board
 */
object Board {
	def initWith(size: Int): Board = {
		val result = new Board
		val newConfig = List.fill(size * size)(0)
        result.configuration = newConfig
        result.size = math.sqrt(newConfig.size).toInt
        result
	}
 
	def initWith(newConfig: List[Int]): Board = {
		val result = new Board
        result.configuration = newConfig
        result.size = math.sqrt(newConfig.size).toInt
        result
	}
 
	def initWith(newConfig: String): Board = {
		val result = new Board
		var config = List.empty[Int]
		for(c <- newConfig) {
		  val playerId = c.toString().toInt
		  config = config ::: List(playerId)
		}
		result.configuration = config
		result.size = math.sqrt(config.size).toInt
        result
	}
 
}

class Board {
  var configuration = List(0, 0, 0, 0, 0, 0, 0, 0, 0)
  var size = 0
  
  def setValue(i: Int, x: Int): Board = {
    val subLists = this.configuration.splitAt(i)
    val before = subLists._1
    val after = subLists._2.tail
    val newConfig = before ::: List(x) ::: after
    Board.initWith(newConfig)
  }
  
  def isEmpty(i: Int): Boolean = {
    this.configuration(i) == 0
  }

  def isEmpty: Boolean = {
    !this.configuration.exists(cell => cell != 0)
  }

  def isFull: Boolean = {
    !this.configuration.exists(cell => cell == 0)
  }

  def setPlayer1(i: Int): Board = {
    this.setValue(i, 1)
  }

  def isPlayer1(i: Int): Boolean = {
    this.configuration(i) == 1
  }

  def setPlayer2(i: Int): Board = {
    this.setValue(i, 2)
  }

  def isPlayer2(i: Int): Boolean = {
    this.configuration(i) == 2
  }

  /**
   * Retorna un set con todas las celdas que forman linas verticales
   * -X-
   * -X-
   * -X- 
   */
  def checkVerticalLines(playerId: Int): Set[Int] = {
    val result = Set.empty[Int]
    val s = this.size
    val end = s * (s - 2) - 1
    for (i <- 0.until(end + 1)) {
      val i1 = i
      val i2 = i1 + s
      val i3 = i2 + s
      if(this.configuration(i1) == this.configuration(i2) && 
          this.configuration(i1) == this.configuration(i3) &&
          this.configuration(i1) ==  playerId) {
        result.add(i1)
        result.add(i2)
        result.add(i3)
      }
    }
    result
  }
    
  /**
   * Retorna un set con todas las celdas que forman lineas horizontales
   * ---
   * XXX
   * --- 
   */
  def checkHorizontalLines(playerId: Int): Set[Int] = {
    val result = Set.empty[Int]
    val s = this.size
    val endX = s - 3;
    val endY = s * (s - 1);
    for (y <- 0.until(endY + 1, s)) {
      for (x <- 0.until(endX + 1, 1)) {
        val i1 = y + x
        val i2 = i1 + 1
        val i3 = i2 + 1
		if(this.configuration(i1) == this.configuration(i2) && 
		  this.configuration(i1) == this.configuration(i3) &&
		  this.configuration(i1) ==  playerId) {
		    result.add(i1)
		    result.add(i2)
		    result.add(i3)
		  }
      }
    }
    result
  }
  
  /**
   * Retorna un set con todas las celdas que forman lineas diagonales
   * X--
   * -X-
   * --X 
   */
  def checkBackDiagonalLines(playerId: Int): Set[Int] = {
    val result = Set.empty[Int]
    val s = this.size
    val endX = s - 3;
    val endY = s * (s - 3);
    for (y <- 0.until(endY + 1, s)) {
      for (x <- 0.until(endX + 1, 1)) {
        val i1 = y + x
        val i2 = i1 + s + 1
        val i3 = i2 + s + 1
		if(this.configuration(i1) == this.configuration(i2) && 
		  this.configuration(i1) == this.configuration(i3) &&
		  this.configuration(i1) ==  playerId) {
		    result.add(i1)
		    result.add(i2)
		    result.add(i3)
		  }
      }
    }
    result
  }
 
  /**
   * Retorna un set con todas las celdas que forman lineas diagonales
   * --X
   * -X-
   * X-- 
   */
  def checkForwardDiagonalLines(playerId: Int): Set[Int] = {
    val result = Set.empty[Int]
    val s = this.size
    val endX = s - 3;
    val endY = s * (s - 3);
    for (y <- 0.until(endY + 1, s)) {
      for (x <- 0.until(endX + 1, 1)) {
        val i1 = y + x + 2
        val i2 = i1 + s - 1
        val i3 = i2 + s - 1
		if(this.configuration(i1) == this.configuration(i2) && 
		  this.configuration(i1) == this.configuration(i3) &&
		  this.configuration(i1) ==  playerId) {
		    result.add(i1)
		    result.add(i2)
		    result.add(i3)
		  }
      }
    }
    result
  }
   
   def player1CellsFormingLines: Set[Int] = {
     this.checkVerticalLines(1).union(this.checkHorizontalLines(1))
     	.union(this.checkBackDiagonalLines(1))
     	.union(this.checkForwardDiagonalLines(1))
   }
   
   def player2CellsFormingLines: Set[Int] = {
     this.checkVerticalLines(2).union(this.checkHorizontalLines(2))
     	.union(this.checkBackDiagonalLines(2))
     	.union(this.checkForwardDiagonalLines(2))
   }   
   
   override def toString: String = {
    "Board[" + this.configuration + "]"
   }
   
}