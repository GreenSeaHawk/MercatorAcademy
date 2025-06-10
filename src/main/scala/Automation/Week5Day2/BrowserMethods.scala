package Automation.Week5Day2

import org.openqa.selenium.{Dimension, Point, WebDriver}
import org.openqa.selenium.chrome.ChromeDriver

object BrowserMethods extends App {

//  Set up
  val driver: WebDriver = new ChromeDriver()
  driver.get("https://selenium.dev/selenium/web/web-form.html")

//  driver.manage().window().maximize()
  driver.manage().window().setPosition(new Point(-1920, 0))
  driver.manage().window().setSize(new Dimension(960, 1080))

  driver.navigate().to("https://the-internet.herokuapp.com")

  driver.navigate().back()

  driver.navigate().forward()

  driver.navigate().refresh()

  driver.close()
//  driver.quit()

}
