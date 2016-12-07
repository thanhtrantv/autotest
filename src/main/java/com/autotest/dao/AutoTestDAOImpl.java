package com.autotest.dao;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.autotest.utils.Helper;
import com.autotest.vo.TestCaseVO;

@Repository
public class AutoTestDAOImpl extends BaseDAOImpl implements AutoTestDAO{
	@Override
	public void createTestCase(TestCaseVO testcase){
		String sql="insert into testcase(testcase_name,testcase_desc,testcase_url,testcase_ip,testcase_browser,testcase_step) values (?,?,?,?,?,?) ";
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
				System.out.println("saving:"+testcase.getTestcaseId());
				
			}
		});
	}
	@Override
	public void saveTestCase(TestCaseVO testcase){
		String sql="update testcase set testcase_name=?,testcase_desc=?,testcase_url=?,testcase_ip=?,testcase_browser=?,testcase_step=?,testcase_status=? where testcase_id=?";
		this.getJdbcTemplate().update(sql,new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement arg0) throws SQLException {
				System.out.println("saving:"+testcase.getTestcaseId());
				arg0.setString(1, testcase.getTestcaseName());
				arg0.setString(2, testcase.getTestcaseDesc());
				arg0.setString(3, testcase.getTestcaseUrl());
				arg0.setString(4, testcase.getTestcaseIp());
				arg0.setString(5, testcase.getTestcaseBrowser());
				
				Blob blob = arg0.getConnection().createBlob();
				blob.setBytes(1, Helper.trim(testcase.getLstStep()).getBytes());
				arg0.setBlob(6, blob);
				arg0.setString(7, testcase.getTestcaseStatus());
				arg0.setString(8, testcase.getTestcaseId());
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
				testcase.setTestcaseStatus(rs.getString("testcase_status"));
				return testcase;
			}
			
		});
		return lst;
	}
	public TestCaseVO loadTestCaseDetail(int id){
		TestCaseVO tc=this.getJdbcTemplate().query("select * from testcase where testcase_id=?",new Object[]{id} ,new ResultSetExtractor<TestCaseVO>(){


			@Override
			public TestCaseVO extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				TestCaseVO testcase=new TestCaseVO();
				while(rs.next()){
				testcase.setLstStep(new String(rs.getBytes("testcase_step")));
				testcase.setTestcaseBrowser(rs.getString("testcase_browser"));
				testcase.setTestcaseIp(rs.getString("testcase_ip"));
				testcase.setTestcaseUrl(rs.getString("testcase_url"));
				testcase.setTestcaseDesc(rs.getString("testcase_desc"));
				testcase.setTestcaseName(rs.getString("testcase_name"));
				testcase.setTestcaseId(rs.getString("testcase_id"));
				testcase.setTestcaseStatus(rs.getString("testcase_status"));
				}
				return testcase;
			}
			
		});
		return tc;
	}
}
