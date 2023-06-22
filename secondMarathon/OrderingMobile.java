package testLeafAssignments.marathon.secondMarathon;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.sukgu.Shadow;

public class OrderingMobile {

	public static void main(String[] args) throws InterruptedException, IOException {
		//1. Launch ServiceNow application
		ChromeOptions ch = new ChromeOptions();
		ch.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(ch);
		driver.get("https://dev31913.service-now.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//2. Login with valid credentials username as admin and password 
		driver.findElement(By.xpath("//input[@id='user_name']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@id='user_password']")).sendKeys("q+NozS5Qe8!E");
		driver.findElement(By.xpath("//button[@id='sysverb_login']")).click();
	
		
		//3. Click-All Enter Service catalog in filter navigator and press enter or Click the ServiceCatalog
		Shadow shade = new Shadow(driver);
		shade.setImplicitWait(20);
		shade.findElementByXPath("//div[@id='all']").click();
		//shade.findElementByXPath("//input[id='filter']").sendKeys("Service catalog");
		shade.findElementByXPath("//span[text()='Service Catalog']").click();

		//title="Main Content"
		//4. Click on  mobiles
		WebElement frame = shade.findElementByXPath("//iframe[@id='gsft_main']");
		driver.switchTo().frame(frame);
		driver.findElement(By.xpath("//h2[text()[normalize-space()='Mobiles']]")).click();
	
		//5. Select Apple iphone6s
		Actions action = new Actions(driver);
		action.scrollToElement(driver.findElement(By.xpath("//a/strong[text()='Apple iPhone 6s']"))).perform();	
		driver.findElement(By.xpath("//a/strong[text()='Apple iPhone 6s']")).click();
		
		//6. Update color field to gold and storage field to 128GB
		Select color = new Select(driver.findElement(By.xpath("(//select[@class='form-control cat_item_option '])[2]")));
		color.selectByVisibleText("Gold");
		Select storage = new Select(driver.findElement(By.xpath("(//select[@class='form-control cat_item_option '])[3]")));
		storage.selectByVisibleText("128GB [add $50.00]");
		//storage.selectByValue("onetwentyeight");
		
		//7. Select  Order now option
		Thread.sleep(3000);
		action.moveToElement(driver.findElement(By.xpath("//button/span[text()='Order Now']"))).perform();
		driver.findElement(By.xpath("//button[@name='oi_order_now_button']")).click();
		
		//8. Verify order is placed and copy the request number"
		//driver.switchTo().frame(0);shade.findElementByXPath("//iframe[@id='gsft_main']");shade.findElementByXPath("//iframe");
		WebElement frame1 = shade.findElementByXPath("//iframe[@id='gsft_main']");
		driver.switchTo().frame(frame1);
		
		String message = driver.findElement(By.xpath("//div[@id='sc_order_status_intro_text']/div/span")).getText();
		System.out.println(message);
		if(message.contains("your request has been submitted"))
			System.out.println("Order placed successfully");
		else
			System.out.println("Failed to place the order");
		String reqId = driver.findElement(By.xpath("//a[@id='requesturl']/b")).getText();
		System.out.println("Request Id "+reqId);
		
		//9. Take a snapshot of the ordered page.
		File order = driver.getScreenshotAs(OutputType.FILE);
		File path = new File("./screenshots/MobileOrdered.jpeg");
		FileUtils.copyFile(order, path);
		System.out.println("Mobile Ordering confirmation screen shot saved");
	}

}
