package lib;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileOutputStream;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class CoreTestCase  {
    protected RemoteWebDriver driver;
    //private static final String PLATFORM_IOS = "ios";
    //private static final String PLATFORM_ANDROID = "android";
    //private static String appiumURL = "http://127.0.0.1:4723/wd/hub";

    @Before
    @Step("Run driver and session")
    public void setUp() throws Exception {
        driver = Platform.getInstance().getDriver();
        this.createAllurePropertyFile();
        this.rotatePortrait();
        this.openWikiWebPageForMobileWeb();
    }

    @After
    @Step("Remove driver and session")
    public void tearDown() {
        driver.quit();
    }

    @Step("Rotate screen to Portrait")
    protected void rotatePortrait() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Method rotatePortrait() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Rotate screen to Landscape")
    protected void rotateLandscape() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else {
            System.out.println("Method rotateLandscape() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Transition of the application to the background")
    protected void backgroundApp(int sec) {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(Duration.ofSeconds(sec));
        } else {
            System.out.println("Method backgroundApp() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Method openWikiWebPageForMobileWeb(this method does nothing for Android and iOS)")
    protected void openWikiWebPageForMobileWeb() {
        if (Platform.getInstance().isMw()) {
            driver.get("https://en.m.wikipedia.org");
        } else {
            System.out.println("Method openWikiWebPageForMobileWeb() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    private void createAllurePropertyFile() {
        String path = System.getProperty("allure.results.directory");
        try {
            Properties props = new Properties();
            FileOutputStream fos = new FileOutputStream(path + "/environment.properties");
            props.setProperty("Environment", Platform.getInstance().getPlatformVar());
            props.store(fos,"See https://docs.qameta.io/allure/#_environment");
            fos.close();
        } catch (Exception e) {
            System.out.println("IO problem when writing allure properties file");
            e.printStackTrace();
        }
    }
}