package Automation.Week4Day4

import Automation.Week4Day4.ByID.driver
import org.openqa.selenium.{By, WebDriver, WebElement}
import org.openqa.selenium.chrome.ChromeDriver

object FindByTask extends App {

  //  Instantiating the ChromeDriver for interacting with the Chrome browser
  val driver: WebDriver = new ChromeDriver()
//  val driver2: WebDriver = new ChromeDriver()


  //  Accessing the web url for testing
  driver.get("https://selenium.dev/selenium/web/web-form.html")
//  driver2.get("https://selenium.dev/selenium/web/index.html")

//  By ID
  val textInput: WebElement = driver.findElement(By.id("my-text-id"))
  textInput.sendKeys("Andrew")
  println("Andrew entered into text input")

//  By name
  val password: WebElement = driver.findElement(By.name("my-password"))
  password.sendKeys("password123")
  println("Password entered")

  //  By class name
  val formControls: java.util.List[WebElement] = driver.findElements(By.className("form-control"))
  val textArea: WebElement = formControls.get(2)
  textArea.sendKeys("test comment")
  println("Comment entered into text area")

  //  By tag name
  val inputElements: java.util.List[WebElement] = driver.findElements(By.tagName("input"))
  println("There are " + inputElements.size() + " input elements")

//  By LinkText
  val returnToIndex: WebElement = driver.findElement(By.linkText("Return to index"))
  returnToIndex.click()
  println("Return to index clicked")

  //  Return to form page
  driver.navigate().back()
  println("Return to web form page")

  //  By PartialLinkText
  val returnToIndexPartial: WebElement = driver.findElement(By.partialLinkText("Return"))
  returnToIndexPartial.click()
  println("Return to index clicked using partial link text")

  driver.quit()
}
