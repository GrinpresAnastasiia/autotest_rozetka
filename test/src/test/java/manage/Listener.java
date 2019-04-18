package manage;
import manage.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Listener extends AbstractWebDriverEventListener {
    Logger logger = LoggerFactory.getLogger(TestBase.class);

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {

        System.out.println("search: " + by);
        logger.info("search   "+ by);
    }
    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        System.out.println("clickOn: " + element);
        logger.info("clickOn: " + element);

    }
    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        System.out.println(element + " clicked");
        logger.info(element + " clicked");

    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        System.out.println(by + " found");
        logger.info(by +  "  found");}

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        System.out.println(throwable);
        logger.error(String.valueOf(throwable));
    }
}
