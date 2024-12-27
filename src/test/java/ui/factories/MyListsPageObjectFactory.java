package ui.factories;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;
import ui.MyListsPageObject;
import ui.android.AndroidMyListsPageObject;
import ui.mobile_web.MWMyListsPageObject;

public class MyListsPageObjectFactory {
    public static MyListsPageObject get(RemoteWebDriver driver) {
        if (Platform.getInstance().isAndroid())
        return new AndroidMyListsPageObject(driver);
        else {
            return new MWMyListsPageObject(driver);
        }
    }
}