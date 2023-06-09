package basicSelenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Date;

public class CrudItemTest {
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
    public void crudVerifyItemTest() throws InterruptedException{
        //click login button
        chrome.findElement(By.xpath(" //img[@src='/Images/design/pagelogin.png']")).click();
        //type email in email txtbox
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_TextBoxEmail")).sendKeys("miguel123@upb.com");
        // type pwd in password txtbox
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_TextBoxPassword")).sendKeys("12345");
        // click login button
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_ButtonLogin")).click();
        // Crear un Projecto
        String nameProject = "Miguel"+new Date().getTime();
        // click AddNewProject
        chrome.findElement(By.xpath("//td[text()='Add New Project']")).click();
        // set name project
        chrome.findElement(By.id("NewProjNameInput")).sendKeys(nameProject);
        // click Add project
        chrome.findElement(By.id("NewProjNameButton")).click();
        // click on project name
        chrome.findElement(By.xpath("(//li//td[text()='"+nameProject+"'])[last()]")).click();

        //Crear Item
        //click on add bar new item
        chrome.findElement(By.id("NewItemContentInput")).click();
        //create name item
        String nameItem = "Item de prueba " + new Date().getTime();
        //set name item
        chrome.findElement(By.id("NewItemContentInput")).sendKeys(nameItem);
        //click Add item
        chrome.findElement(By.id("NewItemAddButton")).click();
        //verificacion create item
        Assertions.assertTrue(chrome.findElements(By.xpath("(//li//td/div[text()='"+nameItem+"'])[last()]")).size() == 1 ,
                "ERROR, the project was not created");
        Thread.sleep(2000);

        //click on item
        chrome.findElement(By.xpath("//li[last()]//div//td[@class='ItemContent']")).click();
        //remove text item
        chrome.findElement(By.id("ItemEditTextbox")).clear();

        //Actualizar el item
        String updateName = "Item actualizacion " + new Date().getTime();
        //change name item
        chrome.findElement(By.id("ItemEditTextbox")).sendKeys(updateName);
        //save
        chrome.findElement(By.xpath("//li[@class=\"BaseItemLi acceptItem acceptBoth\"][last()]"));
        Assertions.assertTrue(chrome.findElements(By.xpath("(//li//td/div[text()='"+updateName+"'])[last()]")).size() == 1 ,
                "ERROR, the project was not created");
        Thread.sleep(2000);

        //Borrar Item
        //click on menu icon
        chrome.findElement(By.xpath("//img[@style=\"display: inline;\"]")).click();
        //click on delete button
        chrome.findElement(By.xpath("//ul//li[@class=\"delete separator\"]//a[@href='#delete']")).click();
        Thread.sleep(2000);

        Assertions.assertTrue(chrome.findElements(By.xpath("(//li//td//div[text()='"+updateName+"'])[last()]")).size() == 0,
                "ERROR, the project was not deleted");
    }
}
