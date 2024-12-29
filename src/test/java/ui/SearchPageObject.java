package ui;

import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPageObject extends MainPageObject {
    protected static String
            SKIP_ELEMENT,
            SEARCH_INIT_ELEMENT,
            SEARCH_INIT_TEXT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_ELEMENT,
            SEARCH_RESULT_ELEMENTS,
            SEARCH_EMPTY_RESULT_ELEMENT,
            SEARCH_RESULT_TITLE_TPL;

    public SearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

    @Step("Skip main screen (for Android)")
    public void skipMainScreen() {
        this.waitForElementAndClick(SKIP_ELEMENT, "Cannot find and click Skip button", 5);
    }

    @Step("Activate search")
    public void initSearchInput() {
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element");
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
    }

    public void initSearchHasText(String text) {
        this.assertElementHasText(SEARCH_INIT_TEXT, text, "Cannot find search input text", 5);
    }

    public void initSearchHasAttributeInText(String text, String attr) {
        this.assertElementHasTextInAttribute(SEARCH_INIT_TEXT, text, attr,"Cannot find search input text", 5);
    }

    @Step("Waiting for the search cancel button to appear")
    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find X to cancel search", 5);
    }

    @Step("Waiting for the search cancel button to disappear")
    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "X is still present on the page", 5);
    }

    @Step("Click to exit search")
    public void clickCancelSearch() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find X and click", 5);
    }

    @Step("Enter '{search_line}' into the search field")
    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find search input", 5);
    }
    @Step("Waiting for search result")
    public void waitForSearchResult(String substring) {
        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementPresent(searchResultXpath, "Cannot find search result with substring " + substring);
    }

    @Step("Waiting for search results")
    public void waitForSearchResults(String substring) {
        this.waitForElementPresent(SEARCH_RESULT_ELEMENTS, "Cannot find search results with substring " + substring);
    }

    @Step("Click by article with some substring")
    public void clickByArticleWithSubstring(String substring) {
        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementAndClick(searchResultXpath, "Cannot find and click search result with substring",10);
    }

    @Step("Get the number of articles found")
    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(SEARCH_RESULT_ELEMENT, "Cannot find anything by request", 15);
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    @Step("Expecting empty search result to be displayed")
    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot find empty result element", 15);
    }

    @Step("Make sure the search results field is empty")
    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENTS, "We supposed not find any results");
    }

    @Step("Make sure the search results field is empty")
    public void assertThereIsEmptySearchResult() {
        this.assertElementNotDisplayed(SEARCH_RESULT_ELEMENT, "We supposed not find any results");
    }

    private static String getResultSearchElementFromTitle (String title) {
        return SEARCH_RESULT_TITLE_TPL.replace("{TITLE}",title );
    }

    @Step("Make sure the search results field is empty")
    public void assertThereIsResultOfSearch() {
        this.assertElementPresent(SEARCH_RESULT_ELEMENT, "We supposed not find any results");
    }
}