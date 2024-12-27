package ui.android;

import org.openqa.selenium.remote.RemoteWebDriver;
import ui.NavigationUI;

public class AndroidNavigationUI extends NavigationUI {
    static {
        MY_LISTS_LINK = "xpath://*[@resource-id='org.wikipedia:id/snackbar_action']" + "[@text='View list']";
    }
    public AndroidNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }
}
