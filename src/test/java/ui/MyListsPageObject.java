package ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

import static java.lang.Thread.sleep;

abstract public class MyListsPageObject extends MainPageObject {

    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            CLOSE_SYNC_POPUP,
            REMOVE_FROM_SAVED_BUTTON,
            SAVED_ARTICLE_ELEMENT;

    public MyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    private static String getFolderXpathByName(String nameOfFolder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", nameOfFolder);
    }

    private static String getSavedArticleXpathByTitle(String articleTitle) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", articleTitle);
    }

    private static String getRemovedLocatorByTitle(String articleTitle) {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", articleTitle);
    }


    public void openFolderByName(String nameOfFolder) {
        String folderNameXpath = getFolderXpathByName(nameOfFolder);
        this.waitForElementAndClick(folderNameXpath, "Cannot find folder by name " + nameOfFolder, 5);
    }

    public void waitForArticleToDisappearByTitle(String articleTitle) {
        String articleXpath = getSavedArticleXpathByTitle(articleTitle);
        this.waitForElementNotPresent(articleXpath, "Saved article still present with title " + articleTitle, 5);
    }

    public void waitForArticleToAppearByTitle(String articleTitleJavascript) {
        String articleXpath = getSavedArticleXpathByTitle(articleTitleJavascript);
        this.waitForElementPresent(articleXpath, "Cannot find saved article by title " + articleTitleJavascript, 10);
    }


    public void closeSyncYourSavedArticlesPopup(){
        this.waitForElementAndClick(CLOSE_SYNC_POPUP, "Can not find and click close button for 'Sync your saved articles' pop-up", 10);
    }

    public void swipeArticleToDelete(String articleTitle) {
        this.waitForArticleToAppearByTitle(articleTitle);

        String articleXpath = getSavedArticleXpathByTitle(articleTitle);
        if (Platform.getInstance().isAndroid()) {
            this.swipeToTheLeftElement(articleXpath, "Cannot find saved article");
        } else {
            sleep(700);
            String remove_locator = getRemovedLocatorByTitle(articleTitle);
            this.waitForElementAndClick(remove_locator, "Cannot click button to remove saved", 20);
        }
        sleep(2000);
        if(Platform.getInstance().isMw()) {
            driver.navigate().refresh();
        }
        sleep(2000);
        this.waitForArticleToDisappearByTitle(articleTitle);
    }

    public int getCountOfSavedArticles() {
        this.waitForElementPresent(
                SAVED_ARTICLE_ELEMENT,
                "Cannot find by the request",
                20
        );
        return this.getAmountOfElements(SAVED_ARTICLE_ELEMENT);
    }

}