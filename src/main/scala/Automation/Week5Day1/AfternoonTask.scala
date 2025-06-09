package Automation.Week5Day1


import org.openqa.selenium.{By, Dimension, Point, WebDriver, WebElement}
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.{ExpectedCondition, WebDriverWait}
import org.openqa.selenium.JavascriptExecutor
import java.time.Duration

object AfternoonTask extends App {

//  SET UP
  val driver: WebDriver = new ChromeDriver()
  driver.get("https://testpages.herokuapp.com/styled/basic-html-form-test.html")
//  Set the browser to fit on the left-hand side of my monitor (this broke EvilTester)
  driver.manage().window().setPosition(new Point(-1920, 0))
  driver.manage().window().setSize(new Dimension(960, 1080))

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

  Thread.sleep(4000)

//  Scroll Down/Resize
  val js = driver.asInstanceOf[JavascriptExecutor]
//  js.executeScript("window.scrollTo(0, document.body.scrollHeight);")


  Thread.sleep(1000)

//  Submit
  val submitButtons: java.util.List[WebElement] = driver.findElements(By.className("styled-click-button"))
  val submit: WebElement = submitButtons.get(1)
  js.executeScript("arguments[0].scrollIntoView();", submit)
  submit.click()
  println("Submit button has been clicked, method: By.className")

//  val submit: WebElement = driver.findElement(By.cssSelector("#HTMLFormElements > table > tbody > tr:nth-child(9) > td > input:nth-child(2)"))
//  submit.click()
//  println("Submit button has been clicked, method: By.cssSelector")

//  val myDynamicSubmit = new WebDriverWait(driver, Duration.ofSeconds(10)).until(
//    new ExpectedCondition[WebElement] {
//      override def apply(d: WebDriver) = d.findElement(By.cssSelector("#HTMLFormElements > table > tbody > tr:nth-child(9) > td > input:nth-child(2)"))
//    })
//  myDynamicSubmit.click()

  Thread.sleep(4000)

//  Link
  driver.navigate().back()
  println("Navigated back to the original page")

  Thread.sleep(4000)

  val evilTester: WebElement = driver.findElement(By.linkText("EvilTester.com"))
  js.executeScript("arguments[0].scrollIntoView();", evilTester)
  evilTester.click()
  println("EvilTester.com link clicked, method: By.linkText")

//  val myDynamicEvilTester = new WebDriverWait(driver, Duration.ofSeconds(10)).until(
//    new ExpectedCondition[WebElement] {
//      override def apply(d: WebDriver) = d.findElement(By.linkText("EvilTester.com"))
//    })
//  myDynamicEvilTester.click()

  Thread.sleep(4000)

  driver.quit()





}
