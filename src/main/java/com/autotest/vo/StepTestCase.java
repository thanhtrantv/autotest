package com.autotest.vo;

import java.io.Serializable;

public class StepTestCase implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String action;
	private String elementType;
	private String elementDefine;
	private String valueEnter;
	private String combineMultiAction;
	
	public String getCombineMultiAction() {
		return combineMultiAction;
	}
	public void setCombineMultiAction(String combineMultiAction) {
		this.combineMultiAction = combineMultiAction;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getElementType() {
		return elementType;
	}
	public void setElementType(String elementType) {
		this.elementType = elementType;
	}
	public String getElementDefine() {
		return elementDefine;
	}
	public void setElementDefine(String elementDefine) {
		this.elementDefine = elementDefine;
	}
	public String getValueEnter() {
		return valueEnter;
	}
	public void setValueEnter(String valueEnter) {
		this.valueEnter = valueEnter;
	}
	
}
