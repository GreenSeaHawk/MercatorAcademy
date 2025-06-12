package Automation.Week5Day3

import org.openqa.selenium.{By, Dimension, JavascriptExecutor, Point, WebDriver, WebElement}
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.Select
import scala.jdk.CollectionConverters._

object AfternoonTask extends App {

  //  Set Up
  val driver: WebDriver = new ChromeDriver()
  driver.get("https://testpages.herokuapp.com/styled/index.html")
  driver.manage().window().maximize()
  driver.findElement(By.linkText("HTML Form Example")).click()
  val js = driver.asInstanceOf[JavascriptExecutor]


  Thread.sleep(2000)

  driver.manage().window().setPosition(new Point(-1920, 0))
  driver.manage().window().setSize(new Dimension(960, 1080))

  val username: WebElement = driver.findElement(By.cssSelector("#HTMLFormElements > table > tbody > tr:nth-child(1) > td > input[type=text]"))
  val password: WebElement = driver.findElement(By.cssSelector("#HTMLFormElements > table > tbody > tr:nth-child(2) > td > input[type=password]"))
  username.sendKeys("Andrew")
  password.sendKeys("password123")
  println("Username and password fields entered")

  val checkbox2: WebElement = driver.findElement(By.cssSelector("#HTMLFormElements > table > tbody > tr:nth-child(5) > td > input[type=checkbox]:nth-child(3)"))
  if (!checkbox2.isSelected()) {
    checkbox2.click()
    println("Checkbox 2 has been selected")
  }
  else {
    println("Checkbox 2 is already selected")
  }
  val radioBox3: WebElement = driver.findElement(By.cssSelector("#HTMLFormElements > table > tbody > tr:nth-child(6) > td > input[type=radio]:nth-child(4)"))
  radioBox3.click()
  println("Radio 3 selected")

  val dropdown: WebElement = driver.findElement(By.name("dropdown"))
  val select = new Select(dropdown)
  select.selectByVisibleText("Drop Down Item 5")
  println(select.getFirstSelectedOption.getText)

  Thread.sleep(2000)

  val submit: WebElement = driver.findElement(By.cssSelector("#HTMLFormElements > table > tbody > tr:nth-child(9) > td > input:nth-child(2)"))
  js.executeScript("arguments[0].scrollIntoView();", submit)
  submit.click()

  Thread.sleep(2000)

  val backToForm: WebElement = driver.findElement(By.cssSelector("#back_to_form"))
  js.executeScript("arguments[0].scrollIntoView();", backToForm)
  backToForm.click()

  Thread.sleep(2000)

//  EXTENSION
//  driver.manage().window().setPosition(new Point(-1920, 0))
//  driver.manage().window().setSize(new Dimension(960, 1080))
  driver.navigate().to("https://www.tutorialspoint.com/selenium/practice/alerts.php")
  println("Navigated to " + driver.getCurrentUrl)

  driver.findElement(By.cssSelector("body > main > div > div > div.col-md-8.col-lg-8.col-xl-8 > div:nth-child(2) > button")).click()
  driver.switchTo().alert().accept()
  println("Alert accepted")

  Thread.sleep(2000)

  driver.findElement(By.cssSelector("body > main > div > div > div.col-md-8.col-lg-8.col-xl-8 > div:nth-child(4) > button")).click()
  driver.switchTo().alert().dismiss()
  println("Alert dismissed")

  Thread.sleep(2000)

  driver.findElement(By.cssSelector("body > main > div > div > div.col-md-8.col-lg-8.col-xl-8 > div:nth-child(5) > button")).click()
  driver.switchTo().alert().sendKeys("LOOK WHAT I WROTE!")
  driver.switchTo().alert().accept()
  println("Text entered into prompt and then accepted")

  Thread.sleep(2000)

