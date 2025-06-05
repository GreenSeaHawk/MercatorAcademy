package Automation.Week4Day4

import Automation.Week4Day4.ChromeBrowserInvoke.driver
import org.openqa.selenium.{By, WebDriver, WebElement}
import org.openqa.selenium.chrome.ChromeDriver

object ByID extends App {

//  Instantiating the ChromeDriver for interacting with the Chrome browser
  val driver: WebDriver = new ChromeDriver()

//  Accessing the web url for testing
  driver.get("https://proleed.academy/exercises/selenium/selenium-element-id-locators-practice-form.php")

  driver.manage().window().maximize()

//  Finding and typing in email
  val emailAddress: WebElement = driver.findElement(By.id("email"))
  emailAddress.sendKeys("example@gmail.com")
  println("Email address entered")

//  Finding and typing in password
  val password: WebElement = driver.findElement(By.id("password"))
  password.sendKeys("Password123")
  println("Password entered")

  val login: WebElement = driver.findElement(By.id("login"))
  login.click()
  println("Logged in successfully")

  driver.quit()

}
