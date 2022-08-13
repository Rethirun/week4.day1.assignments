package week4.day1.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		/*
		 * //Pseudo Code
		 * 
		 * 1. Launch URL "http://leaftaps.com/opentaps/control/login"
		 * 
		 * 2. Enter UserName and Password Using Id Locator
		 * 
		 * 3. Click on Login Button using Class Locator
		 * 
		 * 4. Click on CRM/SFA Link
		 * 
		 * 5. Click on contacts Button
		 * 	
		 * 6. Click on Merge Contacts using Xpath Locator
		 * 
		 * 7. Click on Widget of From Contact
		 * 
		 * 8. Click on First Resulting Contact
		 * 
		 * 9. Click on Widget of To Contact
		 * 
		 * 10. Click on Second Resulting Contact
		 * 
		 * 11. Click on Merge button using Xpath Locator
		 * 
		 * 12. Accept the Alert
		 * 
		 * 13. Verify the title of the page
		 */


		//Step 1: Download and setup
		WebDriverManager.chromedriver().setup();

		//Step 2:Launch the chromebrowser
		ChromeDriver driver=new ChromeDriver();

		//Step 3:Launch the URL
		driver.get("http://leaftaps.com/opentaps/control/login");

		//Step 4:Maximize the window
		driver.manage().window().maximize();

		//Step 5:Add ImplicitWait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//2. Enter UserName and Password Using Id Locator
		driver.findElement(By.id("username")).sendKeys("Demosalesmanager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");

		// 3. Click on Login Button using Class Locator
		driver.findElement(By.className("decorativeSubmit")).click();
				 
		// 4. Click on CRM/SFA Link
		driver.findElement(By.linkText("CRM/SFA")).click();
				 
		// 5. Click on contacts Button
		driver.findElement(By.linkText("Contacts")).click();

		// 6. Click on Merge Contacts using Xpath Locator
		//a[text()='Merge Contacts']
		driver.findElement(By.xpath("//a[text()='Merge Contacts']")).click();
				 
		//7. Click on Widget of From Contact
		//span[text()='From Contact']/following::input[2]/following::a[1]
		driver.findElement(By.xpath("//span[text()='From Contact']/following::input[2]/following::a[1]")).click();
		
		//Switch to window
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> lstWindows=new ArrayList<String>(windowHandles);
		String firstWindowHandle = lstWindows.get(0);
		String secondWindowHandle = lstWindows.get(1);

		//8. Click on First Resulting Contact
		//(//a[@class='linktext'])[1]
		driver.switchTo().window(secondWindowHandle);
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//a[@class='linktext'])[1]")).click();
		
		
		// 9. Click on Widget of To Contact
		//(//span[@class='requiredField'])[1]/following::input[2]/following::a[2]
		driver.switchTo().window(firstWindowHandle);
		driver.findElement(By.xpath("(//span[@class='requiredField'])[1]/following::input[2]/following::a[2]")).click();

		//10. Click on Second Resulting Contact
		//(//table[@class='x-grid3-row-table'])[1]/following::a[1]
		Set<String> secwindowHandles = driver.getWindowHandles();
		List<String> lstSecWindows=new ArrayList<String>(secwindowHandles);
		String thirdWindowHandle = lstSecWindows.get(1);
		
		System.out.println(firstWindowHandle);
		System.out.println(secondWindowHandle);
		System.out.println(thirdWindowHandle);
		
		driver.switchTo().window(thirdWindowHandle);
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//table[@class='x-grid3-row-table'])[1]/following::a[1]")).click();

		// 11. Click on Merge button using Xpath Locator
		//a[@class='buttonDangerous']
		driver.switchTo().window(firstWindowHandle);
		driver.findElement(By.xpath("//a[@class='buttonDangerous']")).click();
		
		//Accept alert
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
		//Verify the title of the Page
		System.out.println(driver.getTitle());


	}

}
