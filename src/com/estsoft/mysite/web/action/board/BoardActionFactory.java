package com.estsoft.mysite.web.action.board;

import com.estsoft.web.action.Action;
import com.estsoft.web.action.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;

		if ("writeform".equals(actionName)) {

			action = new WriteFormAction();

		} else if ("write".equals(actionName)) {

			// to use write.jsp in board folder
			action = new WriteAction();
		} 
//		else if ("viewform".equals(actionName)) {
//
//			action = new ViewFormAction();
//
//		} 
		else if ("view".equals(actionName)) {

			// to use view.jsp in board folder
			action = new ViewAction();

		} else if ("boardmodifyform".equals(actionName)) {

			action = new BoardModifyFormAction();
		} else if ("boardmodify".equals(actionName)) {

			// to use modify.jsp in board folder
			action = new BoardModifyAction();

		} else if("delete".equals(actionName)){
			action = new DeleteAction();
			
		}else {
			// show list of board
			action = new ListAction();
		}
		return action;
	}
}
