package com.autotest.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
		// CheckPageReadyThread user1 = new CheckPageReadyThread("vanthanh.11tlt@gmail.com", "vanthanh@123", "chrome");
		//CheckPageReadyThread user2 = new CheckPageReadyThread("thanhtran@magrabbit.com", "vanthanh@123", "firefox");
		CheckPageReadyThread user3 = new CheckPageReadyThread("caimegithe@zoho.com", "vanthanh@123", "ie");
		//CheckPageReadyThread user4 =new CheckPageReadyThread("caimegithe2@prohistory.tk","vanthanh@123");
        //CheckPageReadyThread user5 =new CheckPageReadyThread("lancute847@gmail.com","vanthanh@123");
		//(new Thread(user1)).start();

		// Thread.sleep(50000);
		//(new Thread(user2)).start();
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
			System.setProperty("webdriver.gecko.driver", "D:\\autotest\\autotest\\autotest/geckodriver.exe");
			//  DesiredCapabilities capability = DesiredCapabilities.firefox();
			//capability.setCapability("marionette", false);
			// capability.setBrowserName("firefox");
			// capability.setPlatform(Platform.VISTA);
			// capability.setVersion("5.3");
			driver = new FirefoxDriver();
		}else if (browser.equals("ie")) {
            DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
            System.setProperty("webdriver.ie.driver",
					System.getProperty("webdriver.ie.driver", "D:\\autotest\\autotest\\autotest/IEDriverServer.exe"));
			capability.setCapability("initialBrowserUrl", "https://localhost:9443");
            capability.setCapability("marionette", false);
            driver = new InternetExplorerDriver(capability);
        }else if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "D:\\autotest\\autotest\\autotest/chromedriver.exe");
			DesiredCapabilities capability = DesiredCapabilities.chrome();
            driver = new ChromeDriver(capability);
        }
        driver.get("https://freebitco.in");
        WebElement element = driver.findElement(By.cssSelector(".login_menu_button"));
        element.click();
		this.sleepThread(2000);
		WebElement elementlogin = driver.findElement(By.cssSelector("#login_form_btc_address"));
        elementlogin.sendKeys(user);
        WebElement elementpass = driver.findElement(By.cssSelector("#login_form_password	"));
        elementpass.sendKeys(pass);
        WebElement elementbuttonsubmit = driver.findElement(By.cssSelector("#login_button"));
        elementbuttonsubmit.click();

        loopToWaitingCountDown(driver);
    }

	public void sleepThread(int count) {
		try {
			Thread.sleep(count);
		} catch (Exception e2) {
			System.out.print("error1::" + e2.getMessage());
			countDown("Will recheck on.....", 10);
		}
	}

	public void loopToWaitingCountDown(WebDriver driver) {
    	System.out.println("looping to check.............");
        try {

			if (checkElementExist(driver, By.cssSelector("#time_remaining"))&& driver.findElement(By.cssSelector("#time_remaining")).isDisplayed()) {
				System.out.print("case1");
				WebElement time_remaining = driver.findElement(By.cssSelector("#time_remaining"));
				while (hasClass(time_remaining, "hasCountdown")) {
					System.out.println("count down looping.....");
					if (checkElementExist(driver, By.cssSelector("#free_play_captchas_recaptcha_v2")) && driver.findElement(By.cssSelector("#free_play_captchas_recaptcha_v2")).isDisplayed()) {
						countDown("Please enter captcha.....", 60);
						//loopToWaitingCountDown(driver);
					} else {
						WebDriverWait wait = new WebDriverWait(driver, 60);// 1 minute
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("free_play_form_button")));
						WebElement free_play_form_button = driver.findElement(By.id("free_play_form_button"));
						free_play_form_button.click();
						System.out.println("rolling ok.....");
						this.sleepThread(5000);
						//loopToWaitingCountDown(driver);
					}
				}
				this.sleepThread(2000);
			//	loopToWaitingCountDown(driver);
			} else {
				System.out.print("case2");
				if (checkElementExist(driver, By.cssSelector("#free_play_captchas_recaptcha_v2")) && driver.findElement(By.cssSelector("#free_play_captchas_recaptcha_v2")).isDisplayed()) {

					//alert captcha
					System.out.println("Please enter captcha.....");
					countDown("Please enter captcha.....", 60);
				} else {
					WebElement free_play_form_button = driver.findElement(By.id("free_play_form_button"));
					free_play_form_button.click();
					closePopup(driver);
					System.out.println("get point ok.....");
				}
				//loopToWaitingCountDown(driver);
			}

        } catch (Exception e) {
			System.out.println("error2:::" + e.getMessage());
			countDown("Will recheck on.....", 10);

        }
		loopToWaitingCountDown(driver);
	}
	public void closePopup(WebDriver driver){
		countDown("Waiting to close popup.....", 10);
		if (checkElementExist(driver, By.cssSelector("#myModal22")) && driver.findElement(By.cssSelector("#myModal22")).isDisplayed()) {
			driver.findElement(By.cssSelector("#myModal22 .close-reveal-modal")).click();
		}
	}
	public boolean checkElementExist(WebDriver driver, By by) {
		boolean rs = false;
		try {
			rs = driver.findElements(by).size() > 0;
		} catch (Exception e) {
			System.out.println("error3:::" + e.getMessage());
			countDown("Will recheck on.....", 10);
			//loopToWaitingCountDown(driver);
		}
		return rs;
	}

	public boolean hasClass(WebElement element, String theClassYouAreSearching) throws Exception {
        String classes = element.getAttribute("class");
        for (String c : classes.split(" ")) {
            if (c.equals(theClassYouAreSearching)) {
                return true;
            }
        }
		//this.sleepThread(50000);
		return false;
    }

	public void countDown(String mess, int secon) {
		for (int i = 0; i < secon; i++) {
			System.out.println(mess + (secon - i));
			this.sleepThread(1000);

		}
	}
}
