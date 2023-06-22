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

public class ArchitectCertifications {

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
				
				//8. Choose Your Role as Salesforce Architect
				action.scrollToElement(driver.findElement(By.xpath("//div[text()='Architect']"))).perform();
				driver.findElement(By.xpath("//div[text()='Architect']")).click();
				
				//9. Get the Text(Summary) of Salesforce Architect
				action.scrollToElement(driver.findElement(By.xpath("//h1[text()='Salesforce Architect']"))).perform();
				String summary = driver.findElement(By.xpath("//h1[text()='Salesforce Architect']/following-sibling::div")).getText();
				System.out.println("Salesforce Architect Summary "+summary);
				
				//10. Get the List of Salesforce Architect Certifications Available
				List<WebElement> certificateList = driver.findElements(By.xpath("(//div[@class='credentials-card ']/div[text()='Certification'])/following-sibling::div/a"));
				System.out.println("Salesforce Architect Certifications Available List count "+certificateList.size());
				for(int i = 0; i < certificateList.size(); i++)
					{System.out.println(certificateList.get(i).getText());}
				
				//11.Click on Application Architect 
				driver.findElement(By.xpath("//a[text()='Application Architect']")).click();
				
				//12.Get the List of Certifications available
				List<WebElement> apparchitectcertificateList = driver.findElements(By.xpath("(//div[@class='credentials-card ']/div[text()='Certification'])/following-sibling::div/a"));
				System.out.println("Salesforce Application Architect Certifications Available List Count "+apparchitectcertificateList.size());
				for(int i = 0; i < apparchitectcertificateList.size(); i++)
					{System.out.println(apparchitectcertificateList.get(i).getText());}
				
				//13.Take A snapshot of Certifications.
				WebElement certificate = driver.findElement(By.xpath("(//div[@class='credentials-card ']/div[text()='Certification'])/following-sibling::div/a[1]"));
				action.scrollToElement(certificate).perform();
				
				
				File certificates = driver.getScreenshotAs(OutputType.FILE);
				File path = new File("./screenshots/appArchitectCertificates.jpeg");
				FileUtils.copyFile(certificates, path);
				System.out.println("Certificates screen shot saved");
				

	}

}
