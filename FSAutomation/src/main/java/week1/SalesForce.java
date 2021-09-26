package week1;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesForce {
	public static void main(String[] args) throws InterruptedException {
		

		// System.setProperty("webdriver.chrome.driver",
		// "C:\\FullStackAutomation\\FSAutomation\\driver\\chromedriver.exe");
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
		
		//4. Click on Opportunity tab 
		JavascriptExecutor jsex = (JavascriptExecutor) driver;
		jsex.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@title='Opportunities']")));
		
		//5. Click on New button
		driver.findElement(By.xpath("//div[@title='New']")).click();
		
		//6. Enter Opportunity name as 'Salesforce Automation by Your Name,Get the text and Store it 
		String enterdName = "Salesforce Automation by Subramanian";
		driver.findElement(By.xpath("//input[@name='Name']")).sendKeys(enterdName);
		
		//7. Choose close date as Today
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("M/dd/yyyy");
		String today = formatter.format(date);
		driver.findElement(By.xpath("//input[@name='CloseDate']")).sendKeys(today);
		
		
		//8. Select 'Stage' as Need Analysis
		driver.findElement(By.xpath("//label[text()='Stage']/following::input[@role='combobox'][1]")).click();
		driver.findElement(By.xpath("//span[@title='Needs Analysis']")).click();
		
		
		//9. click Save and VerifyOppurtunity Name
		driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
		String expOppurtunityName = driver.findElement(By.xpath("//slot[@slot='primaryField']")).getText();
		if(enterdName.equals(expOppurtunityName))
			System.out.println("Pass");
		else
			System.out.println("Fail");
		
		driver.close();
		

	}

}
