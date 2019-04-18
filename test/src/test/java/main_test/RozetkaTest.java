package main_test;

import manage.TestBase;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RozetkaTest extends TestBase {
    public RozetkaTest(String browser) {
        super(browser);
    }

    @Test (priority = 1)
    public void comparisonStrings() throws InterruptedException {
        Actions actions = new Actions(e_driver);
        selectNotebook(actions);
        selectNotebookSSD();
        chooseTwoElements(actions);
        clickOnComparison();
        clickComparisonThings();
        clickOnlyComparison();
        int before = getDifferencesCount();
        System.out.println("BeforeRowsCount = " + before);
        int after = counterDiffCamparison();
        System.out.println("AfterRowsCount = " + after);
        Assert.assertEquals(after, before);
        }

    }

