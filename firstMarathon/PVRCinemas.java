package testLeafAssignments.marathon.firstMarathon;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

/*
 * Testcase 3 (PVR cinemas)
==========
 */
public class PVRCinemas {
public static void main(String[] args) throws InterruptedException {
	//Launch Chrome
	ChromeOptions options = new ChromeOptions();
    ChromeDriver driver =  new ChromeDriver();
    //01) Launch the Url  https://www.pvrcinemas.com/
    driver.get("https://www.pvrcinemas.com/");
    driver.manage().window().maximize();
    //Use Implicit wait
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
	//02) Click  on Movie Library
	driver.findElement(By.xpath("//div[text()='Movie Library ']")).click();
	//03) Select the City -->chennai
	WebElement city = driver.findElement(By.name("city"));
	Select sc4 = new Select(city);
	sc4.selectByVisibleText("Chennai");
	//driver.findElement(By.xpath("//select[@name='city']/option[text()='Chennai']")).click();
	//04) Select the Genre-->Animation
	WebElement genre = driver.findElement(By.name("genre"));
	Select sc5 = new Select(genre);
	sc5.selectByVisibleText("ANIMATION");
	//driver.findElement(By.xpath("//select[@name='genre']/option[text()='ANIMATION']")).click();
	//05) Select the Language-->english
	WebElement lang = driver.findElement(By.name("lang"));
	Select sc6 = new Select(lang);
	sc6.selectByVisibleText("ENGLISH");
	//driver.findElement(By.xpath("//select[@name='lang']/option[text()='ENGLISH']")).click();
	//06) Click on Apply
	driver.findElement(By.xpath("//button[text()='Apply']")).click();
	Thread.sleep(4000);
	//07) Click on first resulting animation movie
	String movieName = driver.findElement(By.xpath("//div[@class='movie-list']/div[1]/div[1]")).getText();
	//Like //div[text()='Paw Patrol']
	//System.out.println("//div[text()='".concat(movieName).concat("']"));
	Thread.sleep(3000);
	driver.findElement(By.xpath("//div[text()='".concat(movieName).concat("']"))).click();
	//08) Click proceed to book
	driver.findElement(By.xpath("//button[text()='Proceed To Book']")).click();
	//09) Enter the all fields  cinema , Name, date, Prefered show time, no of seats, food and beverages,Email and Mobile
	
	WebElement cinemaName = driver.findElement(By.name("cinemaName"));
	Select sc = new Select(cinemaName);
	sc.selectByVisibleText("PVR Velachery Chennai");
	//driver.findElement(By.xpath("//select[@name='cinemaName']/option[3]")).click();
	
	WebElement timings = driver.findElement(By.name("timings"));
	Select sc1 = new Select(timings);
	sc1.selectByVisibleText("12:00 PM - 03:00 PM");
	
	driver.findElement(By.xpath("//div[@class='datepicker-container datepicker-default']")).click();
	
	driver.findElement(By.xpath("(//span[@class='day-unit ng-star-inserted'])[1]")).click();
	driver.findElement(By.xpath("//select[@name='timings']/option[3]")).click();
	
	driver.findElement(By.xpath("//input[@name='noOfTickets']")).sendKeys("4");
	driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Vandhana");
	
	driver.findElement(By.xpath("//input[@name='email']")).sendKeys("vandhanaKrishh@gmail.com");
	driver.findElement(By.xpath("//input[@name='mobile']")).sendKeys("7305290121");
	
	WebElement food = driver.findElement(By.name("food"));
	Select sc2 = new Select(food);
	sc2.selectByVisibleText("No");
	
	driver.findElement(By.xpath("//input[@name='comment']")).sendKeys("No");
	//10) Click on copy to self
	Thread.sleep(3000);
	driver.findElement(By.xpath("//div[@class='form-group col-sm-6']//label[1]")).click();
	//11) Click on  Send Request
	driver.findElement(By.xpath("//button[text()='SEND REQUEST']")).click();
	//12) Click cancel
	driver.findElement(By.xpath("(//li[@class='cancel-btn text-center']/button[text()='CANCEL'])[2]")).click();
	//13) Close the OTP dialog
	driver.findElement(By.xpath("//button[@aria-label='Close this dialog']")).click();
	//14) Verify the title of the page
	System.out.println("Page title is "+driver.getTitle());
	driver.close();
}
}
