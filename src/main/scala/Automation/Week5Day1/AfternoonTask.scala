package Automation.Week5Day1

import Automation.Week4Day4.FindByTask.driver
import org.openqa.selenium.{By, Dimension, JavascriptExecutor, Point, WebDriver, WebElement}
import org.openqa.selenium.chrome.ChromeDriver

object AfternoonTask extends App {

//  SET UP
  val driver: WebDriver = new ChromeDriver()
  driver.get("https://testpages.herokuapp.com/styled/basic-html-form-test.html")
//  Set the browser to fit on the left-hand side of my monitor (this broke EvilTester)
//  driver.manage().window().setPosition(new Point(-1920, 0))
//  driver.manage().window().setSize(new Dimension(960, 1080))

//  Username
  val username: WebElement = driver.findElement(By.name("username"))
  username.sendKeys("AndrewBoyce")
  println("AndrewBoyce entered into username field, method: By.name")

//  Password
  val password: WebElement = driver.findElement(By.cssSelector("#HTMLFormElements > table > tbody > tr:nth-child(2) > td > input[type=password]"))
  password.sendKeys("password123")
  println("password123 entered into password field, method: By.cssSelector")

//  TextArea
  val textArea: WebElement = driver.findElement(By.tagName("textarea"))
  textArea.sendKeys("I wrote some text, how cool is that?")
  println("Text entered into TextArea Comment, method: By.tagName")

  Thread.sleep(2000)

//  Checkbox
  val checkbox2: WebElement = driver.findElement(By.xpath("//*[@id=\"HTMLFormElements\"]/table/tbody/tr[5]/td/input[2]"))
  checkbox2.click()
  println("Checkbox 2 has been selected, method: By.xpath")

  Thread.sleep(2000)

  //  Submit
  val submitButtons: java.util.List[WebElement] = driver.findElements(By.className("styled-click-button"))
  val submit: WebElement = submitButtons.get(1)
  submit.click()
  println("Submit button has been clicked, method: By.className")

  Thread.sleep(4000)

//  Link
  driver.navigate().back()
  println("Navigated back to the original page")

  Thread.sleep(4000)

//  Scroll to the bottom of the browser
//  val js = driver.asInstanceOf[JavascriptExecutor]
//  js.executeScript("window.scrollTo(0, document.body.scrollHeight);")

  val evilTester: WebElement = driver.findElement(By.linkText("EvilTester.com"))
  evilTester.click()
  println("EvilTester.com link clicked, method: By.linkText")

  Thread.sleep(2000)

  driver.quit()





}
