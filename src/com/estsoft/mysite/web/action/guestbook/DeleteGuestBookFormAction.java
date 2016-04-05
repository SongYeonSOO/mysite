package com.estsoft.mysite.web.action.guestbook;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estsoft.db.MySQLWebDBConnection;
import com.estsoft.mysite.dao.GuestBookDao;
import com.estsoft.mysite.vo.GuestBookVo;
import com.estsoft.web.WebUtil;
import com.estsoft.web.action.Action;

public class DeleteGuestBookFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/guestbook/deleteform.jsp");

		// 이게문제야!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		 Long no = Long.parseLong(request.getParameter("id"));

		GuestBookVo vo = new GuestBookVo();
		vo.setNo(no);
		request.setAttribute("vo", vo);
		WebUtil.forward(request, response, "/WEB-INF/views/guestbook/deleteform.jsp");
	}

}
