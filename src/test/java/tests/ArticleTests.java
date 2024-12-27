package tests;

import lib.CoreTestCase;
import lib.Platform;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;
import ui.ArticlePageObject;
import ui.SearchPageObject;
import ui.factories.ArticlePageObjectFactory;
import ui.factories.SearchPageObjectFactory;

public class ArticleTests extends CoreTestCase {
    @Test
    public void testCompareArticleTitle() throws InterruptedException {
        //mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Skip')]"), "Невозможно найти элемент", 15);
        //mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Невозможно найти элемент", 15);
        //mainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "Java", "Невозможно найти элемент", 15);
        //mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Object-oriented programming language']"), "Невозможно найти элемент", 20);
        //mainPageObject.assertElementHasText(By.xpath("//*[@resource-id='pcs']//*[@text='Java (programming language)']"), "Java (programming language)", "Элемент по данному локатору не содержит ожидаемый текст", 20);

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        if (!Platform.getInstance().isMw()){
            searchPageObject.skipMainScreen();
        }
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.assertElementHasTextByXpath("Java (programming language)", "Actual text isn't expected");
    }

    @Test
    public void testSwipeArticle() {
        //mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Skip')]"), "Невозможно найти элемент", 15);
        //mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Невозможно найти элемент", 15);
        //mainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "Appium", "Невозможно найти элемент", 15);
        //mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Automation for Apps']"), "Невозможно найти элемент", 20);
        //mainPageObject.swipeUpToFindElement(By.xpath("//*[contains(@text, 'View article in browser')]"), "Невозможно найти статью", 20);

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        if (!Platform.getInstance().isMw()){
            searchPageObject.skipMainScreen();
        }
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubstring("Automation for Apps");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.swipeToFooter();
    }

    @Test
    public void testAmongOfNotEmptySearch() {
        //mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Skip')]"), "Невозможно найти элемент", 15);
        //mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Невозможно найти элемент", 15);
        //mainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), searchLine, "Невозможно найти элемент", 15);

        //String searchResultLocator = "//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        //mainPageObject.waitForElementPresent(By.xpath(searchResultLocator), "Cannot find anything by request " + searchLine,15);
        //int amountOfSearchResults = mainPageObject.getAmountOfElements(By.xpath(searchResultLocator));
        //Assert.assertTrue("We found too few results",amountOfSearchResults > 0);

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        if (!Platform.getInstance().isMw()){
            searchPageObject.skipMainScreen();
        }
        searchPageObject.initSearchInput();
        String searchLine = "Linkin Park Discography";
        searchPageObject.typeSearchLine(searchLine);
        int amountOfSearchResults = searchPageObject.getAmountOfFoundArticles();

        assertTrue("We found too few results",amountOfSearchResults > 0);
    }

    @Test
    public void testAmongOfEmptySearch() {
        //String searchLine = "rgehrthdsdfhfhdh";
        //mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Skip')]"), "Невозможно найти элемент", 15);
        //mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Невозможно найти элемент", 15);
        //mainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), searchLine, "Невозможно найти элемент", 15);
        //String searchResultLocator = "//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        //String emptyResultLabel = "//*[@text='No results']";
        //mainPageObject.waitForElementPresent(By.xpath(emptyResultLabel),"Cannot find empty result label by the request " + searchLine,15);
        //mainPageObject.assertElementNotPresent(By.xpath(searchResultLocator),"We've find some results by the request '" + searchLine + "'");

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        if (!Platform.getInstance().isMw()){
            searchPageObject.skipMainScreen();
        }
        searchPageObject.initSearchInput();

        String searchLine = "rgehrthdsdfhfhdh";
        searchPageObject.typeSearchLine(searchLine);
        searchPageObject.waitForEmptyResultsLabel();
        searchPageObject.assertThereIsNoResultOfSearch();
    }
}