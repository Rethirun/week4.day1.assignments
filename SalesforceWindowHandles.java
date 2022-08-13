package week4.day1.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesforceWindowHandles {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();//Verify the version,download and set the chrome driver

		ChromeDriver driver=new ChromeDriver();//Launch the chrome browser

		driver.get("https://login.salesforce.com/");//load the URL

		driver.manage().window().maximize();//maximize the browser

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		
		// Enter the username as "ramkumar.ramaiah@testleaf.com -- id username
		driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com");

		//Enter the passsword as Password#123--id password
		driver.findElement(By.id("password")).sendKeys("Password#123");

		//Click the login -- id Login
		driver.findElement(By.id("Login")).click();

		//Click on learn more option in the Mobile publisher.-- //span[text()='Learn More']
		driver.findElement(By.xpath("//span[text()='Learn More']")).click();

		//Switch to the next window using windowHandles
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> lstWindows=new ArrayList<String>(windowHandles);
		String firstWindowHandle = lstWindows.get(0);
		String secondWindowHandle = lstWindows.get(1);

		//click on the confirm button in the redirecting page
		//button[text()='Confirm']
		driver.switchTo().window(secondWindowHandle);
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();

		//Get the title
		System.out.println(driver.getTitle());


		//Get back to the parent window
		driver.switchTo().window(firstWindowHandle);
		System.out.println(driver.getTitle());

		// Close the broswer
		driver.quit();
		

		
		


	}

}
