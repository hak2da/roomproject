package edu.bit.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.bit.board.model.BoardDAO;
import edu.bit.board.model.BoardVO;

public class UpdateFormAction implements CommandAction {

	@Override
	public String process(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");

		BoardDAO dao = BoardDAO.getInstance();
		BoardVO vo = dao.update(num); 
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("vo", vo);
		
		
		return "/borad/updateForm.jsp";
	}

}
