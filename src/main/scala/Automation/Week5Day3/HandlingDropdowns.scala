package Automation.Week5Day3

import org.openqa.selenium.{By, Dimension, Point, WebDriver, WebElement}
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.Select

object HandlingDropdowns extends App {

  //  Set Up
  val driver: WebDriver = new ChromeDriver()
  driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select")
  driver.manage().window().setPosition(new Point(-1920, 0))
  driver.manage().window().setSize(new Dimension(960, 1080))

  val acceptAllButton: WebElement = driver.findElement(By.id("accept-choices"))
  acceptAllButton.click()

  //  Switch to the frame where the dropdown is located
  driver.switchTo().frame("iframeResult")

  val carDropdown: WebElement = driver.findElement(By.name("cars"))
  val select = new Select(carDropdown)
  //  println(carDropdown.getAttribute("value"))
  select.selectByVisibleText("Opel")
  println(select.getFirstSelectedOption.getText) // Opel
  Thread.sleep(2000)
  select.selectByVisibleText("Volvo")
  Thread.sleep(2000)

  driver.quit()
}
