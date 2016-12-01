package com.autotest.service;

import java.util.List;

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

	@Override
	public List<TestCaseVO> loadTestCase() {
		// TODO Auto-generated method stub
		return this.autoTestDAO.loadTestCase();
	}

}
