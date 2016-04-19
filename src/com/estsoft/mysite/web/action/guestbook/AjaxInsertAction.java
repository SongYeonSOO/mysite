package com.estsoft.mysite.web.action.guestbook;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estsoft.db.MySQLWebDBConnection;
import com.estsoft.mysite.dao.GuestBookDao;
import com.estsoft.mysite.vo.GuestBookVo;
import com.estsoft.web.action.Action;

import net.sf.json.JSONObject;

public class AjaxInsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// parameter 받아오기
		String name = request.getParameter("name");
		String passwd = request.getParameter("pass");
		String message = request.getParameter("message");

		System.out.println("name:" + name);
		System.out.println("pass:" + passwd);
		System.out.println("message:" + message);

		GuestBookVo vo = new GuestBookVo();
		// vo setting: name, passwd, reg_date
		vo.setName(name);
		vo.setPasswd(passwd);
		vo.setMessage(message);

		GuestBookDao dao = new GuestBookDao(new MySQLWebDBConnection());
		Long no = dao.insert(vo); // no를 받아와야함

		vo = dao.get(no); // 가 vo를 return 하도록

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", vo);

		JSONObject jsonObject = JSONObject.fromObject(map);
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(jsonObject);
	}

}
