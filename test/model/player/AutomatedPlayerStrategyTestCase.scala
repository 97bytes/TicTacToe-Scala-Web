package model.player

import org.junit.Assert._
import org.junit.Test

class AutomatedPlayerStrategyTestCase {

    @Test def testCompare1 {
	  val s1 = AutomatedPlayerStrategy.verticalStrategy(1, 0)
	  s1.isValid = true 
	  s1.steps = 2
	  
	  val s2 = AutomatedPlayerStrategy.verticalStrategy(1, 0)
	  s2.isValid = true
	  s2.steps = 1
	  
	  var order = s1.compare(s2)
	  assertEquals(1, order)
	  
	  order = s2.compare(s1)
	  assertEquals(-1, order)
	  
	  order = s1.compare(s1)
	  assertEquals(0, order)
    }

    @Test def testCompare2 {
	  val s1 = AutomatedPlayerStrategy.verticalStrategy(1, 0)
	  s1.isValid = false
	  s1.steps = 2
	  
	  val s2 = AutomatedPlayerStrategy.verticalStrategy(1, 0)
	  s2.isValid = true
	  s2.steps = 1
	  
	  var order = s1.compare(s2)
	  assertEquals(1, order)
	  
	  order = s2.compare(s1)
	  assertEquals(-1, order)
    }

    @Test def testCompare3 {
	  val s1 = AutomatedPlayerStrategy.verticalStrategy(1, 0)
	  s1.isValid = true
	  s1.steps = 2
	  
	  val s2 = AutomatedPlayerStrategy.randomStrategy(1)
	  s2.isValid = true
	  s2.steps = 1
	  
	  var order = s1.compare(s2)
	  assertEquals(-1, order)
	  
	  order = s2.compare(s1)
	  assertEquals(1, order)
    }

    @Test def testCompare4 {
	  val s1 = AutomatedPlayerStrategy.verticalStrategy(1, 0)
	  s1.isValid = false
	  s1.steps = 2
	  
	  val s2 = AutomatedPlayerStrategy.randomStrategy(1)
	  s2.isValid = true
	  s2.steps = 1
	  
	  var order = s1.compare(s2)
	  assertEquals(1, order)
	  
	  order = s2.compare(s1)
	  assertEquals(-1, order)
    }
 
    @Test def testCompare5 {
	  val s1 = AutomatedPlayerStrategy.verticalStrategy(1, 0)
	  s1.isValid = true
	  s1.steps = 2
	  
	  val s2 = AutomatedPlayerStrategy.blockOpponentStrategy(1)
	  s2.isValid = true
	  s2.steps = 1
	  
	  var order = s1.compare(s2)
	  assertEquals(1, order)
	  
	  order = s2.compare(s1)
	  assertEquals(-1, order)
    }
    
    @Test def testCompare6 {
	  val s1 = AutomatedPlayerStrategy.verticalStrategy(1, 0)
	  s1.isValid = true
	  s1.steps = 1
	  
	  val s2 = AutomatedPlayerStrategy.blockOpponentStrategy(1)
	  s2.isValid = true
	  s2.steps = 1
	  
	  var order = s1.compare(s2)
	  assertEquals(-1, order)
	  
	  order = s2.compare(s1)
	  assertEquals(1, order)
    }
    
    @Test def testSortList {
	  val s1 = AutomatedPlayerStrategy.verticalStrategy(1, 0)
      s1.isValid = true
      s1.steps = 1
      
	  val s2 = AutomatedPlayerStrategy.forwardDiagonalStrategy(1, 0)
      s2.isValid = true
      s2.steps = 2
      
	  val s3 = AutomatedPlayerStrategy.blockOpponentStrategy(1)
      s3.isValid = true
      s3.steps = 1
      
	  val s4 = AutomatedPlayerStrategy.randomStrategy(1)
      s4.isValid = true
      s4.steps = 1
      
      var strategies = List(s2, s1, s4, s3)
      strategies = scala.util.Random.shuffle(strategies)
      
      val sorted = strategies.sorted
      val expected = List(s1, s3, s2, s4)
      assertEquals(expected, sorted)
    }
      
	  
    
}