package week1_SalesForce;


import java.time.Duration;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateIndividuals {
	
	

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

		//2. Click on toggle menu button from the left corner
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		
        //3.  Click View All and click Individuals from App Launcher
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']")).sendKeys("Individuals");
		driver.findElement(By.xpath("//mark[text()='Individuals']")).click();
		
		//4.Click on the Dropdown icon in the Individuals tab
		driver.findElement(By.xpath("//a[contains(@title,'Individuals')]/following-sibling::one-app-nav-bar-item-dropdown")).click();
		Thread.sleep(1000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click()", driver.findElement(By.xpath("//span[text()='New Individual']")));
		
		driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys("Kumar");
		driver.findElement(By.xpath("//button[@title='Save']")).click();
		//String expectedIndName = driver.findElement(By.xpath("//span[contains(text(),'Kumar')]")).getText();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']"))));
		String expectedIndName = driver.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']")).getText();
		System.out.println(expectedIndName);
		
		
		
		
		
		

	}

}