  driver.navigate().to("https://www.w3schools.com/html/tryit.asp?filename=tryhtml_iframe")
  println("Navigated to " + driver.getCurrentUrl)
  val acceptAllButton: WebElement = driver.findElement(By.id("accept-choices"))
  acceptAllButton.click()
  val allFrames: java.util.List[WebElement] = driver.findElements(By.tagName("iframe"))
  println("There are " + allFrames.size() + " frames on the webpage. These are listed below:")
  for (frame <- allFrames.asScala) {
    println(frame.getAttribute("name"))
  }

  driver.switchTo().frame("iframeResult")
  val currentFrame = js.executeScript("return self.name").toString
  println(s"Current frame name: $currentFrame")

  println("Text displayed in the iframe:")
  println(driver.findElement(By.cssSelector("body > p")).getText)

  Thread.sleep(2000)

  driver.navigate().to("https://www.tutorialspoint.com/selenium/practice/nestedframes.php")
  println("Navigated to " + driver.getCurrentUrl)


//  Find all the frames in the webpage
  val allFrames2: java.util.List[WebElement] = driver.findElements(By.tagName("iframe"))
  println("There are " + allFrames2.size() + " frames on the webpage. These are listed below:")
  for (frame <- allFrames2.asScala) {
    println(frame.getAttribute("name"))
  }
//  Switch to frame1
  driver.switchTo().frame("frame1")
  val allFrames3: java.util.List[WebElement] = driver.findElements(By.tagName("iframe"))
  println("There are " + allFrames3.size() + " frames within frame1. These are listed below:")
  for (frame <- allFrames3.asScala) {
    println(frame.getAttribute("name"))
  }


  driver.quit()

//  RESEARCH
//  1/ File upload/download
//  You would do something like this to upload:
//  val uploadElement = driver.findElement(By.id("file-upload"))
//  uploadElement.sendKeys("/path/to/your/file.txt")
//  To download something you need to configure your WebDriver to have a path to a download
//  directory. Then you just click on the "download file" button and it should work.
//  https://www.pixelqa.com/blog/post/handling-file-uploads-and-downloads-in-selenium-java-with-autoit-and-robot-class#1

//  2/ Scrolling
//  Already done this in previous scripts but something like this:
//  val js = driver.asInstanceOf[JavascriptExecutor]
//  val element = driver.findElement(By.id("footer"))
//  js.executeScript("arguments[0].scrollIntoView(true);", element) element comes into view
//  js.executeScript("window.scrollTo(0, document.body.scrollHeight);") scroll to the bottom

//  3/ Dynamic dropdown
//  This is when what appears in a dropdown isn't hard-coded into the HTML. For example,
//  a search bar. It might take a while for the elements to appear in the dropdown menu
//  and thus into the DOM. So implementing a wait is a good idea:
//  val wait = new WebDriverWait(driver, Duration.ofSeconds(10))
//  val option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='Desired Option']")))
//  option.click()

//  4/ Shadow DOM
//  A shadow DOM is a special part of the DOM that's encapsulated from the main document.
//  Elements inside it cannot be accessed with normal Selenium selectors.
//  Might look something like this:
//  <custom-element>
//    #shadow-root (open)
//    <div id="inside-shadow">Hello</div>
//  </custom-element>
//  To deal with this you need to use the JavascriptExecutor, something like this:
//  // Step 1: Find the shadow host
//  val shadowHost: WebElement = driver.findElement(By.cssSelector("custom-element"))
//
//  // Step 2: Access the shadow root via JavaScript
//  val jsExecutor = driver.asInstanceOf[JavascriptExecutor]
//  val shadowRoot = jsExecutor.executeScript("return arguments[0].shadowRoot", shadowHost).asInstanceOf[WebElement]
//
//  // Step 3: Find element inside the shadow DOM
//  val insideShadow: WebElement = shadowRoot.findElement(By.cssSelector("#inside-shadow"))
//
//  // Step 4: Do something with the element
//  println("Text inside shadow DOM: " + insideShadow.getText)

}
