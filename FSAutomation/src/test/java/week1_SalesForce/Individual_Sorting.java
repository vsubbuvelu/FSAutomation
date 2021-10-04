package week2;

import static org.junit.Assert.*;
import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import io.github.bonigarcia.wdm.WebDriverManager;

public class Individual_Sorting {

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
		//5. Click the sort arrow in the Name
		driver.findElement(By.xpath("//span[@title='Name']/parent::a")).click();
		//6. Verify the Individuals displayed in ascending order by Name
		
		List<WebElement> indNameList = driver.findElements(By.xpath("//tbody/tr/th//a"));
		ArrayList<String> actualNameList = new ArrayList<String>();
		ArrayList<String> sortedNameList = new ArrayList<String>();
		for (int i = 1; i <= indNameList.size(); i++) {
			actualNameList.add(driver.findElement(By.xpath("//tbody/tr["+i+"]/th//a")).getText());
		}
		for (String name:actualNameList) {
			sortedNameList.add(name);			
		}
		
		Collections.sort(sortedNameList);
		
		assertTrue(actualNameList.equals(sortedNameList));
		
		
		
	}

}
