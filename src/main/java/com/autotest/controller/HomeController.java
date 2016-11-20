package com.autotest.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
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
import com.autotest.vo.StepRunTestCaseVO;
import com.autotest.vo.StepTestCase;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class HomeController {
	@Autowired
	private AutoTestService autoTestService;

	@ResponseBody
	@RequestMapping(value = "/getStudent")
	public String getStudent() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, List<String>> maps = new HashMap<String, List<String>>();
		return mapper.writeValueAsString(maps);
	}

	@Autowired
	ApplicationContext context;

	@RequestMapping(value = "/")
	public String index() throws MalformedURLException {

		return "index";

	}

	@ResponseBody
	@RequestMapping(value = "runTestcase", method = RequestMethod.GET)
	public String runTestcase(@RequestParam("lstStep") String stepStr,@RequestParam("url") String url)
			throws JsonParseException, JsonMappingException, IOException {
		try {
			System.out.println("runtestcase!!!!!!!!!!!!!" + stepStr);
			// List<StepTestCase> lstStep=new ArrayList<StepTestCase>();
			ObjectMapper mapper = new ObjectMapper();
			List<StepTestCase> lstStep = mapper.readValue(stepStr, new TypeReference<List<StepTestCase>>() {
			});
			DesiredCapabilities capability = DesiredCapabilities.firefox();

			FirefoxProfile profile = new FirefoxProfile();
//			profile.set
			profile.setEnableNativeEvents(false);
//			profile.setPreference("xpinstall.signatures.required", false);
//			capability.setCapability("marionette", false);
//			capability.setCapability(FirefoxDriver.PROFILE, profile);
			capability.setBrowserName("firefox");
			capability.setPlatform(Platform.WINDOWS);
			capability.setVersion("3.6");
			WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
			// driver.get("https://jqueryui.com/draggable/");
			// driver.get("https://www.google.com.vn/?gfe_rd=cr&ei=HqGsV-_KCO_C8AeMnoxQ&gws_rd=ssl");
			// driver.get("http://www.w3schools.com/html/html5_draganddrop.asp");
			 driver.get(url);
//			driver.get("http://jqueryui.com/resources/demos/droppable/default.html");

//			 Actions builder1 = new Actions(driver);
//			 builder1.dragAndDrop(driver.findElement(By.cssSelector("#draggable")),
//			 driver.findElement(By.cssSelector("#droppable")));
//			 Thread.sleep(1000);
//			 Action selectMultiple1 = builder1.build();
//			 Thread.sleep(1000);
//			 selectMultiple1.perform();
//			 Thread.sleep(1000);

			// driver.findElement(By.id("lst-ib")).sendKeys("test automation");
			// driver.findElement(By.cssSelector("#sblsbb button")).click();
			if (lstStep == null)
				return "err";
			Actions builderMulti = new Actions(driver);
			boolean multi = false;
			WebElement actionPrev = null;
			for (StepTestCase step : lstStep) {
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
						actionPrev = element;
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
						Thread.sleep(1000);
						Action selectMultiple = builderMulti.build();
						Thread.sleep(1000);
						selectMultiple.perform();
						Thread.sleep(1000);
						multi = false;
					}

					System.out.println("simple action!");
					// action
					if (Helper.trim(step.getAction()).equals("ClickAction")) {
						builder.click(element);
					} else if (Helper.trim(step.getAction()).equals("SendKeysAction")) {
						builder.sendKeys(element, step.getValueEnter());
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
					// builder.dragAndDrop(driver.findElement(By.id("div1")),
					// driver.findElement(By.id("div2")));
					Thread.sleep(1000);
					Action selectMultiple = builder.build();
					Thread.sleep(1000);
					selectMultiple.perform();
					Thread.sleep(1000);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Done!!!");
		return "ok";
	}
}
