package testLeafAssignments.marathon.firstMarathon;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

/*Testcase:2(Amazon)
===============
*/
public class Amazon {
public static void main(String[] args) throws InterruptedException {
	//01) Launch Chrome
    ChromeDriver driver =  new ChromeDriver();
    //02) Load https://www.amazon.in/
    driver.get("https://www.amazon.in/");
    driver.manage().window().maximize();
    //Use Implicit wait
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	//03) Type "Bags" in the Search box
	driver.findElement(By.xpath("//form[@id='nav-search-bar-form']//input[@id='twotabsearchtextbox']")).sendKeys("Bags");
	//04) Choose the item in the result (bags for boys)
	driver.findElement(By.xpath("//div[@class='left-pane-results-container']//div[@aria-label='bags for boys']")).click();
	//05) Print the total number of results (like 50000) 1-48 of over 50,000 results for "bags for boys"
	System.out.println(driver.findElement(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']/span[1]")).getText());
	System.out.println(driver.findElement(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']/span[3]")).getText());
	//06) Select the first 2 brands in the left menu (like American Tourister, Generic)
	System.out.println("1st Brand in the list "+driver.findElement(By.xpath("(//div[@id='brandsRefinements']//ul/span[1]/li//a[1]/span)[1]")).getText());
	System.out.println("2st Brand in the list "+driver.findElement(By.xpath("(//div[@id='brandsRefinements']//ul/span[2]/li//a[1]/span)[1]")).getText());
	//07) Choose New Arrivals (Sort)
	driver.findElement(By.xpath("//span[@class='a-button-inner']")).click();
	driver.findElement(By.xpath("//a[text()='Newest Arrivals']")).click();
	Thread.sleep(3000);
	System.out.println(driver.findElement(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']/span[1]")).getText());
	System.out.println(driver.findElement(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']/span[3]")).getText());
	//08) Print the first resulting bag info (name, discounted price)
	System.out.println("Brand Name "+driver.findElement(By.xpath("(//div[@class='a-section a-spacing-base a-text-center'])[1]/div[2]/div[1]//h2/span")).getText());
	System.out.println("Discounted Price"+driver.findElement(By.xpath("(//div[@class='a-section a-spacing-base a-text-center'])[1]/div[2]/div[2]//a"
			+ "/span[@class='a-price']/span[2]/span[2]")).getText());
	//(//div[@class='a-section a-spacing-base a-text-center'])[1]/div[2]/div[2]//a/span[@class='a-price']/span[2]/span[2]
	//09) Get the page title and close the browser(driver.close())
	System.out.println("Page Title "+driver.getTitle());
	driver.close();
}
}
