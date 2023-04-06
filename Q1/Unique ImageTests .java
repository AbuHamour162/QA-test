import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.*;

public class UniqueImageTests {

    @Test
    public void testImageUniqueness() throws IOException {
        // Launch the web driver and navigate to the website
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("https://virtual-landscape.dcbra.in/");

        // Find the image element and get its source URL
        WebElement imageElement = driver.findElement(By.tagName("img"));
        String imageUrl = imageElement.getAttribute("src");

        // Download the image and check if it's unique
        boolean isUnique = isImageUnique(imageUrl);
        assertTrue(isUnique);

        // Quit the web driver
        driver.quit();
    }

    private boolean isImageUnique(String imageUrl) throws IOException {
        // Download the image to a temporary file
        URL url = new URL(imageUrl);
        BufferedImage image = ImageIO.read(url);
        File tempFile = File.createTempFile("temp_image", ".jpg");
        ImageIO.write(image, "jpg", tempFile);

        // Compare the image to previously downloaded images
        // (Assuming that the previous images are stored in a directory called "images")
        File[] previousFiles = new File("images").listFiles();
        for (File previousFile : previousFiles) {
            BufferedImage previousImage = ImageIO.read(previousFile);
            if (compareImages(image, previousImage)) {
                // The image is not unique, delete the temporary file and return false
                tempFile.delete();
                return false;
            }
        }

        // The image is unique, move the temporary file to the "images" directory and return true
        tempFile.renameTo(new File("images/" + tempFile.getName()));
        return true;
    }

    private boolean compareImages(BufferedImage image1, BufferedImage image2) {
        // Compare the two images pixel by pixel
        int width = image1.getWidth();
        int height = image1.getHeight();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel1 = image1.getRGB(x, y);
                int pixel2 = image2.getRGB(x, y);
                if (pixel1 != pixel2) {
                    return false;
                }
            }
        }
        return true;
    }
}
