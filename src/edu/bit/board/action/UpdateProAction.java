package edu.bit.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.bit.board.model.BoardDAO;
import edu.bit.board.model.BoardVO;

public class UpdateProAction implements CommandAction {

	@Override
	public String process(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		String pageNum = request.getParameter("pageNum");
		
		BoardVO vo = new BoardVO();
		
		vo.setNum(Integer.parseInt(request.getParameter("num")));
		vo.setWriter(request.getParameter("writer"));
		vo.setSubject(request.getParameter("subject"));
		vo.setEmail(request.getParameter("email"));
		vo.setContent(request.getParameter("content"));
		vo.setPasswd(request.getParameter("passwd"));
		
		BoardDAO dao = BoardDAO.getInstance();
		int check = dao.update(vo); //실제 변경 내용 반영 함수
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("check", new Integer(check));
		
		return "/board/updatePro.jsp";
	}

}








