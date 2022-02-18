package cucumberselenium;

import cucumberselenium.helpers.WebDriverFactory;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.Before;
import io.cucumber.java.After;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class StepDefinitions {
    private WebDriver driver;

    @Before()
    public void initializeDriver() {
        driver = WebDriverFactory.createWebDriver();
        driver.manage().window().maximize();
    }

    @Given("inquisitive.nl is open")
    public void inquisitive_nl_is_open() {
        driver.get("https://www.inquisitive.nl");;
    }
    @When("I click on the start page link")
    public void i_click_on_the_start_page_link() {
        List<WebElement> images = driver.findElements(By.xpath("//img[@alt='InQuisitive' and @class=' preload-me']"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        for (WebElement image : images) {
            if (image.isDisplayed()) {
                executor.executeScript("arguments[0].click();", image);
                break;
            }
        }
    }

    @When("I click on the sandwich menu")
    public void iClickOnTheSandwichMenu() {
        WebElement menu = driver.findElement(By.xpath("//div[@class='menu-toggle']"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", menu);
    }

    @When("I click on blogs")
    public void iClickOnBlogs() {
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Blogs']")));
        driver. findElement(By.xpath("//span[text()='Blogs']")).click();
    }

    @Then("The page header should be {string}")
    public void thePageHeaderShouldBe(String header) {
        assertTrue(driver.findElements(By.xpath("//h2[text()='" + header + "']")).size() != 0);
    }

    @Then("The page title should start with {string}")
    public void the_page_title_should_start_with(String title) {
        assertTrue(driver.getTitle().startsWith(title));
    }

    @After()
    public void closeBrowser(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "name");
        }

        driver.quit();
    }

}

