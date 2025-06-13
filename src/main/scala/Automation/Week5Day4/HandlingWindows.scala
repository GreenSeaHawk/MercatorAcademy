package Automation.Week5Day4

import org.openqa.selenium.{By, Dimension, Point, WebDriver}
import org.openqa.selenium.chrome.ChromeDriver
import scala.jdk.CollectionConverters._

object HandlingWindows extends App {

  //  Set Up
  val driver: WebDriver = new ChromeDriver()
  driver.get("https://the-internet.herokuapp.com/windows")
  driver.manage().window().setPosition(new Point(-1920, 0))
  driver.manage().window().setSize(new Dimension(960, 1080))

  val parentWindow = driver.getWindowHandle()
  println("Handle for the parent window " + parentWindow)

  //  Click on link
  driver.findElement(By.linkText("Click Here")).click()

  val allWindows: List[String] = driver.getWindowHandles.asScala.toList
  val it = allWindows.iterator

  while (it.hasNext) {
    val handle = it.next()

    if (handle != parentWindow) {
      driver.switchTo().window(handle)
      println("The current window " + handle)
    }
  }

  println("The page title is - " + driver.getTitle)


  driver.quit()

}

