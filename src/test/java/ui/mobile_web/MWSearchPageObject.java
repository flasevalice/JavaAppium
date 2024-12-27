package ui.mobile_web;

import org.openqa.selenium.remote.RemoteWebDriver;
import ui.SearchPageObject;

public class MWSearchPageObject extends SearchPageObject {

    public MWSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    static {
        SKIP_ELEMENT = "xpath://*[contains(@text, 'Skip')]";
        SEARCH_INIT_ELEMENT = "id:searchIcon";
        SEARCH_INIT_TEXT = "css:form>input[aria-label='Search Wikipedia']";
        SEARCH_INPUT = "css:form>input[type='search']";
        SEARCH_CANCEL_BUTTON = "css:button.clear";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://div[contains(@class, 'wikidata-description')][contains(text(),'{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "css:ul.mw-mf-page-list>li.page-summary";
        //SEARCH_RESULT_ELEMENT = "css:ul.mw-mf-page-list";
        SEARCH_RESULT_ELEMENTS = "css:ul.mw-mf-page-list";
        SEARCH_EMPTY_RESULT_ELEMENT = "css:p.without-results";
    }
}