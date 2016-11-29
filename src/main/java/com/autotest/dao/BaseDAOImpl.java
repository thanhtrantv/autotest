/*
 * $Id: BaseDAOImpl.java,v 1.38.6.2 2016/07/12 04:58:06 vn55228 Exp $
 *
 * Copyright (c) 2014 HEB
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of HEB.
 */
package com.autotest.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * BaseDAOImpl class.
 * @author thanhtran
 */
public class BaseDAOImpl {
	/**
	 * jdbcTemplate.
	 */
	protected JdbcTemplate jdbcTemplate;
	protected NamedParameterJdbcTemplate npJdbcTemplate;
	protected DataSourceTransactionManager transactionManager;
	protected DataSource dataSource;

	/**
	 * @return the jdbcTemplate
	 */
	public JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}

	/**
	 * @param jdbcTemplate
	 */
	@Autowired
	public void setJdbcTemplate(@Qualifier("jdbcTemplate") JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.npJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
	}

	/**
	 * @param ds
	 */
	@Autowired
	public void setDataSource(@Qualifier("dataSource") DataSource ds) {
		this.dataSource = ds;
	}

	/**
	 * @return the transactionManager
	 */
	public DataSourceTransactionManager getTransactionManager() {
		return this.transactionManager;
	}

	/**
	 * @param transactionManager
	 */
	@Autowired
	public void setTransactionManager(@Qualifier("transactionManager") DataSourceTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	/**
	 * @return the npJdbcTemplate
	 */
	public NamedParameterJdbcTemplate getNpJdbcTemplate() {
		return this.npJdbcTemplate;
	}

}
