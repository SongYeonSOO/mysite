package com.estsoft.mysite.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import com.estsoft.db.DBConnection;
import com.estsoft.mysite.vo.GuestBookVo;

public class GuestBookDao {
	private DBConnection dbConnection;

	public  GuestBookDao(DBConnection mySQLWebDBConnection) {
		this.dbConnection = mySQLWebDBConnection;
	}

	public void insert(GuestBookVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {

			conn = dbConnection.getConnection();
			String sql = "INSERT INTO guestbook VALUES(null, ?, now(), ?, password(?))";

			//System.out.println(vo.getFirstName());
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  vo.getName());
			pstmt.setString(2, vo.getMessage());
			pstmt.setString(3, vo.getPasswd());
			pstmt.executeUpdate();
			
		} catch (SQLException ex) {
			System.out.println("Error" + ex);
			ex.printStackTrace();
		}
	}

	public void delete(GuestBookVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbConnection.getConnection();
			String sql = "DELETE FROM guestbook WHERE no=? AND passwd= password(?)";

			//System.out.println(vo.getFirstName());
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getPasswd());
			pstmt.executeUpdate();
		
			
		} catch (SQLException ex) {
			System.out.println("Error" + ex);
			ex.printStackTrace();
		}
	}
	
	
	public List<GuestBookVo> getList() {
		List<GuestBookVo> list = new ArrayList<GuestBookVo>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = dbConnection.getConnection();
			stmt = conn.createStatement();

			String sql = "SELECT no, name, DATE_FORMAT(reg_date, '%Y-%m-%d %h %i %s'), message FROM guestbook ORDER BY reg_date desc";
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String reg_date = rs.getString(3);
				String message = rs.getString(4);

				GuestBookVo vo = new GuestBookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setReg_date(reg_date);
				vo.setMessage(message);
				
				list.add(vo);
			}

		} catch (SQLException ex) {
			System.out.println("error:" + ex);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return list;

	}
}
