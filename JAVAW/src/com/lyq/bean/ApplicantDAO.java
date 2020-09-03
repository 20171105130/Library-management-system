package com.lyq.bean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import com.lyq.bean.DBUtil;
import com.mysql.jdbc.Connection;


public class ApplicantDAO {
	public boolean isExistEmail(String email) {
		// TODO Auto-generated method stub
		String sql = "select * from tb_applicant where applicant_email = ?";
		Connection conn = (Connection) DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet  result = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			result = pstmt.executeQuery();
			
			if(result.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	public void save(String email, String password) {
		// TODO Auto-generated method stub
		String sql = "insert into tb_applicant(applicant_email,applicant_pwd,applicant_registdate) values(?,?,?)";
		Connection conn = (Connection) DBUtil.getConnection();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			pstmt.setTimestamp(3, new Timestamp(new Date().getTime()));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(null, pstmt, conn);
		}
		
	}

	public int login(String email, String password) {
		// TODO Auto-generated method stub0
		int applicantID =0;
		 Connection conn = (Connection) DBUtil.getConnection();
		 PreparedStatement pstmt =null;
		 ResultSet rs = null;
		 String sql = "SELECT applicant_id FROM tb_applicant WHERE applicant_email =? and applicant_pwd=?";
		 try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2,password);
			rs = pstmt.executeQuery();
			if(rs.next())
				applicantID = rs.getInt("applicant_id");
		 }catch (SQLException e) {
			 e.printStackTrace();
			} finally {
				DBUtil.closeJDBC(null, pstmt, conn);
			}
		 return applicantID;

	}
}
