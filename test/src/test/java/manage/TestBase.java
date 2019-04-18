package manage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import java.lang.reflect.Method;
import java.util.List;
import static org.openqa.selenium.remote.BrowserType.*;


public class TestBase {
    protected WebDriver e_driver;
    protected EventFiringWebDriver driver;
    protected Listener eventListener;
    protected Logger logger = LoggerFactory.getLogger(TestBase.class);
    private String browser;

    public TestBase(String browser) {
        this.browser = browser;
        e_driver = new ChromeDriver();
    }

    @BeforeClass
    public void setUp() {

        if (browser.equals(CHROME)) {
            e_driver = new EventFiringWebDriver(new ChromeDriver());
        } else if (browser.equals(FIREFOX)) {
            e_driver = new EventFiringWebDriver(new FirefoxDriver());
        } else if (browser.equals(EDGE)) {
            e_driver = new EventFiringWebDriver(new EdgeDriver());
        }
        eventListener = new Listener();
        driver = new EventFiringWebDriver(e_driver).register(eventListener);
        e_driver.navigate().to("https://rozetka.com.ua/");
        e_driver.manage().window().maximize();
    }

    @BeforeMethod
    public void testLogStart(Method m) {
        logger.info("Start test: " + m.getName());
    }

    public int getDifferencesCount() {
        String rowLocator = "//div[@class='comparison-t-row']";
        int rowsCount = driver.findElements(By.xpath(rowLocator)).size();
        System.out.println("rowLocator= " + rowsCount);//27
        List<WebElement> rows = driver.findElements(By.xpath(rowLocator));
        int counter = 0;
        for (int i = 0; i < rows.size(); i++){
            WebElement row = rows.get(i);
            String name = row.getAttribute("name");
            if(name.equals("different")){
                counter++;
            }
        }
        return counter;
    }

    public int counterDiffCamparison() {
        String rowLocator1 = "//div[@class='comparison-t']//div[@class='comparison-t-row']";
        int rowsCount1 = driver.findElements(By.xpath(rowLocator1)).size();
        return rowsCount1;

    }

    public void clickOnlyComparison() {
        driver.findElement(By.cssSelector("[class='m-tabs-link novisited']")).click();
    }


    public void clickComparisonThings() {
        driver.findElement(By.xpath("//span[contains(text(),'Сравнить эти товары')]")).click();
    }

    public void clickOnComparison() {
        driver.findElement(By.id("comparison-header")).click();
    }

    public void selectNotebookSSD() {
        driver.findElement(By.cssSelector("[title='Ноутбуки с SSD']")).click();
    }

    public void selectNotebook(Actions actions) {
        WebElement element = driver.findElement(By.cssSelector("[data-title='Ноутбуки и компьютеры']"));
        actions.moveToElement(element).build().perform();
        driver.findElement(By.cssSelector("[href='https://rozetka.com.ua/notebooks/c80004/']")).click();
    }

    public void chooseTwoElements(Actions actions) throws InterruptedException {
        List<WebElement> myElements = driver.findElements(By.cssSelector(".g-i-tile-catalog"));
        int counter = 0;
        for (WebElement item : myElements) {
            actions.moveToElement(item).build().perform();
            Thread.sleep(2000);
            actions.moveToElement(driver.findElement(By.cssSelector("[title^='Добавить к']"))).click().build().perform();
            Thread.sleep(2000);
            counter++;
            if (counter == 2) break;
        }
        System.out.println("Counter = " + counter);
    }

    @AfterMethod
    public void testLogStop(Method m) {
        logger.info("Stop test: " + m.getName());
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        e_driver.quit();
    }
}




