package tests;

import lib.CoreTestCase;
import lib.Platform;
import org.junit.Test;
import ui.SearchPageObject;
import ui.factories.SearchPageObjectFactory;

public class SearchTests extends CoreTestCase {
    @Test
    public void testSearch() {
//        mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Skip')]"), "Невозможно найти элемент", 5);
//        mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Невозможно найти элемент", 5);
//        mainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Java", "Невозможно найти элемент", 15);
//        mainPageObject.waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Object-oriented programming language']"), "Невозможно найти элемент", 15);

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        if (!Platform.getInstance().isMw()){
            searchPageObject.skipMainScreen();
        }
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch() {
        //mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Skip')]"), "Невозможно найти элемент", 15);
        //mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Невозможно найти элемент", 15);
        //mainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "Java", "Невозможно найти элемент", 15);
        //mainPageObject.waitForElementAndClear(By.id("org.wikipedia:id/search_src_text"), "Невозможно найти элемент", 10);
        //mainPageObject.waitForElementPresent(By.id("org.wikipedia:id/search_container"), "Невозможно найти элемент", 15);
        //mainPageObject.waitForElementNotPresent(By.id("org.wikipedia:id/search_close_btn"), "Элемент остался", 15);
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        if (!Platform.getInstance().isMw()){
            searchPageObject.skipMainScreen();
        }
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testSearchFieldHasText() {
        //mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Skip')]"), "Невозможно найти элемент", 15);
        //mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Невозможно найти элемент", 15);
        //mainPageObject.assertElementHasText(By.id("org.wikipedia:id/search_src_text"), "Search Wikipedia", "Элемент по данному локатору не содержит ожидаемый текст", 15);
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        if (!Platform.getInstance().isMw()){
            searchPageObject.skipMainScreen();
        }
        searchPageObject.initSearchInput();
        if (!Platform.getInstance().isMw()){
            searchPageObject.initSearchHasText("Search Wikipedia");
        } else
            searchPageObject.initSearchHasAttributeInText("Search Wikipedia", "aria-label");
    }

    @Test
    public void testAssertElementPresent() {
        //mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Skip')]"), "Невозможно найти элемент", 15);
        //поиск первой
        //mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Невозможно найти элемент", 15);
        //mainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "Java", "Невозможно найти элемент", 15);
        //mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Object-oriented programming language']"), "Невозможно найти элемент", 20);
        //проверка наличия тайтл
        //mainPageObject.assertElementPresent(By.id("org.wikipedia:id/page_list_item_title"), "Cannot find title of article");
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        if (!Platform.getInstance().isMw()){
            searchPageObject.skipMainScreen();
        }
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        if (Platform.getInstance().isMw()){
            searchPageObject.waitForSearchResult("Java (programming language)");
        } else
            searchPageObject.assertThereIsResultOfSearch();
    }

    @Test
    public void testFindTitlesAndCancelSearch() {
//        mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Skip')]"), "Невозможно найти элемент", 15);
//        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Невозможно найти элемент", 15);
//        mainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "Java", "Невозможно найти элемент", 15);
//
//        mainPageObject.waitForElementPresent(By.id("org.wikipedia:id/search_results_list"), "Невозможно найти элемент", 15);
//        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"),"Невозможно найти элемент", 15);
//        mainPageObject.waitForElementNotPresent(By.id("org.wikipedia:id/search_results_list"), "Невозможно найти элемент", 15);

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        if (!Platform.getInstance().isMw()){
            searchPageObject.skipMainScreen();
        }
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResults("Object-oriented programming language");
        searchPageObject.clickCancelSearch();
        if (Platform.getInstance().isMw()){
            searchPageObject.assertThereIsEmptySearchResult();
        } else
              searchPageObject.assertThereIsNoResultOfSearch();
    }
}