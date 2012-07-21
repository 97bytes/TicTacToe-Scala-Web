package model.player

import org.junit.Assert._
import org.junit.Test
 
class HumanPlayerTestCase {

  @Test def testInit() {
    val player = Player.humanPlayerWith(1, null)
    assertNotNull(player)
    assertEquals(1, player.playerId)
    assertNull(player.gameDirector)
    assertTrue(player.isHuman)
    assertFalse(player.isAutomated)
  }
  
}