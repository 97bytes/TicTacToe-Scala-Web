package model
import java.io.ByteArrayOutputStream
import java.io.ObjectOutputStream
import java.io.ByteArrayInputStream
import java.io.ObjectInputStream
import javax.xml.bind.DatatypeConverter

/**
 * Esta clase guarda el estado del juego codificado en strings.
 * Además permite serializar y deserializar instancias en Base64 para que puedan
 * guardarse fácilmente en el cliente (pues Play es stateless y no gestiona la sesión del cliente
 * en el servidor como hace Struts2)
 */
object SessionData {
  
	def initWith(director: GameDirector): SessionData = {
	    var boardConfiguration = ""
	    var state = ""
	    var winnerPlayer = ""
	    director.board.configuration.foreach(cell => boardConfiguration = boardConfiguration + cell.toString())
	    if (director.isAutomatedPlayerInTurn) {
	      state = "AutomatedPlayerInTurn"
	    } else if (director.isHumanPlayerInTurn) {
	      state = "HumanPlayerInTurn"
	    } else if (director.isGameOver) {
	      state = "GameOver"
		  if (director.winner != null) {
		  	if (director.winner.isAutomated) {
		  		winnerPlayer = "Automated"
		   	} else if (director.winner.isHuman){
		   		winnerPlayer = "Human"
		   	}
		  }
	    } else if (director.isInitialState) {
	      state = "InitialState"
	    }
		val result = new SessionData
		result.boardConfiguration = boardConfiguration
		result.state = state
		result.winnerPlayer = winnerPlayer
        result
	}
	
	def extractGameDirector(data: SessionData): GameDirector = {
		val director = new GameDirector(3)
		director.board = Board.initWith(data.boardConfiguration)
	    if (data.state.equals("AutomatedPlayerInTurn")) {
	      director.state = GameState.automatedPlayerInTurnState(director)
	    } else if (data.state.equals("HumanPlayerInTurn")) {
	      director.state = GameState.humanPlayerInTurnState(director)
	    } else if (data.state.equals("GameOver")) {
	      director.state = GameState.gameOverState(director)
    	  if ("Automated".equals(data.winnerPlayer)) {
    	    director.winner = director.automatedPlayer
    	  } else if ("Human".equals(data.winnerPlayer)) {
    	    director.winner = director.humanPlayer
    	  } else {
    	    director.winner = null
    	  }
	    } else if (data.state.equals("InitialState")) {
	      director.state = GameState.initialState(director)
	    }
        director
	}
	
  def exportToBytes(data: SessionData) = {
    val bos = new ByteArrayOutputStream() ;
    val out = new ObjectOutputStream(bos) ;
    out.writeObject(data);
    out.close();
    val bytes = bos.toByteArray();
    bytes
  }

  def exportToString(data: SessionData): String = {
    val bytes = this.exportToBytes(data);
    val base64 = DatatypeConverter.printBase64Binary(bytes);
    base64
  }

  def importFromBytes(data: Array[Byte]): SessionData = {
	val dataAsBytes = data
	val bis = new ByteArrayInputStream(dataAsBytes)
	val in = new ObjectInputStream(bis)
	var value = in.readObject()
	in.close()
	value.asInstanceOf[SessionData]
  }
	
  def importFromString(base64: String): SessionData = {
    val data = DatatypeConverter.parseBase64Binary(base64);
	var value = this.importFromBytes(data)
	value
  }
	
}
 	
@serializable class SessionData {
  var boardConfiguration = ""
  var state = ""
  var winnerPlayer = ""
    
  override def toString: String = {
    "SessionData[" +
    " boardConfiguration=" + boardConfiguration + 
    ", state=" + state + 
    ", winnerPlayer=" + winnerPlayer + 
    " ]"
  }

    
}