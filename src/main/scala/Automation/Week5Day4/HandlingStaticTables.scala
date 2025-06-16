package Automation.Week5Day4

import org.openqa.selenium.{By, Dimension, Point, WebDriver, WebElement, JavascriptExecutor}
import org.openqa.selenium.chrome.ChromeDriver

import scala.jdk.CollectionConverters._


object HandlingStaticTables extends App {

  //  Set Up
  val driver: WebDriver = new ChromeDriver()
  driver.get("https://w3schools.com/html/html_tables.asp")
  driver.manage().window().setPosition(new Point(-1920, 0))
  driver.manage().window().setSize(new Dimension(960, 1080))

  //  So by using the >1 in the xpath below it takes all the rows that match that
  val rows: java.util.List[WebElement] = driver.findElements(By.xpath("//table[@id='customers']/tbody/tr[position() > 1]"))

  for (i <- 0 until rows.size()) {
    val cols = rows.get(i).findElements(By.tagName("td"))
    val rowText = cols.toArray.map(_.asInstanceOf[org.openqa.selenium.WebElement].getText).mkString("|")
    println(s"Row ${i + 1}: $rowText")
  }

  driver.quit()

}
