package com.autotest.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.autotest.service.AutoTestService;
import com.autotest.utils.Helper;
import com.autotest.vo.StepTestCase;
import com.autotest.vo.TestCaseVO;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class HomeController {
	@Autowired
	private AutoTestService autoTestService;

	@Autowired
	ApplicationContext context;

	@RequestMapping(value = "/")
	public String index() throws MalformedURLException {

		return "index";

	}
	@ResponseBody
	@RequestMapping(value = "saveTestcase", method = RequestMethod.GET)
	public String saveTestcase(@RequestParam("statusTC") String statusTC,@RequestParam("lstStep") String stepStr, @RequestParam("url") String url,
			@RequestParam("ip") String ip, @RequestParam("browser") String browser, @RequestParam("name") String name,@RequestParam("desc") String desc,@RequestParam("id") String id) throws MalformedURLException {
		System.out.println("saving testcase!");
		TestCaseVO testcase=new TestCaseVO();
		testcase.setLstStep(stepStr);
		testcase.setTestcaseUrl(url);
		testcase.setTestcaseIp(ip);
		testcase.setTestcaseBrowser(browser);
		testcase.setTestcaseDesc(desc);
		testcase.setTestcaseName(name);
		testcase.setTestcaseId(id);
		testcase.setTestcaseStatus(statusTC);
		autoTestService.saveTestCase(testcase);
		return "ok";

	}
	
	private void waitForPageToBeReady(WebDriver driver) 
	{
	    JavascriptExecutor js = (JavascriptExecutor)driver;
	    //This loop will rotate for 100 times to check If page Is ready after every 1 second.
	    //You can replace your if you wants to Increase or decrease wait time.
	    for (int i=0; i<400; i++)
	    { 
	        try 
	        {
	            Thread.sleep(500);
	        }catch (InterruptedException e) {} 
	        //To check page ready state.
	        if (js.executeScript("return document.readyState").toString().equals("complete"))
	        { 
	            break; 
	        }   
	      }
	 }
	private WebDriver generateWebdriver(String ipAddress, String browser) {
		DesiredCapabilities capability = null;
		WebDriver driver = null;
		try {
			if (browser.equals("firefox")) {
				capability = DesiredCapabilities.firefox();
				capability.setCapability("marionette", false);
				capability.setBrowserName("firefox");
				capability.setPlatform(Platform.VISTA);
				capability.setVersion("3.6");
				driver = new RemoteWebDriver(new URL("http://" + ipAddress + ":4444/wd/hub"), capability);
				driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			} else if (browser.equals("chrome")) { /* Chrome */
				capability = DesiredCapabilities.chrome();
				driver = new RemoteWebDriver(new URL("http://" + ipAddress + ":4444/wd/hub"), capability);
				driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			} else if (browser.equals("IE")) {
				capability = DesiredCapabilities.internetExplorer();
				System.setProperty("webdriver.ie.driver",
						System.getProperty("webdriver.ie.driver", "D:/java/autotest/IEDriverServer.exe"));
				capability.setCapability("initialBrowserUrl", "https://localhost:9443");
				capability.setCapability("marionette", false);
				driver = new RemoteWebDriver(new URL("http://" + ipAddress + ":4444/wd/hub"), capability);
				driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			} else {
				System.out.println("Not able to set Driver object");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.waitForPageToBeReady(driver);
		return driver;
	}
	@ResponseBody
	@RequestMapping(value = "loadTestcase", method = RequestMethod.GET)
	public String loadTestcase()throws JsonParseException, JsonMappingException, IOException {
		List<TestCaseVO> lstTC=this.autoTestService.loadTestCase();
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(lstTC);
	}
	@ResponseBody
	@RequestMapping(value = "loadTestcaseDetail", method = RequestMethod.GET)
	public String loadTestcaseDetail(@RequestParam("id") String id)throws JsonParseException, JsonMappingException, IOException {
		int idcheck=-1;
		if(Helper.isInteger(id)){
			idcheck=Helper.parseStringToInt(id);
		} 
		System.out.println(idcheck);
		TestCaseVO tc=this.autoTestService.loadTestCaseDetail(idcheck);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(tc);
	}
	@ResponseBody
	@RequestMapping(value = "runTestcase", method = RequestMethod.GET)
	public String runTestcase(@RequestParam("lstStep") String stepStr, @RequestParam("url") String url,
			@RequestParam("ip") String ip, @RequestParam("browser") String browser)
			throws JsonParseException, JsonMappingException, IOException {
		String rs="fail";
		try {
			System.out.println("IP:"+ip + "    data:" + stepStr);
			ObjectMapper mapper = new ObjectMapper();
			List<StepTestCase> lstStep = mapper.readValue(stepStr, new TypeReference<List<StepTestCase>>() {
			});
			WebDriver driver = this.generateWebdriver(ip, browser);
			driver.get(url);
			//wating page ready 
			this.waitForPageToBeReady(driver);
			if (lstStep == null)
				return "err";
			Actions builderMulti = new Actions(driver);
			boolean multi = false;
			for (StepTestCase step : lstStep) {
				this.waitForPageToBeReady(driver);
				System.out.println(step.getAction());
				/// find element
				WebElement element = null;
				By byElement = null;
				if (!Helper.isEmpty(step.getElementType()) && !Helper.isEmpty(step.getElementDefine())) {
					if (Helper.trim(step.getElementType()).equals("CSS")) {
						byElement = By.cssSelector(Helper.trim(step.getElementDefine()));
					} else if (Helper.trim(step.getElementType()).equals("ID")) {
						byElement = By.id(Helper.trim(step.getElementDefine()));
					}
				}
				if (byElement != null) {
					element = driver.findElement(byElement);
				}
				Actions builder = new Actions(driver);
				if (Helper.trim(step.getCombineMultiAction()).equals("Y")) {
					System.out.println("multi aciton");
					if (Helper.trim(step.getAction()).equals("ClickAction")) {
						builderMulti.click(element);
					} else if (Helper.trim(step.getAction()).equals("SendKeysAction")) {
						builderMulti.sendKeys(element, step.getValueEnter());
					} else if (Helper.trim(step.getAction()).equals("MoveMouseAction")) {
						builderMulti.moveToElement(element);
						// builderMulti.build().perform();
						Thread.sleep(1000);
						builderMulti.release(element);
					} else if (Helper.trim(step.getAction()).equals("ClickAndHoldAction")) {
						builderMulti.clickAndHold(element).pause(500);
					} else if (Helper.trim(step.getAction()).equals("ContextClickAction")) {
						builderMulti.contextClick(element);
					} else if (Helper.trim(step.getAction()).equals("KeyUpAction")) {
						builderMulti.keyUp(element, Keys.valueOf(step.getValueEnter()));
					} else if (Helper.trim(step.getAction()).equals("KeyDownAction")) {
						builderMulti.keyDown(element, Keys.valueOf(step.getValueEnter()));
					} else if (Helper.trim(step.getAction()).equals("DoubleClickAction")) {
						builderMulti.doubleClick(element);
					}

					multi = true;
				} else {
					if (multi) {
						System.out.println("run multi action!");
						Action selectMultiple = builderMulti.build();
						Thread.sleep(500);
						selectMultiple.perform();
						this.waitForPageToBeReady(driver);
						multi = false;
					}

					System.out.println("simple action!");
					// action
					if (Helper.trim(step.getAction()).equals("ClickAction")) {
						builder.click(element);
					} else if (Helper.trim(step.getAction()).equals("SendKeysAction")) {
						builder.sendKeys(element, step.getValueEnter()).perform();
					} else if (Helper.trim(step.getAction()).equals("MoveMouseAction")) {
						builder.moveToElement(element);
						builder.release(element);
					} else if (Helper.trim(step.getAction()).equals("ClickAndHoldAction")) {
						builder.clickAndHold(element);
					} else if (Helper.trim(step.getAction()).equals("ContextClickAction")) {
						builder.contextClick(element);
					} else if (Helper.trim(step.getAction()).equals("KeyUpAction")) {
						builder.keyUp(element, Keys.valueOf(step.getValueEnter()));
					} else if (Helper.trim(step.getAction()).equals("KeyDownAction")) {
						builder.keyDown(element, Keys.valueOf(step.getValueEnter()));
					} else if (Helper.trim(step.getAction()).equals("DoubleClickAction")) {
						builder.doubleClick(element);
					}
					Action selectMultiple = builder.build();
					selectMultiple.perform();
					this.waitForPageToBeReady(driver);
				}
			}
			rs="pass";
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Done!!!");
		return rs;
	}
}
