package model

class AutomatedPlayerInTurnState extends GameState {

  override def isAutomatedPlayerInTurn: Boolean = {
    return true
  }

  override def statusMessage: String = {
    "Es mi turno"
  }

  override def pickCell(i: Int) = {
    this.director.board = this.director.board.setPlayer1(i)
    val check = this.director.checkGameOver
    if (check._1) {
      this.director.state = GameState.gameOverState(this.director)
      this.director.winner = check._2
    } else {
      this.director.state = GameState.humanPlayerInTurnState(this.director)
    }
  }

  override def play = {
    this.director.automatedPlayer.play(this.director.board)    
  }
  
}