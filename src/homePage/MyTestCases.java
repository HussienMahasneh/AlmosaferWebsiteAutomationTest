package homePage;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCases {
	WebDriver driver = new ChromeDriver();
	String ALMosaferURL = "https://global.almosafer.com/en";
	String ExpectedDefaultLanguage = "en";
	Random rand = new Random();


	@BeforeTest
	public void MySetup() {

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(10));
		driver.get(ALMosaferURL);
		driver.findElement(By.xpath("//button[@ class ='sc-jTzLTM hQpNle cta__button cta__saudi btn btn-primary']"))
				.click();
	}

	@Test(priority = 1)
	public void CheckTheDefaultLanguageIsEnglish() {
		String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");

		Assert.assertEquals(ActualLanguage, ExpectedDefaultLanguage);

	}

	@Test(priority = 2)
	public void CheckDefaultCurrency() {
		String ExpectedCurrency = "SAR";
		WebElement Currency = driver.findElement(By.xpath("//button[@data-testid ='Header__CurrencySelector']"));

		String ActualCurrency = Currency.getText();
		Assert.assertEquals(ActualCurrency, ExpectedCurrency);

	}

	@Test(priority = 3)
	public void CheckContactNumber() {
		String ExpectedContactNUmber = "+966554400000";
		String ActualContactNumber = driver.findElement(By.tagName("strong")).getText();
		Assert.assertEquals(ActualContactNumber, ExpectedContactNUmber);

	}

	@Test(priority = 4)
	public void CheckTheLogo() {
		boolean ExpectedLogo = true;
		WebElement theFooter = driver.findElement(By.tagName("footer"));
		boolean ActualLogo = theFooter.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ekulBa.eYboXF")).isDisplayed();
		// System.out.println(ActualLogo);
		Assert.assertEquals(ActualLogo, ExpectedLogo);

	}

	@Test(priority = 5)
	public void TestHotelTabIsNotSelected() {
		String ExpectedValue = "false";
		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		
		String ActualValue = HotelTab.getAttribute("aria-selected");
		Assert.assertEquals(ActualValue, ExpectedValue);
		

	}
	
	@Test( priority = 6)
	public void checkFlightDepartureDate () {
		LocalDate todayDate = LocalDate.now();
	//	System.out.println(todayDate);
		List<WebElement> depatureAndArrivalDates = driver.findElements(By.className("LiroG"));

		String ActualDepatureDate = depatureAndArrivalDates.get(0).getText();
		String ActualReturnDate = depatureAndArrivalDates.get(1).getText();

		int ActualDepatureDateAsInt = Integer.parseInt(ActualDepatureDate);
		int ActualreturnDateAsInt = Integer.parseInt(ActualReturnDate);



		//Assert.assertEquals(ActualDepatureDateAsInt, Tomorrow);
		//Assert.assertEquals(ActualreturnDateAsInt, ThedayAfterTomorrow);
		
	}
	@Test (priority = 7)
	public void RandomlyChangeTheLanguage () {
		String [] URLs = { "https://www.almosafer.com/en" ,"https://www.almosafer.com/ar"} ;
		int RandomIndex = rand.nextInt( URLs.length);
		driver.get(URLs[RandomIndex]);
		
		
		
	}

}
