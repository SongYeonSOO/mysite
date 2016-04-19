package com.estsoft.mysite.web.action.guestbook;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estsoft.db.MySQLWebDBConnection;
import com.estsoft.mysite.dao.GuestBookDao;
import com.estsoft.mysite.vo.GuestBookVo;
import com.estsoft.web.action.Action;

import net.sf.json.JSONObject;

public class AjaxListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("p"); // url에서 page(p)받아오자!
		GuestBookDao dao = new GuestBookDao(new MySQLWebDBConnection());
		if (page == "") {
			page = "0";
		}
		List<GuestBookVo> list = dao.getList(Integer.parseInt(page));

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", list);
		JSONObject jsonObject = JSONObject.fromObject(map);
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(jsonObject);

		// web에서 test를 해보자!
	}

}
