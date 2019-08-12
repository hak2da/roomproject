package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import room.model.MemberDAO;
import room.model.MemberVO;

public class EmailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		MemberDAO dao = new MemberDAO();
	   	MemberVO vo = new MemberVO();
	   	ActionForward forward=new ActionForward();
		
	   	forward.setRedirect(true);
   		forward.setPath("./email.to");
   		return forward;
	}

}
