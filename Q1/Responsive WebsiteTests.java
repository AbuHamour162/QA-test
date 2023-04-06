import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.*;

public class ResponsiveWebsiteTests {

    @Test
    public void testWebsiteResponsiveness() {
        // Launch the web driver and navigate to the website
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("https://virtual-landscape.dcbra.in/");

        // Check if the website is responsive for different viewport sizes
        int[] viewportSizes = {320, 480, 768, 1024, 1280};
        for (int viewportSize : viewportSizes) {
            driver.manage().window().setSize(new Dimension(viewportSize, 600));
            assertTrue(isHeaderVisible(driver));
            assertTrue(isFooterVisible(driver));
            assertTrue(isMenuVisible(driver));
            assertTrue(isContentVisible(driver));
        }

        // Quit the web driver
        driver.quit();
    }

    private boolean isHeaderVisible(WebDriver driver) {
        // Check if the website header is visible
        WebElement headerElement = driver.findElement(By.tagName("header"));
        return headerElement.isDisplayed();
    }

    private boolean isFooterVisible(WebDriver driver) {
        // Check if the website footer is visible
        WebElement footerElement = driver.findElement(By.tagName("footer"));
        return footerElement.isDisplayed();
    }

    private boolean isMenuVisible(WebDriver driver) {
        // Check if the website menu is visible
        WebElement menuElement = driver.findElement(By.id("menu"));
        return menuElement.isDisplayed();
    }

    private boolean isContentVisible(WebDriver driver) {
        // Check if the website content is visible
        WebElement contentElement = driver.findElement(By.tagName("main"));
        return contentElement.isDisplayed();
    }
}
