package room.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import room.action.Action;
import room.action.ActionForward;
import room.action.RoomOutAction;

@WebServlet("*.do")
public class RoomOutFrontController 
	extends javax.servlet.http.HttpServlet 
	implements javax.servlet.Servlet {

	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		String RequestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		String command=RequestURI.substring(contextPath.length());
		ActionForward forward=null;
		Action action=null;
		
		if(command.equals("/Out.do")){
			System.out.println("RoomOutFrontController1");
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("out.html");
		} else if (command.equals("/RoomOutAction.do")) {
			action  = new RoomOutAction();
			try {
				forward=action.execute(request, response );
				System.out.println("RoomOutFrontController2");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			if(forward != null){
				if(forward.isRedirect()){
					System.out.println("RoomOutFrontController3");
					response.sendRedirect(forward.getPath());
				}else{
					System.out.println("RoomOutFrontController4");
					RequestDispatcher dispatcher=
							request.getRequestDispatcher(forward.getPath());
					dispatcher.forward(request, response);
				}
			}
		}
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doProcess(request,response);
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doProcess(request,response);
	}
}