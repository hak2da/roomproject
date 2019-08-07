package edu.bit.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import room.board.model.BoardDAO;


public class DeleteProAction implements CommandAction {

	@Override
	public String process(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		BoardDAO  dao = BoardDAO.getInstance();
		String num = request.getParameter("num");
		String pageNum = request.getParameter("pageNum");
		String passwd = request.getParameter("passwd");
		
		int check = dao.delete(Integer.parseInt(num), passwd); 
		
		//저장
		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("check", check);
		
		return "/board/deletePro.jsp" ;
	}

}





