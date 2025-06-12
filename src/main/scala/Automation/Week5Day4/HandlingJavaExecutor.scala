package Automation.Week5Day4

import org.openqa.selenium.{By, Dimension, JavascriptExecutor, Point, WebDriver, WebElement}
import org.openqa.selenium.chrome.ChromeDriver

import java.time.Duration

object HandlingJavaExecutor extends App {


  //  Instantiating the ChromeDriver for interacting with the Chrome browser
  val driver: WebDriver = new ChromeDriver()

  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)) // see notes 12/6
  //basically means it will wait up to 10 seconds for something to appear before throwing an error

  //  Accessing the web url for testing
  driver.get("https://proleed.academy/exercises/selenium/selenium-element-id-locators-practice-form.php")
  driver.manage().window().setPosition(new Point(-1920, 0))
  driver.manage().window().setSize(new Dimension(960, 1080))
//  driver.manage().window().maximize()

  //  Finding and typing in email
  val emailAddress: WebElement = driver.findElement(By.id("email"))
  emailAddress.sendKeys("example@gmail.com")
  println("Email address entered")

  //  Finding and typing in password
  val password: WebElement = driver.findElement(By.id("password"))
  password.sendKeys("Password123")
  println("Password entered")

  val js = driver.asInstanceOf[JavascriptExecutor]
  val login: WebElement = driver.findElement(By.id("login"))
  js.executeScript("arguments[0].scrollIntoView();", login)
  js.executeScript("arguments[0].click();", login)
//  login.click() // not working on this webpage

  val header: WebElement = driver.findElement(By.tagName("h2"))
  assert(header.getText == "Thank You!")

  println("Logged in successfully")

  driver.quit()

}
