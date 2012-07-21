package model
import scala.util.Random
import model.player.Player

class GameDirector(boardSize: Int) {
  var board = Board.initWith(boardSize)
  var automatedPlayer = Player.automatedPlayerWith(1, this)
  var humanPlayer = Player.humanPlayerWith(2, this)
  var state: GameState = GameState.initialState(this)
  var winner: Player = null
  
  def newGame {
    var playerStates = Random.shuffle(List(GameState.automatedPlayerInTurnState(this), GameState.humanPlayerInTurnState(this)))
    this.state = playerStates.first
    this.board = Board.initWith(this.board.size)
    this.winner = null
    this.state.play
  }
  
  def pickCell(i: Int) {
    if (this.board.isEmpty(i)) {
      this.state.pickCell(i)
    }
  }
            
  def checkGameOver: (Boolean, Player) = {
    var cells = this.board.player1CellsFormingLines
    if (!cells.isEmpty) {
      return (true, this.automatedPlayer)
    }
    cells = this.board.player2CellsFormingLines
    if (!cells.isEmpty) {
      return (true, this.humanPlayer)
    }
    if (this.board.isFull) {
      return (true, null)
    }
    return (false, null)
  }
 
  def statusMessage: String = {
    return this.state.statusMessage
  }

  def isGameOver: Boolean = {
    return this.state.isGameOver
  }

  def isInitialState: Boolean = {
    return this.state.isInitialState
  }

  def isHumanPlayerInTurn: Boolean = {
    return this.state.isHumanPlayerInTurn
  }

  def isAutomatedPlayerInTurn: Boolean = {
    return this.state.isAutomatedPlayerInTurn
  }
  
}