package week2;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AdminCertifications {

	@Test
	public void test() throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriver driver = new ChromeDriver(options);
		
		//1. Login to https://login.salesforce.com
		driver.get("https://login.salesforce.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(By.id("username")).sendKeys("fullstack@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("SelBootcamp$123");
		driver.findElement(By.id("Login")).click();
		
		
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Learn More']")));
		driver.findElement(By.xpath("//span[text()='Learn More']")).click();
		String parentwindow=driver.getWindowHandle();
		Set<String> windows=driver.getWindowHandles();
		Iterator<String> windowIterator = windows.iterator();
		
		while(windowIterator.hasNext())
		{
			driver.switchTo().window(windowIterator.next());
			System.out.println(driver.getTitle());
			if(driver.getTitle().equals("Create and Publish Custom-Branded Mobile Apps - Salesforce.com")){
				
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				WebElement resources = (WebElement) jse.executeScript(
						"return document.querySelector(\"#c360-hgf-nav\").shadowRoot.querySelector(\"header > div:nth-child(2) > div > div > div > div > div.globalnavbar-header-container > nav > ul > li.nav-item.menu_item_33 > button > span:nth-child(1)\")");
				
				Actions action = new Actions(driver);
				action.moveToElement(resources).build().perform();
				
				Thread.sleep(2000);
				WebElement certificate = (WebElement) jse.executeScript(
						"return document.querySelector(\"#c360-hgf-nav\").shadowRoot.querySelector(\"header > div:nth-child(2) > div > div > div > div > div.globalnavbar-header-container > nav > ul > li.nav-item.menu_item_33 > div > div > div > div:nth-child(2) > ul > li > div.sub-nav > ul > li:nth-child(3) > a > span:nth-child(1)\")");
				certificate.click();
				
			}
					
		}
        windows=driver.getWindowHandles();
        windowIterator = windows.iterator();
		ArrayList<String> expCerticateList = new ArrayList<String>();
		expCerticateList.add("Administrator");
		expCerticateList.add("Advanced Administrator");
		expCerticateList.add("CPQ Specialist");
		expCerticateList.add("Marketing Cloud Administrator");
		expCerticateList.add("Platform App Builder");
		
		ArrayList<String> actCerticateList = new ArrayList<String>();
		
		while(windowIterator.hasNext())
		{
			driver.switchTo().window(windowIterator.next());
			System.out.println(driver.getTitle());
			if(driver.getTitle().equals("Certification - Administrator Overview"))
			{
				List<WebElement> webelement = driver.findElements(By.xpath("//div[contains(@class,'x-large slds-p-top--small')]//div[3]/a"));
				for (WebElement element:webelement) {
					actCerticateList.add(element.getText());
				}
			}
					
		}
		assertTrue(expCerticateList.equals(actCerticateList));
		
	}
	

}
