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

public class DeleteGuestBookAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/guestbook/deleteform.jsp");
		
		String passwd = request.getParameter("pass");
		Long no = Long.parseLong(request.getParameter("no"));
		
		GuestBookVo vo = new GuestBookVo();
		vo.setNo(no);
		vo.setPasswd(passwd);
		GuestBookDao dao = new GuestBookDao(new MySQLWebDBConnection());
		dao.delete(vo);

		WebUtil.redirect(request, response, "/mysite/guestbook");

	}

}
