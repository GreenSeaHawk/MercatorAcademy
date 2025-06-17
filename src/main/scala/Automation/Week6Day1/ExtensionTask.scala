package Automation.Week6Day1

import Automation.Week6Day1.AfternoonTask.takeScreenshot
import org.openqa.selenium.{By, OutputType, WebDriver, WebElement}
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.io.FileHandler

import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import scala.collection.mutable.ListBuffer


object ExtensionTask extends App {

  val driver: WebDriver = new ChromeDriver()
  driver.get("https://the-internet.herokuapp.com/tables")

  val pathToFolder: String = "/Users/andrew.boyce/Documents/Screenshots/ExtensionTaskW6D1"
  //  val timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date())
  val screenshotsDir = new File(pathToFolder)
  if (!screenshotsDir.exists()) {
    screenshotsDir.mkdirs()
    println("New directory created")
  }

  // Imported from AfternoonTask
  takeScreenshot(driver, basePath = pathToFolder, prefix = "FullPage")

  //  Set up util to capture an element and not the full screen
  def captureElement(
                      element: WebElement,
                      basePath: String = pathToFolder,
                      prefix: String = ""
                    ): Unit = {
    val timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date())
    val srcElement: File = element.getScreenshotAs(OutputType.FILE) // Capture only that element
    val destination = new File(s"${basePath}/${prefix}_${timeStamp}.png")
    FileHandler.copy(srcElement, destination)
    println(s"Screenshot saved to: ${destination.getAbsolutePath}")
  }

  // Make a function that captures the elements from the table
  def extractColumnFromTable(
                              driver: WebDriver,
                              tableRowSelector: By, // Can change this to capture a different table
                              columnIndex: Int = 0 // Can change this to get a different column
                            ): ListBuffer[String] = {
    // Get all the rows
    val rows: java.util.List[WebElement] = driver.findElements(tableRowSelector)
    // Create an empty list
    val columnValues = ListBuffer.empty[String]
    // Go through all the rows from the table and add each element to the list
    for (i <- 0 until rows.size()) {
      val row = rows.get(i)
      val cols = row.findElements(By.tagName("td"))
      val value = cols.get(columnIndex).getText
      columnValues += value
    }
    // Return the now appended to list
    columnValues
  }


  // Capture table1 before any sorting
  val table1: WebElement = driver.findElement(By.id("table1"))
  captureElement(table1, prefix = "Table1")

  // Click on last name to sort alphabetically and capture
  val lastNameHeader: WebElement = driver.findElement(By.cssSelector("#table1 > thead > tr > th:nth-child(1)"))
  lastNameHeader.click()
  captureElement(table1, prefix = "Table1_sorted_once")

  val lastNamesList: ListBuffer[String] = extractColumnFromTable(
    driver = driver,
    tableRowSelector = By.cssSelector("#table1 tbody tr"),
    columnIndex = 0
  )
  println("Sorted alphabetically? " + (lastNamesList == lastNamesList.sorted))

  // Click again and capture again
  lastNameHeader.click()
  captureElement(table1, prefix = "Table1_sorted_twice")
  val lastNamesList2: ListBuffer[String] = extractColumnFromTable(
    driver = driver,
    tableRowSelector = By.cssSelector("#table1 tbody tr"),
    columnIndex = 0
  )
  println("Sorted in reverse? " + (lastNamesList2 == lastNamesList2.sorted(Ordering[String].reverse)))

  driver.quit()

  //  RESEARCH


}
