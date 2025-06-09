package Automation.Week4Day4

import Automation.Week4Day4.FindByTask.driver
import org.openqa.selenium.{By, WebDriver, WebElement}
import org.openqa.selenium.chrome.ChromeDriver

object LocatorIdentificationTask2 extends App {

  val driver: WebDriver = new ChromeDriver()
  driver.get("https://demoqa.com/elements")

//  val goToElements: WebElement = driver.findElement(By.linkText("Elements"))
//  goToElements.click()
// THIS DIDN'T WORK, COULDN'T GET IT TO NAVIGATE TO WHERE I WANTED
  val goToTextBox: WebElement = driver.findElement(By.linkText("Text Box"))
  goToTextBox.click()

  val fullName: WebElement = driver.findElement(By.id("userName"))
  fullName.sendKeys("Andrew Boyce")
  println("Andrew Boyce entered into Full Name box")
}
