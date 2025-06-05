package Automation.Week4Day4

import Automation.Week4Day4.FindByTask.driver
import org.openqa.selenium.{By, WebDriver, WebElement}
import org.openqa.selenium.chrome.ChromeDriver

object LocatorIdentificationTask extends App {

  val driver: WebDriver = new ChromeDriver()
  driver.get("https://www.google.com")

  val buttons: java.util.List[WebElement] = driver.findElements(By.tagName("button"))

  println("There are " + buttons.size() + " buttons")

  buttons.forEach { button =>
    println(s"Text: '${button.getText}', Tag: ${button.getTagName}, Enabled: ${button.isEnabled}")
  }

  val images: java.util.List[WebElement] = driver.findElements(By.tagName("img"))

  println("There are " + images.size() + " images")

  images.forEach { image =>
    println(s"src: '${image.getAttribute("src")}', alt: ${image.getAttribute("alt")}, title: ${image.getAttribute("title")}")
    println("Image displayed: " + image.isDisplayed)
  }

  val aLinks: java.util.List[WebElement] = driver.findElements(By.tagName("a"))

  println("There are " + aLinks.size() + " <a> tags")

  val h1Instances: java.util.List[WebElement] = driver.findElements(By.tagName("h1"))

  println("There are " + h1Instances.size() + " <h1> tags")



  driver.quit()


}
