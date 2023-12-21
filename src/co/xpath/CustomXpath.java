package co.xpath;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CustomXpath {

    public static void main(String[] args) {

        // Set ChromeDriver options (optional)
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // Maximize the browser window

        // Set the path to the ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dev\\Downloads\\chromedriver.exe");

        // Initialize the WebDriver with ChromeDriver and options
        WebDriver driver = new ChromeDriver(options);
        
        driver.manage().deleteAllCookies();
        
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        // Navigate to eBay
        driver.get("http://ebay.com");

        // Find the search input box
        WebElement searchInput = driver.findElement(By.xpath("//input[@id='gh-ac']"));
        searchInput.sendKeys("Guitar");
        System.out.println("Step 1: Search input box found and 'Guitar' entered.");

        // Wait until the search button is visible and clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='gh-btn']")));

        // Check if the search button is visible and then click
        if (searchIcon.isDisplayed()) {
            searchIcon.click();
            System.out.println("Step 2: Search button clicked.");
        } else {
            System.out.println("Error: Search button not visible.");
        }

        // Additional step: Wait for search results to load and verify
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("srp-river-results"))); // Assuming 'srp-river-results' is the ID for search results container
        System.out.println("Step 3: Search results are visible.");

        // Close the browser
        driver.quit();
        System.out.println("Step 4: Browser closed.");
    }
}

