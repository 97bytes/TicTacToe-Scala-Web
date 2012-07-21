package model

object GameState {
	def initialState(gameDirector: GameDirector): InitialState = {
	  val result = new InitialState
	  result.director = gameDirector
	  result
	}

	def humanPlayerInTurnState(gameDirector: GameDirector): HumanPlayerInTurnState = {
	  val result = new HumanPlayerInTurnState
	  result.director = gameDirector
	  result
	}

	def automatedPlayerInTurnState(gameDirector: GameDirector): AutomatedPlayerInTurnState = {
	  val result = new AutomatedPlayerInTurnState
	  result.director = gameDirector
	  result
	}

	def gameOverState(gameDirector: GameDirector): GameOverState = {
	  val result = new GameOverState
	  result.director = gameDirector
	  result
	}
}

class GameState {
  var director: GameDirector = null
  
  def pickCell(i: Int) = {
  }

  def play = {
  }
  
  def isGameOver: Boolean = {
    return false
  }

  def isInitialState: Boolean = {
    return false
  }

  def isHumanPlayerInTurn: Boolean = {
    return false
  }

  def isAutomatedPlayerInTurn: Boolean = {
    return false
  }

  def statusMessage: String = {
    "Invalid state!!"
  }
  
}