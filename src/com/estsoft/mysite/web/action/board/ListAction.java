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

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String title = request.getParameter("title");
//		String content = request.getParameter("content");
//
//		BoardVo vo = new BoardVo();
//		vo.setTitle(title);
//		vo.setContent(content);
//
//		BoardDao dao = new BoardDao(new MySQLWebDBConnection());
//		List<BoardVo> boardlist = dao.SearchList(vo.getTitle(),vo.getContent());
		//이 리스트가 search 결과다
		
		WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");
	}

}
