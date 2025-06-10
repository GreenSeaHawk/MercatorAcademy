package Automation.Week5Day2

import org.openqa.selenium.{By, Dimension, JavascriptExecutor, Point, WebDriver, WebElement}
import org.openqa.selenium.chrome.ChromeDriver

object AfternoonTask3 extends App {

  val driver: WebDriver = new ChromeDriver()
  driver.get("https://demoqa.com/automation-practice-form")
  driver.manage().window().setPosition(new Point(-1920, 0))
  driver.manage().window().setSize(new Dimension(960, 1080))

//  Locate and input if conditions are satisfied
  val firstName: WebElement = driver.findElement(By.cssSelector("#firstName"))
  if (firstName.isDisplayed() && firstName.isEnabled()) {
    firstName.sendKeys("Andrew")
  }

//  Locate and select Hobbies checkbox if not already selected
// So I'm actually clicking on the Sports text and not the checkbox
  val js = driver.asInstanceOf[JavascriptExecutor]
  val hobbiesCheckboxName: WebElement = driver.findElement(By.xpath("//*[@id='hobbiesWrapper']/div[2]/div[1]/label"))
  val hobbiesCheckbox1: WebElement = driver.findElement(By.xpath("//*[@id=\"hobbies-checkbox-1\"]"))
//  val hobbiesCheckbox2: WebElement = driver.findElement(By.xpath("//*[@id=\"hobbies-checkbox-2\"]"))
//  val hobbiesCheckbox3: WebElement = driver.findElement(By.xpath("#hobbies-checkbox-3"))

  println("Sports is displayed? " + hobbiesCheckboxName.isDisplayed())
  js.executeScript("arguments[0].scrollIntoView();", hobbiesCheckboxName)
  if (!hobbiesCheckboxName.isSelected()) {
    hobbiesCheckboxName.click()
    println("Sports checkbox selected")
  }
  println("The checkbox next to Sports is displayed? " + hobbiesCheckbox1.isDisplayed())


//  Submit button
  val submit: WebElement = driver.findElement(By.xpath("//*[@id=\"submit\"]"))
  println("Submit button is displayed? " + submit.isDisplayed())
  println("Submit button is enabled? " + submit.isEnabled())
  if (submit.isEnabled() && submit.isDisplayed()) {
    submit.click()
    println("Submit button clicked") //Highlighted but nothing happens as not all required fields
    //have been filled in
  }

  driver.quit()
  //  EXTENSION RESEARCH

  //  Driver.Navigate is a Selenium WebDriver method that provides a suite of navigation
  //  functionalities, enabling you to perform actions like loading a URL, navigating
  //  forward and backward in the browser’s history, and refreshing the current page.
  //
  //Unlike Driver.Get, which is primarily designed for loading new URLs, Driver.Navigate
  // offers more versatile navigation capabilities, making it ideal for testing scenarios
  // that require interacting with browser navigation states.
  //
  //The key difference is that the navigate API retains cookies with each use. Unlike the
  // get() method, which clears the session state every time it is called, the navigate()
  // method preserves the session state.

}
