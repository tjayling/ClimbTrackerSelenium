package tests;

import static org.junit.Assert.assertEquals;

import java.time.Duration;

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

public class LoginTests {
	private static WebDriver driver;

	static LoginPage loginPage;
	static HomePage homePage;

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
	}

	@Test
	public void correctLoginTest() {
		loginPage.login("user", "password");
		WebElement homePageTitle = homePage.getTitle();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(homePageTitle));
		assertEquals("Welcome", homePageTitle.getText());
	}
	
	@Test
	public void incorrectUsernameTest() {
		loginPage.login("notauser", "notapassword");
		WebElement usernameError = loginPage.getUsernameError();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(usernameError));
		
		assertEquals("incorrect username", usernameError.getText());
	}
	
	@Test
	public void incorrectPasswordTest() {
		loginPage.login("user", "notapassword");
		WebElement passwordError = loginPage.getPasswordError();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(passwordError));
		assertEquals("incorrect password", passwordError.getText());
	}
	
	@Test
	public void noUsernameTest() {
		loginPage.login("", "password");
		WebElement usernameError = loginPage.getUsernameError();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(usernameError));
		assertEquals("please enter a username", usernameError.getText());
	}
	
	@Test
	public void noPasswordTest() {
		loginPage.login("user", "");
		WebElement passwordError = loginPage.getPasswordError();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(passwordError));
		assertEquals("please enter a password", passwordError.getText());
	}
	
	@Test
	public void correctSignUpTest() {
		String username = "newUser";
		loginPage.signup("John", "Doe", username, "newPassword");
		WebElement homePageSubTitle = homePage.getSubTitle();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(homePageSubTitle));
		assertEquals("you are logged in as " + username, homePageSubTitle.getText());
	}
	
	@Test
	public void noFirstNameSignUpTest() {
		loginPage.signup("", "Doe", "user", "newPassword");
		WebElement firstNameError = loginPage.getFirstNameError();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(firstNameError));
		assertEquals("please enter your first name", firstNameError.getText());
	}
	
	@Test
	public void noLastNameSignUpTest() {
		loginPage.signup("John", "", "user", "newPassword");
		WebElement lastNameError = loginPage.getLastNameError();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(lastNameError));
		assertEquals("please enter your last name", lastNameError.getText());
	}

	@AfterClass
	public static void end() {
		driver.quit();
	}
}
