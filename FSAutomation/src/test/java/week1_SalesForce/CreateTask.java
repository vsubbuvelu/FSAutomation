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

public class CreateTask {

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
		
		//Click New Task
		driver.findElement(By.xpath("//a[contains(@title,'Tasks')]/following-sibling::one-app-nav-bar-item-dropdown")).click();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click()", driver.findElement(By.xpath("//span[text()='New Task']")));
		String subjectName = "Bootcamp";
		//6) Enter subject as "Bootcamp "   
		driver.findElement(By.xpath("//label[text()='Subject']/following-sibling::div//input")).sendKeys("Bootcamp");
		
		driver.findElement(By.xpath("//span[text()='Name']/parent::label/following-sibling::div//input")).click();
		//7) Select Contact from DropDown
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@class='listContent']//img[@title='Contact']"))));
		driver.findElement(By.xpath("//div[@class='listContent']//img[@title='Contact']")).click();
		//Select status as 'Waiting on someone else'
		driver.findElement(By.xpath("//span[text()='Status']/parent::span/following-sibling::div")).click();
		driver.findElement(By.xpath("//a[text()='Waiting on someone else']")).click();
		// Save and verify the 'Task created' message
		driver.findElement(By.xpath("//span[text()='Save']")).click();
		
		String expSuccessMessage = "Task "+subjectName+" was created.";
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']"))));
		
		String actualSuccessMessage = driver.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']")).getText();
		assertEquals(actualSuccessMessage, expSuccessMessage);
		
	}

}
