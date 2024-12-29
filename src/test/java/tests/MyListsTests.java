package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import org.junit.Assert;
import org.junit.Test;
import ui.*;
import ui.factories.ArticlePageObjectFactory;
import ui.factories.MyListsPageObjectFactory;
import ui.factories.NavigationUIFactory;
import ui.factories.SearchPageObjectFactory;

@Epic("Tests for lists of articles")
public class MyListsTests extends CoreTestCase {
    private static final String login = "alice99999999",
            password = "hf*eV]%Jc5rG4&r";

    @Test
    @Features(value = {@Feature(value = "MyLists")})
    @DisplayName("Save the first article to reading list")
    @Description("The test finds and adds an article to the reading list, then goes to the created list and deletes it")
    @Step("Starting test testSaveFirstArticleToMyList")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSaveFirstArticleToMyList() {
        //mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Skip')]"), "Невозможно найти элемент", 15);
        //mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Невозможно найти элемент", 15);
        //mainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "Java", "Невозможно найти элемент", 15);
        //mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Object-oriented programming language']"), "Невозможно найти элемент", 20);
        //mainPageObject.waitForElementPresent(By.xpath("//*[@resource-id='pcs']//*[@text='Java (programming language)']"), "Элемент по данному локатору не содержит ожидаемый текст", 20);
        //mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_save'][@text='Save']"),"Невозможно найти кнопку Save",5);
        //mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/snackbar_action'][@text='Add to list']"),"Невозможно найти элемент 'Add to list'",5);
        //mainPageObject.waitForElementAndSendKeys(By.xpath("//*[@resource-id='org.wikipedia:id/text_input'][@text='Name of this list']"),"Новый список","Невозможно вести название списка",5);
        //mainPageObject.waitForElementAndSendKeys(By.xpath("//*[@resource-id='org.wikipedia:id/secondary_text_input']" + "[@text='Description (optional)']"),"Описание списка","Невозможно вести описание списка",5);
        //mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='ui.android:id/button1'][@text='OK']"),"Невозможно найти элемент - кнопку OK", 5);

        //mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/snackbar_action']" + "[@text='View list']"),"Невозможно найти элемент - кнопку 'VIEW LIST'",5);
        //mainPageObject.swipeToTheLeftElement(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']" +"[@text='Java (programming language)']"),"Невозможно найти статью 'Java (programming language)' в списке 'Новый список'");
        //mainPageObject.waitForElementNotPresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']" + "[@text='Java (programming language)']"),"Статья осталась в списке", 5);

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        if (!Platform.getInstance().isMw()) {
            searchPageObject.skipMainScreen();
        }
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        String articleTitle = "Java (programming language)";
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.assertElementHasTextByXpath(articleTitle, "Actual text isn't expected");
        } else articlePageObject.getArticleTitle();

