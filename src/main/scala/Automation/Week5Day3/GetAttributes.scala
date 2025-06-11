package Automation.Week5Day3

import org.openqa.selenium.{By, Dimension, JavascriptExecutor, Point, WebDriver, WebElement}
import org.openqa.selenium.chrome.ChromeDriver

object BrowserMethodsContinued extends App {

  val driver: WebDriver = new ChromeDriver()
//  driver.get("https://proleed.academy/exercises/selenium/selenium-element-id-locators-practice-form.php")
  driver.get("https://www.selenium.dev/selenium/web/web-form.html")
  driver.manage().window().setPosition(new Point(-1920, 0))
  driver.manage().window().setSize(new Dimension(960, 1080))

  val text: WebElement = driver.findElement(By.id("my-text-id"))
  text.sendKeys("Welcome to Mercator")
  println(text.getAttribute("value")) // Welcome to Mercator
  println(text.getAttribute("type")) // text
  text.clear()
  println(text.getAttribute("value")) // Empty string
  val js = driver.asInstanceOf[JavascriptExecutor]

//  The below code retrieves all the attributes of a WebElement
  val attributes = js.executeScript(
    """
    var items = {};
    for (var index = 0; index < arguments[0].attributes.length; ++index) {
        items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value;
    }
    return items;
  """,
    text
  ).asInstanceOf[java.util.Map[String, String]]
  println("Attributes of text " + attributes)

  val button: WebElement = driver.findElement(By.cssSelector("[type=\"submit\"]"))
  val attributesOfButton = js.executeScript(
    """
    var items = {};
    for (var index = 0; index < arguments[0].attributes.length; ++index) {
        items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value;
    }
    return items;
  """,
    button
  ).asInstanceOf[java.util.Map[String, String]]
  println("Attributes of button " + attributesOfButton)
  println(button.getAttribute("href")) // null

  driver.navigate().to("https://www.selenium.dev/")

  Thread.sleep(2000)

  driver.quit()





}
