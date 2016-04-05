package com.estsoft.mysite.web.action.board;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estsoft.db.MySQLWebDBConnection;
import com.estsoft.mysite.dao.BoardDao;
import com.estsoft.mysite.vo.BoardVo;
import com.estsoft.web.WebUtil;
import com.estsoft.web.action.Action;

public class ListAction implements Action {
	//page에 이용!!!
	public static final int COUNT_LIST = 5;
	public static final int COUNT_PAGE = 5;
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String kwd = request.getParameter("kwd");
		String page1 = request.getParameter("page");
		Long currentpage = null;
		
		if(kwd==null){
			kwd="";			
		}
		
		if(page1==null){
			currentpage=1L;
		}else {
			currentpage = Long.parseLong(page1);		
		}
		
		BoardDao dao = new BoardDao(new MySQLWebDBConnection());
		Long beginpage = currentpage - ((currentpage-1)%COUNT_PAGE);
		Long totalpage = (long) Math.ceil(dao.Count()/(float)COUNT_PAGE);
		Long maxpage = null;
		if(totalpage>=beginpage+COUNT_PAGE-1){
			maxpage = beginpage+COUNT_PAGE-1;
		}else{
			maxpage = totalpage;
		}
		
		
		Long boardno = dao.Count()-(currentpage-1)*COUNT_PAGE;
		request.setAttribute("boardno", boardno);
		
		
		
		Map<String, Long> pageinfo = new HashMap<String, Long>();
		pageinfo.put("beginpage", beginpage);
		pageinfo.put("totalpage", totalpage);
		pageinfo.put("maxpage", maxpage);
		pageinfo.put("currentpage", currentpage);		
		request.setAttribute("pageinfo", pageinfo);
		
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContent(content);
		
		List<BoardVo> list = dao.SearchList(kwd, currentpage);
		
		//이 리스트가 search 결과다
		request.setAttribute("list", list);
		request.setAttribute("count", list.size());
		WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");
	}

}
