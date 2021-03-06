package com.estsoft.mysite.web.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.estsoft.db.MySQLWebDBConnection;
import com.estsoft.mysite.dao.BoardDao;
import com.estsoft.mysite.dao.UserDao;
import com.estsoft.mysite.vo.BoardVo;
import com.estsoft.mysite.vo.UserVo;
import com.estsoft.web.WebUtil;
import com.estsoft.web.action.Action;

public class BoardModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		// 로그인된 회원정보 받아오기
		UserVo uservo = (UserVo) session.getAttribute("authUser");

		Long no = Long.parseLong(request.getParameter("no"));

		BoardVo vo = new BoardVo();

		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		System.out.println("title: "+title);
		System.out.println("content: "+content);
		
		Long user_no = uservo.getNo();

		vo.setNo(no);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setUser_no(user_no);

		BoardDao dao = new BoardDao(new MySQLWebDBConnection());
		dao.ModifyUpdate(vo);

		WebUtil.redirect(request, response, "/mysite/board?a=view&no="+ no);

	}
}
