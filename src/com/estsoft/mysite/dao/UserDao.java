package com.estsoft.mysite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.estsoft.db.DBConnection;
import com.estsoft.mysite.vo.UserVo;

public class UserDao {
	private DBConnection dbConnection;

	public UserDao(DBConnection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public void insert(UserVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbConnection.getConnection();

			// ?: name, email, passwd, gender
			String sql = "INSERT INTO user VALUES(null, ?, ?, password(?), ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPasswd());
			pstmt.setString(4, vo.getGender());

			pstmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}

		}

	}
	/*
	 * public void //register //insert String sql2 =
	 * "SELECT no, name, email, gender FROM user WHERE email=? AND passwd=password(?)"
	 * ;
	 * 
	 * //modify String sql3 =
	 * "UPDATE user SET name=? email=? gender=? WHERE no=1";
	 */
}
