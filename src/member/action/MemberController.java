package member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import room.model.MemberDAO;
import room.model.MemberVO;

@WebServlet("register2.do")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pwd1 = request.getParameter("pwd1");
		
		//비밀번호 확인
		if(pwd1.equals(request.getParameter("pwd2"))) {
			
			MemberVO vo = new MemberVO();
			vo.setId(request.getParameter("id"));
			vo.setName(request.getParameter("name"));
			vo.setPwd(pwd1);
			vo.setEmail(request.getParameter("email"));
			vo.setPhone(request.getParameter("phone"));
			vo.setUsernum(1);
			
			//데이터베이스
			MemberDAO dao = new MemberDAO();
			dao.insert(vo);
			
			RequestDispatcher dis = request.getRequestDispatcher("loginMain.jsp");
			dis.forward(request, response);
			
		} else {
			RequestDispatcher dis = request.getRequestDispatcher("registerError.jsp");
			dis.forward(request, response);
		}
		
	}

}
