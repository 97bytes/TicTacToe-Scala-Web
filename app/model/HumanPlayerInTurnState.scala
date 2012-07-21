package model

class HumanPlayerInTurnState extends GameState {

  override def isHumanPlayerInTurn: Boolean = {
    return true
  }

  override def statusMessage: String = {
    "Es tu turno"
  }

  override def pickCell(i: Int) = {
    this.director.board = this.director.board.setPlayer2(i)
    val check = this.director.checkGameOver
    if (check._1) {
      this.director.state = GameState.gameOverState(this.director)
      this.director.winner = check._2
    } else {
      this.director.state = GameState.automatedPlayerInTurnState(this.director)
    }
  }

  override def play = {
    this.director.humanPlayer.play(this.director.board)    
  }
  
}

