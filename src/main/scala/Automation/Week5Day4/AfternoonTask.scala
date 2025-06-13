package Automation.Week5Day4

import org.openqa.selenium.{By, Dimension, JavascriptExecutor, Point, WebDriver, WebElement}
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.{ExpectedConditions, FluentWait, WebDriverWait}

import java.time.Duration
import scala.collection.mutable.ListBuffer

object AfternoonTask extends App {

  //  Set Up
  val driver: WebDriver = new ChromeDriver()
  driver.get("https://cosmocode.io/automation-practice-webtable/")
  driver.manage().window().setPosition(new Point(-1920, 0))
  driver.manage().window().setSize(new Dimension(960, 1080))
//  driver.manage().window().maximize()
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
    //    Thread.sleep(10) // So I can see it being highlighted
    //  Revert the row back to its original colour
    js.executeScript("arguments[0].style.backgroundColor = arguments[1]", row, originalColor)
  }

  //  EXTENSION
  //  1/ JavaScript Executor
  //  Scroll to the bottom of the page:
  js.executeScript("window.scrollTo(0,document.body.scrollHeight);")
  //  Thread.sleep(1000)
  //  Change the background colour of the "Country" column header
  val countryColumnHeader: WebElement = driver.findElement(By.xpath("//*[@id=\"countries\"]/tbody/tr[1]/td[2]"))
  js.executeScript("arguments[0].style.backgroundColor = 'blue'", countryColumnHeader)
  //  Scroll to the top to see the changes
  js.executeScript("window.scrollTo(0,0);")
  //  Thread.sleep(1000)
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
      //      Thread.sleep(50)
    }
    languageText.split(";").map(_.trim).foreach { language =>
      allLanguages += language

    }
  }
  println("A list of all the official languages in the world:\n" + allLanguages)
  //  Assert that French is a listed language
  assert(allLanguages.contains("French"))

  Thread.sleep(2000)

  //  RESEARCH
  //  1. What is the Selenium Actions class, and why is it used?
  //  The Actions class in Selenium is used to simulate advanced user interactions with web
  //  elements. It allows you to build complex sequences of input like:
  //  Hovering
  //  Clicking and holding
  //  Drag and drop
  //  Double-click
  //  Right-click (context click)
  //  Keyboard actions
  //  This is essential when dealing with dynamic UI elements that require interaction patterns
  //  not handled by simple click() or sendKeys().
  //
  //  2. Five (six) Commonly Used Methods in Actions Class
  //| Method            | Description                                  | Example Use Case                      |
  //| ----------------- | -------------------------------------------- | ------------------------------------- |
  //| `moveToElement()` | Moves the mouse to the middle of the element | Hover to reveal a dropdown menu       |
  //| `click()`         | Clicks at the current mouse location         | Clicking after moving over an element |
  //| `clickAndHold()`  | Clicks without releasing the mouse           | Starting a drag operation             |
  //| `release()`       | Releases the mouse button                    | Ending a drag operation               |
  //| `sendKeys()`      | Sends keyboard keys to the focused element   | Typing into a field or using hotkeys  |
  //| `contextClick()`  | Performs a right-click                       | Opening a context menu                |
  //
  //  3. How do you chain multiple actions together using the Actions class?
  val actions = new Actions(driver)

  val ukCheckbox: WebElement = driver.findElement(By.cssSelector("#countries > tbody > tr:nth-child(187) > td:nth-child(1) > input"))
  js.executeScript("arguments[0].scrollIntoView();", ukCheckbox)

  Thread.sleep(1000)
  println("UK checkbox is selected: " + ukCheckbox.isSelected)
  actions.moveToElement(ukCheckbox).click().perform()
  println("UK checkbox is selected: " + ukCheckbox.isSelected)
  Thread.sleep(1000)

  js.executeScript("window.scrollTo(0,0);")
  Thread.sleep(2000)

  val seleniumTutorials: WebElement = driver.findElement(By.linkText("Selenium Tutorials"))
  js.executeScript("arguments[0].scrollIntoView();", seleniumTutorials)
  println("Selenium Tutorials Displayed: " + seleniumTutorials.isDisplayed)
  println("Selenium Tutorials Enabled: " + seleniumTutorials.isEnabled)
  actions.moveToElement(seleniumTutorials).perform()
  println("Selenium Tutorials Displayed: " + seleniumTutorials.isDisplayed)
  println("Selenium Tutorials Enabled: " + seleniumTutorials.isEnabled)
  val systemSetup: WebElement = driver.findElement(By.partialLinkText("1. SYSTEM SETUP"))
  println("System Setup Displayed: " + systemSetup.isDisplayed)
  println("System Setup Enabled: " + systemSetup.isEnabled)
  js.executeScript("arguments[0].scrollIntoView();", systemSetup)
  actions.moveToElement(systemSetup).click().perform()

  Thread.sleep(2000)


  //  4. Difference Between click() and moveToElement().click()
  //  click(): Clicks at the current mouse location or the active element.
  //  moveToElement(element).click(): Ensures the mouse is moved to the specified element before
  //  clicking.
  //  Use Case: When an element needs to be hovered over or in view before clicking it (e.g. for
  //  dynamic menus), moveToElement().click() is more reliable.
  //

  //  5. Drag and Drop
  //  val source = driver.findElement(By.id("draggable"))
  //  val target = driver.findElement(By.id("droppable"))
  //  val actions = new Actions(driver)
  //  actions.dragAndDrop(source, target).perform()
  //  Alternate option:
  //  actions.clickAndHold(source)
  //    .moveToElement(target)
  //    .release()
  //    .perform()

  //  6. Handling Covered or Invisible Elements
  //  Common Exceptions:
  //  ElementNotInteractableException
  //  ElementClickInterceptedException
  //  Handling Tips:
  //  Use explicit waits to ensure visibility.
  //  Scroll into view using JavaScript
  //  Use moveToElement to hover over or focus the element.
  //

  //  7. Actions Class in Headless Mode
  //  Yes, it can be used, but with caveats:
  //  Visual feedback is not available.
  //  Mouse movement coordinates may not behave identically.
  //  Some interactions (like drag-and-drop) might fail if the rendering engine optimizations
  //  skip animations.
  //
  //  What is headless mode?
  //  Headless mode in Selenium (or in browsers generally) refers to running a web browser without
  //  a graphical user interface (GUI). This means the browser operates in the background,
  //  performing all actions (like loading pages, clicking buttons, etc.) without opening a
  //  visible window.
  //
  //  Why use headless mode?
  //  Faster execution: No need to render UI elements on screen.
  //  Ideal for automation: Great for CI/CD pipelines or servers without display environments.
  //  Lower resource consumption: Saves memory and CPU compared to full browser mode.
  //
  //  8. TouchActions in Selenium
  //  TouchActions is an extension of Actions, meant for simulating gestures on mobile devices
  //  (tap, flick, scroll).
  //  Works with mobile emulators or real devices via Appium.
  //  Use Case: When testing mobile web or native apps.
  //
  //  9. moveToElement() vs moveByOffset()
  //  moveToElement(el): Moves to a specific WebElement.
  //  Use when the element is identifiable (e.g., a button).
  //  moveByOffset(x, y): Moves relative to the current mouse position.
  //  Use when precise cursor placement is needed (e.g., within a canvas or pixel-specific areas).
  //  example:
  //  actions.moveByOffest(100,200).click().perform()
  //
  //  10. Real-world example:
  //  Couldn't find one but the problem I'm working on right now would probably fit the description
  //  See Q3


  driver.quit()
}
