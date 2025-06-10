package Automation.Week5Day2

import org.openqa.selenium.{WebDriver, WebElement}
import org.openqa.selenium.chrome.ChromeDriver

object AfternoonTask1 extends App {

  val driver: WebDriver = new ChromeDriver()
  driver.get("https://demoqa.com/browser-windows")

  val title: String = driver.getTitle()
  println("The page title is: " + title)

  val currentURL: String = driver.getCurrentUrl()
  println("The current URL is: " + currentURL)

  driver.close()

}
