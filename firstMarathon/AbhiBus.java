package testLeafAssignments.marathon.firstMarathon;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

/* 


*/
public class AbhiBus {
public static void main(String[] args) throws InterruptedException {
	//01) Launch Firefox / Chrome and add  implicitlyWait
    ChromeDriver driver =  new ChromeDriver();
    //02) Load https://www.abhibus.com/
    driver.get("https://www.abhibus.com/");
    driver.manage().window().maximize();
    //Use Implicit wait
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	//03) Click on Bus
	driver.findElement(By.xpath("//a[@id='pills-home-tab']")).click();
	//04) Type "Chennai" in the FROM text box
	driver.findElement(By.xpath("//input[@id='source']")).sendKeys("Chennai");
	//05) Click the first option from the pop up box
	driver.findElement(By.xpath("(//li [@class='ui-menu-item ui-state-focus'])[1]")).click();
	//06) Type "Bangalore" in the TO text box
	driver.findElement(By.xpath("//input[@id='destination']")).sendKeys("Bangalore");
	//07) Click the first option from the pop up box
	driver.findElement(By.xpath("(//li[@class='ui-menu-item ui-state-focus'])[1]")).click();
	//08) Select tomorrow's date in the Date field
	driver.findElement(By.xpath("//input[@id='datepicker1']")).click();
	String today_date = driver.findElement(By.xpath("//td[@class=' ui-datepicker-days-cell-over  ui-datepicker-today']")).getText();
	System.out.println("Today date "+today_date);
	int tomdate = Integer.parseInt(today_date) ;
	tomdate++;
	System.out.println(tomdate);
	String date = Integer.toString(tomdate);
	String tomDatePath = "(//td/a[text()='".concat(date).concat("'])[1]");
	System.out.println(tomDatePath);
	driver.findElement(By.xpath(tomDatePath)).click();
	//09) Click Search Buses
	driver.findElement(By.xpath("//div/a[text()='Search']")).click();
	//10) Print the name of the first resulting bus (use .getText())
	String firstBusName = driver.findElement(By.xpath("(//div[@class='search-column1']/h2)[1]")).getText();
	System.out.println("First bus in the search result "+firstBusName);
	//11) Choose SLEEPER in the left menu from Bus Type
	driver.findElement(By.xpath("//input[@title='SLEEPER']")).click();
	//12) Print the first resulting bus seat count(Example:35 Seats Available)(use .getText())
	Thread.sleep(3000);
	String seatCount = driver.findElement(By.xpath("(//div[@class='search-column2']/div/p)[1]")).getText();
	System.out.println("Seat count "+seatCount);
	//13) Click SelectSeat
	driver.findElement(By.xpath("(//span[text()='Select Seat'])[1]")).click();
	//14) Choose any one Available seat
	driver.findElement(By.xpath("(//li[contains(@class,'sleeper available')]/a)[1]")).click();
	//15) Print Seats Selected ,Total Fare
	System.out.println("Selected seat no "+driver.findElement(By.xpath("(//div[@class='clearfix']/p[2]/span)[1]")).getText());
	System.out.println("Total Fare "+driver.findElement(By.xpath("//span[@id='ticketfare']")).getText());
	//16) Select Boarding Point  and Dropping Point
	WebElement bordingPoint = driver.findElement(By.xpath("//select[@id='boardingpoint_id']"));
	Select sc = new Select(bordingPoint);
	sc.selectByIndex(5);
	//Dropping point
	WebElement dropPoint = driver.findElement(By.xpath("//select[@id='droppingpoint_id']"));
	Select sc1 = new Select(dropPoint);
	sc1.selectByIndex(3);
	//17) Get the Title of the page(use .getTitle())
	System.out.println(driver.getTitle());
	//18) Close the browser
	//driver.close();
	
	
	
	
	
	
	
	
}
}
