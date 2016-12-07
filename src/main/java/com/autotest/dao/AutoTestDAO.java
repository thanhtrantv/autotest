package com.autotest.dao;

import java.util.List;

import com.autotest.vo.TestCaseVO;

public interface AutoTestDAO {
	public void createTestCase(TestCaseVO testcase);
	public void saveTestCase(TestCaseVO testcase);
	public List<TestCaseVO> loadTestCase();
	public TestCaseVO loadTestCaseDetail(int id);
}
