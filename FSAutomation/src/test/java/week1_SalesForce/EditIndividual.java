package week1_SalesForce;

import static org.junit.Assert.*;
import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.corba.se.spi.orbutil.fsm.Action;
import com.sun.corba.se.spi.orbutil.fsm.FSM;
import com.sun.corba.se.spi.orbutil.fsm.Input;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EditIndividual {

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
		
		//4. Click on the Individuals tab 
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click", driver.findElement(By.xpath("//a[contains(@title,'Individuals')]")));
		
		//5. Search the Individuals 'Kumar'
		String searchIndividualName = "KumarTest";
		driver.findElement(By.xpath("//input[@name='Individual-search-input']")).click();
		driver.findElement(By.xpath("//input[@name='Individual-search-input']")).clear();
		
		driver.findElement(By.xpath("//input[@name='Individual-search-input']")).sendKeys(searchIndividualName,Keys.ENTER);
		Thread.sleep(1000);
		//6. Click on the Dropdown icon and Select Edit
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'forceVirtualActionMarker')]//a")));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[contains(@class,'forceVirtualActionMarker')]//a")).click();
		Thread.sleep(1000);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='Edit']")));
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//div[@title='Edit']"))).click().perform();
		
		driver.findElement(By.xpath("//span[text()='Salutation']/parent::span/following-sibling::div")).click();
		driver.findElement(By.xpath("//a[@title='Mr.']")).click();
		String firstName = "Ganesh";
		driver.findElement(By.xpath("//input[@placeholder='First Name']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys(firstName);
		String expName = "Individual "+'"'+firstName+" "+searchIndividualName+'"'+" was saved.";
		driver.findElement(By.xpath("//button[@title='Save']")).click();
		Thread.sleep(1000);
		String actualSuccessMessage = driver.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']")).getText();
		assertEquals(actualSuccessMessage,expName);
		
		
	}

}
