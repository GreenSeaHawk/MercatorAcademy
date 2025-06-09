package Automation.Week5Day1

import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.{By, WebDriver, WebElement}

object ByIDWithCSSSelector extends App {

//  Instantiating the ChromeDriver for interacting with the Chrome browser
  val driver: WebDriver = new ChromeDriver()

//  Accessing the web url for testing
  driver.get("https://proleed.academy/exercises/selenium/selenium-element-id-locators-practice-form.php")

  driver.manage().window().maximize() //without this it can't click on "login"

//  Finding and typing in email
//  val emailAddress: WebElement = driver.findElement(By.id("email"))
  val emailAddress: WebElement = driver.findElement(By.cssSelector("#email"))
  emailAddress.sendKeys("example@gmail.com")
  println("Email address entered")

//  Finding and typing in password
  val password: WebElement = driver.findElement(By.id("password"))
  password.sendKeys("Password123")
  println("Password entered")

//  val login: WebElement = driver.findElement(By.id("login"))
//  login.click()

  val loginAlternate: WebElement = driver.findElement(By.cssSelector("#login"))
  loginAlternate.click()
  println("Logged in successfully")

  driver.quit()

}
