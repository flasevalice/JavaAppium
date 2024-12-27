package ui.android;

import org.openqa.selenium.remote.RemoteWebDriver;
import ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {
    static {
        SKIP_ELEMENT = "xpath://*[contains(@text, 'Skip')]";
                SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]";
                SEARCH_INIT_TEXT = "id:org.wikipedia:id/search_src_text";
                SEARCH_INPUT = "xpath://*[contains(@text,'Search Wikipedia')]";
                SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
                SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_description'][contains(@text,'{SUBSTRING}')]";
                SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title']";
                SEARCH_RESULT_ELEMENTS = "id:org.wikipedia:id/search_results_list";
                SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results']";
    }
    public AndroidSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
