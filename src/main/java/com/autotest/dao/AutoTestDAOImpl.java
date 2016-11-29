package com.autotest.dao;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.autotest.vo.TestCaseVO;

@Repository
public class AutoTestDAOImpl extends BaseDAOImpl implements AutoTestDAO{
	@Override
	public void createTestCase(TestCaseVO testcase){
		String sql="insert into testcase(testcase_name,testcase_desc,testcase_url,testcase_ip,testcase_browser,testcase_step) value(?,?,?,?,?,?)";
		this.getJdbcTemplate().update(sql,new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement arg0) throws SQLException {
				arg0.setString(1, testcase.getTestcaseName());
				arg0.setString(2, testcase.getTestcaseDesc());
				arg0.setString(3, testcase.getTestcaseUrl());
				arg0.setString(4, testcase.getTestcaseIp());
				arg0.setString(5, testcase.getTestcaseBrowser());
				
				Blob blob = arg0.getConnection().createBlob();
				blob.setBytes(1, testcase.getTestcaseStep().getBytes());
				arg0.setBlob(6, blob);
			}
		});
	}
}
