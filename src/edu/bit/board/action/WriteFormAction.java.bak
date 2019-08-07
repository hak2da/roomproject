package edu.bit.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WriteFormAction implements CommandAction {

	@Override
	public String process(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		int num = 0, ref=1, re_step = 0, re_level =0;
		
		try{
			if( request.getParameter("num") != null ){  //답변글일때, 이때의 num은 content.jsp에서 넘어온다.
				num = Integer.parseInt(request.getParameter("num"));
				ref = Integer.parseInt(request.getParameter("ref"));
				re_step = Integer.parseInt(request.getParameter("re_step"));
				re_level = Integer.parseInt(request.getParameter("re_level"));
			} //if 	
			
		}catch(Exception e){ 
			e.printStackTrace();
		}
		
		//해당 뷰에서 사용할 속성(저장)
		request.setAttribute("num", num);
		request.setAttribute("ref", ref);
		request.setAttribute("re_step", re_step);
		request.setAttribute("re_level", re_level);
		
		return "/board/writeForm.jsp";
	}

}








