import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.*;

public class ImageComparisonTests {

    @Test
    public void testImageDownload() throws IOException {
        // Load the base image
        File baseImageFile = new File("base_image.jpg");
        BufferedImage baseImage = ImageIO.read(baseImageFile);

        // Launch the web driver and navigate to the website
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("https://virtual-landscape.dcbra.in/");

        // Download the new image
        WebElement imageElement = driver.findElement(By.tagName("img"));
        String imageUrl = imageElement.getAttribute("src");
        // Add code here to download the image to a file

        // Load the downloaded image
        File downloadedImageFile = new File("downloaded_image.jpg");
        BufferedImage downloadedImage = ImageIO.read(downloadedImageFile);

        // Compare the two images
        int width = baseImage.getWidth();
        int height = baseImage.getHeight();
        int pixelDiff = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int basePixel = baseImage.getRGB(x, y);
                int downloadedPixel = downloadedImage.getRGB(x, y);
                if (basePixel != downloadedPixel) {
                    pixelDiff++;
                }
            }
        }

        // Verify that there are no differences between the two images
        assertEquals(0, pixelDiff);

        // Quit the web driver
        driver.quit();
    }
}
