package com.autotest.dao;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
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
	public List<TestCaseVO> loadTestCase(){
		List<TestCaseVO> lst=this.getJdbcTemplate().query("select * from testcase", new RowMapper<TestCaseVO>(){

			@Override
			public TestCaseVO mapRow(ResultSet rs, int arg1)
					throws SQLException {
				TestCaseVO testcase=new TestCaseVO();
				testcase.setLstStep(new String(rs.getBytes("testcase_step")));
				testcase.setTestcaseBrowser(rs.getString("testcase_browser"));
				testcase.setTestcaseIp(rs.getString("testcase_ip"));
				testcase.setTestcaseUrl(rs.getString("testcase_url"));
				testcase.setTestcaseDesc(rs.getString("testcase_desc"));
				testcase.setTestcaseName(rs.getString("testcase_name"));
				testcase.setTestcaseId(rs.getString("testcase_id"));
				return testcase;
			}
			
		});
		return lst;
	}
}
