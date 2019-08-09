package member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.action.Action;
import member.action.ActionForward;

@WebServlet("*.to")
public class MemberController extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		ActionForward forward = null;
		Action action = null;

		if (command.equals("/register2.to")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("register2.html");
		} else if (command.equals("/JoinAction.to")) {
			action = new JoinAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/login.to")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("login.jsp");
		} else if (command.equals("/login.to")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("login.jsp");
		} else if (command.equals("/register.to")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("register.html");
		} else if (command.equals("/CJoinAction.to")) {
			action = new CJoinAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/index.to")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("index.jsp");
		} else if (command.equals("/logout.to")) {
			action = new MemberLogoutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/MemberLoginAction.to")) {
			System.out.println("MemberController");
			action = new MemberLoginAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}