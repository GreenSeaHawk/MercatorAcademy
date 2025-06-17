package Automation.Week6Day1

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.{ChromeDriver, ChromeOptions}

object HeadlessMode extends App {

  val options = new ChromeOptions()
  options.addArguments("headless")

  val driver: WebDriver = new ChromeDriver(options)

  driver.quit()


}
