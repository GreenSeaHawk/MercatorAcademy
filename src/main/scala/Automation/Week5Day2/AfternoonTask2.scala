package Automation.Week5Day2

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

object AfternoonTask2 extends App {

  val driver: WebDriver = new ChromeDriver()
  driver.get("https://demoqa.com/automation-practice-form")

  driver.navigate().to("https://example.com")

  driver.navigate().back()

  driver.navigate().forward()

  driver.navigate().refresh()

  driver.manage().window().maximize()

  driver.manage().window().minimize()

  val pageSource: String = driver.getPageSource()
  println("Part of the page source: " + pageSource.take(30))

  driver.close()

}
