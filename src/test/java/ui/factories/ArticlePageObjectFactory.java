package ui.factories;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;
import ui.ArticlePageObject;
import ui.android.AndroidArticlePageObject;;
import ui.mobile_web.MWArticlePageObject;

public class ArticlePageObjectFactory {
    public static ArticlePageObject get(RemoteWebDriver driver) {
        if (Platform.getInstance().isAndroid())
            return new AndroidArticlePageObject(driver);
        else {
            return new MWArticlePageObject(driver);
        }
    }
}