package model.player
import model.GameDirector
import model.Board

object Player {
	def humanPlayerWith(playerId: Int, gameDirector: GameDirector): HumanPlayer = {
		val result = new HumanPlayer
		result.playerId = playerId
		result.gameDirector = gameDirector
        result
	}

	def automatedPlayerWith(playerId: Int, gameDirector: GameDirector): AutomatedPlayer = {
		val result = new AutomatedPlayer
		result.playerId = playerId
		result.gameDirector = gameDirector
        result
	}
}

class Player {
  var playerId = 0
  var gameDirector : GameDirector = null
  
  def isHuman: Boolean = {
    false
  }

  def isAutomated: Boolean = {
    false
  }
  
  def play(board: Board) {
  }

}