        String nameOfFolder = "Learning programming";
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyNewList(nameOfFolder);
        }
        if (Platform.getInstance().isMw()) {
            articlePageObject.addArticlesToMySaved();
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            articlePageObject.waitForTitleElement();
            Assert.assertEquals("We are not on the same page after login",
                    articleTitle,
                    articlePageObject.getArticleTitle());
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(nameOfFolder);
        }

        myListsPageObject.swipeArticleToDelete(articleTitle);
    }

    @Test
    @Features(value = {@Feature(value = "MyLists")})
    @DisplayName("Saving two articles to reading list and delete one of them")
    @Description("The test finds and adds two articles to the reading list, removing one of them from the reading list")
    @Step("Starting test testSaveTwoArticlesAndDeleteOneOfThem")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSaveTwoArticlesAndDeleteOneOfThem() {
        //mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Skip')]"), "Невозможно найти элемент", 15);
        //поиск первой
        //mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Невозможно найти элемент", 15);
        //mainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "Java", "Невозможно найти элемент", 15);
        //mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Object-oriented programming language']"), "Невозможно найти элемент", 20);
        // mainPageObject.waitForElementPresent(By.xpath("//*[@resource-id='pcs']//*[@text='Java (programming language)']"), "Элемент по данному локатору не содержит ожидаемый текст", 20);
        //сохранение первой
        //mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_save'][@text='Save']"),"Невозможно найти кнопку Save",5);
        //mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/snackbar_action'][@text='Add to list']"),"Невозможно найти элемент 'Add to list'",5);
        // в новый список
        //mainPageObject.waitForElementAndSendKeys(By.xpath("//*[@resource-id='org.wikipedia:id/text_input'][@text='Name of this list']"),"Новый список","Невозможно вести название списка",5);
        //mainPageObject.waitForElementAndSendKeys(By.xpath("//*[@resource-id='org.wikipedia:id/secondary_text_input']" + "[@text='Description (optional)']"),"Описание списка","Невозможно вести описание списка",5);
        //mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='ui.android:id/button1'][@text='OK']"),"Невозможно найти элемент - кнопку OK", 5);
        //поиск второй
        //mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/page_toolbar_button_search"), "Невозможно найти элемент", 30);
        //mainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "Appium", "Невозможно найти элемент", 15);
        //mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Automation for Apps']"), "Невозможно найти элемент", 20);
        //mainPageObject.waitForElementPresent(By.xpath("//*[@resource-id='pcs']//*[@text='Appium']"), "Элемент по данному локатору не содержит ожидаемый текст", 20);
        //сохранение второй
        //mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_save'][@text='Save']"),"Невозможно найти кнопку Save",5);
        //mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/snackbar_action'][@text='Add to list']"),"Невозможно найти элемент 'Add to list'",5);
        //в существующий список
        //mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/item_title_container']"), "Невозможно добавление в существующий список", 10);
        //просмотр списка
        //mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/snackbar_action']" + "[@text='View list']"),"Невозможно найти элемент - кнопку 'VIEW LIST'",5);
        //удаление одной из
        //mainPageObject.swipeToTheLeftElement(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']" +"[@text='Java (programming language)']"),"Невозможно найти статью 'Java (programming language)' в списке 'Новый список'");
        //вторая осталась
        //mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']" +"[@text='Appium']"), "Элемент по данному локатору не содержит ожидаемый текст", 20);
        //и заголовок
        //mainPageObject.assertElementHasText(By.xpath("//*[@resource-id='pcs']//*[@text='Appium']"), "Appium", "Элемент по данному локатору не содержит ожидаемый текст", 20);
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        if (!Platform.getInstance().isMw()) {
            searchPageObject.skipMainScreen();
        }
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        String firstArticleTitle = "Java (programming language)";
        String secondArticleTitle = "Automation for Apps";
        String nameOfFolder = "Learning programming";
        int countForSavedArticles = 0;

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.assertElementHasTextByXpath(firstArticleTitle, "Actual text isn't expected");
        } else articlePageObject.getArticleTitle();

        //сохранение 1-й статьи Андроид
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyNewList(nameOfFolder);
        }

        //сохранение 1-й статьи web
        if (Platform.getInstance().isMw()) {
            articlePageObject.addArticlesToMySaved();
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            articlePageObject.waitForTitleElement();
            Assert.assertEquals("We are not on the same page after login",
                    firstArticleTitle,
                    articlePageObject.getArticleTitle());
        }

        //сохранение 2-й статьи Андроид
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyOldList();
        }
        //сохранение 2-й статьи web
        if (Platform.getInstance().isMw()) {
            searchPageObject.initSearchInput();
            searchPageObject.typeSearchLine("Appium");
            searchPageObject.clickByArticleWithSubstring(secondArticleTitle);
            articlePageObject.addArticlesToMySaved();
        }

        //переход в закладки
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(nameOfFolder);
        }

        //удаление
        myListsPageObject.swipeArticleToDelete(firstArticleTitle);

        if (Platform.getInstance().isAndroid()) {
            searchPageObject.clickByArticleWithSubstring(secondArticleTitle);
            articlePageObject.waitForArticleTitleElement("Appium");
        }
        //другой способ проверки удаления статьи
        if (Platform.getInstance().isMw()) {
            countForSavedArticles = myListsPageObject.getCountOfSavedArticles();
            Assert.assertEquals("Saved articles contain more than one result", 1, countForSavedArticles);
            System.out.println("Saved articles found " + countForSavedArticles);
        }
    }
}