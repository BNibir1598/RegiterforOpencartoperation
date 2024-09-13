package com.java17_Selenium.RegiterforOpencartoperation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OpenCartRegisterTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        // Set up the ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "C:\\\\Selenium web Driver\\\\Chrome Driver\\\\chromedriver-win64\\\\chromedriver.exe");

        // Initialize the ChromeDriver
        driver = new ChromeDriver();

        // Navigate to the OpenCart registration page
        driver.get("https://demo.opencart.com/index.php?route=account/register");

        // Maximize the browser window
        driver.manage().window().maximize();
    }

    @Test
    public void registerAccountTest() {
        // Fill in the personal details
        driver.findElement(By.id("input-firstname")).sendKeys("John");
        driver.findElement(By.id("input-lastname")).sendKeys("Doe");
        driver.findElement(By.id("input-email")).sendKeys("johndoe@example.com");

        // Fill in the password
        driver.findElement(By.id("input-password")).sendKeys("Password123");
        driver.findElement(By.id("input-confirm")).sendKeys("Password123");

        // Subscribe to the newsletter
        WebElement newsletterYes = driver.findElement(By.xpath("//input[@name='newsletter' and @value='1']"));
        if (!newsletterYes.isSelected()) {
            newsletterYes.click();
        }

        // Agree to the privacy policy
        driver.findElement(By.name("agree")).click();

        // Submit the registration form
        driver.findElement(By.xpath("//input[@type='submit' and @value='Continue']")).click();

        // Verify registration success
        String expectedMessage = "Your Account Has Been Created!";
        WebElement successMessage = driver.findElement(By.xpath("//h1[contains(text(), 'Your Account Has Been Created!')]"));
        Assert.assertEquals(successMessage.getText(), expectedMessage);
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}



