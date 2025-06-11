package Automation.Week5Day3

import org.openqa.selenium.{By, Dimension, Point, WebDriver, WebElement}
import org.openqa.selenium.chrome.ChromeDriver

object HandlingAlerts extends App {

  //  Set Up
  val driver: WebDriver = new ChromeDriver()
  driver.get("https://the-internet.herokuapp.com/javascript_alerts")
  driver.manage().window().setPosition(new Point(-1920, 0))
  driver.manage().window().setSize(new Dimension(960, 1080))

  val jsAlertButton: WebElement = driver.findElement(By.cssSelector("#content > div > ul > li:nth-child(1) > button"))
  jsAlertButton.click() // Clicks the button that produces an alert

  Thread.sleep(2000)

  println("Alert button text: " + driver.switchTo().alert().getText)
  driver.switchTo().alert().accept() // Presses the 'OK' button on the alert
  println(driver.findElement(By.cssSelector("#result")).getText)

  Thread.sleep(2000)

  val jsConfirmButton: WebElement = driver.findElement(By.cssSelector("#content > div > ul > li:nth-child(2) > button"))
  jsConfirmButton.click() // Clicks the button that produces an JS confirm

  Thread.sleep(2000)

  println("Confirm button text: " + driver.switchTo().alert().getText)
  driver.switchTo().alert().dismiss() // Presses the 'Cancel' button on the alert
  println(driver.findElement(By.cssSelector("#result")).getText)

  Thread.sleep(2000)

  val jsPromptButton: WebElement = driver.findElement(By.cssSelector("#content > div > ul > li:nth-child(3) > button"))
  jsPromptButton.click() // Clicks the button that produces an JS prompt

  Thread.sleep(2000)

  driver.switchTo().alert().sendKeys("I wrote this in the JS prompt box")

  Thread.sleep(2000)

  driver.switchTo().alert().accept() // Presses the 'OK' button on the alert

  Thread.sleep(2000)

  println(driver.findElement(By.cssSelector("#result")).getText)

  Thread.sleep(2000)

  driver.quit()

}
