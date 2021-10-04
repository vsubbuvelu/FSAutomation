package week2;

import static org.testng.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteTask {

	@Test
	public void test() {
		
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
		
        //3. Click view All and click Sales from App Launcher
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		driver.findElement(By.xpath("//p[text()='Sales']")).click();
		
		//4. Click on Tasks tab 
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click()", driver.findElement(By.xpath("//span[text()='Tasks']/parent::a")));
		
		//5.Click on Dropdown icon available under tasks and select value as Recently viewed
		driver.findElement(By.xpath("//button[@title='Select List View']")).click();
		
		driver.findElement(By.xpath("//div[@class='listContent']//span[text()='Recently Viewed']")).click();
		driver.findElement(By.xpath("//button[contains(@title,'Display')]")).click();
		driver.findElement(By.xpath("//li[@title='Table']")).click();
		driver.findElement(By.xpath("//div[contains(@class,'forceVirtualActionMarker')]")).click();
		driver.findElement(By.xpath("//a[@title='Delete']")).click();
		
		driver.findElement(By.xpath("//span[text()='Delete']")).click();


		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		//Task "Bootcamp" was deleted. Undo
		String expSuccessMessage = "Task "+'"'+"Bootcamp"+'"'+" was deleted.";
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']"))));
		
		String actualSuccessMessage = driver.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']")).getText();
		assertEquals(actualSuccessMessage, expSuccessMessage);
		
		
		
		
	}

}
