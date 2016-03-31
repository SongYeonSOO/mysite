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

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String passwd = request.getParameter("password");

		UserVo vo = new UserVo();
		vo.setEmail(email);
		vo.setPasswd(passwd);
		

		UserDao dao = new UserDao(new MySQLWebDBConnection());
		UserVo authUser = dao.get(vo); // authenticated user
//		System.out.println(authUser);
		// authentication fail case! 다시 로그인 페이지 만들자!
		if (authUser == null) {
			// email or passwd wrong -> end
			// 1. redirect to login page
			// 2. email/passwd 중 뭐가 틀렸는 지 구분할 필요 없다 -> security problem!
			// WebUtil.redirect(request, response,
			// "/mysite/user?a=loginform&result=fail");

			WebUtil.forward(request, response, "/WEB-INF/views/user/loginfailform.jsp");
			return;
		}

		// login success!!
		// (true) : object가 있든없든 새로 만들어줌. 만약 true가 없고, sessionid에 mapping된
		// session obj.이 없으면 null을 return 할 것임
		// login의 경우엔 보통 처음엔 null일 것이므로 true를 해주는게 좋음
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser);
		WebUtil.redirect(request, response, "/mysite/main");
	}

}
