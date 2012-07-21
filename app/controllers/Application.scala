package controllers

import play.api._
import play.api.mvc._
import model.Board
import model.GameDirector
import model.SessionData
import play.api.data._
import play.api.data.Forms._
import model.GameState

/**
 * Este singleton procesa las peticiones HTPP y dirige el flujo de navegación
 * entre las páginas de la aplicación.
 * Como Play es stateless, hemos creado un objeto SessionData que serializamos en Base64 y 
 * guardamos del lado del cliente usando un cookie. La gestión del cookie está a cargo de Play
 */
object Application extends Controller {
  
  /**
   * Este método se invoca cuando el usuario está en la raíz de la 
   * aplicación.
   */
  def index = Action {
    Ok(views.html.index("Cuerpo de la pagina"))
  }

  /**
   * Este método se invoca cuando el usuario pincha el link
   * para empezar de nuevo el juego.
   */
  def newGame = Action { implicit request => {
	    var director = this.getDirector(request)
	    director.newGame
	    director.state = GameState.humanPlayerInTurnState(director)
	    this.displayBoardPage(director, director.statusMessage)
	  }
  }
  
  /**
   * Los siguientes métodos se invocan cuando el usuario pincha los enlaces de las
   * celdas libres del tablero.
   * Extreamos el director serializado en la sesión del usuario.
   */
  def pickCell0 = Action { implicit request => {
	  	this.playGame(0, this.getDirector(request))
	  }
  }

  def pickCell1 = Action { implicit request => {
	  	this.playGame(1, this.getDirector(request))
	  }
  }

  def pickCell2 = Action { implicit request => {
	  	this.playGame(2, this.getDirector(request))
	  }
  }

  def pickCell3 = Action { implicit request => {
	  	this.playGame(3, this.getDirector(request))
	  }
  }

  def pickCell4 = Action { implicit request => {
	  	this.playGame(4, this.getDirector(request))
	  }
  }

  def pickCell5 = Action { implicit request => {
	  	this.playGame(5, this.getDirector(request))
	  }
  }

  def pickCell6 = Action { implicit request => {
	  	this.playGame(6, this.getDirector(request))
	  }
  }

  def pickCell7 = Action { implicit request => {
	  	this.playGame(7, this.getDirector(request))
	  }
  }

  def pickCell8 = Action { implicit request => {
	  	this.playGame(8, this.getDirector(request))
	  }
  }

  /**
   * Avanza el juego y retorna un Result que muestra la pagina correspondiente al estado actual.
   */
  def playGame(cell: Int, director: GameDirector):Result = {
    director.pickCell(cell)
    if (director.isGameOver) {
      return this.displayGameOverBoardPage(director)
    }
    director.automatedPlayer.play(director.board)
    if (director.isGameOver) {
      return this.displayGameOverBoardPage(director)
    }
    this.displayBoardPage(director, director.statusMessage)
  } 

  /**
   * Obtiene el director, serializado en Base64 como un parámetro en la sesión
   * del cliente. Internamente, Play almacena el parámetro como un cookie en el cliente.
   * Play no mantiene la sesión del usuario en el servidor.
   * Si el parámetro no existe significa que el usuario recién se ha conectado y no se le ha
   * instanciado un director. En este caso, se instancia un director nuevo
   */
  def getDirector(request: Request[AnyContent]): GameDirector = {
    var director = new GameDirector(3)
    var sessionParam = request.session.get("sessionParam").getOrElse("")
    if (!sessionParam.equals("")) {
      val data = SessionData.importFromString(sessionParam)
      director = SessionData.extractGameDirector(data)
    }
    director
  }
 
  /**
   * Retorna un Result para mostrar la página con el tablero
   * 1) Serializamos el director en la sesión del cliente (Play lo almacenará en un cookie)
   * 2) Retornamos un Result para mostrar la página correspondiente, enviado un helper
   * que ayuda para construir la tabla.
   * La página que se muestra es /views/board.scala.html
   */
  def displayBoardPage(director: GameDirector, statusMessage: String):Result = { 
    val data = SessionData.initWith(director)
    val sessionParam = SessionData.exportToString(data)
    val helper = new HtmlHelper(director)
    Ok(views.html.board(statusMessage, helper)).withSession("sessionParam" -> sessionParam)
  }

  /**
   * Retorna un Result para mostrar la página de fin del juego
   * 1) Serializamos el director en la sesión del cliente (Play lo almacenará en un cookie)
   * 2) Retornamos un Result para mostrar la página correspondiente, enviado un helper
   * que ayuda para construir la tabla.
   * La página que se muestra es /views/gameOver.scala.html
   */
  def displayGameOverBoardPage(director: GameDirector):Result = { 
    val data = SessionData.initWith(director)
    val sessionParam = SessionData.exportToString(data)
    val helper = new HtmlHelper(director)
    Ok(views.html.gameOver(director.statusMessage, helper)).withSession("sessionParam" -> sessionParam)
  }

  
}