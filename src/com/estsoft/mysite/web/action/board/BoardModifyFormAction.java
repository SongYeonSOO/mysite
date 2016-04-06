package com.estsoft.mysite.web.action.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.estsoft.db.MySQLWebDBConnection;
import com.estsoft.mysite.dao.BoardDao;
import com.estsoft.mysite.vo.BoardVo;
import com.estsoft.mysite.vo.UserVo;
import com.estsoft.web.WebUtil;
import com.estsoft.web.action.Action;

public class BoardModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		// 로그인된 회원정보 받아오기
		UserVo uservo = (UserVo) session.getAttribute("authUser");
//		System.out.println("userVo: " + uservo);

		Long no = Long.parseLong(request.getParameter("no"));
//		System.out.println("no: " + no);

		BoardDao dao = new BoardDao(new MySQLWebDBConnection());
		BoardVo vo = dao.view(no,false);
		request.setAttribute("vo", vo);
		WebUtil.forward(request, response, "/WEB-INF/views/board/modify.jsp");

	}

}
