package controllers
import model.GameDirector
import views.html.board

/**
 * Esta clase auxiliar facilita la gestión de la capa de presentación
 *
 */
class HtmlHelper(aDirector: GameDirector) {
  val director = aDirector
  
  /**
   * Retorna el código HTML para mostrar la imagen de una celda durante la partida.
   * Las celdas libres llevan un link.
   */
  def renderCell(index: Int): String = {
    if (this.director.board.isEmpty(index)) {
      return "<a href=\"pickCell" + index + "\"><img src=\"/assets/images/empty_180x180.png\"/></a>"
    }
    if (this.director.board.isPlayer1(index)) {
      return "<img src=\"/assets/images/player1_180x180.png\"/>"
    }
    if (this.director.board.isPlayer2(index)) {
      return "<img src=\"/assets/images/player2_180x180.png\"/>"
    }
    "Invalid cell: " + index
  }
  
  /**
   * Retorna el código HTML para mostrar la imagen de una celda, cuando el juego ha terminado.
   */
  def renderCellGameOver(index: Int): String = {
    if (this.director.board.isEmpty(index)) {
      return "<img src=\"/assets/images/empty_180x180.png\"/>"
    }
    if (this.director.board.isPlayer1(index)) {
      return "<img src=\"/assets/images/player1_180x180.png\"/>"
    }
    if (this.director.board.isPlayer2(index)) {
      return "<img src=\"/assets/images/player2_180x180.png\"/>"
    }
    "Invalid cell: " + index
  }
    
}