package Automation.Week5Day4

import org.openqa.selenium.{By, Dimension, JavascriptExecutor, Point, WebDriver, WebElement}
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.{ExpectedConditions, FluentWait, WebDriverWait}

import java.time.Duration
import scala.collection.mutable.ListBuffer

object AfternoonTask extends App {

  //  Set Up
  val driver: WebDriver = new ChromeDriver()
  driver.get("https://cosmocode.io/automation-practice-webtable/")
  driver.manage().window().setPosition(new Point(-1920, 0))
  driver.manage().window().setSize(new Dimension(960, 1080))
  val js = driver.asInstanceOf[JavascriptExecutor]
  //  Implicit Wait
  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10))

  //  Explicit Wait
  // Record the start time to see how long Selenium waited
  val startTime = System.nanoTime()

  val explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10))
  val checkbox: WebElement = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"countries\"]/tbody/tr[66]")))

  // Record the end time and calculate
  val endTime = System.nanoTime()
  // Calculate the duration in seconds (or milliseconds)
  val durationSeconds = (endTime - startTime) / 1_000_000_000.0
  println(f"Selenium waited for approximately $durationSeconds seconds")

  println("Germany is visible, yay!")

  //  Get the row with germany in it:
  val germany: WebElement = driver.findElement(By.xpath("//*[@id=\"countries\"]/tbody/tr[66]"))
  //  Get all the table cells within that row as a list:
  val colGermany: java.util.List[WebElement] = germany.findElements(By.tagName("td"))
  //  Print this nicely:
  println(s"German Capital: ${colGermany.get(2).getText}, German Currency: ${colGermany.get(3).getText}")

  //  Static Table Handling
  //  So by using the > 1 in the xpath below it takes all the rows that match that
  //  In this case all but the header row
  val rows: java.util.List[WebElement] = driver.findElements(By.xpath("//*[@id=\"countries\"]/tbody/tr[position() > 1]"))

  //  Example of what happens if you print the first row:
  //  println(rows.get(0).getText) // Afghanistan Kabul Afghani Dari Persian; Pashto

  // Iterate through all the rows:
  for (i <- 0 until rows.size()) {
    //    Get each row:
    val row = rows.get(i)
    //  Split up each row into a list by column:
    val cols: java.util.List[WebElement] = row.findElements(By.tagName("td"))
    // Not needed:
    //    for (j <- 0 until cols.size()){ //cols.size() = 5
    //      println(cols.get(j).getText) // This prints out every item from the row
    //    }
    // Prints out just the country
    println(s"Country ${i + 1}: " + cols.get(1).getText)
    //  Find the original background colour for each row
    val originalColor = js.executeScript("return window.getComputedStyle(arguments[0]).backgroundColor", row).toString
    //  Make the background of each row yellow as we go through it:
    js.executeScript("arguments[0].style.backgroundColor = 'yellow'", row)
    js.executeScript("arguments[0].scrollIntoView();", row)
    Thread.sleep(10) // So I can see it being highlighted
    //  Revert the row back to its original colour
    js.executeScript("arguments[0].style.backgroundColor = arguments[1]", row, originalColor)
  }

  //  EXTENSION
  //  1/ JavaScript Executor
  //  Scroll to the bottom of the page:
  js.executeScript("window.scrollTo(0,document.body.scrollHeight);")
  Thread.sleep(1000)
  //  Change the background colour of the "Country" column header
  val countryColumnHeader: WebElement = driver.findElement(By.xpath("//*[@id=\"countries\"]/tbody/tr[1]/td[2]"))
  js.executeScript("arguments[0].style.backgroundColor = 'blue'", countryColumnHeader)
  //  Scroll to the top to see the changes
  js.executeScript("window.scrollTo(0,0);")
  Thread.sleep(1000)
  // Print out the page title
  println("Page title: " + driver.getTitle)

  //  2/ Fluent Wait
  //  First we embark on the search for India
  var india: WebElement = null
  for (i <- 0 until rows.size()) {
    //    Get each row:
    val row = rows.get(i)
    //  Split up each row into a list by column:
    val cols: java.util.List[WebElement] = row.findElements(By.tagName("td"))
    // Find India
    if (cols.get(1).getText == "India") {
      india = row
      println("We found India: " + india.getText)
    }
  }
  val fluentWait = new FluentWait[WebDriver](driver)
    .withTimeout(Duration.ofSeconds(15)) // Max total time until an exception is raised
    .pollingEvery(Duration.ofMillis(500)) // How frequently the condition is checked
    .until(ExpectedConditions.visibilityOf(india)) //condition to wait for eg visible

  println("We waited waited fluently for: " + fluentWait.getText)

  // 3/ Table Validation
  //  Get a list of all the languages in the table
  val allLanguages: ListBuffer[String] = ListBuffer.empty[String]
  for (i <- 0 until rows.size()) {
    //    Get each row:
    val row = rows.get(i)
    //  Split up each row into a list by column:
    val cols: java.util.List[WebElement] = row.findElements(By.tagName("td"))
    //  There are multiple languages for some countries so need to split them up
    // split on ; then map(_.trim) gets rid of any whitespace
    val languageText = cols.get(4).getText
    // If French is in the row then scroll into view and make it green
    if (languageText.contains("French")) {
      js.executeScript("arguments[0].scrollIntoView();", row)
      js.executeScript("arguments[0].style.backgroundColor = 'green'", row)
      Thread.sleep(50)
    }
    languageText.split(";").map(_.trim).foreach { language =>
      allLanguages += language

    }
  }
  println("A list of all the official languages in the world:\n" + allLanguages)
  //  Assert that French is a listed language
  assert(allLanguages.contains("French"))


  driver.quit()


}
