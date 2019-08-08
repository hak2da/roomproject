package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import room.model.MemberDAO;

public class MemberLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();

		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd1");

		MemberDAO dao = new MemberDAO();
		int check = dao.loginCheck(id, pwd);

		if (check == 0) // 비밀번호 틀릴경우 -> 다시 로그인 화면으로 이동
		{
			// 로그인 실패시 메시지를 request에 담는다.
			request.setAttribute("fail", "0");

			forward.setRedirect(false);
			forward.setPath("login.to");
		} else if (check == -1) // 아이디가 없을 경우 -> 다시 로그인 화면으로 이동
		{
			request.setAttribute("fail", "-1");

			forward.setRedirect(false);
			forward.setPath("login.to");
		} else {
			// 로그인 성공 -> 세션에 아이디를 저장
			session.setAttribute("sessionID", id);

			// 로그인 성공후 메인화면으로 이동
			forward.setRedirect(true);
			forward.setPath("index.to");
		}

		return forward;

	}

}
