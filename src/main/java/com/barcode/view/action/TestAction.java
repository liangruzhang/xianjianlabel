package com.barcode.view.action;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(TestAction.class);
	public String add(){
		logger.info("add mothed");
		return SUCCESS;
	}
}
