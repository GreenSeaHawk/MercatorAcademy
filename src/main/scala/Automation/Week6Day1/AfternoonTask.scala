package Automation.Week6Day1

import org.openqa.selenium.{By, OutputType, TakesScreenshot, WebDriver, WebElement}
import org.openqa.selenium.chrome.{ChromeDriver, ChromeOptions}
import org.openqa.selenium.io.FileHandler
import org.openqa.selenium.support.ui.{ExpectedConditions, WebDriverWait}

import java.io.File
import java.text.SimpleDateFormat
import java.time.Duration
import java.util.Date

object AfternoonTask extends App {

  // Set up headless browser
  val options = new ChromeOptions()
  options.addArguments("--headless=new")
  val driver: WebDriver = new ChromeDriver(options)

  // Util screenshot function
  def takeScreenshot(
                      driver: WebDriver,
                      basePath: String = "/Users/andrew.boyce/Documents/Screenshots/AfternoonTaskW6D1",
                      prefix: String = ""
                    ): Unit = {
    // Set up params with default options
    // Create a timestamp
    val timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date())
    // Take a screenshot
    val screenshotFile = driver.asInstanceOf[TakesScreenshot].getScreenshotAs(OutputType.FILE)
    // Create a destination based on input strings and timestamp
    val destination = new File(s"${basePath}/${prefix}_${timeStamp}.png")
    // Save the screenshot to the destination
    FileHandler.copy(screenshotFile, destination)
    // Print out where the screenshot has been saved locally
    println(s"Screenshot saved to: ${destination.getAbsolutePath}")
  }

  try {
    driver.get("https://the-internet.herokuapp.com/login")

    driver.findElement(By.id("username")).sendKeys("tomsmith")
    driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!") // Happy path
    //    driver.findElement(By.id("password")).sendKeys("WrongPassword!") // Sad path
    driver.findElement(By.cssSelector("#login > button")).click()


    // Record the start time to see how long Selenium waited
    val startTime = System.nanoTime()

    // Wait for Logout button
    val explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5))
    val logout: WebElement = explicitWait.until(
      ExpectedConditions.elementToBeClickable(By.linkText("Logout"))
    ) // Happy path
    // Get it to fail based on fake id
    //    val falseID: WebElement = explicitWait.until(
    //      ExpectedConditions.visibilityOfElementLocated(By.id("This id doesn't appear on the page"))
    //    ) // Sad path

    // Record the end time and calculate
    val endTime = System.nanoTime()
    // Calculate the duration in seconds (or milliseconds)
    val durationSeconds = (endTime - startTime) / 1_000_000_000.0
    println(f"Selenium waited for approximately $durationSeconds seconds")

    // Make a directory for this tasks' screenshots
    val screenshotsDir = new File("/Users/andrew.boyce/Documents/Screenshots/AfternoonTaskW6D1")
    if (!screenshotsDir.exists()) {
      screenshotsDir.mkdirs()
      println("New directory created")
    }

    takeScreenshot(driver, prefix = "success")
  }
  catch {
    case e: Exception =>
      println("Exception: " + e.getMessage)
      takeScreenshot(driver, prefix = "failure")

  }
  finally {
    driver.quit()
    println("driver quit successfully")
  }
}
