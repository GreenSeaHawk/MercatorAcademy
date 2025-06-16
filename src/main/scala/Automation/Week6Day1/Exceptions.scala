package Automation.Week6Day1

import org.openqa.selenium.{By, NoSuchElementException, WebDriver}
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.{ExpectedConditions, WebDriverWait}

import java.time.Duration

object Exceptions extends App {

  val driver: WebDriver = new ChromeDriver()

  try {
    //    Will catch a element not found error
    //    driver.get("https://www.selenium.dev/selenium/web/web-form.html")
    //    driver.findElement(By.cssSelector("Invalid Input"))

    //    Will catch an element not visible error
    driver.get("https://the-internet.herokuapp.com/dynamic_loading/1")
    driver.findElement(By.id("start")).click() // this didn't work for some reason
    val explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10))
    explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")))
  }
  catch {
    case e: NoSuchElementException =>
      println("Element not found: " + e.getMessage) //  prints error message (in white)
    //      e.printStackTrace() // prints exception like it would if there was no try/catch (in red)

    case e: Exception =>
      println("Exception: " + e.getMessage)
  }
  finally {
    println("\nI made it to finally!")
    if (driver != null) {
      driver.quit()
    }
  }
  driver.quit()

}
