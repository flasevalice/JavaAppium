package ui.factories;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;
import ui.NavigationUI;
import ui.android.AndroidNavigationUI;
import ui.mobile_web.MWNavigationUI;

public class NavigationUIFactory {
    public static NavigationUI get(RemoteWebDriver driver) {
        if (Platform.getInstance().isAndroid())
        return new AndroidNavigationUI(driver);
        else {
            return new MWNavigationUI(driver);
        }
    }
}
