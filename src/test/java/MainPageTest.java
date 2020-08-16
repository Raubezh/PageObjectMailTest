import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class MainPageTest {

    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        driver = new FirefoxDriver();
//        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("https://mail.ru");
        mainPage = new MainPage(driver);
    }

    @Test
    public void loginTest(){
        mainPage = mainPage.login("2idp", "inbox.ru","488768");
    }

    @Test
    public void loginWithIncorrectCredsTest(){
        mainPage = mainPage.login("fghh","mail.ru", "fgtr");
        String error = mainPage.getErrorText();
        Assert.assertEquals("Неверное имя или пароль", error);
        MainPage newMainPage = mainPage.login("2idp", "@inbox.ru","848586");
    }

    @After
    public void tearDown(){
        driver.quit();
    }

}
