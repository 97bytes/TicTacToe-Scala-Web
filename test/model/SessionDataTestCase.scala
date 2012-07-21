package model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class SessionDataTestCase {

    @Test def testInitWith1() {
		val director = new GameDirector(3)
		val data = SessionData.initWith(director)
		assertNotNull(data)
		assertEquals("000000000", data.boardConfiguration)
		assertEquals("InitialState", data.state)
		assertEquals("", data.winnerPlayer)
    }

    @Test def testInitWith2() {
		val director = new GameDirector(3)
		director.board = Board.initWith("111000222")
		director.state = GameState.automatedPlayerInTurnState(director)
		
		val data = SessionData.initWith(director)
		assertNotNull(data)
		assertEquals("111000222", data.boardConfiguration)
		assertEquals("AutomatedPlayerInTurn", data.state)
		assertEquals("", data.winnerPlayer)
    }

    @Test def testInitWith3() {
		val director = new GameDirector(3)
		director.board = Board.initWith("010222101")
		director.state = GameState.gameOverState(director)
		director.winner = director.automatedPlayer

		var data = SessionData.initWith(director)
		assertNotNull(data)
		assertEquals("010222101", data.boardConfiguration)
		assertEquals("GameOver", data.state)
		assertEquals("Automated", data.winnerPlayer)
		
		director.winner = director.humanPlayer
		data = SessionData.initWith(director)
		assertNotNull(data)
		assertEquals("010222101", data.boardConfiguration)
		assertEquals("GameOver", data.state)
		assertEquals("Human", data.winnerPlayer)
    }

    @Test def testExtractGameDirector1() {
		val data = new SessionData()
		data.boardConfiguration = "010222101"
		data.state = "GameOver"
		data.winnerPlayer = "Automated"

		val director = SessionData.extractGameDirector(data)
		assertNotNull(director)
		assertEquals(List(0, 1, 0, 2, 2, 2, 1, 0, 1), director.board.configuration)
		assertTrue(director.isGameOver)
		assertTrue(director.winner.isAutomated)
    }

    @Test def testExtractGameDirector2() {
		val data = new SessionData()
		data.boardConfiguration = "010222101"
		data.state = "AutomatedPlayerInTurn"
		data.winnerPlayer = ""

		val director = SessionData.extractGameDirector(data)
		assertNotNull(director)
		assertEquals(List(0, 1, 0, 2, 2, 2, 1, 0, 1), director.board.configuration)
		assertTrue(director.isAutomatedPlayerInTurn)
		assertNull(director.winner)
    }

    @Test def testExportToBytes() {
		val data = new SessionData()
		data.boardConfiguration = "010222101"
		data.state = "AutomatedPlayerInTurn"
		data.winnerPlayer = ""

		val exportData = SessionData.exportToBytes(data)
		assertNotNull(exportData)
    }

    @Test def testExportToString() {
		val data = new SessionData()
		data.boardConfiguration = "010222101"
		data.state = "AutomatedPlayerInTurn"
		data.winnerPlayer = ""

		val exportData = SessionData.exportToString(data)
		assertNotNull(exportData)
    }

    @Test def testImportFromBytes() {
		val data = new SessionData()
		data.boardConfiguration = "010222101"
		data.state = "AutomatedPlayerInTurn"
		data.winnerPlayer = ""
		val exportData = SessionData.exportToBytes(data)

		val importedData: SessionData = SessionData.importFromBytes(exportData)
		assertNotNull(importedData)
		assertEquals("010222101", importedData.boardConfiguration)
		assertEquals("AutomatedPlayerInTurn", importedData.state)
		assertEquals("", importedData.winnerPlayer)
   }

    @Test def testImportFromString() {
		val data = new SessionData()
		data.boardConfiguration = "010222101"
		data.state = "GameOver"
		data.winnerPlayer = "Human"
		val exportData = SessionData.exportToString(data)

		val importedData: SessionData = SessionData.importFromString(exportData)
		assertNotNull(importedData)
		assertEquals("010222101", importedData.boardConfiguration)
		assertEquals("GameOver", importedData.state)
		assertEquals("Human", importedData.winnerPlayer)
   }

    
}