package com.estsoft.mysite.web.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estsoft.db.MySQLWebDBConnection;
import com.estsoft.mysite.dao.BoardDao;
import com.estsoft.mysite.vo.BoardVo;
import com.estsoft.web.WebUtil;
import com.estsoft.web.action.Action;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// write execute!		
		Long no = Long.parseLong(request.getParameter("no"));


		BoardDao dao = new BoardDao(new MySQLWebDBConnection());
		BoardVo vo = dao.view(no,true);

		request.setAttribute("vo", vo);
		WebUtil.forward(request, response, "/WEB-INF/views/board/view.jsp");
	}

}
