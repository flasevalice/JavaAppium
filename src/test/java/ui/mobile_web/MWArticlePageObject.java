package ui.mobile_web;

import org.openqa.selenium.remote.RemoteWebDriver;
import ui.ArticlePageObject;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        //TITLE = "//*[@resource-id='pcs']//*",
        TITLE = "css:#content h1";
        FOOTER_ELEMENT = "css:footer";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "css:a#ca-watch";
        //OPTIONS_REMOVE_FROM_MYLISTS_BUTTON="css:a#ca-watch.watched";
        //FIRST_WORD_AT_TITLE= "xpath://*[@id='mf-section-0']//b";//#mf-section-0 > p > b //*[@id="mf-section-0"]/p/b  //*[@id="mf-section-0"]/p[2]/b
        //TITLE_FROM_CONTENTS_TPL="xpath://XCUIElementTypeStaticText[@name='{TITLE}']";
        FOOTER_ELEMENT = "css:footer";
        SAVE_BUTTON = "xpath://*[@resource-id='org.wikipedia:id/page_save'][@text='Save']";
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
        OPTIONS_REMOVE_FROM_MYLISTS_BUTTON = "css:a#ca-watch.watched";
        FIRST_WORD_AT_TITLE = "xpath://*[@id='mf-section-0']//b";//#mf-section-0 > p > b //*[@id="mf-section-0"]/p/b  //*[@id="mf-section-0"]/p[2]/b
        //TITLE_FROM_CONTENTS_TPL = "xpath://XCUIElementTypeStaticText[@name='{TITLE}']";
    }

public MWArticlePageObject(RemoteWebDriver driver) {
    super(driver);
}
}
