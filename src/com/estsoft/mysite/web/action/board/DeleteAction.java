package com.estsoft.mysite.web.action.board;

import java.io.IOException;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;
import java.util.Timer;
import java.util.concurrent.Executor;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.estsoft.db.MySQLWebDBConnection;
import com.estsoft.mysite.dao.BoardDao;
import com.estsoft.mysite.vo.BoardVo;
import com.estsoft.web.WebUtil;
import com.estsoft.web.action.Action;
import com.mysql.jdbc.Buffer;
import com.mysql.jdbc.CachedResultSetMetaData;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ExceptionInterceptor;
import com.mysql.jdbc.Extension;
import com.mysql.jdbc.Field;
import com.mysql.jdbc.MySQLConnection;
import com.mysql.jdbc.MysqlIO;
import com.mysql.jdbc.ResultSetInternalMethods;
import com.mysql.jdbc.ServerPreparedStatement;
import com.mysql.jdbc.SingleByteCharsetConverter;
import com.mysql.jdbc.StatementImpl;
import com.mysql.jdbc.StatementInterceptorV2;
import com.mysql.jdbc.log.Log;
import com.mysql.jdbc.profiler.ProfilerEventHandler;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/board/list.jsp");
		Long no = Long.parseLong(request.getParameter("no"));
		Long userno = Long.parseLong(request.getParameter("user_no"));

		BoardVo vo = new BoardVo();
		vo.setNo(no);
		vo.setUser_no(userno);
		BoardDao dao = new BoardDao(new MySQLWebDBConnection());

		dao.delete(vo);

		WebUtil.redirect(request, response, "/mysite/board");
	}

}
