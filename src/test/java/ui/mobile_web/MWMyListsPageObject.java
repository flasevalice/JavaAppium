package ui.mobile_web;

import org.openqa.selenium.remote.RemoteWebDriver;
import ui.MyListsPageObject;

public class MWMyListsPageObject extends MyListsPageObject {
    static {
        ARTICLE_BY_TITLE_TPL = "xpath://ul[contains(@class, 'watchlist')]//h3[contains(text(), '{TITLE}')]";
        REMOVE_FROM_SAVED_BUTTON = "xpath://ul[contains(@class, 'watchlist')]//h3[contains(text(), '{TITLE}')]/../../a[contains(@class, 'watched')]";
        CLOSE_SYNC_POPUP="id:Close";
        SAVED_ARTICLE_ELEMENT = "xpath://li[@class='page-summary with-watchstar']";
    }

    public MWMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
