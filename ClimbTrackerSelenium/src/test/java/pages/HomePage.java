package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	private static final String URL = "http://127.0.0.1:5500/html/home.html";

	@FindBy(xpath = "/html/body/header/nav/button")
	private WebElement logoutButton;

	@FindBy(xpath = "/html/body/section/article/div[2]/button[1]")
	private WebElement populateTableWithTestDataButton;

	@FindBy(xpath = "/html/body/section/article/div[2]/button[2]")
	private WebElement deleteSelectedButton;

	@FindBy(xpath = "/html/body/section/article/div[2]/button[3]")
	private WebElement deleteAllButton;

	@FindBy(xpath = "/html/body/section/article/div[1]/table/tbody/tr[1]/input")
	private WebElement checkbox1;

	@FindBy(xpath = "/html/body/section/article/div[1]/table/tbody/tr[2]/input")
	private WebElement checkbox2;

	@FindBy(xpath = "/html/body/section/article/div[1]/table/tbody/tr[1]")
	private WebElement tableRow1;

	@FindBy(xpath = "/html/body/section/article/div[1]/table/tbody/tr[1]/td[1]")
	private WebElement tableRouteName1;
	
	@FindBy(xpath = "/html/body/section/article/div[1]/table/tbody/tr[1]/td[2]")
	private WebElement tableTime1;
	
	@FindBy(id="route-options-input")
	private WebElement routeInput;
	
	@FindBy(xpath = "/html/body/section/article/div[3]/div[1]/p")
	private WebElement routeError;

	@FindBy(id="time-input")
	private WebElement timeTakenInput;
	
	@FindBy(xpath = "/html/body/section/article/div[3]/div[2]/p")
	private WebElement timeError;
	
	@FindBy(id="attempts-input")
	private WebElement attemptsInput;
	
	@FindBy(xpath = "/html/body/section/article/div[3]/div[3]/p")
	private WebElement attemptsError;
	
	@FindBy(xpath="/html/body/section/article/div[3]/button")
	private WebElement logClimbButton;
	
	@FindBy(tagName = "tr")
	private List<WebElement> tableRows;

	@FindBy(id = "title")
	private WebElement title;

	@FindBy(tagName = "h2")
	private WebElement subTitle;

	public void logout(WebDriver driver) {
		logoutButton.click();
		driver.get("http://127.0.0.1:5500/html/index.html");
	}
	
	public void deleteAllEntries(WebDriver driver) {
		deleteAllButton.click();
		driver.switchTo().alert().accept();
	}
	
	public void deleteSelectedEntries(WebDriver driver, WebDriverWait wait) {
		checkbox1.click();
		wait.until(ExpectedConditions.elementToBeClickable(deleteSelectedButton));
		deleteSelectedButton.click();
		driver.switchTo().alert().accept();
	}
	
	public void createEntry(String routeName, String timeTaken, String attempts) {
		routeInput.sendKeys(routeName);
		timeTakenInput.sendKeys(timeTaken);
		attemptsInput.sendKeys(attempts);
		logClimbButton.click();
	}

	public void populateTableWithTestData() {
		populateTableWithTestDataButton.click();
	}

	public String getUrl() {
		return URL;
	}

	public WebElement getLogoutButton() {
		return logoutButton;
	}

	public WebElement getCheckbox1() {
		return checkbox1;
	}

	public WebElement getTableRow1() {
		return tableRow1;
	}

	public WebElement getTableRouteName1() {
		return tableRouteName1;
	}
	
	public WebElement getTableTime1() {
		return tableTime1;
	}

	public WebElement getRouteInput() {
		return routeInput;
	}

	public WebElement getRouteError() {
		return routeError;
	}

	public WebElement getTimeTakenInput() {
		return timeTakenInput;
	}

	public WebElement getTimeError() {
		return timeError;
	}

	public WebElement getAttemptsInput() {
		return attemptsInput;
	}

	public WebElement getAttemptsError() {
		return attemptsError;
	}

	public WebElement getPopulateTableWithTestDataButton() {
		return populateTableWithTestDataButton;
	}

	public WebElement getDeleteSelectedButton() {
		return deleteSelectedButton;
	}

	public WebElement getDeleteAllButton() {
		return deleteAllButton;
	}

	public List<WebElement> getTableRows() {
		return tableRows;
	}
	
	public WebElement getTitle() {
		return title;
	}

	public WebElement getSubTitle() {
		return subTitle;
	}
}
