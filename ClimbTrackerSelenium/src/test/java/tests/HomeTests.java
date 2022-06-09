package tests;

import static org.junit.Assert.assertEquals;

import java.time.Duration;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.HomePage;
import pages.LoginPage;

public class HomeTests {
	private static WebDriver driver;

	private static LoginPage loginPage;
	private static HomePage homePage;
	
	private WebDriverWait wait;

	@BeforeClass
	public static void init() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setHeadless(false);
		driver = new ChromeDriver(options);

		loginPage = PageFactory.initElements(driver, LoginPage.class);
		homePage = PageFactory.initElements(driver, HomePage.class);
	}

	@Before
	public void setup() {
		driver.get(LoginPage.getUrl());
		loginPage.login("user", "password");
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(homePage.getTitle()));
		wait.until(ExpectedConditions.elementToBeClickable(homePage.getPopulateTableWithTestDataButton()));
		homePage.populateTableWithTestData();
		wait.until(ExpectedConditions.elementToBeClickable(homePage.getDeleteAllButton()));
		homePage.deleteAllEntries(driver);
		wait.until(ExpectedConditions.elementToBeClickable(homePage.getPopulateTableWithTestDataButton()));
	}
	
	@Test
	public void logoutTest() {
		homePage.logout(driver);
		WebElement loginPageTitle = loginPage.getTitle();
		wait.until(ExpectedConditions.visibilityOf(loginPageTitle));
		assertEquals("climb tracker v1.0", loginPageTitle.getText());
		loginPage.login("user", "password");
	}
	
	@Test
	public void populateTableWithTestDataTest() {
		wait.until(ExpectedConditions.elementToBeClickable(homePage.getPopulateTableWithTestDataButton()));
		homePage.populateTableWithTestData();
		wait.until(ExpectedConditions.visibilityOf(homePage.getDeleteAllButton()));
		assertEquals(5, homePage.getTableRows().size());
	}
	
	
	@Test
	public void deleteAllItemsTest() {
		homePage.populateTableWithTestData();
		wait.until(ExpectedConditions.elementToBeClickable(homePage.getDeleteAllButton()));
		homePage.deleteAllEntries(driver);
		wait.until(ExpectedConditions.elementToBeClickable(homePage.getPopulateTableWithTestDataButton()));
		assertEquals(1, homePage.getTableRows().size());
	}
	
	@Test
	public void deleteSelectedTest() {
		homePage.populateTableWithTestData();
		wait.until(ExpectedConditions.elementToBeClickable(homePage.getDeleteAllButton()));
		homePage.deleteSelectedEntries(driver, wait);
		wait.until(ExpectedConditions.elementToBeClickable(homePage.getPopulateTableWithTestDataButton()));
		assertEquals(4, homePage.getTableRows().size());
//		assertEquals("The Beast! (v7)", homePage.getTableRouteName4().getText());
	}
	
	@Test
	public void successfulCreateEntryTest() {
		String routeName = "1: Boooooring (v1)";
		String timeTaken = "30";
		String attempts = "5";
		
		wait.until(ExpectedConditions.elementToBeClickable(homePage.getPopulateTableWithTestDataButton()));
		homePage.createEntry(routeName, timeTaken, attempts);
		wait.until(ExpectedConditions.elementToBeClickable(homePage.getDeleteAllButton()));
		assertEquals(routeName.split(": ")[1], homePage.getTableRouteName1().getText());
		assertEquals(timeTaken, homePage.getTableTime1().getText());
	}
	
	@Test
	public void unsuccessfulRouteCreateEntryTest() {
		String routeName = "";
		String timeTaken = "30";
		String attempts = "5";
		
		wait.until(ExpectedConditions.elementToBeClickable(homePage.getPopulateTableWithTestDataButton()));
		homePage.createEntry(routeName, timeTaken, attempts);
		assertEquals("please select a route", homePage.getRouteError().getText());
	}
	
	@Test
	public void unsuccessfulTimeCreateEntryTest() {
		String routeName = "1: Boooooring (v1)";
		String timeTaken = "";
		String attempts = "5";
		
		wait.until(ExpectedConditions.elementToBeClickable(homePage.getPopulateTableWithTestDataButton()));
		homePage.createEntry(routeName, timeTaken, attempts);
		assertEquals("please enter a number", homePage.getTimeError().getText());
		
		timeTaken = "abc";
		homePage.createEntry(routeName, timeTaken, attempts);
		assertEquals("please enter a number", homePage.getTimeError().getText());
	}

	
	@Test
	public void unsuccessfulAttemptsCreateEntryTest() {
		String routeName = "1: Boooooring (v1)";
		String timeTaken = "30";
		String attempts = "";

		homePage.createEntry(routeName, timeTaken, attempts);
		assertEquals("please enter a number", homePage.getAttemptsError().getText());
		
		attempts = "abc";
		homePage.createEntry(routeName, timeTaken, attempts);
		wait.until(ExpectedConditions.elementToBeClickable(homePage.getAttemptsError()));
		assertEquals("please enter a number", homePage.getAttemptsError().getText());
	}
	
	@After
	public void afterEach() {
		wait.until(ExpectedConditions.elementToBeClickable(homePage.getPopulateTableWithTestDataButton()));
	}

	@AfterClass
	public static void end() {
		driver.quit();
	}
}