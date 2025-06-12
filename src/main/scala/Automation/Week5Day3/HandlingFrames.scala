package Automation.Week5Day3

import org.openqa.selenium.{By, Dimension, JavascriptExecutor, Point, WebDriver, WebElement}
import org.openqa.selenium.chrome.ChromeDriver

object HandlingFrames extends App {

  //  Set Up
  val driver: WebDriver = new ChromeDriver()
  driver.get("https://the-internet.herokuapp.com/nested_frames")
  driver.manage().window().setPosition(new Point(-1920, 0))
  driver.manage().window().setSize(new Dimension(960, 1080))

  driver.switchTo().frame("frame-top")
  driver.switchTo().frame("frame-middle")

//  How to find the name of the current frame
  val jsExecutor = driver.asInstanceOf[JavascriptExecutor]
  val currentFrame = jsExecutor.executeScript("return self.name").toString
  println(s"Current frame name: $currentFrame")

//  Print text from middle frame
  val middleText: String = driver.findElement(By.id("content")).getText
  println("Text written: " + middleText)

  driver.switchTo().defaultContent() // Takes you out of being in a frame
  driver.switchTo().frame(1) // Using index to get to frame-bottom
  val currentFrame2 = jsExecutor.executeScript("return self.name").toString
  println(s"Current frame name: $currentFrame2")

  driver.switchTo().defaultContent()

  val frameTop: WebElement = driver.findElement(By.name("frame-top"))
  driver.switchTo().frame(frameTop)
  driver.switchTo().frame("frame-left")
  val currentFrame3 = jsExecutor.executeScript("return self.name").toString
  println(s"Current frame name: $currentFrame3")

  driver.quit()

}
