package com.autotest.vo;

import java.util.ArrayList;
import java.util.List;

public class TestCaseVO {
	private String testcaseId;
	private String testcaseDesc;
	private String testcaseName;
	private String testcaseUrl;
	private String testcaseIp;
	private String testcaseBrowser;
	private String testcaseStep;
	private String lstStep;
	
/**
	 * @return the lstStep
	 */
	public String getLstStep() {
		return lstStep;
	}
	/**
	 * @param lstStep the lstStep to set
	 */
	public void setLstStep(String lstStep) {
		this.lstStep = lstStep;
	}
	//	/**
//	 * @return the lstStep
//	 */
//	public List<StepTestCase> getLstStep() {
//		if(lstStep==null){
//			lstStep=new ArrayList<StepTestCase>();
//		}
//		return lstStep;
//	}
//	/**
//	 * @param lstStep the lstStep to set
//	 */
//	public void setLstStep(List<StepTestCase> lstStep) {
//		this.lstStep = lstStep;
//	}
	/**
	 * @return the testcaseId
	 */
	public String getTestcaseId() {
		return testcaseId;
	}
	/**
	 * @param testcaseId the testcaseId to set
	 */
	public void setTestcaseId(String testcaseId) {
		this.testcaseId = testcaseId;
	}
	/**
	 * @return the testcaseDesc
	 */
	public String getTestcaseDesc() {
		return testcaseDesc;
	}
	/**
	 * @param testcaseDesc the testcaseDesc to set
	 */
	public void setTestcaseDesc(String testcaseDesc) {
		this.testcaseDesc = testcaseDesc;
	}
	/**
	 * @return the testcaseName
	 */
	public String getTestcaseName() {
		return testcaseName;
	}
	/**
	 * @param testcaseName the testcaseName to set
	 */
	public void setTestcaseName(String testcaseName) {
		this.testcaseName = testcaseName;
	}
	/**
	 * @return the testcaseUrl
	 */
	public String getTestcaseUrl() {
		return testcaseUrl;
	}
	/**
	 * @param testcaseUrl the testcaseUrl to set
	 */
	public void setTestcaseUrl(String testcaseUrl) {
		this.testcaseUrl = testcaseUrl;
	}
	/**
	 * @return the testcaseIp
	 */
	public String getTestcaseIp() {
		return testcaseIp;
	}
	/**
	 * @param testcaseIp the testcaseIp to set
	 */
	public void setTestcaseIp(String testcaseIp) {
		this.testcaseIp = testcaseIp;
	}
	/**
	 * @return the testcaseBrowser
	 */
	public String getTestcaseBrowser() {
		return testcaseBrowser;
	}
	/**
	 * @param testcaseBrowser the testcaseBrowser to set
	 */
	public void setTestcaseBrowser(String testcaseBrowser) {
		this.testcaseBrowser = testcaseBrowser;
	}
	/**
	 * @return the testcaseStep
	 */
	public String getTestcaseStep() {
		return testcaseStep;
	}
	/**
	 * @param testcaseStep the testcaseStep to set
	 */
	public void setTestcaseStep(String testcaseStep) {
		this.testcaseStep = testcaseStep;
	}
	
}
