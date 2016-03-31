package com.estsoft.mysite.web.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.estsoft.mysite.vo.UserVo;
import com.estsoft.web.WebUtil;
import com.estsoft.web.action.Action;

public class LogoutAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//새로 만들 필요는 없다.
		HttpSession session = request.getSession();
		//user가 login을 안해놓고, logout url을 아는경우 -> null 반환 ; login을 안한 상태라서 session이 비어있음
		if(session == null){
			WebUtil.redirect(request, response, "/mysite/main");
			return ;
		}
		
		//진짜 로그아웃 처리부분
		//진짜 authUser라는 obj를 제거함
		session.removeAttribute("authUser");
		session.invalidate();
		WebUtil.redirect(request, response, "/mysite/main");
	}

}
