package room.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.model.BoardVO;
import net.board.model.BoardDAO;

public class BoardReplyAction implements Action {
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	 throws Exception{
		 	request.setCharacterEncoding("UTF-8");
		 	ActionForward forward = new ActionForward();
		 	
			BoardDAO boarddao=new BoardDAO();
			BoardVO boarddata=new BoardVO();
	   		int result=0;
	   		
	   		boarddata.setBOARD_NUM(Integer.parseInt(request.getParameter("BOARD_NUM")));
	   		boarddata.setBOARD_NAME(request.getParameter("BOARD_NAME"));
	   		boarddata.setBOARD_PASS(request.getParameter("BOARD_PASS"));
	   		boarddata.setBOARD_SUBJECT(request.getParameter("BOARD_SUBJECT"));
	   		boarddata.setBOARD_CONTENT(request.getParameter("BOARD_CONTENT"));
	   		boarddata.setBOARD_RE_REF(Integer.parseInt(request.getParameter("BOARD_RE_REF")));
	   		boarddata.setBOARD_RE_LEV(Integer.parseInt(request.getParameter("BOARD_RE_LEV")));
	   		boarddata.setBOARD_RE_SEQ(Integer.parseInt(request.getParameter("BOARD_RE_SEQ")));
	   		
	   		result=boarddao.boardReply(boarddata);
	   		if(result==0){
	   			System.out.println("답장 실패");
	   			return null;
	   		}
	   		System.out.println("답장 완료");
	   		
	   		forward.setRedirect(true);
	   		forward.setPath("./BoardDetailAction.do?num="+result);
	   		return forward;
	}  	
}