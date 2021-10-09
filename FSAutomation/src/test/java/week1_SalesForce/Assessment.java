package week3;


import java.time.Duration;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assessment {

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
		
        //3. Click view All and click Sales from App Launcher
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		driver.findElement(By.xpath("//p[text()='Service Console']")).click();
		
		//Click New Task
		driver.findElement(By.xpath("//button[@title='Show Navigation Menu']")).click();
		driver.findElement(By.xpath("//span[text()='Home']")).click();
		int closed = 0,open = 0;
		Thread.sleep(2000);
		System.out.println("closed = "+driver.findElement(By.xpath("//span[@class='metricLabel' and text()='Closed']/following-sibling::span")).getText().substring(1));
		
		if(!driver.findElement(By.xpath("//span[@class='metricLabel' and text()='Closed']/following-sibling::span")).getText().substring(1).equals("0"))
			closed = Integer.parseInt(driver.findElement(By.xpath("//span[@class='metricLabel' and text()='Closed']/following-sibling::span")).getText().substring(1));
		
		if(!driver.findElement(By.xpath("//span[@class='metricLabel' and text()='Open (>70%)']/following-sibling::span")).getText().substring(1).equals("0"))
			open = Integer.parseInt(driver.findElement(By.xpath("//span[@class='metricLabel' and text()='Open (>70%)']/following-sibling::span")).getText().substring(1));
		
		if((closed+open)<10000) {
			driver.findElement(By.xpath("//button[@title='Edit Goal']")).click();
			driver.findElement(By.xpath("//span[@class='currencyCode']/following-sibling::input")).clear();
			driver.findElement(By.xpath("//span[@class='currencyCode']/following-sibling::input")).sendKeys("10000");
			driver.findElement(By.xpath("//span[text()='Save']")).click();
		}
			
		driver.findElement(By.xpath("//button[@title='Show Navigation Menu']")).click();
		driver.findElement(By.xpath("//span[text()='Dashboards']")).click();
		driver.findElement(By.xpath("//div[@title='New Dashboard']")).click();
		
		

		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='dashboard']")));
		
	
		String dashboar_Name = "Subramanian_Workout";
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='dashboardNameInput']")));
		driver.findElement(By.xpath("//input[@id='dashboardNameInput']")).clear();
		driver.findElement(By.xpath("//input[@id='dashboardNameInput']")).sendKeys(dashboar_Name);
		
		driver.findElement(By.xpath("//input[@id='dashboardDescriptionInput']")).clear();
		driver.findElement(By.xpath("//input[@id='dashboardDescriptionInput']")).sendKeys(dashboar_Name+"Description");
		driver.findElement(By.xpath("//button[@id='submitBtn']")).click();
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//button[text()='Done']")).click();


		
	}

}
