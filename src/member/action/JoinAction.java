package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import room.model.MemberDAO;
import room.model.MemberVO;

public class JoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		MemberDAO dao = new MemberDAO();
	   	MemberVO vo = new MemberVO();
	   	ActionForward forward=new ActionForward();
		
	   	boolean result=false;
	   	
	   	vo.setName(request.getParameter("name"));
	   	vo.setId(request.getParameter("id"));
	   	vo.setPwd(request.getParameter("pwd1"));
	   	vo.setEmail(request.getParameter("email"));
	   	vo.setPhone(request.getParameter("phone"));
	   	vo.setUsernum(1);
	   	
	   	result = dao.insert(vo);
   		
   		if(result==false){
   			System.out.println("회원 등록 실패");
   			return null;
   		}
   		System.out.println("회원 등록 완료");
   		
   		forward.setRedirect(true);
   		forward.setPath("./login.do");
   		return forward;
	}

}
