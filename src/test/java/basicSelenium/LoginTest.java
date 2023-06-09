package basicSelenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class LoginTest {
    ChromeDriver chrome;

    @BeforeEach
    public void openBrowser(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/driver/chromedriver.exe");
        chrome = new ChromeDriver();

        chrome.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        chrome.manage().window().maximize();
        chrome.get("http://todo.ly/");
    }

    @AfterEach
    public void closeBrowser(){
        chrome.quit();
    }

    @Test
    public void verifyLoginSuccessfully() throws InterruptedException {
        //click login button
        chrome.findElement(By.xpath(" //img[@src='/Images/design/pagelogin.png']")).click();
        //type email in email txtbox
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_TextBoxEmail")).sendKeys("miguel123@upb.com");
        // type pwd in password txtbox
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_TextBoxPassword")).sendKeys("12345");
        // click login button
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_ButtonLogin")).click();


        int logoutControlAmount = chrome.findElements(By.xpath("//a[text()='Logout']")).size();
        Assertions.assertTrue(logoutControlAmount == 1, "ERROR! the login was failed, please review credentials");

        Thread.sleep(5000);

    }
}
