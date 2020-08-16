import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private By mailField = By.xpath("//input[@id='mailbox:login']");
    private By dropdownMailSelect = By.xpath("//div[@id='mailbox:select_container']");
    private By passwordButton = By.xpath("//input[@value='Ввести пароль']");
    private By signInButton = By.xpath("//input[@value='Ввести пароль']");
    private By passwordField = By.xpath("//input[@id='mailbox:password']");
    private By getLoginError = By.xpath("//div[@class='mailbox__row mailbox__row_condensed i-font-md i-color-coral']");


    public MainPage typeEmail(String email) {
        driver.findElement(mailField).sendKeys(email);
        return this;
    }

    public MainPage typePassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public MainPage clickPasswordButton(){
        driver.findElement(passwordButton).click();
        return new MainPage(driver);
    }

    public MainPage clickSignInButton(){
        driver.findElement(signInButton).click();
        return new MainPage(driver);
    }

    public MainPage login(String email, String option, String password){
        this.typeEmail(email);
        this.selectOption(option);
        this.clickPasswordButton();
        this.typePassword(password);
        this.clickSignInButton();
        return new MainPage(driver);
    }

    public MainPage selectOption (String option) {
        String optionXpath = String.format("//select/option[contains(text(),'%s')]", option);
        driver.findElement(By.xpath(optionXpath)).click();
        return this;
    }
    public String getErrorText() {
        return driver.findElement(getLoginError).getText();
    }


}
