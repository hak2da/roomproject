package room.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import room.board.command.BoardCommand;


@WebServlet("*.do")
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String com = requestURI.substring(contextPath.length());
		
		BoardCommand command = null;
		String nextPage = "";
		
		//분기 
		if( com.equals("list.do")) {
			/*
			 * command = new ListBoardCommand(); command.execute(request, response);
			 */
			nextPage = "list.jsp";
		}else if( com.equals("writeForm.do")) {
			nextPage = "writeForm.jsp";
		}else if( com.equals("writePro.do")) {
			nextPage = "writePro.jsp";
		}else if( com.equals("deleteForm.do")) {
			nextPage = "deleteForm.jsp";
		}else if( com.equals("deletePro.do")) {
			nextPage = "deletePro.jsp";
		}else if( com.equals("updateForm.do")) {
			nextPage = "updateForm.jsp";
		}else if( com.equals("updatePro.do")) {
			nextPage = "updatePro.jsp";
		}
		
		RequestDispatcher d = request.getRequestDispatcher(nextPage);
		d.forward(request, response);
	}

}



