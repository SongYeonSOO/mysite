package com.estsoft.mysite.web.action.guestbook;

import java.util.List;

import javax.servlet.RequestDispatcher;

import com.estsoft.db.MySQLWebDBConnection;
import com.estsoft.mysite.dao.GuestBookDao;
import com.estsoft.mysite.vo.GuestBookVo;
import com.estsoft.web.WebUtil;
import com.estsoft.web.action.Action;
import com.estsoft.web.action.ActionFactory;

public class GuestBookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;

		if ("insert".equals(actionName)) {
			action = new InsertGuestBookAction();
		} else if ("deleteform".equals(actionName)) {

			action = new DeleteGuestBookFormAction();

		} else if ("delete".equals(actionName)) {

			action = new DeleteGuestBookAction();

		} else if ("ajax".equals(actionName)) {

			action = new AjaxAction();

		} else if ("ajax-list".equals(actionName)) {

			action = new AjaxListAction();

		} else if ("ajax-insert".equals(actionName)) {

			action = new AjaxInsertAction();

		} else if ("ajax-delete".equals(actionName)) {

			action = new AjaxDeleteAction();

		} else {

			action = new GuestBookIndexAction();

		}
		return action;
	}

}
