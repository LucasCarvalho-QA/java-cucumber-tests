package org.rdlopes.demo.bdd.tooling;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class WebDriverConfigurations {
    public static WebDriver driver;

    public static WebDriver ConfigureChromeWebDriver(){

        //WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("window-size=1920x1080");
        options.addArguments("--headless");


        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return driver;
    }

    public static void TerminateDriver() throws InterruptedException {
        driver.quit();
    }
}
