package edu.bit.board.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import room.board.model.BoardDAO;
import room.board.model.BoardVO;

public class WriteProAction implements CommandAction {

	@Override
	public String process(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		BoardVO vo = new BoardVO();
		
		vo.setId(request.getParameter("id"));
		vo.setPwd(request.getParameter("pwd"));
		vo.setAverage(Float.parseFloat(request.getParameter("average")));
		vo.setTitle(request.getParameter("title"));
		vo.setContent(request.getParameter("content"));
		vo.setBdate(new Timestamp(System.currentTimeMillis()));
		
		BoardDAO dao = BoardDAO.getInstance();
		dao.insert(vo);
		
		
		return "/board/writePro.jsp";
	}

}












