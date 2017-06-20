package com.autotest.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by thanhtran on 6/20/2017.
 */
public class CheckPageReadyThread implements Runnable {
    private String user;
    private String pass;
    private String browser;

    public CheckPageReadyThread(String user, String pass, String browser) {
        this.user = user;
        this.pass = pass;
        this.browser = browser;
    }

    public static void main(String[] arg) throws Exception {
        CheckPageReadyThread user1 = new CheckPageReadyThread("vanthanh.11tlt@gmail.com", "vanthanh@123", "chrome");
        CheckPageReadyThread user2 = new CheckPageReadyThread("thanhtran@magrabbit.com", "vanthanh@123", "firefox");
        CheckPageReadyThread user3 = new CheckPageReadyThread("caimegithe@zoho.com", "vanthanh@123", "ie");
        //CheckPageReadyThread user4 =new CheckPageReadyThread("caimegithe2@prohistory.tk","vanthanh@123");
        //CheckPageReadyThread user5 =new CheckPageReadyThread("lancute847@gmail.com","vanthanh@123");
        (new Thread(user1)).start();

        Thread.sleep(50000);
        (new Thread(user2)).start();
        Thread.sleep(50000);
        (new Thread(user3)).start();
        //(new Thread(user4)).start();
        // (new Thread(user5)).start();
    }

    @Override
    public void run() {
        System.out.print("starting thread...");

        //sDesiredCapabilities capability = DesiredCapabilities.chrome();
        WebDriver driver = null;
        if (browser.equals("firefox")) {
            DesiredCapabilities capability = DesiredCapabilities.firefox();
            capability.setCapability("marionette", false);
            capability.setBrowserName("firefox");
            capability.setPlatform(Platform.VISTA);
            capability.setVersion("3.6");
            driver = new FirefoxDriver(capability);
        }else if (browser.equals("ie")) {
            DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
            System.setProperty("webdriver.ie.driver",
                    System.getProperty("webdriver.ie.driver", "D:/java/autotest/IEDriverServer.exe"));
            capability.setCapability("initialBrowserUrl", "https://localhost:9443");
            capability.setCapability("marionette", false);
            driver = new InternetExplorerDriver(capability);
        }else if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "D:\\java\\workspace\\Autotest\\autotest/chromedriver.exe");
            DesiredCapabilities capability = DesiredCapabilities.chrome();
            driver = new ChromeDriver(capability);
        }
        driver.get("https://freebitco.in");
        WebElement element = driver.findElement(By.cssSelector(".login_menu_button"));
        element.click();
        WebElement elementlogin = driver.findElement(By.cssSelector("#login_form_btc_address"));
        elementlogin.sendKeys(user);
        WebElement elementpass = driver.findElement(By.cssSelector("#login_form_password	"));
        elementpass.sendKeys(pass);
        WebElement elementbuttonsubmit = driver.findElement(By.cssSelector("#login_button"));
        elementbuttonsubmit.click();

        //captcha
        //id:::::::   free_play_captchas_recaptcha_v2

        loopToWaitingCountDown(driver);
    }

    public void loopToWaitingCountDown(WebDriver driver) {
        try {
            WebElement time_remaining = driver.findElement(By.cssSelector("#time_remaining"));
            while (hasClass(time_remaining, "hasCountdown")) {
                System.out.println("count down looping.....");
                WebElement free_play_captchas_recaptcha_v2 = driver.findElement(By.cssSelector("#free_play_captchas_recaptcha_v2"));
                if (free_play_captchas_recaptcha_v2.isDisplayed()) {
                    //alert captcha
                    System.out.println("Please enter captcha.....");
                    Thread.sleep(50000);
                    loopToWaitingCountDown(driver);
                } else {
                    WebElement free_play_form_button = driver.findElement(By.cssSelector("#free_play_form_button"));
                    free_play_form_button.click();
                    System.out.println("get point ok.....");
                    loopToWaitingCountDown(driver);
                }
            }
            WebElement free_play_captchas_recaptcha_v22 = driver.findElement(By.cssSelector("#free_play_captchas_recaptcha_v2"));
            if (free_play_captchas_recaptcha_v22.isDisplayed()) {
                //alert captcha
                System.out.println("Please enter captcha.....");
                Thread.sleep(50000);
            } else {
                WebElement free_play_form_button = driver.findElement(By.cssSelector("#free_play_form_button"));
                free_play_form_button.click();
                System.out.println("get point ok.....");
            }
            loopToWaitingCountDown(driver);
        } catch (Exception e) {
            System.out.print("error");
            System.out.print(e.getMessage());
            try {
                Thread.sleep(50000);
            } catch (Exception e2) {
                System.out.print("error");
                System.out.print(e2.getMessage());
            }
            loopToWaitingCountDown(driver);
        }
    }

    public boolean hasClass(WebElement element, String theClassYouAreSearching) throws Exception {
        String classes = element.getAttribute("class");
        for (String c : classes.split(" ")) {
            if (c.equals(theClassYouAreSearching)) {
                return true;
            }
        }
        Thread.sleep(50000);
        return false;
    }
}
