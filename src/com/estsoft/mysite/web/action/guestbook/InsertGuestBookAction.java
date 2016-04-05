package com.estsoft.mysite.web.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estsoft.db.MySQLWebDBConnection;
import com.estsoft.mysite.dao.GuestBookDao;
import com.estsoft.mysite.vo.GuestBookVo;
import com.estsoft.web.WebUtil;
import com.estsoft.web.action.Action;

public class InsertGuestBookAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String passwd = request.getParameter("pass");
		String message = request.getParameter("content");
		GuestBookVo vo = new GuestBookVo();
		vo.setName(name);
		vo.setMessage(message);
		vo.setPasswd(passwd);

		GuestBookDao dao = new GuestBookDao(new MySQLWebDBConnection());
		dao.insert(vo);

		// insert를 하는 페이지에서 계속 F5를 누르면 계속 INSERT가 진행된다 -> 302 redirect를 이용해서
		// insert가 추가로 되지않도록
		WebUtil.redirect(request, response, "/mysite/guestbook");
	}

}
