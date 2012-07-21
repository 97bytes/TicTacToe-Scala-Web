package model.player

import scala.collection.mutable.Set
import model.Board

class RandomStrategy extends AutomatedPlayerStrategy {

  override def isRandomStrategy = {
    true
  }
  
  override def toString: String = {
    "RandomStrategy"
  }

  override def evaluate(board: Board) {
     val end = board.size * board.size
     var freeCells = List.empty[Int]
     for (i <- 0.until(end)) {
       if (board.isEmpty(i)) {
         freeCells = freeCells ::: List(i)
       }
     }
     if (freeCells.isEmpty) {
       this.steps = 0
       this.isValid = false
       return
     }
     freeCells = scala.util.Random.shuffle(freeCells)
     this.targetCell = freeCells.first
     this.steps = 1
     this.isValid = true
  }
  
}