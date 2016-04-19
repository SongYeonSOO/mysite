package com.estsoft.mysite.web.action.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estsoft.db.MySQLWebDBConnection;
import com.estsoft.mysite.dao.UserDao;
import com.estsoft.mysite.vo.UserVo;
import com.estsoft.web.action.Action;

import net.sf.json.JSONObject;

public class CheckEmailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// html응답을 안할꺼니깐
		response.setContentType("application/json; charset=utf-8");

		// 내부처리
		String email = request.getParameter("email");
		UserDao dao = new UserDao(new MySQLWebDBConnection());
		UserVo vo = dao.get(email);

		// body를 적어줌

		// protocol
		/*
		 * 1. result 2. message 3. data { result: "success" or "fail" message:
		 * 실패한 경우 fail msg data: 통신 데이터 }
		 * 
		 * 
		 * // 내가 정하는 것! 
		 *  통신성공 case 
 			email 사용가능 {result: "success" data:true} 
 			email 사용불가능 {result: "success" data: false}
		 * 
		 */

		Map<String, Object> map = new HashMap<String, Object>();
		// 여기까지 왔으면 success상태
		map.put("result", "success");
		map.put("data", vo == null); // null이면 사용할 수 있음 // not null: 사용할 수 없음

		PrintWriter out = response.getWriter();
		// ""안에 json을 적어줌
		// String jsonString = "{\"name\": \"YS\", \"email\":\"YS@email.com\"}";
		// out.println(jsonString.toString());
		JSONObject jsonObject = JSONObject.fromObject(map);
		out.println(jsonObject.toString());
	}

}
