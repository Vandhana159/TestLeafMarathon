package testLeafAssignments.marathon.secondMarathon;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import io.github.sukgu.Shadow;

public class SalesforceAdminCertifications {

	public static void main(String[] args) throws InterruptedException, IOException {
		//1. Launch Salesforce application https://login.salesforce.com/
		ChromeOptions ch = new ChromeOptions();
		ch.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(ch);
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//2. Login with username as "hari.radhakrishnan@qeagle.com" and password as "Leaf@1234"
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("hari.radhakrishnan@qeagle.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Leaf@1234");
		driver.findElement(By.xpath("//input[@id='Login']")).click();
		
		//3. Click on Learn More link in Mobile Publisher
		driver.findElement(By.xpath("//article[2]/div[2]/div[3]//span[text()='Learn More']")).click();
		
		//4. Click confirm on Confirm redirect
		Set<String> openedWindows = driver.getWindowHandles();
		List<String> openedWindowsList = new LinkedList<String>(openedWindows);
		driver.switchTo().window(openedWindowsList.get(1));
		Thread.sleep(2000);
		System.out.println(driver.findElement(By.xpath("//div/h1")).getText());
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();
		
		//5. Click Learning -- Shadow element
		Shadow dom =new Shadow(driver);
		WebElement shadeElement = dom.findElementByXPath("//span[text()='Learning']");
		shadeElement.click();
		
		//6. And mouse hover on Learning On Trailhead
		WebElement element = dom.findElementByXPath("//span[text()='Learning on Trailhead']");
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();

		//7. Clilck on Salesforce Certifications
		WebElement element1 = dom.findElementByXPath("//a[text()='Salesforce Certification']");	
		action.scrollToElement(element1).perform();
		driver.executeScript("arguments[0].click();", element1);
		
		//8. Click on first resulting Ceritificate
		driver.findElement(By.xpath("//div[@class='credentials-card '][1]/div[3]/a[1]")).click();
		Thread.sleep(3000);
		//9. verify - Administrator Overview page
		String currentUrl = driver.getCurrentUrl();
		if(currentUrl.equalsIgnoreCase("https://trailhead.salesforce.com/en/credentials/administrator"))
			System.out.println("Navigated to Administrator certicate page successfully");
		else
			System.out.println("Navigation failed");
		
		//10.Print the Certifications available for Administrator Certifications (List)
		List<WebElement> certificateList = driver.findElements(By.xpath("(//div[@class='credentials-card ']/div[text()='Certification'])/following-sibling::div/a"));
		System.out.println("Administrator Certificate List");
		for(int i = 0; i < certificateList.size(); i++)
			{System.out.println(certificateList.get(i).getText());}
		
		//11.Take A snapshot of Certifications.
		WebElement certificate = driver.findElement(By.xpath("//div[@class='credentials-card '][1]"));
		action.scrollToElement(certificate).perform();
		
		
		File certificates = driver.getScreenshotAs(OutputType.FILE);
		File path = new File("./screenshots/certificateList.jpeg");
		FileUtils.copyFile(certificates, path);
		System.out.println("Certificates screen shot saved");
	}

}
