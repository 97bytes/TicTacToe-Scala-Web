package model

class GameOverState extends GameState {

  override def isGameOver: Boolean = {
    return true
  }

  override def statusMessage: String = {
    if (this.director.winner == null) {
      return "El juego ha terminado sin ganador!"
    }
    if (this.director.winner.isHuman) {
      return "El juego ha terminado y has ganado!"
    }
    if (this.director.winner.isAutomated) {
      return "El juego ha terminado y ha ganado el ordenador!"
    }
    return "Estado invalido!"
  }
  
}
