package com.estsoft.mysite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.estsoft.db.DBConnection;
import com.estsoft.db.MySQLWebDBConnection;
import com.estsoft.mysite.vo.BoardVo;
import com.estsoft.mysite.vo.UserVo;

public class BoardDao {
	private DBConnection dbConnection;

	public BoardDao(DBConnection dbConnection) {
		this.dbConnection = dbConnection;
	}

	// 게시글 보자
	public BoardVo view(Long no) {
		BoardVo boardVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dbConnection.getConnection();

			// 검색기능까지 한번에 해결하자 . content의 내용에 뭐가 들었을까!
			String sql = "SELECT b.title, b.content, b.reg_date, u.name, b.group_no, b.order_no, b.depth, b.hit FROM board b,user u WHERE b.no = ? and b.user_no = u.no";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				// System.out.println("BoardDao: ok");
				String title = rs.getString(1);
				String content = rs.getString(2);
				String reg_date = rs.getString(3);
				String user_name = rs.getString(4);
				Long group_no = rs.getLong(5);
				Long order_no = rs.getLong(6);
				Long depth = rs.getLong(7);
				Long hit = rs.getLong(8);

				boardVo = new BoardVo();
				boardVo.setNo(no);
				boardVo.setTitle(title);
				boardVo.setContent(content);
				boardVo.setReg_date(reg_date);
				boardVo.setUser_name(user_name);
				boardVo.setGroup_no(group_no);
				boardVo.setOrder_no(order_no);
				boardVo.setDepth(depth);
				boardVo.setHit(hit + 1);
				UpdateHit(no);
			}

		} catch (SQLException e) {

			System.out.println("error:" + e);
			e.printStackTrace();

			return null;

		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

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
		return boardVo;

	}

	// Hit count증가 -> page 읽기 시 자동으로 증가함
	private void UpdateHit(Long no) {
		BoardVo boardVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbConnection.getConnection();

			// 검색기능까지 한번에 해결하자 . content의 내용에 뭐가 들었을까!
			String sql = "UPDATE board b SET b.hit=b.hit+1 WHERE b.no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);

			pstmt.executeUpdate();

		} catch (SQLException e) {

			System.out.println("error:" + e);
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

	// 게시판 리스트를 만들자
	public List<BoardVo> SearchList(String title1, String content1) {
		List<BoardVo> list = new ArrayList<BoardVo>();
		BoardVo boardVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbConnection.getConnection();

//			if (title1 == null && content1 == null) {
				String sql = "SELECT b.no, b.title, b.content, b.reg_date, u.name, b.group_no, b.order_no, b.depth, b.hit FROM board b,user u WHERE b.user_no = u.no";
				pstmt = conn.prepareStatement(sql);

				rs = pstmt.executeQuery(sql);

				while (rs.next()) {
					Long no = rs.getLong(1);
					String title = rs.getString(2);
					String content = rs.getString(3);
					String reg_date = rs.getString(4);
					String user_name = rs.getString(5);
					Long group_no = rs.getLong(6);
					Long order_no = rs.getLong(7);
					Long depth = rs.getLong(8);
					Long hit = rs.getLong(9);

					boardVo = new BoardVo();
					boardVo.setNo(no);
					boardVo.setTitle(title);
					boardVo.setContent(content);
					boardVo.setReg_date(reg_date);
					boardVo.setUser_name(user_name);
					boardVo.setGroup_no(group_no);
					boardVo.setOrder_no(order_no);
					boardVo.setDepth(depth);
					boardVo.setHit(hit);

					list.add(boardVo);

				}
//			} 
//			else {
//				String sql = "SELECT b.no, b.title, b.content, b.reg_date, u.name, b.group_no, b.order_no, b.depth, b.hit FROM board b,user u WHERE  title like %?% OR content like %?% AND b.user_no = u.no";
//
//				pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, title1);
//				pstmt.setString(2, content1);
//
//				rs = pstmt.executeQuery(sql);
//
//				while (rs.next()) {
//					Long no = rs.getLong(1);
//					String title = rs.getString(2);
//					String content = rs.getString(3);
//					String reg_date = rs.getString(4);
//					Long user_no = rs.getLong(5);
//					Long group_no = rs.getLong(6);
//					Long order_no = rs.getLong(7);
//					Long depth = rs.getLong(8);
//					Long hit = rs.getLong(9);
//
//					boardVo = new BoardVo();
//					boardVo.setNo(no);
//					boardVo.setTitle(title);
//					boardVo.setContent(content);
//					boardVo.setReg_date(reg_date);
//					boardVo.setUser_no(user_no);
//					boardVo.setGroup_no(group_no);
//					boardVo.setOrder_no(order_no);
//					boardVo.setDepth(depth);
//					boardVo.setHit(hit);
//
//					list.add(boardVo);
//				}
//}		
		} catch (SQLException ex) {
			System.out.println("error:" + ex);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
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

	// **** Action에서는 updateRe 이후에 insertRe를 해야한다
	// updateRe: 같은 그룹 내의 orderno보다 큰 orderNo는 다 증가시켜준다
	public void updateRe(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbConnection.getConnection();

			// Group number가 같고 Order number가 크거나 같은 게시글의 order num과 depth를
			// 증가시켜서
			// 기존 게시글의 순서를 맞춰준다.
			String sql = "UPDATE board SET order_no=order_no+1, depth=depth+1 WHERE group_no=? and order_no>=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, vo.getGroup_no());
			pstmt.setLong(2, vo.getOrder_no());

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

	// 새 게시글 삽입(원글, 답글 모두 이용?)  content랑 title, user_no 받아옴
	public void insert(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbConnection.getConnection();

			// null: no( auto inc ); db에서의 no는 view에서 중요하지 않음
			// ?: title, content, user_no, group_no, order_no, depth, hit
			
			//****************************check!!!!!!!!!!!!!!!!!!!!!!!!!!!!

			
//			if(vo.getOrder_no() == null){
				//새글
			String sql = "INSERT INTO board VALUES(null, ?, ?, now(), ?, (select ifnull( max( group_no ), 0 ) + 1  from board as b), 1, 0, 1)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getUser_no());


//			}else{
//				//답글
//				String sql = "INSERT INTO board VALUES(null, ?, ?, now(), ?, ?, ?, ?, 1)";
//
//				pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, vo.getTitle());
//				pstmt.setString(2, vo.getContent());
//				pstmt.setLong(3, vo.getUser_no());
//				pstmt.setLong(4, vo.getGroup_no());
//				pstmt.setLong(5, vo.getOrder_no());
//				pstmt.setLong(6, vo.getDepth());
//
//			}

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

	// 게시글 수정 : 원글, 답글 모두 사용
	public void ModifyUpdate(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbConnection.getConnection();

			// 게시글 수정
			String sql = "UPDATE board SET title=?, content=? WHERE user_no=? AND no= ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getUser_no());
			pstmt.setLong(4, vo.getNo());

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

	// 게시글 삭제 : 원글, 답글 모두 사용
	public void delete(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbConnection.getConnection();

			// 게시글 수정
			String sql = "DELETE FROM board WHERE no= ? And user_no = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, vo.getNo());
			pstmt.setLong(1, vo.getUser_no());
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

}
