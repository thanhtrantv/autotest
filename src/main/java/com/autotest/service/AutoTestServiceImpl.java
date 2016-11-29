package com.autotest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autotest.dao.AutoTestDAO;
import com.autotest.vo.TestCaseVO;

@Service
public class AutoTestServiceImpl implements AutoTestService {
	@Autowired
	private AutoTestDAO autoTestDAO;

	@Override
	public void createTestCase(TestCaseVO testcase) {
		this.autoTestDAO.createTestCase(testcase);
	}

}
