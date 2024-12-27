package ui.android;

import org.openqa.selenium.remote.RemoteWebDriver;
import ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {
    static {
        //TITLE = "//*[@resource-id='pcs']//*",
        TITLE = "xpath:org.wikipedia:id/view_page_title_text";
        FOOTER_ELEMENT = "xpath://*[contains(@text, 'View article in browser')]";
        SAVE_BUTTON = "xpath://*[@resource-id='org.wikipedia:id/page_save'][@text='Save']";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@resource-id='org.wikipedia:id/snackbar_action'][@text='Add to list']";
        ADD_TO_MY_LIST_OVERLAY = "xpath:org.wikipedia:id/onboarding_button";
        MY_LIST_NAME_INPUT = "xpath://*[@resource-id='org.wikipedia:id/text_input'][@text='Name of this list']";
        MY_LIST_OK_BUTTON = "xpath://*[@resource-id='android:id/button1'][@text='OK']";
        CLOSE_ARTICLE_BUTTON = "xpath://ui.android.widget.ImageButton[@content-desc='Navigate up']";
        TITLE_WITH_NAME_TPL = "xpath://*[@text='{TITLE}']";
        TITLE_ARTICLE_TPL = "xpath://*[@resource-id='pcs']//*[@text='{TITLE}']";
        SEARCH_RESULT_LOCATOR = "xpath://*[@resource-id='pcs-edit-section-title-description']";
        MY_LIST_NAME = "xpath://*[@text='Learning programming']";
        MY_OLD_LIST_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/item_title_container']";
        HEADER = "xpath:org.wikipedia:id/page_header_view";
    }

    public AndroidArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
