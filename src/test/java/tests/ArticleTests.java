package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import org.junit.Assert;
import org.junit.Test;
import ui.ArticlePageObject;
import ui.SearchPageObject;
import ui.factories.ArticlePageObjectFactory;
import ui.factories.SearchPageObjectFactory;

@Epic("Tests for Articles")
public class ArticleTests extends CoreTestCase {
    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value = "Article")}) // отмечаем, какие фичи затронуты тестом
    @DisplayName("Compere article title with expected one") // отображаемое имя кейса в отчёте аллюра раздела Suites
    @Description("The test checks the presence and correct title text inside the opened article in web view") //описание того, как работает тест
    @Step("Starting test testCompereArticleTitle") //разметка шагов, описание последовательности действий
    @Severity(value = SeverityLevel.BLOCKER)
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

        // articlePageObject.takeScreenshot("article_page");
        if (!Platform.getInstance().isMw()) {
            articlePageObject.assertElementHasTextByXpath("Java (programming language)", "Actual text isn't expected");
        } else {
            String articleTitle = articlePageObject.getArticleTitle();
            Assert.assertEquals("Actual text isn't expected", "Java (programming language)", articleTitle);
        }
    }

    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value = "Article")}) // отмечаем, какие фичи затронуты тестом
    @DisplayName("Swipe article to the footer")
    @Description("We open article and swipe it to the footer")
    @Step("Starting test testSwipeArticle")
    @Severity(value = SeverityLevel.MINOR)
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
    @Features(value = {@Feature(value="Search"),@Feature(value = "Article")})
    @DisplayName("Checking the number of results")
    @Description("The test checks for finding a valid value and producing a non-empty list of results")
    @Step("Starting test testAmountOfNotEmptySearch")
    @Severity(value = SeverityLevel.NORMAL)
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

        Assert.assertTrue("We found too few results",amountOfSearchResults > 0);
    }

    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value = "Article")})
    @DisplayName("Checking empty search")
    @Description("The test checks the search for an invalid value and the return of an empty search result")
    @Step("Starting test testAmountOfEmptySearch")
    @Severity(value = SeverityLevel.NORMAL)
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