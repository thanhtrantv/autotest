package com.autotest.service;

import java.util.List;

import com.autotest.vo.TestCaseVO;

public interface AutoTestService {
	public void createTestCase(TestCaseVO testcase);
	public List<TestCaseVO> loadTestCase();
}
