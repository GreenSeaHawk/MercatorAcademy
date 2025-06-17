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

  //  Pagination

  //  How can Selenium be used to test data consistency across paginated tables (e.g.,
  //  validating that items are not duplicated or skipped across pages)?
  //  You can create a list/set of data and when switching pages you can check that this
  //  information isn't duplicated by finding out if the new data you just scraped is in
  //  the list/set already collated.

  //  What are some best practices for writing scalable Selenium tests that verify pagination
  //  works correctly even when the dataset size changes over time?
  //  Best practice:
  //  - Use dynamic waits
  //  - Don't hardcode table sizes
  //  - Test edge cases like first and last page

  //  Dynamic Tables

  //  What are the main challenges in locating and verifying content in dynamic tables that
  //  are updated via JavaScript/AJAX?
  //  There could be timing issues with loading of tables/rows
  //  A table could update without reloading the page

  //  How can you ensure stability in element locators when working with dynamic tables that
  //  re-render DOM elements frequently?
  //  Use explicit waits and expected conditions to make sure the content has loaded
  //  Use stable selectors instead of xpath
  //  Re-fetch elements after each interaction

  //  Dynamic Dropdown Lists

  //  What are effective strategies to handle dropdowns whose options are loaded
  //  asynchronously based on user input (e.g., country → city)?
  //  Use actions to hover over an element or click something to expose what we want

  //  How does the use of Select2, Chosen, or other JavaScript-powered dropdown libraries
  //  affect test implementation in Selenium?
  //  They often hide the native select and use div-based UI.
  //  So you need to use strategies that don't rely on that.
}
