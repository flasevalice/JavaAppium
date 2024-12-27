package ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import static ui.MyListsPageObject.REMOVE_FROM_SAVED_BUTTON;

abstract public class ArticlePageObject extends MainPageObject {
    protected static String
            //TITLE = "//*[@resource-id='pcs']//*",
            TITLE,
            FOOTER_ELEMENT,
            SAVE_BUTTON,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            TITLE_WITH_NAME_TPL,
            TITLE_ARTICLE_TPL,
            SEARCH_RESULT_LOCATOR,
            MY_LIST_NAME,
            MY_OLD_LIST_ELEMENT,
            HEADER,
            CLOSE_CONTENTS_BUTTON,
            OPTIONS_REMOVE_FROM_MYLISTS_BUTTON,
            FIRST_WORD_AT_TITLE;


    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultArticleTitle(String article_title) {
        return TITLE_WITH_NAME_TPL.replace("{TITLE}", article_title);
    }

    private static String getTitle(String article_title) {
        return TITLE_ARTICLE_TPL.replace("{TITLE}", article_title);
    }
    /* TEMPLATES METHODS */

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page", 15);
    }

    public WebElement waitForArticleTitleElement(String article_title) {
        String searchResultXpath = getTitle(article_title);
        return this.waitForElementPresent(searchResultXpath, "Cannot find article title on page", 15);
    }

    public String getArticleTitle() {
        WebElement titleElement = waitForTitleElement();
        if (Platform.getInstance().isAndroid())
            return titleElement.getAttribute("text");
        else return titleElement.getText();

        //return titleElement.getAttribute("text");
        //String searchResult =
        //return getResultArticleTitle(searchResult);
        //WebElement element = waitForElementPresent(by, err_msg, timeoutInSeconds);
        //return titleElement.getText();
        //Assert.assertEquals(err_msg, text, articleTitle);
    }

    public void assertElementHasTextByXpath(String expectedText, String err_msg) {
        String actualText = waitForArticleTitleElement(expectedText).getText();
        Assert.assertEquals(
                err_msg,
                expectedText,
                actualText);
    }

    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    20
            );
        } else {
            this.scrollWebPageTillElementNotVisible(FOOTER_ELEMENT, "Can not find the end of article", 40);
        }
    }

    public void addArticleToMyNewList(String nameOfFolder) {
        this.waitForElementAndClick(SAVE_BUTTON, "Cannot find button 'Save'", 5);

        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find button 'Add to list'", 5);

        this.waitForElementAndSendKeys(MY_LIST_NAME_INPUT, nameOfFolder, "Cannot put text into article input", 10);

        this.waitForElementAndClick(MY_LIST_OK_BUTTON, "Cannot press 'OK' button", 15);
    }

    public void addArticleToList() {
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find button 'Add to list'", 5);
    }

    public void addArticlesToMySaved() {
        if (Platform.getInstance().isMw()) {
            sleep(2000);
            removeArticleFromSavedIfItAdded();
        }
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Can not find and click 'Add to saved' button",
                15
        );
    }

    public void removeArticleFromSavedIfItAdded() {
        sleep(2000);
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MYLISTS_BUTTON)) {
            this.waitForElementAndClick(OPTIONS_REMOVE_FROM_MYLISTS_BUTTON, "Can not find and click button to remove article from saved", 20);
            if(Platform.getInstance().isMw()) {
                driver.navigate().refresh();
            }
            sleep(2000);
            this.waitForElementPresent(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Can not find button to add article to MyLists after removing it from this list before", 30);
        }
    }

    public String waitForContentOfArticle() {
        WebElement element = this.waitForElementPresent(FIRST_WORD_AT_TITLE, "Can not find first word of ", 10);
        return element.getText();
    }

    public void addArticleToMyOldList() {
        this.waitForElementAndClick(SAVE_BUTTON, "Cannot find button 'Save'", 5);
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find button 'Add to list'", 5);
        this.waitForElementAndClick(MY_OLD_LIST_ELEMENT, "Cannot find old list", 5);
    }

    public void closeArticle() {
        this.waitForElementAndClick(CLOSE_ARTICLE_BUTTON, "Cannot close article, cannot find X link", 5);
    }
}