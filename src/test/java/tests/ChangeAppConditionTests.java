package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import ui.ArticlePageObject;
import ui.SearchPageObject;
import ui.factories.ArticlePageObjectFactory;
import ui.factories.SearchPageObjectFactory;

@Epic("Change app condition tests")
public class ChangeAppConditionTests extends CoreTestCase {
    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value = "Article")})
    @DisplayName("Change screen orientation on search results")
    @Description("The test checks the display of the article title by change orientation on the screen")
    @Step("Starting test testChangeScreenOrientationOnSearchResults")
    @Severity(value = SeverityLevel.MINOR)
    public void testChangeScreenOrientationOnSearchResults() {
        //String searchLine = "Java";
        //String articleTitle = "Java (programming language)";
        //String articleDescription = "Object-oriented programming language";
        //mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Skip')]"), "Невозможно найти элемент", 15);
        //mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Невозможно найти элемент", 15);
        //mainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), searchLine, "Невозможно найти элемент", 15);
        //mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='" + articleDescription+"']"), "Невозможно найти элемент", 30);
        //String searchResultLocator = "//*[@resource-id='pcs-edit-section-title-description']";
        //WebElement titleBeforeLandscapeRotation = mainPageObject.waitForElementPresent(By.xpath(searchResultLocator),"Cannot find title of article",30);
        //mainPageObject.rotateLandscape();
        //WebElement titleAfterLandscapeRotation = mainPageObject.waitForElementPresent(By.xpath(searchResultLocator),"Cannot find title of article",30);
        //Assert.assertEquals( "Article title have been changed after screen rotation", titleBeforeLandscapeRotation, titleAfterLandscapeRotation);
        //mainPageObject.rotatePortrait();
        //WebElement titleAfterSecondRotation = mainPageObject.waitForElementPresent(By.xpath(searchResultLocator),"Cannot find title of article",30);
        //Assert.assertEquals( "Article title have been changed after screen rotation", titleBeforeLandscapeRotation, titleAfterSecondRotation);
        if (Platform.getInstance().isMw()) {
            return;
        }
        String articleTitle = "Java (programming language)";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        if (!Platform.getInstance().isMw()) {
            searchPageObject.skipMainScreen();
        }
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.assertElementHasTextByXpath(articleTitle, "Actual text isn't expected");
        WebElement titleBeforeLandscapeRotation = articlePageObject.waitForArticleTitleElement(articleTitle);
        this.rotateLandscape();
        WebElement titleAfterLandscapeRotation = articlePageObject.waitForArticleTitleElement(articleTitle);
        Assert.assertEquals("Article title have been changed after screen rotation", titleBeforeLandscapeRotation, titleAfterLandscapeRotation);
        this.rotatePortrait();
        WebElement titleAfterSecondRotation = articlePageObject.waitForArticleTitleElement(articleTitle);
        Assert.assertEquals("Article title have been changed after screen rotation", titleBeforeLandscapeRotation, titleAfterSecondRotation);
    }


    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value = "Article")})
    @DisplayName("Change search article in background")
    @Description("The test checks the display of the article title by minimizing and expanding the application")
    @Step("Starting test testSearchArticleInBackground")
    @Severity(value = SeverityLevel.MINOR)
    public void testChangeSearchArticleInBackground() {
        //String searchLine = "Java";
        //String articleTitle = "Java (programming language)";
        //String articleDescription = "Object-oriented programming language";
        //mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Skip')]"), "Невозможно найти элемент", 15);
        //mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Невозможно найти элемент", 15);
        //mainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), searchLine, "Невозможно найти элемент", 15);

        //String searchResultLocator = "//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='"+articleDescription+"']";
        //mainPageObject.waitForElementPresent(By.xpath(searchResultLocator), "Cannot find element", 15);
        //driver.runAppInBackground(15);
        //mainPageObject.waitForElementPresent(By.xpath(searchResultLocator), "Cannot find element after returning from background ", 15);
        if (Platform.getInstance().isMw()) {
            return;
        }
        String articleTitle = "Java (programming language)";
        String articleDescription = "Object-oriented programming language";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        if (!Platform.getInstance().isMw()) {
            searchPageObject.skipMainScreen();
        }
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult(articleDescription);
        this.backgroundApp(15);
        searchPageObject.waitForSearchResult(articleDescription);
    }
}