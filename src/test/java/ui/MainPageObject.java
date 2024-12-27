package ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPageObject {
    protected RemoteWebDriver driver;

    public MainPageObject(RemoteWebDriver driver) {
        this.driver = driver;
    }

    //не работает из-за версии верстки, поэтому заменено отображением
    public String waitForElementAndGetAttribute(String locator, String attribute, String err_msg, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, err_msg, timeoutInSeconds);
        return element.getAttribute(attribute);
    }


    public void assertElementPresent(String locator, String err_msg) {
        int amountOfTitles = getAmountOfElements(locator);
        if (amountOfTitles < 1) {
            String defaultMessage = "An element '" + locator + "' not present";
            throw new AssertionError(defaultMessage + " " + err_msg);
        }
    }

    public void assertElementNotPresent(String locator, String err_msg) {
        int amountOfElements = getAmountOfElements(locator);
        if (amountOfElements > 0) {
            String defaultMessage = "An element '" + locator + "' supposed to be not present";
            throw new AssertionError(defaultMessage + " " + err_msg);
        }
    }

    public void assertElementNotDisplayed(String locator, String err_msg) {
        By by = this.getLocatorByString(locator);
        if (!driver.findElements(by).get(0).isEnabled()) {
            String defaultMessage = "An element '" + locator + "' supposed to be not present";
            throw new AssertionError(defaultMessage + " " + err_msg);
        }
    }

    public void assertElementIsDisplayed(String locator, String err_msg) {
        By by = this.getLocatorByString(locator);
        if (driver.findElement(by).isEnabled()) {
            String defaultMessage = "An element '" + locator + "' supposed to be not present";
            throw new AssertionError(defaultMessage + " " + err_msg);
        }
    }

    public int getAmountOfElements(String locator) {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void scrollWebPageUp(){
        if(Platform.getInstance().isMw()){
            JavascriptExecutor JSExecutor= (JavascriptExecutor)driver;
            JSExecutor.executeScript("window.scrollBy(0,250)");
        } else {
            System.out.println("Method scrollWebPageUp() do nothing for platform "+ Platform.getInstance().getPlatformVar());
        }
    }

    public void scrollWebPageTillElementNotVisible(String locator, String error_message,int max_swipes){
        int already_swiped=0;
        WebElement element = this.waitForElementPresent(locator, error_message);
        while(!this.isElementLocatedOnScreen(locator)){
            scrollWebPageUp();
            ++already_swiped;
            if(already_swiped> max_swipes){
                Assert.assertTrue(error_message, element.isDisplayed());
            }
        }
    }

    public boolean isElementLocatedOnScreen(String locator) {
        int element_location_by_y = this.waitForElementPresent(locator, "Can not find element by locator " + locator, 5).getLocation().getY();
        if(Platform.getInstance().isMw()) {
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            Object js_result = JSExecutor.executeScript("return window.pageYOffset");
            element_location_by_y -=Integer.parseInt(js_result.toString());
        }
        int screen_size_by_y = driver.manage().window().getSize().getHeight();
        return element_location_by_y < screen_size_by_y;

    }

    public void swipeToTheLeftElement(String locator, String err_msg) {
        if (driver instanceof AppiumDriver) {
            WebElement element = waitForElementPresent(locator, "Невозможно найти элемент до которого свайпать. \n"
                    + err_msg, 5);
            int left_x = element.getLocation().getX();
            int right_x = left_x + element.getSize().getWidth();
            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().getHeight();
            int middle_y = (upper_y + lower_y) / 2;

            TouchAction action = new TouchAction((AppiumDriver) driver);
            action.press(right_x, middle_y)
                    .waitAction(300)
                    .moveTo(left_x, middle_y)
                    .release()
                    .perform();
        } else {
            System.out.println("Method swipeToTheLeftElement() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void swipeUp(int timeOfSwipe) {
        if (driver instanceof AppiumDriver) {
            TouchAction action = new TouchAction((AppiumDriver) driver);

            Dimension size = driver.manage().window().getSize();
            int x = size.width / 2;
            int start_y = (int) (size.height * 0.8);
            int end_y = (int) (size.height * 0.2);
            action.press(x, start_y).waitAction(timeOfSwipe).moveTo(x, end_y).release().perform();
        } else {
            System.out.println("Method swipeUp() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void swipeUpQuick() {
        swipeUp(200);
    }

    public void swipeUpToFindElement(String locator, String err_msg, int maxSwipes) {
        By by = this.getLocatorByString(locator);
        int alreadySwiped = 0;
        while (driver.findElements(by).size() == 0) {
            if (alreadySwiped > maxSwipes) {
                waitForElementPresent(locator, "Невозможно найти элемент \n" + err_msg, 0);
                return;
            }
            swipeUpQuick();
            ++alreadySwiped;
        }
    }

    public WebElement waitForElementPresent(String locator, String err_msg, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(err_msg + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public boolean isElementPresent(String locator) {
        sleep(100);
        return getAmountOfElements(locator) > 0;
    }

    public void tryClickElementWithFewAttempts(String locator, String error_message, int amount_of_attempts) {
        int current_attempts = 0;
        boolean need_more_attempts = true;
        while (need_more_attempts) {
            try {
                this.waitForElementAndClick(locator, error_message, 1);
                need_more_attempts = false;
            } catch (Exception e) {
                if (current_attempts > amount_of_attempts) {
                    this.waitForElementAndClick(locator, error_message, 1);
                }
            }
            ++current_attempts;
        }
    }


    public WebElement waitForElementPresent(String locator, String err_msg) {
        return waitForElementPresent(locator, err_msg, 15);
    }

    public WebElement waitForElementAndClick(String locator, String err_msg, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, err_msg, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String err_msg, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, err_msg, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(String locator, String err_msg, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(err_msg + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(String locator, String err_msg, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, err_msg, timeoutInSeconds);
        element.clear();
        return element;
    }

    public void assertElementHasText(String locator, String text, String err_msg, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, err_msg, timeoutInSeconds);
        String articleTitle = element.getText();
        Assert.assertEquals(err_msg, text, articleTitle);
    }

    public void assertElementHasTextInAttribute(String locator, String text,  String attr, String err_msg, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, err_msg, timeoutInSeconds);
        String articleTitle = element.getAttribute(attr);
        Assert.assertEquals(err_msg, text, articleTitle);
    }

    private By getLocatorByString(String locator_with_type) {
        String[] exploded_locator = locator_with_type.split(":", 2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];
        if (by_type.equals("xpath")) return By.xpath(locator);
        else if (by_type.equals("id")) return By.id(locator);
        else if (by_type.equals("css")) return By.cssSelector(locator);
        else throw new IllegalArgumentException("Cannot get type of locator. Locator: " + locator_with_type);
    }

    public void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}