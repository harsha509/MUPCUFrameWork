package com.qa.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {
    public WebDriver driver;
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    /**
     * This method is to initialize the driver based on given browser
     * @param browser
     * @return thread local driver
     */
    public WebDriver init_driver(String browser){
        if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            tlDriver.set(new ChromeDriver());
        }
        else if(browser.equalsIgnoreCase("safari")){
            tlDriver.set(new SafariDriver());
        }
        else
            System.out.println("Please provide correct driver : " + browser);
        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        return getDriver();
    }

    /**
     * this is used to get the driver with ThreadLocal
     * @return
     */
    public static synchronized WebDriver getDriver(){
        return  tlDriver.get();
    }
}
