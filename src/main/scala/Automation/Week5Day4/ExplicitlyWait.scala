package Automation.Week5Day4

import org.openqa.selenium.{By, Dimension, JavascriptExecutor, Point, WebDriver, WebElement}
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.{ExpectedConditions, WebDriverWait}

import java.time.Duration

object ExplicitlyWait extends App {

  //  Instantiating the ChromeDriver for interacting with the Chrome browser
  val driver: WebDriver = new ChromeDriver()



  //  Accessing the web url for testing
  driver.get("https://www.w3schools.com/howto/howto_css_custom_checkbox.asp")
  driver.manage().window().setPosition(new Point(-1920, 0))
  driver.manage().window().setSize(new Dimension(960, 1080))
  //  driver.manage().window().maximize()

  val acceptAllButton: WebElement = driver.findElement(By.id("accept-choices"))
  acceptAllButton.click()
  val explicitWait = new WebDriverWait(driver, Duration.ofMinutes(1))

  val checkbox: WebElement = explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main\"]/div[3]/div[2]/label[1]\n ")))
  checkbox.click() // Getting an error


  driver.quit()


}
