package com.estsoft.mysite.web.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.estsoft.db.MySQLWebDBConnection;
import com.estsoft.mysite.dao.UserDao;
import com.estsoft.mysite.vo.UserVo;
import com.estsoft.web.WebUtil;
import com.estsoft.web.action.Action;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String passwd = request.getParameter("password");
		String gender = request.getParameter("gender");
		Long no = Long.parseLong(request.getParameter("no"));
		System.out.println("no:   "+no);
		
		
		
		UserVo vo = new UserVo();
			
			vo.setNo(no);
			vo.setName(name);
			vo.setGender(gender);
			vo.setPasswd(passwd);
		
		UserDao dao = new UserDao(new MySQLWebDBConnection());
		dao.Update(vo);
		
		HttpSession session = request.getSession();
		UserVo uservo =(UserVo) session.getAttribute("authUser");
		uservo.setName(name);
		
		WebUtil.redirect(request, response, "/mysite/main");
	}
}
