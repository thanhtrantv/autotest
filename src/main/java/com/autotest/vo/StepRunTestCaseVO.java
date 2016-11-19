package com.autotest.vo;

import java.io.Serializable;
import java.util.List;

public class StepRunTestCaseVO implements Serializable {
	private List<StepTestCase> lstStep;

	public List<StepTestCase> getLstStep() {
		return lstStep;
	}

	public void setLstStep(List<StepTestCase> lstStep) {
		this.lstStep = lstStep;
	}
	
}
