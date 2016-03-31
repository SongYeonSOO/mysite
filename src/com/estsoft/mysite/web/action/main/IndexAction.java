package com.estsoft.mysite.web.action.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estsoft.web.WebUtil;
import com.estsoft.web.action.Action;

public class IndexAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Cookie(cookiename, cookiecontent);
		Cookie cookie = new Cookie("testCookie", "HelloWorld");

		// cookie.setMaxAge(60*60*24*365); // left for 1 year
		cookie.setMaxAge(60 * 60 * 24 * 1);
		cookie.setPath("/mysite/");
		response.addCookie(cookie);

		WebUtil.forward(request, response, "/WEB-INF/views/main/index.jsp");
	}

}
