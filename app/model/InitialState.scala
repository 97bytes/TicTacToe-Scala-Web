package model

class InitialState extends GameState {

  override def isInitialState: Boolean = {
    return true
  }

  override def statusMessage: String = {
    "Bienvenido!"
  }

}
