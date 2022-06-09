package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
	private static final String URL = "http://127.0.0.1:5500/html/login.html";
	
	@FindBy(id = "title")
	private WebElement title;
	
	@FindBy(id = "username-input")
	private WebElement usernameInput;

	@FindBy(id = "password-input")
	private WebElement passwordInput;

	@FindBy(id = "first-name-input")
	private WebElement firstNameInput;

	@FindBy(id = "last-name-input")
	private WebElement lastNameInput;

	@FindBy(id = "login-button")
	private WebElement loginButton;

	@FindBy(id = "signup-button")
	private WebElement signupButton;

	@FindBy(id = "new-user-link")
	private WebElement newUserLink;

	@FindBy(id = "existing-user-link")
	private WebElement existingUserLink;

	@FindBy(xpath = "/html/body/section/div/div[3]/p")
	private WebElement usernameError;

	@FindBy(xpath = "/html/body/section/div/div[5]/p")
	private WebElement passwordError;

	@FindBy(xpath = "/html/body/section/div/div[1]/div/div[2]/p")
	private WebElement firstNameError;

	@FindBy(xpath = "/html/body/section/div/div[1]/div/div[4]/p")
	private WebElement lastNameError;

	public void login(String username, String password) {
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		loginButton.click();
	}

	public void signup(String firstName, String lastName, String username, String password) {
		newUserLink.click();
		firstNameInput.sendKeys(firstName);
		lastNameInput.sendKeys(lastName);
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		signupButton.click();
	}

	public static String getUrl() {
		return URL;
	}
	
	public WebElement getTitle() {
		return title;
	}

	public WebElement getUsernameInput() {
		return usernameInput;
	}

	public WebElement getPasswordInput() {
		return passwordInput;
	}

	public WebElement getFirstNameInput() {
		return firstNameInput;
	}

	public WebElement getLastNameInput() {
		return lastNameInput;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}

	public WebElement getSignupButton() {
		return signupButton;
	}

	public WebElement getNewUserLink() {
		return newUserLink;
	}

	public WebElement getExistingUserLink() {
		return existingUserLink;
	}

	public WebElement getUsernameError() {
		return usernameError;
	}

	public WebElement getPasswordError() {
		return passwordError;
	}

	public WebElement getFirstNameError() {
		return firstNameError;
	}

	public WebElement getLastNameError() {
		return lastNameError;
	}
}
